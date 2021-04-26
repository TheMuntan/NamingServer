package dsbt.ResponseBody;

public class ReturnMessage {
    private String message;

    public ReturnMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
