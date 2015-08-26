
package com.jmg.sa.exception;

/**
 * @author Javier Moreno Garcia
 *
 */
public class OperationNotAuthorizedException extends RuntimeException {

    private static final long serialVersionUID = -2688284220824207072L;

    public OperationNotAuthorizedException() {
    }

    public OperationNotAuthorizedException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OperationNotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationNotAuthorizedException(String message) {
        super(message);
    }

    public OperationNotAuthorizedException(Throwable cause) {
        super(cause);
    }
}
