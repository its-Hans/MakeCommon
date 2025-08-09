package make.common.result;

public interface Result<T, E> {
    Object result();

    ResultType type();

    default T unwrap() throws NotOkError {
        return type().unwrap(result());
    }

    default T expected(String message) throws NotOkError {
        return type().expected(result(), message);
    }

    static <T, E> Result<T, E> err(E err) {
        return new Result<>() {
            @Override
            public Object result() {
                return err;
            }

            @Override
            public ResultType type() {
                return ResultType.ERR;
            }
        };
    }

    static <T, E> Result<T, E> ok(T ok) {
        return new Result<>() {
            @Override
            public Object result() {
                return ok;
            }

            @Override
            public ResultType type() {
                return ResultType.OK;
            }
        };
    }
}
