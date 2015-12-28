#include<list>
#include<vector>
#include<string>
#include<fstream>


//global variables
int line, debug;  // source line number
bool parseerror; // stop after parsing if set to true

void passert(bool condition, string errormsg){
  if (!condition) {
    cout<<"error on line "<<line<<" : "<<errormsg<<'\n';
    parseerror = true;
  }
}

void fatal(string errormsg){
    cout<<"error on line "<<line<<" : "<<errormsg<<'\n';
    exit(1);
}


class machine {
  int ival1, ival2;
  float rval1,rval2;
  bool comparingint;
  int registeri[4];
  float registerr[4];
public:
  machine(){
    ival1=0;ival2=0;rval1=0;rval2=0;
    for (int i=0; i<4; i++){registeri[i]=0;registerr[i]=0;}
  }  
  void setstatusi(int i1, int i2){ival1=i1; ival2=i2; comparingint=true;}
  void setstatusr(float r1, float r2){rval1=r1; rval2=r2; comparingint=false;}
  bool gt() {if (comparingint) return ival1>ival2; else return rval1>rval2;}
  bool ge() {if (comparingint) return ival1>=ival2; else return rval1>=rval2;}
  bool lt() {if (comparingint) return ival1<ival2; else return rval1<rval2;}
  bool le() {if (comparingint) return ival1<=ival2; else return rval1<=rval2;}
  bool eq() {if (comparingint) return ival1==ival2; else return rval1==rval2;}
  bool ne() {if (comparingint) return ival1!=ival2; else return rval1!=rval2;}
  void setregi(int reg, int i){registeri[reg]=i;}
  void setregr(int reg, float r){registerr[reg]=r;}
  int regi(int reg){return registeri[reg];}
  float regr(int reg){return registerr[reg];}
  void print(){
    cout<<"status:"<<ival1<<','<<ival2<<"  "<<rval1<<','<<rval2;
    if (comparingint) cout<<" int\n"; else cout<<" float\n";
    cout<<"regs "<<registeri[0]<<' '<<registeri[1]<<' '<<registeri[2]<<' '<<registeri[3]<<"   ";
    cout<<registerr[0]<<' '<<registerr[1]<<' '<<registerr[2]<<' '<<registerr[3]<<'\n';
  }
};


enum optype {var,str, label, move, addi,addr, subi,subr, muli,mulr, divi,divr, 
             inci, deci, cmpi, push, pop, ret, link, unlnk, cmpr, 
             jsr, jmp, jgt, jlt, jge, jle, jeq, jne, 
             // all jumps must be between jsr..jne 
	     sys, endofprogram, emptyline, unknown };
enum operandtype {id,stackref,reg,num,strval,empty,nonknown};
enum syscalls {SCreadi,SCreadr,SCwritei,SCwriter,SCwrites,SChalt,SCunknown};


class opcode;

class StackElement {
  int ivalue;
  float rvalue;
  list<opcode>::iterator address;
  enum stackcontent {dataitem, adritem, fpitem} elementtype;
public:
  StackElement(int ii, float ff){ivalue=ii; rvalue=ff; elementtype=dataitem;}
  StackElement(int fp){ivalue=fp; elementtype=fpitem;}
  StackElement(list<opcode>::iterator adr){address=adr; elementtype=adritem;}
  void setival(int i){
    passert(elementtype==dataitem,"illegal data stack reference"); 
    ivalue=i;
  }
  void setrval(float r){
    passert(elementtype==dataitem,"illegal data stack reference"); 
    rvalue=r;
  }
  int ival()  {passert(elementtype==dataitem,"illegal int stack reference"); 
               return ivalue;}
  float rval(){passert(elementtype==dataitem,"illegal float stack reference"); 
               return rvalue;}
  int fval(){passert(elementtype==fpitem,"illegal fp stack reference"); 
             return ivalue;}
  list<opcode>::iterator aval(){
               passert(elementtype==adritem,"illegal pc stack reference"); 
               return address;}
};

// GLOBAL VARIABLE. MACHINE AND SYSTEM STATE:
machine cpu;
vector<StackElement> stack; 
int fp = 0;
//for the user, the stack will grow downwards, however the implementation
// is a list that starts from 1. Offsets will need to be negated.
  


