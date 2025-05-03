package by.bnd.intensive_spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ServerResponse<T> {

    @JsonProperty("isSuccess")
    private boolean isSuccess;
    private HttpStatus statusCode;
    @JsonProperty("errors")
    private List<String> errorMessages;
    private T result;

    public ServerResponse() {
        this.isSuccess = true;
        this.errorMessages = new ArrayList<>();
    }
}
