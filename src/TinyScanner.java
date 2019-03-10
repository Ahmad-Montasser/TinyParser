import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TinyScanner {
    public static String wholeFile;
    public static int pointer =0;
    Scanner sc = null;
    int state;
    String currentTokenValue="";
    String currentTokenType;
    Token t;

    public TinyScanner() {
        File f = new File("Code.txt");
        try {
            FileInputStream fis =new FileInputStream(f);
            byte[] data =new byte[(int)f.length()];
            fis.read(data);
            fis.close();
            wholeFile=new String(data,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Token getNextToken() {
        char c=wholeFile.charAt(pointer);
          while(Character.isWhitespace(c)){
              pointer++;
              c=wholeFile.charAt(pointer);
          }
          if (c=='{'){
              currentTokenType="COMMENT";
              pointer++;
              c=wholeFile.charAt(pointer);
              while(c != '}'){
                currentTokenValue += c;
                pointer++;
                c=wholeFile.charAt(pointer);
            }
            t=new Token(currentTokenType,currentTokenValue);
            pointer++;
              c=wholeFile.charAt(pointer);
              currentTokenValue="";
          }
          else if(Character.isDigit(c)){
              currentTokenType="INNUM";
              while(Character.isDigit(c)){
                  currentTokenValue += c;
                  pointer++;
                  c=wholeFile.charAt(pointer);
              }
              t=new Token(currentTokenType,currentTokenValue);
              pointer++;
              currentTokenValue="";
          }
          else if(c == ':'){
              currentTokenType="INASSIGN";
              currentTokenValue += c;
              pointer++;
              c=wholeFile.charAt(pointer);
            if(c == '='){
                currentTokenValue += c;
                t=new Token(currentTokenType,currentTokenValue);
                pointer++;
                currentTokenValue="";
            }
          }
        else if(c =='+'||c=='*'||c=='='|| c=='-'){
            currentTokenType="SPECIALCHAR";
                currentTokenValue += c;
                pointer++;
                c=wholeFile.charAt(pointer);
            t=new Token(currentTokenType,currentTokenValue);
            pointer++;
            currentTokenValue="";
        }
        else if(Character.isLetter(c)){
            currentTokenType="INID";
            while(Character.isLetter(c)){
                currentTokenValue += c;
                switch (c){
                    case 'i':
                        pointer++;
                        c=wholeFile.charAt(pointer);
                        if(c=='f') {
                            currentTokenValue += c;

                        }

                }
                pointer++;
                c=wholeFile.charAt(pointer);
            }
            t=new Token(currentTokenType,currentTokenValue);
            pointer++;
            currentTokenValue="";
        }


        return t;
        }

}
