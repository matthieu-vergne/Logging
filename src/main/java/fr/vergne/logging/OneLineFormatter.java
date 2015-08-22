package fr.vergne.logging;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class OneLineFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		long millis = record.getMillis();
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
				.format(new Date(millis));
		Level level = record.getLevel();
		String location = record.getSourceClassName() + "."
				+ record.getSourceMethodName() + "()";
		String message = record.getMessage();
		return date + " " + level + ": " + message + " [" + location + "]\n";
	}
}
