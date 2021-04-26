package dsbt.Exceptions;

import dsbt.ResponseBody.ReturnMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(MissingHostname.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ReturnMessage missingHostnameHandler(MissingHostname ex){
        return new ReturnMessage(ex.getMessage());
    }

}
