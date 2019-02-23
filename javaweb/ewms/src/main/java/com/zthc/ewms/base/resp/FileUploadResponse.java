package com.zthc.ewms.base.resp;

public class FileUploadResponse {
    private int code;
    private String msg;
    private Data data;

    private String errorNo;

    public enum Code {
        SUCCESS(1), FAILURE(0);

        private int code;

        Code(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public FileUploadResponse(int code, String msg, String src) {
        this.code = code;
        this.msg = msg;
        this.data = new Data(src);
    }
    public FileUploadResponse(int code, String msg, Data data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public FileUploadResponse(int code, String msg, String src, String name) {
        this.code = code;
        this.msg = msg;
        this.data = new Data(src, name);
    }


    public FileUploadResponse(Data data) {
        this.data = data;
        this.code = Code.SUCCESS.getCode();
        this.msg = "success";
    }

    public FileUploadResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getErrorNo() {
        return errorNo;
    }

    public class Data{
        private String src;
        private String name;
        public Data() {
        }

        public Data(String src, String name) {
            this.src = src;
            this.name = name;
        }


        public Data(String src) {
            this.src = src;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }
}
