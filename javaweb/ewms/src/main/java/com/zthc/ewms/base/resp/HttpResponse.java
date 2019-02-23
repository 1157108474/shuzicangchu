package com.zthc.ewms.base.resp;

public class HttpResponse<T> {

    private Status status;
    private String message;
    private T data;
    private String errorNo;

    public enum Status {
        SUCCESS(1), FAILURE(0);

        private int code;

        Status(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public HttpResponse(Status status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public HttpResponse(T data) {
        this.data = data;
        this.status = Status.SUCCESS;
        this.message = "success";
    }

    public HttpResponse() {
    }

    public int getStatus() {
        return status.getCode();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }

    public enum ErrorEnum {
        SUCCESS("0", "成功"),
        OTHER_ERROR("100", "其他错误");

        private String errorNo;

        private String message;

        ErrorEnum(String errorNo, String message) {
            this.errorNo = errorNo;
            this.message = message;
        }

        public String getErrorNo() {
            return errorNo;
        }

        public String getMessage() {
            return message;
        }
    }
}