class Symbol {
  string nam;
  int ivalue;
  float rvalue;
  string svalue;
  bool sconst;
public:
  Symbol(string s){nam=s; ivalue=0;rvalue=0;sconst=false;}
  Symbol(string s1, string s2){nam=s1; ivalue=0;rvalue=0;svalue=s2;sconst=true;}
  void setival(int i){ivalue=i;}
  void setrval(float r){rvalue=r;}
  string name(){return nam;}
  int ival(){return ivalue;}
  float rval(){return rvalue;}
  string sval(){return svalue;}
  bool strconst() {return sconst;}
  void print(){cout<<nam<<':'; if (sconst) cout<<svalue; else cout<<ivalue<<'/'<<rvalue; }
};
  

class operand {
  string opname;
  operandtype ot;
  int stackoffset, registernumber;
  float literalvalue;
  list<Symbol>::iterator varref;
public:
  string name(){return opname;}

  // functions used during parsing
  void settype(operandtype t){ot=t;}
  void setreg(int regno){ot=reg; registernumber=regno;}
  void setlit(float r){ot=num; literalvalue=r;}
  void setstackref(int ofs){ot=stackref; stackoffset=ofs;}
  void setname(string ss){opname=ss;}
  void setvarref(list<Symbol>::iterator vr){varref=vr;}
  operandtype type(){return ot;}

  // functions used during "execution"
  // generic operations (operate on both int and float field)
  void setval(operand &op){setival(op.ival());setrval(op.rval());}
  void add(operand &op){ 
    setival(ival()+op.ival()); 
    setrval(rval()+op.rval());
  }
  void sub(operand &op){
    setival(ival()-op.ival());
    setrval(rval()-op.rval());
  }
  void mul(operand &op){
    setival(ival()*op.ival()); 
    setrval(rval()*op.rval());
  }
  void divi(operand &op){setival(ival()/op.ival());}
  void divr(operand &op){setrval(rval()/op.rval());}
  void inci(){setival(ival()+1);}
  void deci(){setival(ival()-1);}

  // set/get individual int and float field
  void setival(int i){
    if (ot==id) {varref->setival(i);}
    else if (ot==reg) cpu.setregi(registernumber,i);
    else if (ot==stackref) stack[fp-stackoffset].setival(i); 
    else fatal("setival: illegal operand type");
  }
  void setrval(float r){
    if (ot==id) {varref->setrval(r);}
    else if (ot==stackref) stack[fp-stackoffset].setrval(r); 
    else if (ot==reg) cpu.setregr(registernumber,r);
    else fatal("setrval: illegal operand type");
  }
  int ival(){
    if (ot==id) {return varref->ival();}
    else if (ot==stackref) return stack[fp-stackoffset].ival(); 
    else if (ot==reg) return cpu.regi(registernumber);
    else if (ot==num) return literalvalue;
    else fatal("operand::ival: illegal operand type");
  }
  float rval(){
    if (ot==id) {return varref->rval();}
    else if (ot==stackref) return stack[fp-stackoffset].rval(); 
    else if (ot==reg) return cpu.regr(registernumber);
    else if (ot=num) return literalvalue;
    else fatal("operand::rval: illegal operand type");
  }
  string sval(){
    if (ot==id) {return varref->sval();}
    else fatal("operand::sval: illegal operand type");
  }
  void print(){
    switch (ot) {
    case id: {cout<<"id:"<<opname;break;}
    case stackref: {cout<<"stackref:"<<opname;break;}
    case reg: {cout<<"reg"<<registernumber;break;}
    case num: {cout<<"num:"<<literalvalue;break;}
    case strval: {cout<<"str:"<<opname;break;}
    case empty: {if (debug>=4) cout<<"emptyop"; break;}
    case nonknown: {cout<<"unknownop";break;}
    }
  }
};


class opcode {
  int srcline;
  optype typ;
  operand op1,op2;
  list<opcode>::iterator jumptarget; //for jump instructions
  string opname(optype t){
    switch (t) {
    case var: {return "var";}
    case str: {return "str";}
    case label: {return "label";}
    case move: {return "move";}
    case addi: {return "addi";}
    case addr: {return "addr";}
    case subi: {return "subi";}
    case subr: {return "subr";}
    case muli: {return "muli";}
    case mulr: {return "mulr";}
    case divi: {return "divi";}
    case divr: {return "divr";}
    case inci: {return "inci";}
    case deci: {return "deci";}
    case cmpi: {return "cmpi";}
    case cmpr: {return "cmpr";}
    case push: {return "push";}
    case pop: {return "pop";}
    case jsr: {return "jsr";}
    case ret: {return "ret";}
    case link: {return "link";}
    case unlnk: {return "unlnk";}
    case jmp: {return "jmp";}
    case jgt: {return "jgt";}
    case jlt: {return "jlt";}
    case jge: {return "jge";}
    case jle: {return "jle";}
    case jeq: {return "jeq";}
    case jne: {return "jne";}
    case sys: {return "sys";}
    default: {return "unknown";}
    }
  }
public:
  opcode(optype t,operand o1, operand o2): typ(t),op1(o1),op2(o2),srcline(line){}
  int src(){return srcline;}
  void settarget(list<opcode>::iterator pc){jumptarget=pc;}
  list<opcode>::iterator target(){return jumptarget;}
  string name() {return opname(typ);}
  optype code(){return typ;}
  operand & o1(){return op1;}
  operand & o2(){return op2;}
  void print(){
    cout<<"line:"<<srcline<<"  "<<opname(typ)<<"  ";
    op1.print(); cout<<"  ";
    op2.print(); 
  }
};




