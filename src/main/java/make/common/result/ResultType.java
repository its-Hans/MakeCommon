package make.common.result;

public enum ResultType {
    OK {

        @SuppressWarnings("unchecked")
        @Override
        <T> T unwrap(Object result) {
            return (T) result;
        }

        @SuppressWarnings("unchecked")
        @Override
        <T> T expected(Object result, String message) {
            return (T) result;
        }
    },

    ERR {

        @Override
        <T> T unwrap(Object result) throws NotOkError {
            throw new NotOkError("result isn't ok but unwrap called");
        }

        @Override
        <T> T expected(Object result, String message) throws NotOkError {
            throw new NotOkError(message);
        }
    }

    ;

    abstract <T> T unwrap(Object result);
    abstract <T> T expected(Object result, String message);
}
