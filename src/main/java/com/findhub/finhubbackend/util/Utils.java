package com.findhub.finhubbackend.util;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	
	public static Date calculateExpirationDate(int expirationTime) {
		Calendar calendar= Calendar.getInstance();
		calendar.setTimeInMillis(new java.util.Date().getTime());
		calendar.add(Calendar.MINUTE, expirationTime);
		return new Date(calendar.getTime().getTime());
	}
}
