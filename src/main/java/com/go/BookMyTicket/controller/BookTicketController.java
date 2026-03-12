package com.go.BookMyTicket.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.go.BookMyTicket.dto.BookedSeatResponse;
import com.go.BookMyTicket.dto.BookingRequest;
import com.go.BookMyTicket.dto.BookingResponse;
import com.go.BookMyTicket.dto.DtoRequest;
import com.go.BookMyTicket.service.TicketServiceImpl;

import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/api/seat")
@Slf4j
public class BookTicketController {
	
	@Autowired
	private TicketServiceImpl  TicketServiceImpl;
	

	@PostMapping("/{totalSeats}")
	public ResponseEntity createSeats(@PathVariable int totalSeats){
		log.info("BookTicketController :: createSeats");
		TicketServiceImpl.initializeSeats(totalSeats);
		return ResponseEntity.status(HttpStatus.CREATED)
	            .body("Successfully initialized " + totalSeats + " seats.");
		
	}
	@GetMapping("/getAllSeats")
	public ResponseEntity<List<DtoRequest>> getSeats(){
		log.info("BookTicketController :: getSeats");
		List<DtoRequest> bookedSeats=TicketServiceImpl.getAllSeats();
	    return ResponseEntity.status(HttpStatus.OK).body(bookedSeats);
		
	}
	@GetMapping("/getBookedSeats")
	public ResponseEntity<BookedSeatResponse> getBookedSeats(){
		log.info("BookTicketController :: getSeats");
		List<DtoRequest> book=TicketServiceImpl.getBookedSeats();
		long totalSeatBooked =book.size();
	    return ResponseEntity.ok(new BookedSeatResponse(book,totalSeatBooked));
		
	}
	
	@PostMapping("/book")
	public ResponseEntity<BookingResponse> bookSeats(@RequestBody BookingRequest bookingRequest) {
		log.info("BookTicketController :: bookSeats");
		BookingResponse bookingResponse = TicketServiceImpl.bookSeats(bookingRequest);

		if (bookingResponse.totalPrice() == 0) {
			// must return here
			return ResponseEntity.badRequest().body(bookingResponse);
		}

		return ResponseEntity.status(HttpStatus.OK).body(bookingResponse);
	}

}
