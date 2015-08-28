package fr.vergne.logging;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
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
		String message = record.getMessage() == null ? "" : record.getMessage() + " ";
		String logged = date + " " + level + ": " + message + "[" + location
				+ "]\n";

		Throwable cause = record.getThrown();
		if (cause != null) {
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			PrintStream stream = new PrintStream(bytes);
			cause.printStackTrace(stream);
			stream.close();

			logged += new String(bytes.toByteArray(), Charset.forName("UTF-8"));
		} else {
			// nothing to append
		}

		return logged;
	}
}
