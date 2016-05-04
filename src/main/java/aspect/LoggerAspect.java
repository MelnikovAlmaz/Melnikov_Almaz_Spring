package aspect;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;

;

@Aspect
public class LoggerAspect {

    private final static Logger LOGGER = LogManager.getLogger(LoggerAspect.class);



    private void logServiceRequest(JoinPoint pjp) throws Throwable{
            LOGGER.info("Service: " + pjp.getTarget().getClass() + " -- "
                    + pjp.getSignature());
            logArguments(pjp);
    }

    private void logArguments(JoinPoint pjp) {
        StringBuffer argLog = new StringBuffer();
        for (Object arg : pjp.getArgs()) {
            argLog.append(arg);
            argLog.append(",");
        }
        LOGGER.info("Args: " + argLog);
    }
}