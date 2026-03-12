package com.go.BookMyTicket.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.go.BookMyTicket.Entity.BookSeat;
import com.go.BookMyTicket.dto.BookingRequest;
import com.go.BookMyTicket.dto.BookingResponse;
import com.go.BookMyTicket.dto.DtoRequest;
import com.go.BookMyTicket.repository.BookRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {
	@Autowired
	private BookRepository bookRepository;

	@Override
	@Transactional
	public void initializeSeats(int totalSeats) {
		log.info("Initializing {} seats...", totalSeats);
		bookRepository.deleteAll();
		log.debug("All existing seats deleted from repository");
		List<BookSeat> seats = IntStream.range(0, totalSeats).mapToObj(i -> new BookSeat(null, null, false))
				.collect(Collectors.toList());
		bookRepository.saveAll(seats);
		log.info("Successfully initialized {} seats", seats.size());
	}

	@Override
	public List<DtoRequest> getAllSeats() {
		log.info("Fetching all seats from repository");
		return bookRepository.findAll().stream().map(TicketBookingMapper::mapper2) // map every seat
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public BookingResponse bookSeats(BookingRequest bookingRequest) {
		log.info("Starting booking process for user: {}", bookingRequest.userName());
		List<BookSeat> seats = bookRepository.findAllById(bookingRequest.seatIds());

		if (seats.stream().anyMatch(BookSeat::isBooked)) {
			log.warn("Booking failed: Some seats already booked for request {}", bookingRequest.seatIds());
			return new BookingResponse(0, "Some seats already booked!");
		}

		double totalPrice = 0;
		for (BookSeat seat : seats) {
			double price = ticketPrice(seat.getId());
			totalPrice += price;
			seat.setBookedBy(bookingRequest.userName());
			seat.setBooked(true);
		}

		bookRepository.saveAll(seats); // ✅ persist all changes at once

		log.info("Booking successful for user: {}. Total price: {}", bookingRequest.userName(), totalPrice);
		return new BookingResponse(totalPrice, "Booking successful!");
	}

	private double ticketPrice(long id) {
		if (id >= 1 && id <= 50) {
			return 50;
		} else if (id >= 51 && id <= 80) {
			return 75;
		} else if (id >= 81 && id <= 100) {
			return 100;
		} else {
			throw new IllegalArgumentException("Invalid seat number: " + id);
		}
	}

	@Override
	public List<DtoRequest> getBookedSeats() {
		log.info("Fetching all seats from repository and Filtered booked seats");
		return bookRepository.findAll().stream().filter(BookSeat::isBooked).map(TicketBookingMapper::mapper2)
				.collect(Collectors.toList());
		/* long count=bookedSeat.size(); */

		/*
		 * return bookedSeat.stream() .map(seat -> new DtoRequest( seat.id(),
		 * seat.booked(), seat.bookedBy(), count )) .toList();
		 */

	}
}
