package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerClass {
    private static final Logger logger = LoggerFactory.getLogger(LoggerClass.class);

    public static void log(String message) {
        logger.info(message);
    }

    public static void myMethod() {
        logger.trace("This is a TRACE message");
        logger.debug("This is a DEBUG message");
        logger.info("This is an INFO message");
        logger.warn("This is a WARN message");
        logger.error("This is an ERROR message");
    }
}
