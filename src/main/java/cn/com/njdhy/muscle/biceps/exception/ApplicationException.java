package cn.com.njdhy.muscle.biceps.exception;

/**
 * <一句话功能简述> 应用异常
 * <功能详细描述>
 *
 * @author 胡志海
 */
public class ApplicationException extends RuntimeException {

    private String msg;
    private String code;
    private RuntimeException runtimeException;

    public ApplicationException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ApplicationException(String msg, RuntimeException e) {
        super(msg, e);
        this.msg = msg;
        this.runtimeException = e;
    }

    public ApplicationException(String msg, String code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ApplicationException(String msg, String code, RuntimeException e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
        this.runtimeException = e;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RuntimeException getRuntimeException() {
        return runtimeException;
    }

    public void setRuntimeException(RuntimeException runtimeException) {
        this.runtimeException = runtimeException;
    }
}
