import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Parser {
    public static int index=0;
    private ArrayList<Token> tokenList ;
    public Parser(ArrayList<Token> tokenList) {
        try {
            PrintStream fOut = new PrintStream("parser_output.txt");
            System.setOut(fOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.tokenList = tokenList;
        program();
    }

    private void matchValue(Token expectedToken)throws TypeOrValueException {
        Token nextToken = tokenList.get(index);
            if(nextToken.equals(expectedToken)){
                //System.out.println(nextToken);
                index++;
            }
            else
            { throw new TypeOrValueException(expectedToken.getValue());
            }

    }
    private void matchType (Token expectedToken)throws TypeOrValueException {
        Token nextToken = tokenList.get(index);
        if (expectedToken.getType().equalsIgnoreCase(nextToken.getType())){
            //System.out.println(nextToken);
            index++;
        }
        else{
            throw new TypeOrValueException(expectedToken.getType());
        }
    }



    private void program() {
        System.out.println("program is Found");
        stmtSequence();
    }

    private void stmtSequence() {
        //System.out.println("stmt-seq is Found");
        statement();
        while(tokenList.size()>index&&tokenList.get(index).getValue().equalsIgnoreCase(";")){
            try {
                matchValue(new Token(";"));
            } catch (TypeOrValueException e) {
                e.printExpectedToken();
            }
            //System.out.println("Statement End");
            if(index<tokenList.size()){
                if(tokenList.get(index).getValue().equalsIgnoreCase("until")||
                     tokenList.get(index).getValue().equalsIgnoreCase("end")
                        )
                    return;

            statement();
            }
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
        try {
            matchValue(new Token("if"));
            matchValue(new Token("("));
            exp();
            matchValue(new Token(")"));
            matchValue(new Token("then"));
            stmtSequence();
            if(tokenList.get(index).getValue().equals("else")){
                matchValue(new Token("else"));
                stmtSequence();
                matchValue(new Token("end"));
            }
        } catch (TypeOrValueException e) {
            e.printExpectedToken();
        }
    }

    private void repeatStmt(){
        try {
            System.out.println("repeat is Found");
            matchValue(new Token("repeat"));
            stmtSequence();
            matchValue(new Token("until"));
            exp();
        } catch (TypeOrValueException e) {
            e.printStackTrace();
        }
    }

    private void assignStmt(){
        try {
            System.out.println("assign is Found");
            matchType(new Token("inId",""));
            matchValue(new Token(":="));
            exp();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private void readStmt(){
        try {
            System.out.println("read is Found");
            matchValue(new Token("read"));
            matchType(new Token("inId",""));
        } catch (TypeOrValueException e) {
            e.printExpectedToken();
        }

    }

    private void writeStmt(){
        try {
            System.out.println("write is Found");
            matchValue(new Token("write"));
            exp();
        } catch (TypeOrValueException e) {
            e.printExpectedToken();
        }

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
        if(tokenList.get(index).getValue().equals("=")) {
            try {
                matchValue(new Token("="));
            } catch (TypeOrValueException e) {
                e.printExpectedToken();
            }
        }
        else if(tokenList.get(index).getValue().equals("<")) {
            try {
                matchValue(new Token("<"));
            } catch (TypeOrValueException e) {
                e.printExpectedToken();
            }
        }
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
            try {
                if(tokenList.get(index).getValue().equals("+"))
                matchValue(new Token("+"));
                else if(tokenList.get(index).getValue().equals("-"))
                    matchValue(new Token("-"));
                else
                    matchValue(new Token("addOp"));
            } catch (TypeOrValueException e) {
                e.printExpectedToken();
            }
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
       try {
           if(tokenList.get(index).getValue().equals("*"))
               matchValue(new Token("*"));
           else if(tokenList.get(index).getValue().equals("/"))
               matchValue(new Token("/"));
           else
               matchValue(new Token("mulOp"));
       }catch (TypeOrValueException e){
           e.printExpectedToken();
       }

    }

    private void factor(){
       try {
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
               matchValue(new Token("( or Number or identifier"));
       }catch (TypeOrValueException e){
           e.printExpectedToken();
       }
       }

}
