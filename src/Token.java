public class Token {
   private String type;
    private String value;
    private boolean valid=true;


    public Token(String value) {
        this.value = value;
    }

    public Token() {
    }

    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public boolean equals(Token t) {
        if(t.getValue().equalsIgnoreCase(this.getValue()))
            return true;
        return  false;
    }

    @Override
    public String toString() {
        if(type==States.inAssign.toString())
            type="Special Character";
        if(valid){
            return value +" : " + type;
        }
        return "invalid";
    }
}
