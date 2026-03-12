package com.go.BookMyTicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.go.BookMyTicket.Entity.BookSeat;
@Repository
public interface BookRepository extends JpaRepository<BookSeat, Long> {
	long countByBookedTrue();

}
