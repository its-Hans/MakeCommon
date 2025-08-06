package make.common.result;

public class NotOkError extends Error {
    NotOkError(String message) {
        super(message);
    }
}
