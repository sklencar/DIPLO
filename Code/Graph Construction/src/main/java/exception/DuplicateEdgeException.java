package exception;

/**
 * Created by Vito on 28. 4. 2016.
 */
public class DuplicateEdgeException extends Throwable {
    public DuplicateEdgeException(String s) {
        System.err.println(s);
    }


}
