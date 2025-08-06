package make.common.result;

public final class Err<T, E> implements Result<T, E> {
    private final E err;

    public Err(E err) {
        this.err = err;
    }

    @Override
    public Object result() {
        return err;
    }

    @Override
    public ResultType type() {
        return ResultType.ERR;
    }
}
