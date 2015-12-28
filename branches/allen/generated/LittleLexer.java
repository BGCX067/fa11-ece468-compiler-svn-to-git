// $ANTLR 3.3 Nov 30, 2010 12:50:56 src/Little.g 2011-09-20 02:11:44

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class LittleLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
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
    public static final int IDENTIFIER=4;
    public static final int STRINGLITERAL=5;
    public static final int INTLITERAL=6;
    public static final int FLOATLITERAL=7;
    public static final int KEYWORD=8;
    public static final int COMMENT=9;
    public static final int WS=10;

    // delegates
    // delegators

    public LittleLexer() {;} 
    public LittleLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public LittleLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "src/Little.g"; }

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:3:7: ( 'PROGRAM' )
            // src/Little.g:3:9: 'PROGRAM'
            {
            match("PROGRAM"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:4:7: ( 'BEGIN' )
            // src/Little.g:4:9: 'BEGIN'
            {
            match("BEGIN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:5:7: ( 'END' )
            // src/Little.g:5:9: 'END'
            {
            match("END"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:6:7: ( 'STRING' )
            // src/Little.g:6:9: 'STRING'
            {
            match("STRING"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:7:7: ( ':=' )
            // src/Little.g:7:9: ':='
            {
            match(":="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:8:7: ( ';' )
            // src/Little.g:8:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:9:7: ( 'FLOAT' )
            // src/Little.g:9:9: 'FLOAT'
            {
            match("FLOAT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:10:7: ( 'INT' )
            // src/Little.g:10:9: 'INT'
            {
            match("INT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:11:7: ( 'VOID' )
            // src/Little.g:11:9: 'VOID'
            {
            match("VOID"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:12:7: ( ',' )
            // src/Little.g:12:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:13:7: ( 'FUNCTION' )
            // src/Little.g:13:9: 'FUNCTION'
            {
            match("FUNCTION"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:14:7: ( '(' )
            // src/Little.g:14:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:15:7: ( ')' )
            // src/Little.g:15:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:16:7: ( 'READ' )
            // src/Little.g:16:9: 'READ'
            {
            match("READ"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:17:7: ( 'WRITE' )
            // src/Little.g:17:9: 'WRITE'
            {
            match("WRITE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:18:7: ( 'RETURN' )
            // src/Little.g:18:9: 'RETURN'
            {
            match("RETURN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:19:7: ( '+' )
            // src/Little.g:19:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:20:7: ( '-' )
            // src/Little.g:20:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:21:7: ( '*' )
            // src/Little.g:21:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:22:7: ( '/' )
            // src/Little.g:22:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:23:7: ( 'IF' )
            // src/Little.g:23:9: 'IF'
            {
            match("IF"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:24:7: ( 'THEN' )
            // src/Little.g:24:9: 'THEN'
            {
            match("THEN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:25:7: ( 'ELSE' )
            // src/Little.g:25:9: 'ELSE'
            {
            match("ELSE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:26:7: ( 'ENDIF' )
            // src/Little.g:26:9: 'ENDIF'
            {
            match("ENDIF"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:27:7: ( '<' )
            // src/Little.g:27:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:28:7: ( '>' )
            // src/Little.g:28:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:29:7: ( '=' )
            // src/Little.g:29:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:30:7: ( '!=' )
            // src/Little.g:30:9: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:31:7: ( 'DO' )
            // src/Little.g:31:9: 'DO'
            {
            match("DO"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:32:7: ( 'WHILE' )
            // src/Little.g:32:9: 'WHILE'
            {
            match("WHILE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "KEYWORD"
    public final void mKEYWORD() throws RecognitionException {
        try {
            int _type = KEYWORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:162:2: ( 'PROGRAM' | 'BEGIN' | 'END' | 'PROTO' | 'FUNCTION' | 'READ' | 'WRITE' | 'IF' | 'THEN' | 'ELSE' | 'ENDIF' | 'RETURN' | 'CASE' | 'ENDCASE' | 'BREAK' | 'DEFAULT' | 'DO' | 'WHILE' | 'FLOAT' | 'INT' | 'VOID' | 'STRING' )
            int alt1=22;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // src/Little.g:162:4: 'PROGRAM'
                    {
                    match("PROGRAM"); 


                    }
                    break;
                case 2 :
                    // src/Little.g:162:14: 'BEGIN'
                    {
                    match("BEGIN"); 


                    }
                    break;
                case 3 :
                    // src/Little.g:162:22: 'END'
                    {
                    match("END"); 


                    }
                    break;
                case 4 :
                    // src/Little.g:162:28: 'PROTO'
                    {
                    match("PROTO"); 


                    }
                    break;
                case 5 :
                    // src/Little.g:162:36: 'FUNCTION'
                    {
                    match("FUNCTION"); 


                    }
                    break;
                case 6 :
                    // src/Little.g:162:47: 'READ'
                    {
                    match("READ"); 


                    }
                    break;
                case 7 :
                    // src/Little.g:162:54: 'WRITE'
                    {
                    match("WRITE"); 


                    }
                    break;
                case 8 :
                    // src/Little.g:163:3: 'IF'
                    {
                    match("IF"); 


                    }
                    break;
                case 9 :
                    // src/Little.g:163:8: 'THEN'
                    {
                    match("THEN"); 


                    }
                    break;
                case 10 :
                    // src/Little.g:163:15: 'ELSE'
                    {
                    match("ELSE"); 


                    }
                    break;
                case 11 :
                    // src/Little.g:163:22: 'ENDIF'
                    {
                    match("ENDIF"); 


                    }
                    break;
                case 12 :
                    // src/Little.g:163:30: 'RETURN'
                    {
                    match("RETURN"); 


                    }
                    break;
                case 13 :
                    // src/Little.g:163:39: 'CASE'
                    {
                    match("CASE"); 


                    }
                    break;
                case 14 :
                    // src/Little.g:163:46: 'ENDCASE'
                    {
                    match("ENDCASE"); 


                    }
                    break;
                case 15 :
                    // src/Little.g:164:3: 'BREAK'
                    {
                    match("BREAK"); 


                    }
                    break;
                case 16 :
                    // src/Little.g:164:11: 'DEFAULT'
                    {
                    match("DEFAULT"); 


                    }
                    break;
                case 17 :
                    // src/Little.g:164:21: 'DO'
                    {
                    match("DO"); 


                    }
                    break;
                case 18 :
                    // src/Little.g:164:26: 'WHILE'
                    {
                    match("WHILE"); 


                    }
                    break;
                case 19 :
                    // src/Little.g:165:3: 'FLOAT'
                    {
                    match("FLOAT"); 


                    }
                    break;
                case 20 :
                    // src/Little.g:165:11: 'INT'
                    {
                    match("INT"); 


                    }
                    break;
                case 21 :
                    // src/Little.g:165:17: 'VOID'
                    {
                    match("VOID"); 


                    }
                    break;
                case 22 :
                    // src/Little.g:165:24: 'STRING'
                    {
                    match("STRING"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:170:2: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
            // src/Little.g:170:4: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // src/Little.g:170:24: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // src/Little.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "STRINGLITERAL"
    public final void mSTRINGLITERAL() throws RecognitionException {
        try {
            int _type = STRINGLITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:174:2: ( '\"' (~ ( '--' | '\"' ) )* '\"' )
            // src/Little.g:174:4: '\"' (~ ( '--' | '\"' ) )* '\"'
            {
            match('\"'); 
            // src/Little.g:174:8: (~ ( '--' | '\"' ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\u0000' && LA3_0<='!')||(LA3_0>='#' && LA3_0<='\uFFFF')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // src/Little.g:174:10: ~ ( '--' | '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRINGLITERAL"

    // $ANTLR start "FLOATLITERAL"
    public final void mFLOATLITERAL() throws RecognitionException {
        try {
            int _type = FLOATLITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:178:2: ( ( '0' .. '9' )? '.' ( '0' .. '9' )+ )
            // src/Little.g:178:4: ( '0' .. '9' )? '.' ( '0' .. '9' )+
            {
            // src/Little.g:178:4: ( '0' .. '9' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // src/Little.g:178:5: '0' .. '9'
                    {
                    matchRange('0','9'); 

                    }
                    break;

            }

            match('.'); 
            // src/Little.g:178:18: ( '0' .. '9' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // src/Little.g:178:19: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FLOATLITERAL"

    // $ANTLR start "INTLITERAL"
    public final void mINTLITERAL() throws RecognitionException {
        try {
            int _type = INTLITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:182:2: ( ( '0' .. '9' )+ )
            // src/Little.g:182:4: ( '0' .. '9' )+
            {
            // src/Little.g:182:4: ( '0' .. '9' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // src/Little.g:182:5: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTLITERAL"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:186:2: ( '--' (~ ( '--' | '\"' | '\\n' ) )* )
            // src/Little.g:186:4: '--' (~ ( '--' | '\"' | '\\n' ) )*
            {
            match("--"); 

            // src/Little.g:186:9: (~ ( '--' | '\"' | '\\n' ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='\u0000' && LA7_0<='\t')||(LA7_0>='\u000B' && LA7_0<='!')||(LA7_0>='#' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // src/Little.g:186:11: ~ ( '--' | '\"' | '\\n' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/Little.g:189:5: ( ( ' ' | '\\r' | '\\n' | '\\t' )+ )
            // src/Little.g:189:7: ( ' ' | '\\r' | '\\n' | '\\t' )+
            {
            // src/Little.g:189:7: ( ' ' | '\\r' | '\\n' | '\\t' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\t' && LA8_0<='\n')||LA8_0=='\r'||LA8_0==' ') ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // src/Little.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // src/Little.g:1:8: ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | KEYWORD | IDENTIFIER | STRINGLITERAL | FLOATLITERAL | INTLITERAL | COMMENT | WS )
        int alt9=37;
        alt9 = dfa9.predict(input);
        switch (alt9) {
            case 1 :
                // src/Little.g:1:10: T__11
                {
                mT__11(); 

                }
                break;
            case 2 :
                // src/Little.g:1:16: T__12
                {
                mT__12(); 

                }
                break;
            case 3 :
                // src/Little.g:1:22: T__13
                {
                mT__13(); 

                }
                break;
            case 4 :
                // src/Little.g:1:28: T__14
                {
                mT__14(); 

                }
                break;
            case 5 :
                // src/Little.g:1:34: T__15
                {
                mT__15(); 

                }
                break;
            case 6 :
                // src/Little.g:1:40: T__16
                {
                mT__16(); 

                }
                break;
            case 7 :
                // src/Little.g:1:46: T__17
                {
                mT__17(); 

                }
                break;
            case 8 :
                // src/Little.g:1:52: T__18
                {
                mT__18(); 

                }
                break;
            case 9 :
                // src/Little.g:1:58: T__19
                {
                mT__19(); 

                }
                break;
            case 10 :
                // src/Little.g:1:64: T__20
                {
                mT__20(); 

                }
                break;
            case 11 :
                // src/Little.g:1:70: T__21
                {
                mT__21(); 

                }
                break;
            case 12 :
                // src/Little.g:1:76: T__22
                {
                mT__22(); 

                }
                break;
            case 13 :
                // src/Little.g:1:82: T__23
                {
                mT__23(); 

                }
                break;
            case 14 :
                // src/Little.g:1:88: T__24
                {
                mT__24(); 

                }
                break;
            case 15 :
                // src/Little.g:1:94: T__25
                {
                mT__25(); 

                }
                break;
            case 16 :
                // src/Little.g:1:100: T__26
                {
                mT__26(); 

                }
                break;
            case 17 :
                // src/Little.g:1:106: T__27
                {
                mT__27(); 

                }
                break;
            case 18 :
                // src/Little.g:1:112: T__28
                {
                mT__28(); 

                }
                break;
            case 19 :
                // src/Little.g:1:118: T__29
                {
                mT__29(); 

                }
                break;
            case 20 :
                // src/Little.g:1:124: T__30
                {
                mT__30(); 

                }
                break;
            case 21 :
                // src/Little.g:1:130: T__31
                {
                mT__31(); 

                }
                break;
            case 22 :
                // src/Little.g:1:136: T__32
                {
                mT__32(); 

                }
                break;
            case 23 :
                // src/Little.g:1:142: T__33
                {
                mT__33(); 

                }
                break;
            case 24 :
                // src/Little.g:1:148: T__34
                {
                mT__34(); 

                }
                break;
            case 25 :
                // src/Little.g:1:154: T__35
                {
                mT__35(); 

                }
                break;
            case 26 :
                // src/Little.g:1:160: T__36
                {
                mT__36(); 

                }
                break;
            case 27 :
                // src/Little.g:1:166: T__37
                {
                mT__37(); 

                }
                break;
            case 28 :
                // src/Little.g:1:172: T__38
                {
                mT__38(); 

                }
                break;
            case 29 :
                // src/Little.g:1:178: T__39
                {
                mT__39(); 

                }
                break;
            case 30 :
                // src/Little.g:1:184: T__40
                {
                mT__40(); 

                }
                break;
            case 31 :
                // src/Little.g:1:190: KEYWORD
                {
                mKEYWORD(); 

                }
                break;
            case 32 :
                // src/Little.g:1:198: IDENTIFIER
                {
                mIDENTIFIER(); 

                }
                break;
            case 33 :
                // src/Little.g:1:209: STRINGLITERAL
                {
                mSTRINGLITERAL(); 

                }
                break;
            case 34 :
                // src/Little.g:1:223: FLOATLITERAL
                {
                mFLOATLITERAL(); 

                }
                break;
            case 35 :
                // src/Little.g:1:236: INTLITERAL
                {
                mINTLITERAL(); 

                }
                break;
            case 36 :
                // src/Little.g:1:247: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 37 :
                // src/Little.g:1:255: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA9 dfa9 = new DFA9(this);
    static final String DFA1_eotS =
        "\34\uffff\1\43\7\uffff";
    static final String DFA1_eofS =
        "\44\uffff";
    static final String DFA1_minS =
        "\1\102\1\122\1\105\2\114\1\105\1\110\1\106\2\uffff\1\105\2\uffff"+
        "\1\117\2\uffff\1\104\3\uffff\1\101\6\uffff\1\107\1\103\7\uffff";
    static final String DFA1_maxS =
        "\1\127\2\122\1\116\1\125\1\105\1\122\1\116\2\uffff\1\117\2\uffff"+
        "\1\117\2\uffff\1\104\3\uffff\1\124\6\uffff\1\124\1\111\7\uffff";
    static final String DFA1_acceptS =
        "\10\uffff\1\11\1\15\1\uffff\1\25\1\26\1\uffff\1\2\1\17\1\uffff\1"+
        "\12\1\5\1\23\1\uffff\1\7\1\22\1\10\1\24\1\20\1\21\2\uffff\1\6\1"+
        "\14\1\1\1\4\1\13\1\16\1\3";
    static final String DFA1_specialS =
        "\44\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\2\1\11\1\12\1\3\1\4\2\uffff\1\7\6\uffff\1\1\1\uffff\1\5\1"+
            "\14\1\10\1\uffff\1\13\1\6",
            "\1\15",
            "\1\16\14\uffff\1\17",
            "\1\21\1\uffff\1\20",
            "\1\23\10\uffff\1\22",
            "\1\24",
            "\1\26\11\uffff\1\25",
            "\1\27\7\uffff\1\30",
            "",
            "",
            "\1\31\11\uffff\1\32",
            "",
            "",
            "\1\33",
            "",
            "",
            "\1\34",
            "",
            "",
            "",
            "\1\35\22\uffff\1\36",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\37\14\uffff\1\40",
            "\1\42\5\uffff\1\41",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "160:1: KEYWORD : ( 'PROGRAM' | 'BEGIN' | 'END' | 'PROTO' | 'FUNCTION' | 'READ' | 'WRITE' | 'IF' | 'THEN' | 'ELSE' | 'ENDIF' | 'RETURN' | 'CASE' | 'ENDCASE' | 'BREAK' | 'DEFAULT' | 'DO' | 'WHILE' | 'FLOAT' | 'INT' | 'VOID' | 'STRING' );";
        }
    }
    static final String DFA9_eotS =
        "\1\uffff\4\32\2\uffff\3\32\3\uffff\2\32\1\uffff\1\56\2\uffff\1\32"+
        "\4\uffff\2\32\2\uffff\1\63\2\uffff\11\32\1\75\4\32\2\uffff\1\32"+
        "\1\104\2\32\1\uffff\3\32\1\115\4\32\1\122\1\uffff\6\32\1\uffff\10"+
        "\32\1\uffff\1\141\3\32\1\uffff\1\145\1\146\3\32\1\152\1\32\1\154"+
        "\1\32\1\154\1\156\1\154\1\157\1\32\1\uffff\1\32\1\162\1\32\2\uffff"+
        "\1\32\1\165\1\166\1\uffff\1\32\1\uffff\1\32\2\uffff\1\32\1\172\1"+
        "\uffff\1\32\1\174\2\uffff\1\32\1\176\1\154\1\uffff\1\32\1\uffff"+
        "\1\154\1\uffff\1\u0080\1\uffff";
    static final String DFA9_eofS =
        "\u0081\uffff";
    static final String DFA9_minS =
        "\1\11\1\122\1\105\1\114\1\124\2\uffff\1\114\1\106\1\117\3\uffff"+
        "\1\105\1\110\1\uffff\1\55\2\uffff\1\110\4\uffff\1\105\1\101\2\uffff"+
        "\1\56\2\uffff\1\117\1\107\1\105\1\104\1\123\1\122\1\117\1\116\1"+
        "\124\1\60\1\111\1\101\2\111\2\uffff\1\105\1\60\1\106\1\123\1\uffff"+
        "\1\107\1\111\1\101\1\60\1\105\1\111\1\101\1\103\1\60\1\uffff\2\104"+
        "\1\125\1\124\1\114\1\116\1\uffff\1\101\1\105\1\122\1\117\1\116\1"+
        "\113\1\106\1\101\1\uffff\1\60\1\116\2\124\1\uffff\2\60\1\122\2\105"+
        "\1\60\1\125\1\60\1\101\4\60\1\123\1\uffff\1\107\1\60\1\111\2\uffff"+
        "\1\116\2\60\1\uffff\1\114\1\uffff\1\115\2\uffff\1\105\1\60\1\uffff"+
        "\1\117\1\60\2\uffff\1\124\2\60\1\uffff\1\116\1\uffff\1\60\1\uffff"+
        "\1\60\1\uffff";
    static final String DFA9_maxS =
        "\1\172\2\122\1\116\1\124\2\uffff\1\125\1\116\1\117\3\uffff\1\105"+
        "\1\122\1\uffff\1\55\2\uffff\1\110\4\uffff\1\117\1\101\2\uffff\1"+
        "\56\2\uffff\1\117\1\107\1\105\1\104\1\123\1\122\1\117\1\116\1\124"+
        "\1\172\1\111\1\124\2\111\2\uffff\1\105\1\172\1\106\1\123\1\uffff"+
        "\1\124\1\111\1\101\1\172\1\105\1\111\1\101\1\103\1\172\1\uffff\2"+
        "\104\1\125\1\124\1\114\1\116\1\uffff\1\101\1\105\1\122\1\117\1\116"+
        "\1\113\1\106\1\101\1\uffff\1\172\1\116\2\124\1\uffff\2\172\1\122"+
        "\2\105\1\172\1\125\1\172\1\101\4\172\1\123\1\uffff\1\107\1\172\1"+
        "\111\2\uffff\1\116\2\172\1\uffff\1\114\1\uffff\1\115\2\uffff\1\105"+
        "\1\172\1\uffff\1\117\1\172\2\uffff\1\124\2\172\1\uffff\1\116\1\uffff"+
        "\1\172\1\uffff\1\172\1\uffff";
    static final String DFA9_acceptS =
        "\5\uffff\1\5\1\6\3\uffff\1\12\1\14\1\15\2\uffff\1\21\1\uffff\1\23"+
        "\1\24\1\uffff\1\31\1\32\1\33\1\34\2\uffff\1\40\1\41\1\uffff\1\42"+
        "\1\45\16\uffff\1\44\1\22\4\uffff\1\43\11\uffff\1\25\6\uffff\1\35"+
        "\10\uffff\1\3\4\uffff\1\10\16\uffff\1\27\3\uffff\1\11\1\16\3\uffff"+
        "\1\26\1\uffff\1\37\1\uffff\1\2\1\30\2\uffff\1\7\2\uffff\1\17\1\36"+
        "\3\uffff\1\4\1\uffff\1\20\1\uffff\1\1\1\uffff\1\13";
    static final String DFA9_specialS =
        "\u0081\uffff}>";
    static final String[] DFA9_transitionS = {
            "\2\36\2\uffff\1\36\22\uffff\1\36\1\27\1\33\5\uffff\1\13\1\14"+
            "\1\21\1\17\1\12\1\20\1\35\1\22\12\34\1\5\1\6\1\24\1\26\1\25"+
            "\2\uffff\1\32\1\2\1\31\1\30\1\3\1\7\2\32\1\10\6\32\1\1\1\32"+
            "\1\15\1\4\1\23\1\32\1\11\1\16\3\32\6\uffff\32\32",
            "\1\37",
            "\1\40\14\uffff\1\41",
            "\1\43\1\uffff\1\42",
            "\1\44",
            "",
            "",
            "\1\45\10\uffff\1\46",
            "\1\50\7\uffff\1\47",
            "\1\51",
            "",
            "",
            "",
            "\1\52",
            "\1\54\11\uffff\1\53",
            "",
            "\1\55",
            "",
            "",
            "\1\57",
            "",
            "",
            "",
            "",
            "\1\61\11\uffff\1\60",
            "\1\62",
            "",
            "",
            "\1\35",
            "",
            "",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "\1\76",
            "\1\77\22\uffff\1\100",
            "\1\101",
            "\1\102",
            "",
            "",
            "\1\103",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "\1\105",
            "\1\106",
            "",
            "\1\107\14\uffff\1\110",
            "\1\111",
            "\1\112",
            "\12\32\7\uffff\2\32\1\114\5\32\1\113\21\32\6\uffff\32\32",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "\1\142",
            "\1\143",
            "\1\144",
            "",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "\1\147",
            "\1\150",
            "\1\151",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "\1\153",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "\1\155",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "\1\160",
            "",
            "\1\161",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "\1\163",
            "",
            "",
            "\1\164",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "",
            "\1\167",
            "",
            "\1\170",
            "",
            "",
            "\1\171",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "",
            "\1\173",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "",
            "",
            "\1\175",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "",
            "\1\177",
            "",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            "",
            "\12\32\7\uffff\32\32\6\uffff\32\32",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | KEYWORD | IDENTIFIER | STRINGLITERAL | FLOATLITERAL | INTLITERAL | COMMENT | WS );";
        }
    }
 

}