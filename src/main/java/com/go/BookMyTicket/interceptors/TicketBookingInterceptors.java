package com.go.BookMyTicket.interceptors;

import java.time.Instant;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class TicketBookingInterceptors implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		log.info("[REQUEST] Method:{},URI:{},IP:{},START TIME:{} ", request.getMethod(), request.getRequestURI(),
				request.getRemoteAddr(), Instant.ofEpochMilli(startTime));
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		long startTime = (long) request.getAttribute("startTime");
		long duration = System.currentTimeMillis() - startTime;
		log.info("[RESPONSE] STATUS:{},URI:{},DURATION:{} ms", response.getStatus(), request.getRequestURI(), duration);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		if (ex != null) {
			log.error("[ERROR],exception accured in URI:{},Error:{} ", request.getRequestURI(), ex.getMessage());
		}
	}


}
