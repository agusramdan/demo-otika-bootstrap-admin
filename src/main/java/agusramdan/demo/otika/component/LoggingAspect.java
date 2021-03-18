package agusramdan.demo.otika.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LoggingAspect
{
    //private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    //AOP expression for which methods shall be intercepted
    @Around("execution(* agusramdan.demo.otika.controller..*(..)))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        try {
            return proceedingJoinPoint.proceed();
        }finally {
            stopWatch.stop();
            //Log method execution time
            log.info("Execution time of {}.{} :: {} ms",  className , methodName , stopWatch.getTotalTimeMillis() );
        }
    }
}