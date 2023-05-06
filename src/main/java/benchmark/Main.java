package benchmark;

import benchmark.map.MapForEachLargeBenchmark;
import benchmark.map.MapForEachSmallBenchmark;
import benchmark.map.MapGetLargeBenchmark;
import benchmark.map.MapGetSmallBenchmark;
import benchmark.set.SetContainsLargeBenchmark;
import benchmark.set.SetContainsSmallBenchmark;
import benchmark.set.SetForEachLargeBenchmark;
import benchmark.set.SetForEachSmallBenchmark;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class Main {
    public static void main(String[] args) throws RunnerException {
        run(SetContainsSmallBenchmark.class);
        run(SetContainsLargeBenchmark.class);
        run(SetForEachSmallBenchmark.class);
        run(SetForEachLargeBenchmark.class);

        run(MapGetSmallBenchmark.class);
        run(MapGetLargeBenchmark.class);
        run(MapForEachSmallBenchmark.class);
        run(MapForEachLargeBenchmark.class);
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
