package com.go.BookMyTicket.dto;

import java.util.List;

public record BookingRequest(List<Long> seatIds, String userName) {

}
