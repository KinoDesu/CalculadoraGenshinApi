package dev.kinodesu.model.response;

public class GenshinApiResponse {
    private int retcode;
    private String message;
    private ResponseData data;

    public GenshinApiResponse(int retcode, String message, ResponseData data) {
        this.retcode = retcode;
        this.message = message;
        this.data = data;
    }

    public GenshinApiResponse() {
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseData getData() {
        return data;
    }

    public void setData(ResponseData data) {
        this.data = data;
    }
}
