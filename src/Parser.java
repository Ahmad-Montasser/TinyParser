import java.util.ArrayList;

public class Parser {
    public static int index=0;
    private ArrayList<Token> tokenList ;
    public Parser(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
        program();
    }

    private void matchValue(Token expectedToken){
        Token nextToken = tokenList.get(index);
            if(nextToken.equals(expectedToken)){
           //     System.out.println(nextToken);
                index++;
            }

    }
    private void matchType(Token expectedToken) {
        Token nextToken = tokenList.get(index);
        if (expectedToken.getType().equalsIgnoreCase(expectedToken.getType())){
        //  System.out.println(nextToken);
            index++;
        }
    }



    private void program() {
        System.out.println("program is Found");
        stmtSequence();
    }

    private void stmtSequence() {
        System.out.println("stmt-seq is Found");
        statement();
        while(tokenList.size()>index&&tokenList.get(index).getValue().equalsIgnoreCase(";")){
            matchValue(new Token(";"));
            //System.out.println("Statement End");
            if(tokenList.get(index).getValue().equalsIgnoreCase("until")||
                    tokenList.get(index).getValue().equalsIgnoreCase("end")
                    )
                return;
            statement();
        }

    }

    private void statement() {
        if(tokenList.get(index).getValue().equals("read"))
            readStmt();
        else if(tokenList.get(index).getValue().equals("write"))
            writeStmt();
        else if(tokenList.get(index).getValue().equals("repeat"))
            repeatStmt();
        else if(tokenList.get(index).getValue().equals("if"))
            ifStmt();
        else if(tokenList.get(index).getType().equals("inId"))
            assignStmt();
        else
            ;//TODO Handle Errors

    }

    private void ifStmt(){
        System.out.println("ifStmt is Found");
        matchValue(new Token("if"));
        matchValue(new Token("("));
        exp();
        matchValue(new Token(")"));
        matchValue(new Token("then"));
        stmtSequence();
        if(tokenList.get(index).getValue().equals("else")){
            matchValue(new Token("else"));
            stmtSequence();
        }
        matchValue(new Token("end"));
    }

    private void repeatStmt(){
        System.out.println("repeat is Found");
        matchValue(new Token("repeat"));
        stmtSequence();
        matchValue(new Token("until"));
        exp();
    }

    private void assignStmt(){
        System.out.println("assign is Found");
        matchType(new Token("inId",""));
        matchValue(new Token(":="));
        exp();
    }

    private void readStmt(){
        System.out.println("read is Found");
        matchValue(new Token("read"));
        matchType(new Token("inId",""));

    }

    private void writeStmt(){
        System.out.println("write is Found");
        matchValue(new Token("write"));
        exp();

    }

    private void exp() {
        System.out.println("exp is Found");
        simpleExp();
        while(tokenList.get(index).getValue().equals("<")||
                tokenList.get(index).getValue().equals("=")){
            compOp();
            simpleExp();
        }

    }

    private void compOp() {
        if(tokenList.get(index).getValue().equals("="))
            matchValue(new Token("="));
        else if(tokenList.get(index).getValue().equals("<"))
            matchValue(new Token("<"));
        else
            //TODO Handle Errors
            return;

    }

    private void simpleExp() {
        term();
        while(tokenList.get(index).getValue().equals("+")||
                tokenList.get(index).getValue().equals("-")){
            addOp();
            term();
        }

    }

    private void addOp() {
        if(tokenList.get(index).getValue().equals("+"))
            matchValue(new Token("+"));
        else if(tokenList.get(index).getValue().equals("-"))
            matchValue(new Token("-"));
        else
            //TODO Handle Errors
            return;
    }

    private void term() {
        factor();
        while(tokenList.get(index).getValue().equals("/")||
                tokenList.get(index).getValue().equals("*")){
            mulOp();
            factor();
        }
    }

    private void mulOp() {
        if(tokenList.get(index).getValue().equals("*"))
            matchValue(new Token("*"));
        else if(tokenList.get(index).getValue().equals("/"))
            matchValue(new Token("/"));
        else
            //TODO Handle Errors
            return;
    }

    private void factor(){
        if(tokenList.get(index).getValue().equals("(")){
            matchValue(tokenList.get(index));
            exp();
            matchValue(new Token(")"));
        }
        else if(tokenList.get(index).getType().equals("inNum")){
            matchType(new Token("inNum",""));
        }
        else if(tokenList.get(index).getType().equals("inId")){
            matchType(new Token("inId",""));
        }
        else
            //TODO handle errors
            System.out.println("no match");
    }

}
