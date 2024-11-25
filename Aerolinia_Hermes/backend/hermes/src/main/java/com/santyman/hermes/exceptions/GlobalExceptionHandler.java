package com.santyman.hermes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.santyman.hermes.exceptions.customExceptions.BookingExistException;
import com.santyman.hermes.exceptions.customExceptions.BookingNotFoundException;
import com.santyman.hermes.exceptions.customExceptions.FlightExistException;
import com.santyman.hermes.exceptions.customExceptions.FlightNotFoundException;
import com.santyman.hermes.exceptions.customExceptions.InvalidEmailException;
import com.santyman.hermes.exceptions.customExceptions.InvalidPasswordException;
import com.santyman.hermes.exceptions.customExceptions.PersonalLastNameException;
import com.santyman.hermes.exceptions.customExceptions.PersonalNamesException;
import com.santyman.hermes.exceptions.customExceptions.UserExistException;
import com.santyman.hermes.exceptions.customExceptions.UserNotFoundException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationException(MethodArgumentNotValidException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorDetails> handleEmailException(InvalidEmailException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    
    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ErrorDetails> handleUserExistException(UserExistException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonalNamesException.class)
    public ResponseEntity<ErrorDetails> handlePersonalNamesException(PersonalNamesException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PersonalLastNameException.class)
    public ResponseEntity<ErrorDetails> handlePersonalLastnameException(PersonalLastNameException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorDetails> handleInvalidPasswordException(InvalidPasswordException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleFlightNotFoundException(FlightNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleBookingNotFoundException(BookingNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlightExistException.class)
    public ResponseEntity<ErrorDetails> handleExistFlightException(FlightExistException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookingExistException.class)
    public ResponseEntity<ErrorDetails> handleExistBooking(BookingExistException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
    
}