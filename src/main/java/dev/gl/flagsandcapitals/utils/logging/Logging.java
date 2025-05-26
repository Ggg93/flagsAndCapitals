package dev.gl.flagsandcapitals.utils.logging;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;

/**
 *
 * @author gl
 */
public class Logging {

    public static java.util.logging.Logger logger;
    public static String propertiesAddress = ".\\src\\main\\resources\\logging\\logging.properties";

    public static java.util.logging.Logger getLocalLogger(Class clazz) {

        // firstly check that we have already read JUL configuration
        if (logger == null) {
            initLogger();
        }
        return java.util.logging.Logger.getLogger(clazz.getCanonicalName());
    }

    /**
     * init logger
     *
     * @return true if load customConfiguration; false otherwise
     */
    public static boolean initLogger() {

        Exception ex = null;
        File file = new File(propertiesAddress);
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream(file));
        } catch (Exception e) {
            ex = e;
        }

        logger = java.util.logging.Logger.getLogger(Logging.class.getCanonicalName());
        logger.config("Logger has been initialized from "
                + (ex == null ? "custom " : "default ")
                + "configuration");
        if (ex != null) {
            logger.log(Level.SEVERE, null, ex);
        }

        return ex == null;
    }
}
