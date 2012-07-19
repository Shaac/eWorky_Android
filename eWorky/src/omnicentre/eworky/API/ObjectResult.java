package omnicentre.eworky.API;

/**
 * This class is the counterpart of Worki.Rest.ObjectResult<T>.
 * It is used to parse the JSON.
 *
 * @param <T> the class used to parse the JSON in the "response" field.
 */
public class ObjectResult<T> {

    /**
     * The "meta" field.
     */
    private MetaJson meta;

    /**
     * The "response" field.
     */
    private T response;

    public ObjectResult() {
    }

    /**
     * Get the response.
     * @return the response.
     * @throws NoSuccessException in case of an invalid request.
     */
    public T getResponse() throws NoSuccessException {
        meta.check();
        return response;
    }
}
