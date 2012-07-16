package omnicentre.eworky.API;

/**
 * This class is used to parse the JSON on the "meta" filed of the JSON sent
 * by the API.
 *
 */
public class MetaJson {
    
    /**
     * The "statusCode" field.
     */
    private int statusCode;
    
    /**
     * The "message" field.
     */
    private String message;
    
    public MetaJson() {
    }
    
    /**
     * Check if everything went right.
     * @throws NoSuccessException in case of a statusCode not equal to 200.
     */
    public void check() throws NoSuccessException {
        if (statusCode != 200)
            throw new NoSuccessException(message);
    }
}
