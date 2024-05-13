package com.vn.mobilecity.aop;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * Aspect for logging execution of service and repository Spring components.
 * <p>
 * By default, it only runs with the "dev" profile.
 */
@Aspect
public class LoggingAspect {
    private final String POD_ID;
    static final String LOGGING_FORMAT = "type={}; ip={}; method={}.{}(); args={}; execTime={} ms; response={}";

    public LoggingAspect() {
        POD_ID = System.getenv("POD_ID");
    }

    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositoryPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void servicePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.vn.mobilecity.controller..*)" +
            " || within(com.vn.mobilecity.service..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Retrieves the {@link org.slf4j.Logger} associated to the given {@link JoinPoint}.
     *
     * @param joinPoint join point we want the logger for.
     * @return {@link org.slf4j.Logger} associated to the given {@link JoinPoint}.
     */
    private org.slf4j.Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice.
     * @param e         exception.
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut()" +
            " && controllerPointcut() " +
            "&& repositoryPointcut() " +
            "&& servicePointcut()",
            throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger(joinPoint)
                .error(
                        "Exception in {}() with cause = {}",
                        joinPoint.getSignature().getName(),
                        e.getCause() != null ? e.getCause() : "NULL"
                );
    }

    /**
     * Advice that logs when a method is entered and exited.
     * https://stackoverflow.com/questions/38822971/spring-aop-exclude-some-classes
     *
     * @param joinPoint join point for advice.
     * @return result.
     * @throws Throwable throws {@link IllegalArgumentException}.
     */
    @Around("controllerPointcut()" +
            "&& !@annotation(com.vn.mobilecity.aop.LoggingRequestOut)" +
            "&& !@target(com.vn.mobilecity.aop.LoggingRequestOut)" +
            "&& !@annotation(com.vn.mobilecity.aop.NoLogging)" +
            "&& !@target(com.vn.mobilecity.aop.NoLogging)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        org.slf4j.Logger log = logger(joinPoint);
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }

        final StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            stopWatch.stop();
            log.info(LOGGING_FORMAT, "request-in", POD_ID, className, methodName,
                    joinPoint.getArgs(), stopWatch.getTotalTimeMillis(), getValue(result));
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                    + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }

    /**
     * Advice that logs when a method is entered and exited.
     * https://stackoverflow.com/questions/38822971/spring-aop-exclude-some-classes
     *
     * @param joinPoint join point for advice.
     * @return result.
     * @throws Throwable throws {@link IllegalArgumentException}.
     */
    @Around("servicePointcut()" +
            "&& @annotation(com.vn.mobilecity.aop.LoggingRequestOut)")
    public Object logRequestOut(ProceedingJoinPoint joinPoint) throws Throwable {
        org.slf4j.Logger log = logger(joinPoint);
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        }

        final StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            stopWatch.stop();
            log.info(LOGGING_FORMAT, "request-out", POD_ID, className, methodName,
                    joinPoint.getArgs(), stopWatch.getTotalTimeMillis(), getValue(result));
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                    + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }

    private String getValue(Object result) {
        String returnValue = null;
        if (null != result) {
            if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
                returnValue = ReflectionToStringBuilder.toString(result);
            } else {
                returnValue = result.toString();
            }
        }
        return returnValue;
    }
}