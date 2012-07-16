package omnicentre.eworky.API;

public class NoSuccessException extends Exception {

    private static final long serialVersionUID = 3233459810356257436L;
    private String error;
    
    public NoSuccessException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
