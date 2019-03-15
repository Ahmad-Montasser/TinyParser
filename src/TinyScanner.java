import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;

enum States {start,inNum,inId,inAssign,inComment,done,error}

public class TinyScanner {
    public static String wholeFile;
    public static int pointer =0;
    public static States state =States.start ;
    String currentTokenValue="";
    String currentTokenType;
    Token t;
    char c;

    public TinyScanner() {
        File f = new File("tiny_sample_code.txt");
        try {
            FileInputStream fis =new FileInputStream(f);
            PrintStream fOut = new PrintStream("scanner_output.txt");
            System.setOut(fOut);
            byte[] data =new byte[(int)f.length()];
            fis.read(data);
            fis.close();
            wholeFile=new String(data,"UTF-8");
            c=wholeFile.charAt(pointer);
            String temp="";

            for (int i=0;i<wholeFile.length();i++){
            char d =wholeFile.charAt(i);
                if (isSpecialCharacter(d))
                    if(wholeFile.charAt(i-1) != ':' && d != '=' )
                        temp+=" ";
                temp+=d;

            }
            wholeFile=temp+" ";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTokens() {
    for (int i=pointer;i<=wholeFile.length();i++){
        if(i==wholeFile.length())
            c=' ';
        else
            c=wholeFile.charAt(i);
        setState();
        switch (state){
            case start:
                continue;
            case inAssign:
                currentTokenValue += c;
                currentTokenType=state.toString();
                continue;
            case inId:
                currentTokenValue += c;
                currentTokenType=state.toString();
                continue;
            case inNum:
                currentTokenValue += c;
                currentTokenType=state.toString();
                continue;
            case error:
                System.out.println("Invalid Token");
                state=States.start;
                break;
            case done:
                //To set Special Characters
                currentTokenValue+=c;
                 currentTokenValue = currentTokenValue.trim();
                if (isSpecialCharacter(c)) {
                    currentTokenType = "Special Character";
                }
                //To know reserved words
                if(isReservedWord(currentTokenValue)&&currentTokenType==States.inId.toString())
                    currentTokenType="Reserved Word";
                t=new Token(currentTokenType,currentTokenValue);
                System.out.println(t);
                currentTokenValue="";
                currentTokenType="";
                state = States.start;
                break;

        }
    }

    }

    private void setState(){
            if(state.equals(States.start)) {
                if (Character.isWhitespace(c)) {
                    state = States.start;
                } else if (c == '{') {
                    state = States.inComment;
                } else if (Character.isDigit(c)) {
                    state = States.inNum;
                } else if (Character.isLetter(c)) {
                    state = States.inId;
                } else if (c == ':') {
                    state = States.inAssign;
                }else if (isSpecialCharacter(c)) {
                    state = States.done;
                }
            }
            else if(state.equals(States.inComment)) {
                if (c=='}') {
                    state = States.start;
                } else {
                    state = States.inComment;
                }
            }
            else if(state.equals(States.inId)) {
                if (Character.isLetter(c)) {
                    state = States.inId;
                } else {
                    state = States.done;
                }
            }else if(state.equals(States.inNum)) {
                if (Character.isDigit(c)) {
                    state = States.inNum;
                } else {
                    state = States.done;
                }
            }
            else if(state.equals(States.inAssign)) {
                if (c=='=') {
                    state = States.done;
                } else {
                    state = States.error;
                }
            }else if(state.equals(States.done)) {
                state=States.start;
            }
    }

    private boolean isSpecialCharacter(char c){
        if (c == ';'||c == '*'||c == '='||c == '-'||c == '+'||c == ')'||c == '('||c == '<'||c == '>') {
            return true;
        }
        return false;

    }

    private boolean isReservedWord(String s){
        if      (s.equals("write")||s.equals("if")||s.equals("until") || s.equals("read")
                ||s.equals("end")||s.equals("else") ||s.equals("then") ||s.equals("repeat"))
            return true;
        return false;
    }

}


