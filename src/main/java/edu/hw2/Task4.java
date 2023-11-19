package edu.hw2;

public class Task4 {

    private Task4() {
    }


    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length >= 2) {

            StackTraceElement caller = stackTrace[1];
            String className = caller.getClassName();
            String methodName = caller.getMethodName();

            return new CallingInfo(className, methodName);

        } else {
            throw new IllegalStateException("Cannot retrieve calling info");
        }
    }

    public record CallingInfo(String className, String methodName) {

    }
}
