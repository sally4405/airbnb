package louie.dong.airbnb.exception;

import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class RequestValidationFailException extends RuntimeException {

    public static RequestValidationFailException of(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            sb = sb.append(field).append(defaultMessage).append("\r\n");
        }
        return super(sb.toString());
    }
}