// class jumptarget {
//   string labl;
//   list<opcode>::iterator target;
// public:
//   jumptarget(string l, list<opcode>::iterator t){labl=l,target=t;}
//   string label(){return labl;}
//   list<opcode>::iterator  targetpc(){return target;}
// };




void parseline(optype & code, operand& op1, operand& op2, ifstream & src, int line) {
  op1.settype(empty);
  op2.settype(empty);

  if (src.eof()) {code=endofprogram; return;}
  char buf[200], *p;
  char sc[40], ch;
  int is;
  string ss;
  int ip =0;
  src.get(buf,200,'\n'); // read a source line
  //  while (src.get(ch)) if (! isspace(ch)){ src.putback(ch); break;} // skip end of line
  src.get(ch); 
  if (ch!='\n' && !src.eof()) cout<<"warning: '"<<ch<<"' found at end of line "<<line<<'\n';
  if (debug==4)  cout << "debug: "<<buf;
  
  // get opcode
  while (buf[ip]==' '){ip++;} // eat white space
  is = 0;
  while (isalpha(buf[ip])){sc[is++]=tolower(buf[ip++]);} // move opcode to ss, move to lower case
  sc[is]=0;
  ss=sc; // for some operations it's more convenient to have it in a string type

  if (debug==4)  cout << "debug: "<<ss;
  if (ss=="end") code = endofprogram;  
  else if (ss=="") code = emptyline;
  else if (ss=="var") code = var;
  else if (ss=="str") code = str;
  else if (ss=="label") code = label;
  else if (ss=="move") code = move;
  else if (ss=="addi") code = addi;
  else if (ss=="addr") code = addr;
  else if (ss=="subi") code = subi;
  else if (ss=="subr") code = subr;
  else if (ss=="muli") code = muli;
  else if (ss=="mulr") code = mulr;
  else if (ss=="divi") code = divi;
  else if (ss=="divr") code = divr;
  else if (ss=="inci") code = inci;
  else if (ss=="deci") code = deci;
  else if (ss=="cmpi") code = cmpi;
  else if (ss=="cmpr") code = cmpr;
  else if (ss=="push") code = push;
  else if (ss=="pop") code = pop;
  else if (ss=="jsr") code = jsr;
  else if (ss=="ret") code = ret;
  else if (ss=="link") code = link;
  else if (ss=="unlnk") code = unlnk;
  else if (ss=="jmp") code = jmp;
  else if (ss=="jgt") code = jgt;
  else if (ss=="jlt") code = jlt;
  else if (ss=="jge") code = jge;
  else if (ss=="jle") code = jle;
  else if (ss=="jeq") code = jeq;
  else if (ss=="jne") code = jne;
  else if (ss=="sys") code = sys;
  else code = unknown;
  
  // read first operand
  while (buf[ip]==' '){ip++;} // eat white space
  is = 0;
  if (buf[ip]!=';') {
    while (isgraph(buf[ip])) {sc[is++] = buf[ip++];} // move operand to sc
  }
  sc[is]=0;
  ss=sc; 
  if (debug==4)  cout << "debug: "<<ss;

  if (ss=="R0" || ss=="r0") {op1.setreg(0);}
  else if (ss=="R1" || ss=="r1") {op1.setreg(1);}
  else if (ss=="R2" || ss=="r2") {op1.setreg(2);}
  else if (ss=="R3" || ss=="r3") {op1.setreg(3);}
  else if (isalpha(sc[0])) {op1.settype(id);}
  else if (sc[0]=='$') {op1.setstackref(atoi(&sc[1]));}
  else if (sc[0]=='+' || sc[0]=='-' || isdigit(sc[0])) { // assume it's a number
    op1.setlit(strtod(sc,0)); // literals are always represented as floats
  }
  else if (ss!="") {op1.settype(nonknown);}
  op1.setname(ss);
  
  // read second operand
  while (buf[ip]==' '){ip++;} // eat white space
  is = 0;
  if (isalpha(buf[ip])||buf[ip]=='$') {  // alpha,$ followed by alphanum with punctuation
    while (isgraph(buf[ip])) {sc[is++]=buf[ip++];} 
    sc[is]=0;
    ss=sc; 
    if (debug==4)  cout << "debug: "<<ss;
    if (ss=="R0" || ss=="r0") {op2.setreg(0);}
    else if (ss=="R1" || ss=="r1") {op2.setreg(1);}
    else if (ss=="R2" || ss=="r2") {op2.setreg(2);}
    else if (ss=="R3" || ss=="r3") {op2.setreg(3);}
    else if (sc[0]=='$') {op2.setstackref(atoi(&sc[1]));}
    else  {op2.settype(id);}
    op2.setname(ss);
  }
  else if (buf[ip]=='"') { // doublequote-delimited string
    op2.settype(strval);
    ip++; // skip leading 
    is=0;
    while (buf[ip]!='"') {
      if (buf[ip-1]=='\\' && buf[ip]=='n') {sc[is-1]='\n'; ip++;}
      else sc[is++]=buf[ip++];
    }
    sc[is]=0; ip++;
    op2.setname(sc);
  }

  while (isspace(buf[ip])){ip++;} // eat white space
  if (buf[ip] != ';' && buf[ip] != 0) cout<< "line "<<line<<" warning: non comment found at end of line\n";

}

