package com.santyman.hermes.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santyman.hermes.dtos.bookingDTO.BookingDto;
import com.santyman.hermes.entities.BookingStatus;
import com.santyman.hermes.services.IBookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
    
    @Autowired
    private IBookingService bookingService;

    @GetMapping("/booking/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id){
        return ResponseEntity.ok().body(bookingService.findById(id));
    }

    @GetMapping("/bookingByfligth/{id}")
    public ResponseEntity<Set<BookingDto>> getBookingByFligth(@PathVariable Long id){
        Set<BookingDto> bookingDtos = bookingService.getBookingsByFligth(id);
        return ResponseEntity.ok().body(bookingDtos);
    }

    @GetMapping("/booking")
    public ResponseEntity<List<BookingDto>> getBookings(){
        List<BookingDto> bookingsToResponse = bookingService.showBookings();
        return ResponseEntity.ok().body(bookingsToResponse);
    }

    @GetMapping("/bookingByUser")
    public ResponseEntity<List<BookingDto>> getBookingByUser(@RequestParam("user") String user){
        List<BookingDto> bookingDtos = bookingService.getBookingByUserCustomer(user);
        return ResponseEntity.ok().body(bookingDtos);
    }

    @GetMapping("/bookingByStatus")
    public ResponseEntity<List<BookingDto>> getBookingsByStatus(@RequestParam("status") String status){
        BookingStatus statusAux = BookingStatus.valueOf(status);
        List<BookingDto> bookingDtosResponse = bookingService.getBookingByStatus(statusAux);
        return ResponseEntity.ok().body(bookingDtosResponse);
    }

    @GetMapping("/bookingBystatusAndUser")
    public ResponseEntity<List<BookingDto>> getBookingByStatusAndUser(@RequestParam("user") String user,@RequestParam("status") String status){
        BookingStatus statusAux = BookingStatus.valueOf(status);
        List<BookingDto> bookingDtosResponse = bookingService.getBookingsByUserAndStatus(user, statusAux);
        return ResponseEntity.ok().body(bookingDtosResponse);
    }


    @PostMapping("/booking/user/{IdUser}/flight/{idFligth}")
    public ResponseEntity<BookingDto> createBooking(@Valid @RequestBody BookingDto bookingDto,@PathVariable Long IdUser, @PathVariable Long idFligth){
        BookingDto dtoTOSend = bookingService.registerBooking(bookingDto, idFligth, IdUser);
        return new ResponseEntity<BookingDto>(dtoTOSend, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/booking/{id}")
    public ResponseEntity<Boolean> deleteBooking(@PathVariable Long id){
        Boolean bookingIsDelete = bookingService.deleteBooking(id);
        return ResponseEntity.ok().body(bookingIsDelete);
    }

   

    @PutMapping("/booking/{id}/user/{idUser}/flight/{idFlight}")
    public ResponseEntity<BookingDto> updateBooking(@RequestBody BookingDto bookingDto, @PathVariable Long id, @PathVariable Long idUser, @PathVariable Long idFlight){
        BookingDto bookingUpdate = bookingService.updateBookingDto(id, bookingDto, idUser, idFlight);
        return new ResponseEntity<BookingDto>(bookingUpdate,HttpStatus.ACCEPTED);
    }
}