package exception;

/**
 * Created by Vito on 28. 4. 2016.
 */
public class NullNodeException extends Throwable {
    public NullNodeException(String s) {
        System.err.println("NullNodeException: " + s);
    }


}
