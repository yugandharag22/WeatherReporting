package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LogUtils {

    private static Logger logger = LoggerFactory.getLogger(LogUtils.class);


    private static String getPrettyMessage(String message) {
        return " AUTOMATION_LOG: ==> " + message;
    }

    public static void debug(String message) {
        logger.debug(getPrettyMessage(message));
    }

    private static void sysout(String message) {
        System.out.println(getPrettyMessage(message));
    }

    public static void info(String message) {
        logger.info(getPrettyMessage(message));
        Allure.step(message,Status.PASSED);
    }

    public static void error(String message) {
        Allure.step(message, Status.FAILED);
        logger.error(getPrettyMessage(message));
    }

    public static void error(String message, Exception e) {
        Allure.step(message, Status.FAILED);
        logger.error(getPrettyMessage(message), e);
    }

    public static void warn(String message) {
        logger.warn(getPrettyMessage(message));
    }
}
