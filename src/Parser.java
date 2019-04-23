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
                System.out.println(nextToken);
                index++;
            }

    }
    private void matchType(Token expectedToken) {
        Token nextToken = tokenList.get(index);
        if (expectedToken.getType().equalsIgnoreCase(expectedToken.getType())){
            System.out.println(nextToken);
            index++;
        }

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
            return;
    }
    private void ifStmt(){
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
        matchValue(new Token("repeat"));
        stmtSequence();
        matchValue(new Token("until"));
        exp();
    }
    private void assignStmt(){
        matchType(new Token("inId",""));
        matchValue(new Token(":="));
        exp();
    }
    private void readStmt(){
        matchValue(new Token("read"));
        matchType(new Token("inId",""));

    }
    private void writeStmt(){
        matchValue(new Token("write"));
        exp();
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
    private void program() {
        stmtSequence();
    }
    private void stmtSequence() {
        statement();
        matchValue(new Token(";"));
        System.out.println("Statment End");
        while(tokenList.size()>index){
            matchValue(new Token(";"));
            statement();
            System.out.println("Statment End");
        }

    }

    private void exp() {
        simpleExp();
        while(tokenList.get(index).getValue().equals("<")||
                tokenList.get(index).getValue().equals("=")){
            compOp();
            matchValue(tokenList.get(index));
            simpleExp();
        }
    }

    private void simpleExp() {
        term();
        while(tokenList.get(index).getValue().equals("+")||
                tokenList.get(index).getValue().equals("-")){
            addOp();
            matchValue(tokenList.get(index));
            term();
        }
    }

    private void term() {
        factor();
        while(tokenList.get(index).getValue().equals("/")||
                tokenList.get(index).getValue().equals("*")){
            mulOp();
            matchValue(tokenList.get(index));
            factor();
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
    private void mulOp() {
        if(tokenList.get(index).getValue().equals("*"))
            matchValue(new Token("*"));
        else if(tokenList.get(index).getValue().equals("/"))
            matchValue(new Token("/"));
        else
            //TODO Handle Errors
            return;
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
}
