package easysalesassistant.api.controllers;

import easysalesassistant.api.exceptions.*;
import easysalesassistant.api.exceptions.dto.ErrorExceptionDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CategoryIsDeletedException.class)
    public ResponseEntity<ErrorExceptionDTO> categoryIsDeletedException(CategoryIsDeletedException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = ProviderIsDeletedException.class)
    public ResponseEntity<ErrorExceptionDTO> providerIsDeletedException(ProviderIsDeletedException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = StoreIsDeletedException.class)
    public ResponseEntity<ErrorExceptionDTO> storeIsDeletedException(StoreIsDeletedException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = BranchIsDeletedException.class)
    public ResponseEntity<ErrorExceptionDTO> branchIsDeletedException(BranchIsDeletedException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = ProductIsDeletedException.class)
    public ResponseEntity<ErrorExceptionDTO> productIsDeletedException(ProductIsDeletedException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = NotFoundSellException.class)
    public ResponseEntity<ErrorExceptionDTO> notFoundSellException(NotFoundSellException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotEnoughStock.class)
    public ResponseEntity<ErrorExceptionDTO> notEnoughStockExceptionHandler(NotEnoughStock ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = NotFoundStoreException.class)
    public ResponseEntity<ErrorExceptionDTO> notFoundStoreExceptionHandler(NotFoundStoreException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotFoundProviderException.class)
    public ResponseEntity<ErrorExceptionDTO> notFoundProviderExceptionHandler(NotFoundProviderException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotFoundProductException.class)
    public ResponseEntity<ErrorExceptionDTO> productDoesntExistsExceptionHandler(NotFoundProductException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNameAlreadyExistsException.class)
    public ResponseEntity<ErrorExceptionDTO> userNameAlreadyExistsExceptionHandler(UserNameAlreadyExistsException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(value = UserDisabledException.class)
    public ResponseEntity<ErrorExceptionDTO> userDisabledExceptionHandler(UserDisabledException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = NotFoundAddressException.class)
    public ResponseEntity<ErrorExceptionDTO> notFoundAddressExceptionHandler(NotFoundAddressException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = NotFoundBranchException.class)
    public ResponseEntity<ErrorExceptionDTO> notFoundBranchExceptionHandler(NotFoundBranchException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = NotFoundCategoryException.class)
    public ResponseEntity<ErrorExceptionDTO> notFoundCategoryExceptionHandler(NotFoundCategoryException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = NotFoundCityException.class)
    public ResponseEntity<ErrorExceptionDTO> notFoundCityExceptionHandler(NotFoundCityException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = NotFoundStateException.class)
    public ResponseEntity<ErrorExceptionDTO> notFoundCityExceptionHandler(NotFoundStateException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = NotFoundSystemUserException.class)
    public ResponseEntity<ErrorExceptionDTO> notFoundCityExceptionHandler(NotFoundSystemUserException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }



    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorExceptionDTO> runtimeExceptionHandler(RuntimeException ex){
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(500).build();
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> {
            errors.put(e.getField(),e.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().message(ex.getMessage()).code(404).build();
        return new ResponseEntity<>(error,status);
    }
}
