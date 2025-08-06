package make.common.result;

public enum ResultType {
    OK {
        @Override
        public boolean isOk() {
            return true;
        }

        @Override
        public boolean isErr() {
            return false;
        }

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
        public boolean isOk() {
            return false;
        }

        @Override
        public boolean isErr() {
            return true;
        }

        @Override
        <T> T unwrap(Object result) {
            throw new NotOkError("result isn't ok but unwrap called");
        }

        @Override
        <T> T expected(Object result, String message) throws NotOkError {
            throw new NotOkError(message);
        }
    }

    ;

    public abstract boolean isOk();
    public abstract boolean isErr();
    abstract <T> T unwrap(Object result) throws NotOkError;
    abstract <T> T expected(Object result, String message) throws NotOkError;
}
