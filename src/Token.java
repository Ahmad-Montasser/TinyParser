public class Token {
    String type;
    String value;
    boolean valid=true;

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


    @Override
    public String toString() {
        if(valid){
            return value +" : " + type;
        }
        return "invalid";
    }
}
