package com.auth.server.controller;

import com.auth.server.util.ApplicationException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerAdviceImplTest {

    private final Logger log = LoggerFactory.getLogger(ControllerAdviceImplTest.class);

    private ControllerAdviceImpl controllerAdvice = new ControllerAdviceImpl();

    @Test
    public void userRecordNotFoundTest() {
        ApplicationException exception = new ApplicationException("testException");
        ResponseEntity<String> response = controllerAdvice.userRecordNotFound(exception);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("testException", exception.getMessage(), "exception message not same");
    }

    @Test
    public void requestHeaderMissingTest() {
        Exception exception = new Exception("testException");
        ResponseEntity<String> response = controllerAdvice.requestHeaderMissing(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("testException", exception.getMessage(), "exception message not same");
    }

    @Test
    public void validationFailedException() {
        MethodArgumentNotValidException exception = Mockito.mock(MethodArgumentNotValidException.class, Mockito.RETURNS_DEEP_STUBS);
        List<ObjectError> objectErrors = Mockito.mock(List.class);
        Mockito.when(exception.getBindingResult().getAllErrors()).thenReturn(objectErrors);
        Mockito.doAnswer(invocation -> invocation.getMock())
                .when(objectErrors).forEach(Mockito.any());
        ResponseEntity<Map<String, String>> response = controllerAdvice.validationFailedException(exception);
        Mockito.verify(exception.getBindingResult().getAllErrors()).forEach(Mockito.any());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().size() == 0, "errors size is not 0");
    }
}