syscalls checksyscall(operand op){
  if (op.name()=="readi"){return SCreadi;}
  if (op.name()=="readr"){return SCreadr;}
  if (op.name()=="writei"){return SCwritei;}
  if (op.name()=="writer"){return SCwriter;}
  if (op.name()=="writes"){return SCwrites;}
  if (op.name()=="halt"){return SChalt;}
  return SCunknown;
}

void linkvariable(list<Symbol>&table, operand & o, int srcline){
  // if the operand is an id, find the variable in the table and make a 
  // reference to it in the operand
  if (o.type()==id) {
    for (list<Symbol>::iterator it=table.begin();it!=table.end();it++){
      if (it->name()==o.name()) {
	o.setvarref(it);
	return;
      }
    }
    cout << "error on line "<<srcline<<" identifier "<<o.name()<<" not defined\n";
    parseerror = true;
  }
}


int main(int argc, char *argv[]){
  if (argc==1) {cerr<<"usage: tiny srcfile [d1|d2|d3 [mix]]\n"; exit(1);}

  bool mix = false; // allow mixed declarations and code
  debug = 0;

  if (argc>=3){
    if (string(argv[2])=="d1") debug=1;
    else if (string(argv[2])=="d2") debug=2;
    else if (string(argv[2])=="d3") debug=3;
    else if (string(argv[2])=="d4") debug=4;
  }
  if (argc>=4){
    if (string(argv[3])=="mix") mix=true;
  }
  ifstream src(argv[1]);
  if (!src) {cerr<<argv[1]<<" not found\n"; exit(1);}

  line = 0;
  parseerror = false;
  list<opcode> program;
  list<Symbol> symboltable;
  //  list<jumptarget> labeltab;
  operand op1,op2;
  optype  code;
  bool done = false; 
  bool declarations = true; // parsing is in the declarations section
  
  while (!done) {
    line ++;
    parseline(code,op1,op2,src,line);
    if (code==endofprogram) {done=true;}
    else if (code==emptyline) {}
    else {
      //      checkoperands(op1,op2); 
      // op1 can be num/reg/id, op2 can be reg/id/strval/empty
      // only one of them can be an id
      // there must be 1 or 2 operands
      
      switch (code) {
      case var:  {
	passert(declarations||mix,"declarations must preceed all code"); 
	passert(op1.type()==id,"identifier operand expected"); 
	passert(op2.type()==empty,"only one operand expected"); 
	symboltable.push_back(Symbol(op1.name())); break;
      }
      case str :  {
	passert(declarations||mix,"declarations must preceed all code"); 
	passert(op1.type()==id,"1st operand must be indentifier"); 
	passert(op2.type()==strval,"2nd operand must be string"); 
	symboltable.push_back(Symbol(op1.name(),op2.name())); 
	break;
      }
      case label : {
	passert(op1.type()==id,"1st operand must be indentifier"); 
	passert(op2.type()==empty,"only one operand expected"); 
	//	labeltab.push_back(op1.name());  // may not need this
        declarations = false;
	program.push_back(opcode(label,op1,op2));
	break;
      }
      case move: {
	passert( (op1.type()==num || op1.type()==reg || 
		  op1.type()== id || op1.type()== stackref)
		 && (op2.type()==reg || op2.type()== id|| op2.type()== stackref),
		 "illegal operand type");
	passert(!((op1.type()==id || op1.type()== stackref)&& 
		(op2.type()==id || op2.type()== stackref)),
		"both  operands are memory refs");
        declarations = false;
	program.push_back(opcode(code,op1,op2)); 
	break;
      }
      case inci: 
      case deci: {
	passert(op1.type()==reg,"operand must be a register"); 
	passert(op2.type()==empty,"only one operand expected"); 
        declarations = false;
	program.push_back(opcode(code,op1,op2)); 
	break;
      }
      case addi:
      case addr: 
      case subr:
      case subi:
      case muli:
      case mulr:
      case divi:
      case divr:
      case cmpi:
      case cmpr: {
	passert( (op1.type()==num || op1.type()==reg || 
		  op1.type()== id || op1.type()== stackref)
		 && (op2.type()==reg), "illegal operand type");
        declarations = false;
	program.push_back(opcode(code,op1,op2)); 
	break;
      }
      case push: {
	passert(op2.type()==empty,"zero or one operand expected"); 
	passert(op1.type()==num || op1.type()==reg || op1.type()== id
		|| op1.type()== stackref || op1.type()==empty ,
		"illegal operand type"); 
	program.push_back(opcode(code,op1,op2));
        declarations = false;
	break;
      }
      case pop:{
	passert(op2.type()==empty,"zero or one operand expected"); 
	passert(op1.type()==reg || op1.type()== id || 
		op1.type()==stackref ||op1.type()==empty, 
		"illegal operand type"); 
	program.push_back(opcode(code,op1,op2));
        declarations = false;
	break;
      }
      case jsr: {
	passert(op2.type()==empty,"only one operand expected"); 
	passert(op1.type()==id,"operand must be an identifier"); 
	program.push_back(opcode(code,op1,op2));
        declarations = false;
	break;
      }
      case ret: {
	passert(op1.type()==empty && op2.type()==empty,"no operand expected"); 
	program.push_back(opcode(code,op1,op2));
        declarations = false;
	break;
      }
      case link: {
	passert(op1.type()==num && op2.type()==empty,"illegal operand"); 
	program.push_back(opcode(code,op1,op2));
        declarations = false;
	break;
      }
      case unlnk: {
	passert(op1.type()==empty && op2.type()==empty,"no operand expected"); 
	program.push_back(opcode(code,op1,op2));
        declarations = false;
	break;
      }
      case jmp:
      case jgt:
      case jlt:
      case jge:
      case jle:
      case jne:
      case jeq: {
	passert(op1.type()==id,"operand must be an identifier"); 
	passert(op2.type()==empty,"only one operand expected"); 
        declarations = false;
	program.push_back(opcode(code,op1,op2));
	break;
      }
      case sys : {
        syscalls scl = checksyscall(op1);
	passert(scl != SCunknown,"unknown system call");
	if (scl == SChalt) {
	  passert(op2.type()==empty,"only one operand expected");
	} 
	// operand types are not fully checked
        declarations = false;
	program.push_back(opcode(code,op1,op2)); break;
      }
      case unknown : {
	passert(false,"unknown opcode");
      }
      }
    }
  }
  
  if (debug>=1) { // print all identifiers
    for (list<Symbol>::iterator it  = symboltable.begin(); it!=symboltable.end(); it++) {
      cout<<"id "; it->print();cout<<'\n';
    }
    for (list<opcode>::iterator it  = program.begin(); it!=program.end(); it++) {
    it->print(); cout<<'\n';
    }
  }
  
  // check if all ids are defined; set jump target pcs:
  
  for (list<opcode>::iterator it  = program.begin(); it!=program.end(); it++) {
    if (it->code()>=jsr && it->code()<=jne) {
      // for all jump instructions, insert the jump target
      list<opcode>::iterator jt = program.begin();
      // find the opcode that has this label 
      bool labelfound = false;
      for (  ; jt!=program.end() && ! labelfound; jt++) {
	if (jt->code()==label && jt->o1().name()==it->o1().name()) {
	  it->settarget(jt);
	  labelfound = true;
	}
      }
      if (!labelfound) {
	cout<<"error on line "<<it->src()<<" jump target is not defined\n";
	parseerror=true;
      }
    }
    else if (it->code()==sys){
      // for sys calls the first operand is the syscall name, although its operand
      // type id "id", so only check the 2nd operand
      linkvariable(symboltable,it->o2(),it->src());
    }
    else if (it->code()!=label) {
      // in all other cases, except labels, check both operands
      linkvariable(symboltable,it->o1(),it->src());
      linkvariable(symboltable,it->o2(),it->src());
    }
  }
  
  if (parseerror) exit(1);
  
  // execute the program
  bool eop = false;
  int itemp;
  float rtemp;
  list<opcode>::iterator pc = program.begin();
  while (!eop && pc != program.end()) {
    if (debug>=3) {
      cpu.print();
      for (list<Symbol>::iterator it  = symboltable.begin(); it!=symboltable.end(); it++) {
	it->print(); cout <<"  ";
      }
      cout<<'\n';
    }
    if (debug >=2) {pc->print(); cout<<'\n';}
    line = pc->src();
    switch (pc->code()) {
    case move : { pc->o2().setval(pc->o1());pc++;break;}
    case label : {pc++;break;}
    case addi:
    case addr : {pc->o2().add(pc->o1()); pc++; break;}
    case subi:
    case subr : {pc->o2().sub(pc->o1()); pc++; break;}
    case muli:
    case mulr : {pc->o2().mul(pc->o1());  pc++; break;}
    case divi:  {pc->o2().divi(pc->o1()); pc++; break;}
    case divr : {pc->o2().divr(pc->o1()); pc++; break;}
    case inci : {pc->o1().inci(); pc++; break;}
    case deci : {pc->o1().deci(); pc++; break;}
    case cmpi       : {cpu.setstatusi(pc->o1().ival(),pc->o2().ival());  pc++; break;}
    case cmpr       : {cpu.setstatusr(pc->o1().rval(),pc->o2().rval());  pc++; break;}
    case push: {
      if (pc->o1().type() != empty) 
	stack.push_back(StackElement(pc->o1().ival(),pc->o1().rval()));
      else
	stack.push_back(StackElement(0,0));
      pc++;
      break;
    }
    case pop: {
      if (pc->o1().type() != empty) {
	pc->o1().setival(stack.back().ival());
	pc->o1().setrval(stack.back().rval());
      }
      stack.pop_back();
      pc++;
      break;
    }
    case jsr: {
      list<opcode>::iterator subrpc=pc->target();
      pc++;
      stack.push_back(StackElement(pc));
      pc = subrpc;
      break;
    }
    case ret: {
      pc = stack.back().aval();
      stack.pop_back();
      break;
    }
    case link: { 
      // push(fp); fp <- sp; reserve space on stack for local variables;
      stack.push_back(StackElement(fp));  
      fp = stack.size()-1;
      for (int ii=1;ii<=pc->o1().ival();ii++) {
	stack.push_back(StackElement(0,0));
      }
      pc++;
      break;
    }
    case unlnk: {
      // clean stack up to fp; pop(fp)
      while (stack.size()>fp+1) stack.pop_back();
      fp = stack.back().fval();
      stack.pop_back();
      pc++;
      break;
    }
    case jmp :       {pc=pc->target(); break;}
    case jgt:       {if (cpu.gt()) pc=pc->target(); else pc++; break;}
    case jlt:       {if (cpu.lt()) pc=pc->target(); else pc++; break;}
    case jge:       {if (cpu.ge()) pc=pc->target(); else pc++; break;}
    case jle:       {if (cpu.le()) pc=pc->target(); else pc++; break;}
    case jeq:       {if (cpu.eq()) pc=pc->target(); else pc++; break;}
    case jne:       {if (cpu.ne()) pc=pc->target(); else pc++; break;}
    case sys:  { 
      switch (checksyscall(pc->o1())) {
      case SCreadi : {cin >> itemp; pc->o2().setival(itemp); pc++; break;}
      case SCreadr : {cin >> rtemp; pc->o2().setrval(rtemp); pc++; break;}
      case SCwriter : {cout << pc->o2().rval(); pc++; break;}
      case SCwritei : {cout << pc->o2().ival(); pc++; break;}
      case SCwrites : {cout << pc->o2().sval(); pc++; break;} 
      case SChalt : {eop=true; break;}
      }
    }
    }
  }  
}
