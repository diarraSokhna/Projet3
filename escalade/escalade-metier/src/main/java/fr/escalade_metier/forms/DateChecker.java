package fr.escalade_metier.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateChecker {
	public static boolean isValid(String strdate, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		df.setLenient(false);
		try {
			Date date = df.parse(strdate);
			return true;
		} catch (ParseException ex) {
			Logger.getLogger(DateChecker.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
}
