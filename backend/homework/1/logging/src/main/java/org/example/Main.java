package org.example;
import org.slf4j.*;

//import java.util.logging.Logger;

class Logback{
    static final Logger logger = LoggerFactory.getLogger(Logback.class);

    public static Logger getLogger(){
        return logger;
    }
}