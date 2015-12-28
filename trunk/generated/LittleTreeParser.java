// $ANTLR 3.3 Nov 30, 2010 12:50:56 src/LittleTreeParser.g 2011-12-02 00:41:30
 import java.util.HashMap; 
          import java.util.LinkedList;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


public class LittleTreeParser extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "DECL", "ASSIGN", "FUNC_HDR", "PROG_HDR", "FUNC_BODY", "WRITE", "READ", "DOWHILE", "IF", "CONDITION", "ELSE", "PARAMS", "FUNC_CALL", "RETURN", "IDENTIFIER", "STRINGLITERAL", "INTLITERAL", "FLOATLITERAL", "KEYWORD", "OPERATOR", "COMMENT", "WS", "'PROGRAM'", "'BEGIN'", "'END'", "'STRING'", "':='", "';'", "','", "'FLOAT'", "'INT'", "'VOID'", "'FUNCTION'", "'('", "')'", "'READ'", "'WRITE'", "'RETURN'", "'+'", "'-'", "'*'", "'/'", "'IF'", "'THEN'", "'ENDIF'", "'ELSE'", "'<'", "'>'", "'='", "'!='", "'DO'", "'WHILE'"
    };
    public static final int EOF=-1;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__50=50;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int DECL=4;
    public static final int ASSIGN=5;
    public static final int FUNC_HDR=6;
    public static final int PROG_HDR=7;
    public static final int FUNC_BODY=8;
    public static final int WRITE=9;
    public static final int READ=10;
    public static final int DOWHILE=11;
    public static final int IF=12;
    public static final int CONDITION=13;
    public static final int ELSE=14;
    public static final int PARAMS=15;
    public static final int FUNC_CALL=16;
    public static final int RETURN=17;
    public static final int IDENTIFIER=18;
    public static final int STRINGLITERAL=19;
    public static final int INTLITERAL=20;
    public static final int FLOATLITERAL=21;
    public static final int KEYWORD=22;
    public static final int OPERATOR=23;
    public static final int COMMENT=24;
    public static final int WS=25;

    // delegates
    // delegators


        public LittleTreeParser(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public LittleTreeParser(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return LittleTreeParser.tokenNames; }
    public String getGrammarFileName() { return "src/LittleTreeParser.g"; }

     
        ProgramStorageTable symTable;
        String curr_fname = "Global";
        

    public static class program_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "program"
    // src/LittleTreeParser.g:18:1: program[ProgramStorageTable symTable] : ^(r= PROG_HDR i= IDENTIFIER pgm_body ) ;
    public final LittleTreeParser.program_return program(ProgramStorageTable symTable) throws RecognitionException {
        LittleTreeParser.program_return retval = new LittleTreeParser.program_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode r=null;
        AKNode i=null;
        LittleTreeParser.pgm_body_return pgm_body1 = null;


        AKNode r_tree=null;
        AKNode i_tree=null;

        this.symTable = symTable;
              
        try {
            // src/LittleTreeParser.g:21:3: ( ^(r= PROG_HDR i= IDENTIFIER pgm_body ) )
            // src/LittleTreeParser.g:22:5: ^(r= PROG_HDR i= IDENTIFIER pgm_body )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();_last = (AKNode)input.LT(1);
            r=(AKNode)match(input,PROG_HDR,FOLLOW_PROG_HDR_in_program70); 
            r_tree = (AKNode)adaptor.dupNode(r);

            root_1 = (AKNode)adaptor.becomeRoot(r_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (AKNode)input.LT(1);
            i=(AKNode)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_program76); 
            i_tree = (AKNode)adaptor.dupNode(i);

            adaptor.addChild(root_1, i_tree);

            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_pgm_body_in_program78);
            pgm_body1=pgm_body();

            state._fsp--;

            adaptor.addChild(root_1, pgm_body1.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "program"

    public static class pgm_body_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pgm_body"
    // src/LittleTreeParser.g:26:1: pgm_body : decl func_declarations ;
    public final LittleTreeParser.pgm_body_return pgm_body() throws RecognitionException {
        LittleTreeParser.pgm_body_return retval = new LittleTreeParser.pgm_body_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        LittleTreeParser.decl_return decl2 = null;

        LittleTreeParser.func_declarations_return func_declarations3 = null;



        try {
            // src/LittleTreeParser.g:27:3: ( decl func_declarations )
            // src/LittleTreeParser.g:27:5: decl func_declarations
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_decl_in_pgm_body97);
            decl2=decl();

            state._fsp--;

            adaptor.addChild(root_0, decl2.getTree());
            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_func_declarations_in_pgm_body99);
            func_declarations3=func_declarations();

            state._fsp--;

            adaptor.addChild(root_0, func_declarations3.getTree());

            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "pgm_body"

    public static class decl_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "decl"
    // src/LittleTreeParser.g:30:1: decl : ( var_decl_list | string_decl_list )* ;
    public final LittleTreeParser.decl_return decl() throws RecognitionException {
        LittleTreeParser.decl_return retval = new LittleTreeParser.decl_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        LittleTreeParser.var_decl_list_return var_decl_list4 = null;

        LittleTreeParser.string_decl_list_return string_decl_list5 = null;



        try {
            // src/LittleTreeParser.g:31:3: ( ( var_decl_list | string_decl_list )* )
            // src/LittleTreeParser.g:31:5: ( var_decl_list | string_decl_list )*
            {
            root_0 = (AKNode)adaptor.nil();

            // src/LittleTreeParser.g:31:5: ( var_decl_list | string_decl_list )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==DECL) ) {
                    alt1=1;
                }
                else if ( (LA1_0==29) ) {
                    alt1=2;
                }


                switch (alt1) {
            	case 1 :
            	    // src/LittleTreeParser.g:31:6: var_decl_list
            	    {
            	    _last = (AKNode)input.LT(1);
            	    pushFollow(FOLLOW_var_decl_list_in_decl115);
            	    var_decl_list4=var_decl_list();

            	    state._fsp--;

            	    adaptor.addChild(root_0, var_decl_list4.getTree());

            	    }
            	    break;
            	case 2 :
            	    // src/LittleTreeParser.g:31:22: string_decl_list
            	    {
            	    _last = (AKNode)input.LT(1);
            	    pushFollow(FOLLOW_string_decl_list_in_decl119);
            	    string_decl_list5=string_decl_list();

            	    state._fsp--;

            	    adaptor.addChild(root_0, string_decl_list5.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "decl"

    public static class string_decl_list_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "string_decl_list"
    // src/LittleTreeParser.g:35:1: string_decl_list : ( string_decl ) ;
    public final LittleTreeParser.string_decl_list_return string_decl_list() throws RecognitionException {
        LittleTreeParser.string_decl_list_return retval = new LittleTreeParser.string_decl_list_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        LittleTreeParser.string_decl_return string_decl6 = null;



        try {
            // src/LittleTreeParser.g:36:3: ( ( string_decl ) )
            // src/LittleTreeParser.g:36:5: ( string_decl )
            {
            root_0 = (AKNode)adaptor.nil();

            // src/LittleTreeParser.g:36:5: ( string_decl )
            // src/LittleTreeParser.g:36:6: string_decl
            {
            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_string_decl_in_string_decl_list139);
            string_decl6=string_decl();

            state._fsp--;

            adaptor.addChild(root_0, string_decl6.getTree());

            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "string_decl_list"

    public static class string_decl_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "string_decl"
    // src/LittleTreeParser.g:38:1: string_decl : ^(t= 'STRING' id= IDENTIFIER sv= STRINGLITERAL ) ;
    public final LittleTreeParser.string_decl_return string_decl() throws RecognitionException {
        LittleTreeParser.string_decl_return retval = new LittleTreeParser.string_decl_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode t=null;
        AKNode id=null;
        AKNode sv=null;

        AKNode t_tree=null;
        AKNode id_tree=null;
        AKNode sv_tree=null;

        try {
            // src/LittleTreeParser.g:39:3: ( ^(t= 'STRING' id= IDENTIFIER sv= STRINGLITERAL ) )
            // src/LittleTreeParser.g:39:6: ^(t= 'STRING' id= IDENTIFIER sv= STRINGLITERAL )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();_last = (AKNode)input.LT(1);
            t=(AKNode)match(input,29,FOLLOW_29_in_string_decl156); 
            t_tree = (AKNode)adaptor.dupNode(t);

            root_1 = (AKNode)adaptor.becomeRoot(t_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (AKNode)input.LT(1);
            id=(AKNode)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_string_decl160); 
            id_tree = (AKNode)adaptor.dupNode(id);

            adaptor.addChild(root_1, id_tree);

            _last = (AKNode)input.LT(1);
            sv=(AKNode)match(input,STRINGLITERAL,FOLLOW_STRINGLITERAL_in_string_decl164); 
            sv_tree = (AKNode)adaptor.dupNode(sv);

            adaptor.addChild(root_1, sv_tree);


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }

            symTable.addSymbolByFunctionName(curr_fname, new Symbol(id.getText(), t.getText(),sv.getText()));

            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "string_decl"

    public static class var_decl_list_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "var_decl_list"
    // src/LittleTreeParser.g:44:1: var_decl_list : ( var_decl ) ;
    public final LittleTreeParser.var_decl_list_return var_decl_list() throws RecognitionException {
        LittleTreeParser.var_decl_list_return retval = new LittleTreeParser.var_decl_list_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        LittleTreeParser.var_decl_return var_decl7 = null;



        try {
            // src/LittleTreeParser.g:45:3: ( ( var_decl ) )
            // src/LittleTreeParser.g:45:5: ( var_decl )
            {
            root_0 = (AKNode)adaptor.nil();

            // src/LittleTreeParser.g:45:5: ( var_decl )
            // src/LittleTreeParser.g:45:6: var_decl
            {
            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_var_decl_in_var_decl_list189);
            var_decl7=var_decl();

            state._fsp--;

            adaptor.addChild(root_0, var_decl7.getTree());

            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "var_decl_list"

    public static class var_decl_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "var_decl"
    // src/LittleTreeParser.g:48:1: var_decl : ^( DECL t= ( 'FLOAT' | 'INT' ) id= IDENTIFIER ) ;
    public final LittleTreeParser.var_decl_return var_decl() throws RecognitionException {
        LittleTreeParser.var_decl_return retval = new LittleTreeParser.var_decl_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode t=null;
        AKNode id=null;
        AKNode DECL8=null;

        AKNode t_tree=null;
        AKNode id_tree=null;
        AKNode DECL8_tree=null;

        try {
            // src/LittleTreeParser.g:49:3: ( ^( DECL t= ( 'FLOAT' | 'INT' ) id= IDENTIFIER ) )
            // src/LittleTreeParser.g:50:5: ^( DECL t= ( 'FLOAT' | 'INT' ) id= IDENTIFIER )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();_last = (AKNode)input.LT(1);
            DECL8=(AKNode)match(input,DECL,FOLLOW_DECL_in_var_decl211); 
            DECL8_tree = (AKNode)adaptor.dupNode(DECL8);

            root_1 = (AKNode)adaptor.becomeRoot(DECL8_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (AKNode)input.LT(1);
            t=(AKNode)input.LT(1);
            if ( (input.LA(1)>=33 && input.LA(1)<=34) ) {
                input.consume();

                t_tree = (AKNode)adaptor.dupNode(t);

                adaptor.addChild(root_1, t_tree);

                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            _last = (AKNode)input.LT(1);
            id=(AKNode)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_var_decl225); 
            id_tree = (AKNode)adaptor.dupNode(id);

            adaptor.addChild(root_1, id_tree);


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }

            symTable.addSymbolByFunctionName(curr_fname, new Symbol(id.getText(), t.getText())); 

            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "var_decl"

    public static class func_declarations_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "func_declarations"
    // src/LittleTreeParser.g:55:1: func_declarations : ( func_decl )* ;
    public final LittleTreeParser.func_declarations_return func_declarations() throws RecognitionException {
        LittleTreeParser.func_declarations_return retval = new LittleTreeParser.func_declarations_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        LittleTreeParser.func_decl_return func_decl9 = null;



        try {
            // src/LittleTreeParser.g:56:3: ( ( func_decl )* )
            // src/LittleTreeParser.g:56:5: ( func_decl )*
            {
            root_0 = (AKNode)adaptor.nil();

            // src/LittleTreeParser.g:56:5: ( func_decl )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==FUNC_HDR) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // src/LittleTreeParser.g:56:6: func_decl
            	    {
            	    _last = (AKNode)input.LT(1);
            	    pushFollow(FOLLOW_func_decl_in_func_declarations248);
            	    func_decl9=func_decl();

            	    state._fsp--;

            	    adaptor.addChild(root_0, func_decl9.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "func_declarations"

    public static class func_decl_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "func_decl"
    // src/LittleTreeParser.g:58:1: func_decl : ^( FUNC_HDR id= IDENTIFIER ( param_decl_list )? func_body ) ;
    public final LittleTreeParser.func_decl_return func_decl() throws RecognitionException {
        LittleTreeParser.func_decl_return retval = new LittleTreeParser.func_decl_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode id=null;
        AKNode FUNC_HDR10=null;
        LittleTreeParser.param_decl_list_return param_decl_list11 = null;

        LittleTreeParser.func_body_return func_body12 = null;


        AKNode id_tree=null;
        AKNode FUNC_HDR10_tree=null;

        try {
            // src/LittleTreeParser.g:59:3: ( ^( FUNC_HDR id= IDENTIFIER ( param_decl_list )? func_body ) )
            // src/LittleTreeParser.g:60:5: ^( FUNC_HDR id= IDENTIFIER ( param_decl_list )? func_body )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();_last = (AKNode)input.LT(1);
            FUNC_HDR10=(AKNode)match(input,FUNC_HDR,FOLLOW_FUNC_HDR_in_func_decl267); 
            FUNC_HDR10_tree = (AKNode)adaptor.dupNode(FUNC_HDR10);

            root_1 = (AKNode)adaptor.becomeRoot(FUNC_HDR10_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (AKNode)input.LT(1);
            id=(AKNode)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_func_decl271); 
            id_tree = (AKNode)adaptor.dupNode(id);

            adaptor.addChild(root_1, id_tree);

            curr_fname = id.getText();symTable.addSymbolTable(new SymbolTable(curr_fname));
            // src/LittleTreeParser.g:60:113: ( param_decl_list )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==PARAMS) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // src/LittleTreeParser.g:60:113: param_decl_list
                    {
                    _last = (AKNode)input.LT(1);
                    pushFollow(FOLLOW_param_decl_list_in_func_decl275);
                    param_decl_list11=param_decl_list();

                    state._fsp--;

                    adaptor.addChild(root_1, param_decl_list11.getTree());

                    }
                    break;

            }

            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_func_body_in_func_decl278);
            func_body12=func_body();

            state._fsp--;

            adaptor.addChild(root_1, func_body12.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "func_decl"

    public static class param_decl_list_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "param_decl_list"
    // src/LittleTreeParser.g:69:1: param_decl_list : ^( PARAMS ( param_decl )* ) ;
    public final LittleTreeParser.param_decl_list_return param_decl_list() throws RecognitionException {
        LittleTreeParser.param_decl_list_return retval = new LittleTreeParser.param_decl_list_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode PARAMS13=null;
        LittleTreeParser.param_decl_return param_decl14 = null;


        AKNode PARAMS13_tree=null;

        try {
            // src/LittleTreeParser.g:70:3: ( ^( PARAMS ( param_decl )* ) )
            // src/LittleTreeParser.g:70:5: ^( PARAMS ( param_decl )* )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();_last = (AKNode)input.LT(1);
            PARAMS13=(AKNode)match(input,PARAMS,FOLLOW_PARAMS_in_param_decl_list310); 
            PARAMS13_tree = (AKNode)adaptor.dupNode(PARAMS13);

            root_1 = (AKNode)adaptor.becomeRoot(PARAMS13_tree, root_1);



            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // src/LittleTreeParser.g:70:14: ( param_decl )*
                loop4:
                do {
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( ((LA4_0>=33 && LA4_0<=34)) ) {
                        alt4=1;
                    }


                    switch (alt4) {
                	case 1 :
                	    // src/LittleTreeParser.g:70:14: param_decl
                	    {
                	    _last = (AKNode)input.LT(1);
                	    pushFollow(FOLLOW_param_decl_in_param_decl_list312);
                	    param_decl14=param_decl();

                	    state._fsp--;

                	    adaptor.addChild(root_1, param_decl14.getTree());

                	    }
                	    break;

                	default :
                	    break loop4;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "param_decl_list"

    public static class param_decl_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "param_decl"
    // src/LittleTreeParser.g:72:1: param_decl : ^(t= ( 'FLOAT' | 'INT' ) id= IDENTIFIER ) ;
    public final LittleTreeParser.param_decl_return param_decl() throws RecognitionException {
        LittleTreeParser.param_decl_return retval = new LittleTreeParser.param_decl_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode t=null;
        AKNode id=null;

        AKNode t_tree=null;
        AKNode id_tree=null;

        try {
            // src/LittleTreeParser.g:73:3: ( ^(t= ( 'FLOAT' | 'INT' ) id= IDENTIFIER ) )
            // src/LittleTreeParser.g:73:5: ^(t= ( 'FLOAT' | 'INT' ) id= IDENTIFIER )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();t=(AKNode)input.LT(1);
            if ( (input.LA(1)>=33 && input.LA(1)<=34) ) {
                input.consume();

                t_tree = (AKNode)adaptor.dupNode(t);

                root_1 = (AKNode)adaptor.becomeRoot(t_tree, root_1);

                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }



            match(input, Token.DOWN, null); 
            _last = (AKNode)input.LT(1);
            id=(AKNode)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_param_decl339); 
            id_tree = (AKNode)adaptor.dupNode(id);

            adaptor.addChild(root_1, id_tree);


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }

            symTable.addSymbolByFunctionName(curr_fname, new Symbol(id.getText(), "PARAM"));

            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "param_decl"

    public static class func_body_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "func_body"
    // src/LittleTreeParser.g:78:1: func_body : decl stmt_list ;
    public final LittleTreeParser.func_body_return func_body() throws RecognitionException {
        LittleTreeParser.func_body_return retval = new LittleTreeParser.func_body_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        LittleTreeParser.decl_return decl15 = null;

        LittleTreeParser.stmt_list_return stmt_list16 = null;



        try {
            // src/LittleTreeParser.g:79:3: ( decl stmt_list )
            // src/LittleTreeParser.g:79:5: decl stmt_list
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_decl_in_func_body364);
            decl15=decl();

            state._fsp--;

            adaptor.addChild(root_0, decl15.getTree());
            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_stmt_list_in_func_body366);
            stmt_list16=stmt_list();

            state._fsp--;

            adaptor.addChild(root_0, stmt_list16.getTree());

            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "func_body"

    public static class call_expr_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "call_expr"
    // src/LittleTreeParser.g:81:1: call_expr : ^( FUNC_CALL IDENTIFIER ( expr )* ) ;
    public final LittleTreeParser.call_expr_return call_expr() throws RecognitionException {
        LittleTreeParser.call_expr_return retval = new LittleTreeParser.call_expr_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode FUNC_CALL17=null;
        AKNode IDENTIFIER18=null;
        LittleTreeParser.expr_return expr19 = null;


        AKNode FUNC_CALL17_tree=null;
        AKNode IDENTIFIER18_tree=null;

        try {
            // src/LittleTreeParser.g:82:3: ( ^( FUNC_CALL IDENTIFIER ( expr )* ) )
            // src/LittleTreeParser.g:82:6: ^( FUNC_CALL IDENTIFIER ( expr )* )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();_last = (AKNode)input.LT(1);
            FUNC_CALL17=(AKNode)match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_call_expr380); 
            FUNC_CALL17_tree = (AKNode)adaptor.dupNode(FUNC_CALL17);

            root_1 = (AKNode)adaptor.becomeRoot(FUNC_CALL17_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (AKNode)input.LT(1);
            IDENTIFIER18=(AKNode)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_call_expr382); 
            IDENTIFIER18_tree = (AKNode)adaptor.dupNode(IDENTIFIER18);

            adaptor.addChild(root_1, IDENTIFIER18_tree);

            // src/LittleTreeParser.g:82:29: ( expr )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==FUNC_CALL||LA5_0==IDENTIFIER||(LA5_0>=INTLITERAL && LA5_0<=FLOATLITERAL)||(LA5_0>=42 && LA5_0<=45)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // src/LittleTreeParser.g:82:29: expr
            	    {
            	    _last = (AKNode)input.LT(1);
            	    pushFollow(FOLLOW_expr_in_call_expr384);
            	    expr19=expr();

            	    state._fsp--;

            	    adaptor.addChild(root_1, expr19.getTree());

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "call_expr"

    public static class stmt_list_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "stmt_list"
    // src/LittleTreeParser.g:86:1: stmt_list : ( stmt )* ;
    public final LittleTreeParser.stmt_list_return stmt_list() throws RecognitionException {
        LittleTreeParser.stmt_list_return retval = new LittleTreeParser.stmt_list_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        LittleTreeParser.stmt_return stmt20 = null;



        try {
            // src/LittleTreeParser.g:87:3: ( ( stmt )* )
            // src/LittleTreeParser.g:87:5: ( stmt )*
            {
            root_0 = (AKNode)adaptor.nil();

            // src/LittleTreeParser.g:87:5: ( stmt )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==ASSIGN||(LA6_0>=WRITE && LA6_0<=IF)||LA6_0==RETURN) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // src/LittleTreeParser.g:87:6: stmt
            	    {
            	    _last = (AKNode)input.LT(1);
            	    pushFollow(FOLLOW_stmt_in_stmt_list402);
            	    stmt20=stmt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, stmt20.getTree());

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "stmt_list"

    public static class stmt_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "stmt"
    // src/LittleTreeParser.g:89:1: stmt : ( assign_stmt | write_stmt | do_stmt | read_stmt | if_stmt | return_stmt );
    public final LittleTreeParser.stmt_return stmt() throws RecognitionException {
        LittleTreeParser.stmt_return retval = new LittleTreeParser.stmt_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        LittleTreeParser.assign_stmt_return assign_stmt21 = null;

        LittleTreeParser.write_stmt_return write_stmt22 = null;

        LittleTreeParser.do_stmt_return do_stmt23 = null;

        LittleTreeParser.read_stmt_return read_stmt24 = null;

        LittleTreeParser.if_stmt_return if_stmt25 = null;

        LittleTreeParser.return_stmt_return return_stmt26 = null;



        try {
            // src/LittleTreeParser.g:90:3: ( assign_stmt | write_stmt | do_stmt | read_stmt | if_stmt | return_stmt )
            int alt7=6;
            switch ( input.LA(1) ) {
            case ASSIGN:
                {
                alt7=1;
                }
                break;
            case WRITE:
                {
                alt7=2;
                }
                break;
            case DOWHILE:
                {
                alt7=3;
                }
                break;
            case READ:
                {
                alt7=4;
                }
                break;
            case IF:
                {
                alt7=5;
                }
                break;
            case RETURN:
                {
                alt7=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // src/LittleTreeParser.g:90:5: assign_stmt
                    {
                    root_0 = (AKNode)adaptor.nil();

                    _last = (AKNode)input.LT(1);
                    pushFollow(FOLLOW_assign_stmt_in_stmt416);
                    assign_stmt21=assign_stmt();

                    state._fsp--;

                    adaptor.addChild(root_0, assign_stmt21.getTree());

                    }
                    break;
                case 2 :
                    // src/LittleTreeParser.g:90:19: write_stmt
                    {
                    root_0 = (AKNode)adaptor.nil();

                    _last = (AKNode)input.LT(1);
                    pushFollow(FOLLOW_write_stmt_in_stmt420);
                    write_stmt22=write_stmt();

                    state._fsp--;

                    adaptor.addChild(root_0, write_stmt22.getTree());

                    }
                    break;
                case 3 :
                    // src/LittleTreeParser.g:90:32: do_stmt
                    {
                    root_0 = (AKNode)adaptor.nil();

                    _last = (AKNode)input.LT(1);
                    pushFollow(FOLLOW_do_stmt_in_stmt424);
                    do_stmt23=do_stmt();

                    state._fsp--;

                    adaptor.addChild(root_0, do_stmt23.getTree());

                    }
                    break;
                case 4 :
                    // src/LittleTreeParser.g:90:42: read_stmt
                    {
                    root_0 = (AKNode)adaptor.nil();

                    _last = (AKNode)input.LT(1);
                    pushFollow(FOLLOW_read_stmt_in_stmt428);
                    read_stmt24=read_stmt();

                    state._fsp--;

                    adaptor.addChild(root_0, read_stmt24.getTree());

                    }
                    break;
                case 5 :
                    // src/LittleTreeParser.g:90:54: if_stmt
                    {
                    root_0 = (AKNode)adaptor.nil();

                    _last = (AKNode)input.LT(1);
                    pushFollow(FOLLOW_if_stmt_in_stmt432);
                    if_stmt25=if_stmt();

                    state._fsp--;

                    adaptor.addChild(root_0, if_stmt25.getTree());

                    }
                    break;
                case 6 :
                    // src/LittleTreeParser.g:90:64: return_stmt
                    {
                    root_0 = (AKNode)adaptor.nil();

                    _last = (AKNode)input.LT(1);
                    pushFollow(FOLLOW_return_stmt_in_stmt436);
                    return_stmt26=return_stmt();

                    state._fsp--;

                    adaptor.addChild(root_0, return_stmt26.getTree());

                    }
                    break;

            }
            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "stmt"

    public static class assign_stmt_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assign_stmt"
    // src/LittleTreeParser.g:95:1: assign_stmt : ^(r= ASSIGN i= IDENTIFIER expr ) ;
    public final LittleTreeParser.assign_stmt_return assign_stmt() throws RecognitionException {
        LittleTreeParser.assign_stmt_return retval = new LittleTreeParser.assign_stmt_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode r=null;
        AKNode i=null;
        LittleTreeParser.expr_return expr27 = null;


        AKNode r_tree=null;
        AKNode i_tree=null;

        try {
            // src/LittleTreeParser.g:96:3: ( ^(r= ASSIGN i= IDENTIFIER expr ) )
            // src/LittleTreeParser.g:96:5: ^(r= ASSIGN i= IDENTIFIER expr )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();_last = (AKNode)input.LT(1);
            r=(AKNode)match(input,ASSIGN,FOLLOW_ASSIGN_in_assign_stmt457); 
            r_tree = (AKNode)adaptor.dupNode(r);

            root_1 = (AKNode)adaptor.becomeRoot(r_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (AKNode)input.LT(1);
            i=(AKNode)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_assign_stmt461); 
            i_tree = (AKNode)adaptor.dupNode(i);

            adaptor.addChild(root_1, i_tree);

            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_expr_in_assign_stmt463);
            expr27=expr();

            state._fsp--;

            adaptor.addChild(root_1, expr27.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "assign_stmt"

    public static class read_stmt_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "read_stmt"
    // src/LittleTreeParser.g:100:1: read_stmt : ^( READ IDENTIFIER ) ;
    public final LittleTreeParser.read_stmt_return read_stmt() throws RecognitionException {
        LittleTreeParser.read_stmt_return retval = new LittleTreeParser.read_stmt_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode READ28=null;
        AKNode IDENTIFIER29=null;

        AKNode READ28_tree=null;
        AKNode IDENTIFIER29_tree=null;

        try {
            // src/LittleTreeParser.g:101:3: ( ^( READ IDENTIFIER ) )
            // src/LittleTreeParser.g:101:5: ^( READ IDENTIFIER )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();_last = (AKNode)input.LT(1);
            READ28=(AKNode)match(input,READ,FOLLOW_READ_in_read_stmt482); 
            READ28_tree = (AKNode)adaptor.dupNode(READ28);

            root_1 = (AKNode)adaptor.becomeRoot(READ28_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (AKNode)input.LT(1);
            IDENTIFIER29=(AKNode)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_read_stmt484); 
            IDENTIFIER29_tree = (AKNode)adaptor.dupNode(IDENTIFIER29);

            adaptor.addChild(root_1, IDENTIFIER29_tree);


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "read_stmt"

    public static class write_stmt_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "write_stmt"
    // src/LittleTreeParser.g:103:1: write_stmt : ^( WRITE id= IDENTIFIER ) ;
    public final LittleTreeParser.write_stmt_return write_stmt() throws RecognitionException {
        LittleTreeParser.write_stmt_return retval = new LittleTreeParser.write_stmt_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode id=null;
        AKNode WRITE30=null;

        AKNode id_tree=null;
        AKNode WRITE30_tree=null;

        try {
            // src/LittleTreeParser.g:104:3: ( ^( WRITE id= IDENTIFIER ) )
            // src/LittleTreeParser.g:104:5: ^( WRITE id= IDENTIFIER )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();_last = (AKNode)input.LT(1);
            WRITE30=(AKNode)match(input,WRITE,FOLLOW_WRITE_in_write_stmt499); 
            WRITE30_tree = (AKNode)adaptor.dupNode(WRITE30);

            root_1 = (AKNode)adaptor.becomeRoot(WRITE30_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (AKNode)input.LT(1);
            id=(AKNode)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_write_stmt503); 
            id_tree = (AKNode)adaptor.dupNode(id);

            adaptor.addChild(root_1, id_tree);


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "write_stmt"

    public static class return_stmt_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "return_stmt"
    // src/LittleTreeParser.g:107:1: return_stmt : ^( RETURN expr ) ;
    public final LittleTreeParser.return_stmt_return return_stmt() throws RecognitionException {
        LittleTreeParser.return_stmt_return retval = new LittleTreeParser.return_stmt_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode RETURN31=null;
        LittleTreeParser.expr_return expr32 = null;


        AKNode RETURN31_tree=null;

        try {
            // src/LittleTreeParser.g:108:3: ( ^( RETURN expr ) )
            // src/LittleTreeParser.g:108:5: ^( RETURN expr )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();_last = (AKNode)input.LT(1);
            RETURN31=(AKNode)match(input,RETURN,FOLLOW_RETURN_in_return_stmt518); 
            RETURN31_tree = (AKNode)adaptor.dupNode(RETURN31);

            root_1 = (AKNode)adaptor.becomeRoot(RETURN31_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_expr_in_return_stmt520);
            expr32=expr();

            state._fsp--;

            adaptor.addChild(root_1, expr32.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "return_stmt"

    public static class expr_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expr"
    // src/LittleTreeParser.g:113:1: expr : ( ( ^(f= ( '+' | '-' | '*' | '/' ) expr expr ) | f= IDENTIFIER | f= INTLITERAL | f= FLOATLITERAL ) | call_expr );
    public final LittleTreeParser.expr_return expr() throws RecognitionException {
        LittleTreeParser.expr_return retval = new LittleTreeParser.expr_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode f=null;
        LittleTreeParser.expr_return expr33 = null;

        LittleTreeParser.expr_return expr34 = null;

        LittleTreeParser.call_expr_return call_expr35 = null;


        AKNode f_tree=null;

        try {
            // src/LittleTreeParser.g:114:3: ( ( ^(f= ( '+' | '-' | '*' | '/' ) expr expr ) | f= IDENTIFIER | f= INTLITERAL | f= FLOATLITERAL ) | call_expr )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==IDENTIFIER||(LA9_0>=INTLITERAL && LA9_0<=FLOATLITERAL)||(LA9_0>=42 && LA9_0<=45)) ) {
                alt9=1;
            }
            else if ( (LA9_0==FUNC_CALL) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // src/LittleTreeParser.g:114:5: ( ^(f= ( '+' | '-' | '*' | '/' ) expr expr ) | f= IDENTIFIER | f= INTLITERAL | f= FLOATLITERAL )
                    {
                    root_0 = (AKNode)adaptor.nil();

                    // src/LittleTreeParser.g:114:5: ( ^(f= ( '+' | '-' | '*' | '/' ) expr expr ) | f= IDENTIFIER | f= INTLITERAL | f= FLOATLITERAL )
                    int alt8=4;
                    switch ( input.LA(1) ) {
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                        {
                        alt8=1;
                        }
                        break;
                    case IDENTIFIER:
                        {
                        alt8=2;
                        }
                        break;
                    case INTLITERAL:
                        {
                        alt8=3;
                        }
                        break;
                    case FLOATLITERAL:
                        {
                        alt8=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 0, input);

                        throw nvae;
                    }

                    switch (alt8) {
                        case 1 :
                            // src/LittleTreeParser.g:114:6: ^(f= ( '+' | '-' | '*' | '/' ) expr expr )
                            {
                            _last = (AKNode)input.LT(1);
                            {
                            AKNode _save_last_1 = _last;
                            AKNode _first_1 = null;
                            AKNode root_1 = (AKNode)adaptor.nil();f=(AKNode)input.LT(1);
                            if ( (input.LA(1)>=42 && input.LA(1)<=45) ) {
                                input.consume();

                                f_tree = (AKNode)adaptor.dupNode(f);

                                root_1 = (AKNode)adaptor.becomeRoot(f_tree, root_1);

                                state.errorRecovery=false;
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }



                            match(input, Token.DOWN, null); 
                            _last = (AKNode)input.LT(1);
                            pushFollow(FOLLOW_expr_in_expr558);
                            expr33=expr();

                            state._fsp--;

                            adaptor.addChild(root_1, expr33.getTree());
                            _last = (AKNode)input.LT(1);
                            pushFollow(FOLLOW_expr_in_expr560);
                            expr34=expr();

                            state._fsp--;

                            adaptor.addChild(root_1, expr34.getTree());

                            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                            }


                            }
                            break;
                        case 2 :
                            // src/LittleTreeParser.g:115:5: f= IDENTIFIER
                            {
                            _last = (AKNode)input.LT(1);
                            f=(AKNode)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_expr570); 
                            f_tree = (AKNode)adaptor.dupNode(f);

                            adaptor.addChild(root_0, f_tree);


                            }
                            break;
                        case 3 :
                            // src/LittleTreeParser.g:116:5: f= INTLITERAL
                            {
                            _last = (AKNode)input.LT(1);
                            f=(AKNode)match(input,INTLITERAL,FOLLOW_INTLITERAL_in_expr580); 
                            f_tree = (AKNode)adaptor.dupNode(f);

                            adaptor.addChild(root_0, f_tree);


                            }
                            break;
                        case 4 :
                            // src/LittleTreeParser.g:117:5: f= FLOATLITERAL
                            {
                            _last = (AKNode)input.LT(1);
                            f=(AKNode)match(input,FLOATLITERAL,FOLLOW_FLOATLITERAL_in_expr590); 
                            f_tree = (AKNode)adaptor.dupNode(f);

                            adaptor.addChild(root_0, f_tree);


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // src/LittleTreeParser.g:118:5: call_expr
                    {
                    root_0 = (AKNode)adaptor.nil();

                    _last = (AKNode)input.LT(1);
                    pushFollow(FOLLOW_call_expr_in_expr597);
                    call_expr35=call_expr();

                    state._fsp--;

                    adaptor.addChild(root_0, call_expr35.getTree());

                    }
                    break;

            }
            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "expr"

    public static class if_stmt_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "if_stmt"
    // src/LittleTreeParser.g:136:1: if_stmt : ^( IF cond stmt_list ( else_expr )? ) ;
    public final LittleTreeParser.if_stmt_return if_stmt() throws RecognitionException {
        LittleTreeParser.if_stmt_return retval = new LittleTreeParser.if_stmt_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode IF36=null;
        LittleTreeParser.cond_return cond37 = null;

        LittleTreeParser.stmt_list_return stmt_list38 = null;

        LittleTreeParser.else_expr_return else_expr39 = null;


        AKNode IF36_tree=null;

        try {
            // src/LittleTreeParser.g:137:3: ( ^( IF cond stmt_list ( else_expr )? ) )
            // src/LittleTreeParser.g:137:5: ^( IF cond stmt_list ( else_expr )? )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();_last = (AKNode)input.LT(1);
            IF36=(AKNode)match(input,IF,FOLLOW_IF_in_if_stmt629); 
            IF36_tree = (AKNode)adaptor.dupNode(IF36);

            root_1 = (AKNode)adaptor.becomeRoot(IF36_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_cond_in_if_stmt631);
            cond37=cond();

            state._fsp--;

            adaptor.addChild(root_1, cond37.getTree());
            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_stmt_list_in_if_stmt633);
            stmt_list38=stmt_list();

            state._fsp--;

            adaptor.addChild(root_1, stmt_list38.getTree());
            // src/LittleTreeParser.g:137:25: ( else_expr )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==ELSE) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // src/LittleTreeParser.g:137:25: else_expr
                    {
                    _last = (AKNode)input.LT(1);
                    pushFollow(FOLLOW_else_expr_in_if_stmt635);
                    else_expr39=else_expr();

                    state._fsp--;

                    adaptor.addChild(root_1, else_expr39.getTree());

                    }
                    break;

            }


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "if_stmt"

    public static class else_expr_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "else_expr"
    // src/LittleTreeParser.g:139:1: else_expr : ^( ELSE stmt_list ) ;
    public final LittleTreeParser.else_expr_return else_expr() throws RecognitionException {
        LittleTreeParser.else_expr_return retval = new LittleTreeParser.else_expr_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode ELSE40=null;
        LittleTreeParser.stmt_list_return stmt_list41 = null;


        AKNode ELSE40_tree=null;

        try {
            // src/LittleTreeParser.g:140:3: ( ^( ELSE stmt_list ) )
            // src/LittleTreeParser.g:140:5: ^( ELSE stmt_list )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();_last = (AKNode)input.LT(1);
            ELSE40=(AKNode)match(input,ELSE,FOLLOW_ELSE_in_else_expr650); 
            ELSE40_tree = (AKNode)adaptor.dupNode(ELSE40);

            root_1 = (AKNode)adaptor.becomeRoot(ELSE40_tree, root_1);



            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                _last = (AKNode)input.LT(1);
                pushFollow(FOLLOW_stmt_list_in_else_expr652);
                stmt_list41=stmt_list();

                state._fsp--;

                adaptor.addChild(root_1, stmt_list41.getTree());

                match(input, Token.UP, null); 
            }adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "else_expr"

    public static class cond_expr_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "cond_expr"
    // src/LittleTreeParser.g:142:1: cond_expr : ^( CONDITION cond ) ;
    public final LittleTreeParser.cond_expr_return cond_expr() throws RecognitionException {
        LittleTreeParser.cond_expr_return retval = new LittleTreeParser.cond_expr_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode CONDITION42=null;
        LittleTreeParser.cond_return cond43 = null;


        AKNode CONDITION42_tree=null;

        try {
            // src/LittleTreeParser.g:143:3: ( ^( CONDITION cond ) )
            // src/LittleTreeParser.g:143:5: ^( CONDITION cond )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();_last = (AKNode)input.LT(1);
            CONDITION42=(AKNode)match(input,CONDITION,FOLLOW_CONDITION_in_cond_expr666); 
            CONDITION42_tree = (AKNode)adaptor.dupNode(CONDITION42);

            root_1 = (AKNode)adaptor.becomeRoot(CONDITION42_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_cond_in_cond_expr668);
            cond43=cond();

            state._fsp--;

            adaptor.addChild(root_1, cond43.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "cond_expr"

    public static class cond_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "cond"
    // src/LittleTreeParser.g:145:1: cond : ^( ( '<' | '>' | '=' | '!=' ) expr expr ) ;
    public final LittleTreeParser.cond_return cond() throws RecognitionException {
        LittleTreeParser.cond_return retval = new LittleTreeParser.cond_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode set44=null;
        LittleTreeParser.expr_return expr45 = null;

        LittleTreeParser.expr_return expr46 = null;


        AKNode set44_tree=null;

        try {
            // src/LittleTreeParser.g:146:3: ( ^( ( '<' | '>' | '=' | '!=' ) expr expr ) )
            // src/LittleTreeParser.g:146:5: ^( ( '<' | '>' | '=' | '!=' ) expr expr )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();set44=(AKNode)input.LT(1);
            if ( (input.LA(1)>=50 && input.LA(1)<=53) ) {
                input.consume();

                set44_tree = (AKNode)adaptor.dupNode(set44);

                root_1 = (AKNode)adaptor.becomeRoot(set44_tree, root_1);

                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }



            match(input, Token.DOWN, null); 
            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_expr_in_cond698);
            expr45=expr();

            state._fsp--;

            adaptor.addChild(root_1, expr45.getTree());
            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_expr_in_cond700);
            expr46=expr();

            state._fsp--;

            adaptor.addChild(root_1, expr46.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "cond"

    public static class do_stmt_return extends TreeRuleReturnScope {
        AKNode tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "do_stmt"
    // src/LittleTreeParser.g:149:1: do_stmt : ^( DOWHILE cond_expr stmt_list ) ;
    public final LittleTreeParser.do_stmt_return do_stmt() throws RecognitionException {
        LittleTreeParser.do_stmt_return retval = new LittleTreeParser.do_stmt_return();
        retval.start = input.LT(1);

        AKNode root_0 = null;

        AKNode _first_0 = null;
        AKNode _last = null;

        AKNode DOWHILE47=null;
        LittleTreeParser.cond_expr_return cond_expr48 = null;

        LittleTreeParser.stmt_list_return stmt_list49 = null;


        AKNode DOWHILE47_tree=null;

        try {
            // src/LittleTreeParser.g:150:3: ( ^( DOWHILE cond_expr stmt_list ) )
            // src/LittleTreeParser.g:150:5: ^( DOWHILE cond_expr stmt_list )
            {
            root_0 = (AKNode)adaptor.nil();

            _last = (AKNode)input.LT(1);
            {
            AKNode _save_last_1 = _last;
            AKNode _first_1 = null;
            AKNode root_1 = (AKNode)adaptor.nil();_last = (AKNode)input.LT(1);
            DOWHILE47=(AKNode)match(input,DOWHILE,FOLLOW_DOWHILE_in_do_stmt715); 
            DOWHILE47_tree = (AKNode)adaptor.dupNode(DOWHILE47);

            root_1 = (AKNode)adaptor.becomeRoot(DOWHILE47_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_cond_expr_in_do_stmt717);
            cond_expr48=cond_expr();

            state._fsp--;

            adaptor.addChild(root_1, cond_expr48.getTree());
            _last = (AKNode)input.LT(1);
            pushFollow(FOLLOW_stmt_list_in_do_stmt719);
            stmt_list49=stmt_list();

            state._fsp--;

            adaptor.addChild(root_1, stmt_list49.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


            }

            retval.tree = (AKNode)adaptor.rulePostProcessing(root_0);

        }
             finally {
        }
        return retval;
    }
    // $ANTLR end "do_stmt"

    // Delegated rules


 

    public static final BitSet FOLLOW_PROG_HDR_in_program70 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_program76 = new BitSet(new long[]{0x0000000020000050L});
    public static final BitSet FOLLOW_pgm_body_in_program78 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_decl_in_pgm_body97 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_func_declarations_in_pgm_body99 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_decl_list_in_decl115 = new BitSet(new long[]{0x0000000020000012L});
    public static final BitSet FOLLOW_string_decl_list_in_decl119 = new BitSet(new long[]{0x0000000020000012L});
    public static final BitSet FOLLOW_string_decl_in_string_decl_list139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_string_decl156 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_string_decl160 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_STRINGLITERAL_in_string_decl164 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_var_decl_in_var_decl_list189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECL_in_var_decl211 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_set_in_var_decl215 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_var_decl225 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_func_decl_in_func_declarations248 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_FUNC_HDR_in_func_decl267 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_func_decl271 = new BitSet(new long[]{0x0000000020029E30L});
    public static final BitSet FOLLOW_param_decl_list_in_func_decl275 = new BitSet(new long[]{0x0000000020029E30L});
    public static final BitSet FOLLOW_func_body_in_func_decl278 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PARAMS_in_param_decl_list310 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_param_decl_in_param_decl_list312 = new BitSet(new long[]{0x0000000600000008L});
    public static final BitSet FOLLOW_set_in_param_decl329 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_param_decl339 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_decl_in_func_body364 = new BitSet(new long[]{0x0000000000021E20L});
    public static final BitSet FOLLOW_stmt_list_in_func_body366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNC_CALL_in_call_expr380 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_call_expr382 = new BitSet(new long[]{0x00003C0000350008L});
    public static final BitSet FOLLOW_expr_in_call_expr384 = new BitSet(new long[]{0x00003C0000350008L});
    public static final BitSet FOLLOW_stmt_in_stmt_list402 = new BitSet(new long[]{0x0000000000021E22L});
    public static final BitSet FOLLOW_assign_stmt_in_stmt416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_write_stmt_in_stmt420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do_stmt_in_stmt424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_read_stmt_in_stmt428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_stmt_in_stmt432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_stmt_in_stmt436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_assign_stmt457 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_assign_stmt461 = new BitSet(new long[]{0x00003C0000350000L});
    public static final BitSet FOLLOW_expr_in_assign_stmt463 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_READ_in_read_stmt482 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_read_stmt484 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_WRITE_in_write_stmt499 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_write_stmt503 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RETURN_in_return_stmt518 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_return_stmt520 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_expr542 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr558 = new BitSet(new long[]{0x00003C0000350000L});
    public static final BitSet FOLLOW_expr_in_expr560 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IDENTIFIER_in_expr570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTLITERAL_in_expr580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOATLITERAL_in_expr590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_call_expr_in_expr597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_stmt629 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_cond_in_if_stmt631 = new BitSet(new long[]{0x0000000000025E28L});
    public static final BitSet FOLLOW_stmt_list_in_if_stmt633 = new BitSet(new long[]{0x0000000000004008L});
    public static final BitSet FOLLOW_else_expr_in_if_stmt635 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ELSE_in_else_expr650 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stmt_list_in_else_expr652 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONDITION_in_cond_expr666 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_cond_in_cond_expr668 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_cond682 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_cond698 = new BitSet(new long[]{0x00003C0000350000L});
    public static final BitSet FOLLOW_expr_in_cond700 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DOWHILE_in_do_stmt715 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_cond_expr_in_do_stmt717 = new BitSet(new long[]{0x0000000000021E28L});
    public static final BitSet FOLLOW_stmt_list_in_do_stmt719 = new BitSet(new long[]{0x0000000000000008L});

}