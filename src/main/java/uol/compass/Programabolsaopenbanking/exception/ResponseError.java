package uol.compass.Programabolsaopenbanking.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public class ResponseError {

    @JsonProperty("status_code")
    private int status_code;
    private String message;

    public ResponseError() {}

    public ResponseError(int statusCode, String message) {
        this.status_code = statusCode;
        this.message = message;
    }

    public int getCodeStatus() {
        return status_code;
    }

    public void setCodeStatus(int statusCode) {
        this.status_code = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
