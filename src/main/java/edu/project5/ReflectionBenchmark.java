package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;



@SuppressWarnings("UncommentedMain")
@State(Scope.Thread)
public class ReflectionBenchmark {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(TEST_TIME))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(TEST_TIME))
            .build();

        new Runner(options).run();
    }

    private static final int TEST_TIME = 5;

    private static final String METHOD_NAME = "name";

    private Student student;

    private Method method;

    private MethodHandle methodHandle;

    private Function function;

    @Setup
    public void setup() throws Throwable {

        student = new Student("Alexander", "Biryukov");
        method = Student.class.getMethod(METHOD_NAME);

        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, METHOD_NAME,
            MethodType.methodType(String.class));

        CallSite callSite = LambdaMetafactory.metafactory(
            lookup,
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            methodHandle,
            MethodType.methodType(String.class, Student.class)
        );

        function = (Function) callSite.getTarget().invokeExact();

    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        bh.consume(student.name());
    }

    @Benchmark
    public void reflection(Blackhole bh) throws Exception {

        bh.consume(method.invoke(student));

    }

    @Benchmark
    public void methodHandles(Blackhole bh) throws Throwable {

        bh.consume(methodHandle.invoke(student));

    }

    @Benchmark
    public void lambdaMetaFactory(Blackhole bh) {
        bh.consume(function.apply(student));
    }
}
