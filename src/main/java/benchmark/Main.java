package benchmark;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class Main {
    public static void main(String[] args) throws RunnerException {
        run(SetForEachBenchmark.class);
    }

    private static void run(Class<?> clazz) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(clazz.getName())
            .forks(2)
            .measurementIterations(10)
            .warmupIterations(5)
            .measurementTime(TimeValue.milliseconds(100))
            .warmupTime(TimeValue.milliseconds(100))
            .resultFormat(ResultFormatType.CSV)
            .result(clazz.getSimpleName() + ".csv")
            .build();

        new Runner(opt).run();
    }
}
