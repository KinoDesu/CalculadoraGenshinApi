package dev.kinodesu.model.response.calculator;

public class CalculatorResponse {
    private int retcode;
    private String message;
    private CalculatorResponseData data;

    public CalculatorResponse() {
    }

    public CalculatorResponse(int retcode, String message, CalculatorResponseData data) {
        this.retcode = retcode;
        this.message = message;
        this.data = data;
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

    public CalculatorResponseData getData() {
        return data;
    }

    public void setData(CalculatorResponseData data) {
        this.data = data;
    }
}
