package make.common.result;

public final class Ok<T, E> implements Result<T, E> {
    private final T ok;

    public Ok(T ok) {
        this.ok = ok;
    }

    @Override
    public Object result() {
        return ok;
    }

    @Override
    public ResultType type() {
        return ResultType.OK;
    }
}
