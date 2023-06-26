package com.findhub.finhubbackend.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
		return (date == null)
				? date
				: new Date(date.getTime() + 1000 * 60 * 60 * 24 * days);
	}

	public static String capitalize(String str) {
		return (str == null || str.isEmpty())
				? str
				: str.substring(0, 1).toUpperCase()
						+ str.substring(1).toLowerCase();
	}

	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	public static boolean isNum(String input) {
		if (input.isEmpty())
			return false;
		else
			try {
				Double.parseDouble(input);
				// Float.parseFloat(input);
				// Integer.parseInt(input);
			} catch (NumberFormatException NaN) {
				return false;
			}

		return true;
	}
}
