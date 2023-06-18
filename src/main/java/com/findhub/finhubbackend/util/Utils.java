package com.findhub.finhubbackend.util;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	public static Date calculateExpirationDate(int expirationTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new java.util.Date().getTime());
		calendar.add(Calendar.MINUTE, expirationTime);
		return new Date(calendar.getTime().getTime());
	}

	public static Date addDate(Date date, int days) {
		return new Date(date.getTime() + 1000 * 60 * 60 * 24 * days);
	}
}
