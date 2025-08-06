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
}
