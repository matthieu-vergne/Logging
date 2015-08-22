package fr.vergne.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO Write Javadoc
public class LoggerConfiguration {

	private LoggerConfiguration() {
	}

	/**
	 * 
	 * @deprecated This logger is prone to be unmanageable, prefer to use
	 *             {@link #getSimpleLogger(String)} or
	 *             {@link #getSimpleLogger(Class)}.
	 */
	static public Logger getSimpleLogger() {
		Logger logger = Logger.getAnonymousLogger();
		configureSimpleLogger(logger, null);
		return logger;
	}

	static public Logger getSimpleLogger(String name) {
		Logger logger = Logger.getLogger(name);
		configureSimpleLogger(logger, null);
		return logger;
	}

	static public Logger getSimpleLogger(Class<?> clazz) {
		Logger logger = Logger.getLogger(clazz.getName());
		configureSimpleLogger(logger, null);
		return logger;
	}

	static public void configureSimpleLogger(Logger logger) {
		configureSimpleLogger(logger, null);
	}

	static public void configureSimpleLogger(Logger logger, String file) {
		logger.setUseParentHandlers(false);
		addSimpleConsoleFormatting(logger);
		if (file == null) {
			// do not use any file
		} else {
			addFileHandler(logger, file);
		}
	}

	static public void addSimpleConsoleFormatting(Logger logger) {
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(createSimpleFormatter());
		handler.setLevel(Level.ALL);
		logger.addHandler(handler);
	}

	public static void addFileHandler(Logger logger, String filename) {
		try {
			FileHandler handler = new FileHandler(filename);
			handler.setFormatter(createSimpleFormatter());
			handler.setLevel(Level.ALL);
			logger.addHandler(handler);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Formatter createSimpleFormatter() {
		return new OneLineFormatter();
	}
}
