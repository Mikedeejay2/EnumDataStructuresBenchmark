package benchmark.set;

import benchmark.TestEnumSmall;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.ReferenceArraySet;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SetForEachSmallBenchmark {
    private final List<TestEnumSmall> enumValues = Arrays.asList(TestEnumSmall.values());
    private final List<Integer> enumOrdinals = enumValues.stream().map(Enum::ordinal).collect(Collectors.toList());

    private final EnumSet<TestEnumSmall> enumSet = EnumSet.allOf(TestEnumSmall.class);
    private final HashSet<TestEnumSmall> hashSet = new HashSet<>(enumValues);
    private final ReferenceSet<TestEnumSmall> referenceHashSet = new ReferenceOpenHashSet<>(enumValues);
    private final ReferenceSet<TestEnumSmall> referenceArraySet = new ReferenceArraySet<>(enumValues);
    private final IntSet intHashSet = new IntOpenHashSet(enumOrdinals);
    private final IntSet intArraySet = new IntArraySet(enumOrdinals);

    @Benchmark
    public void forEachEnumSet() {
        for(TestEnumSmall cur : enumSet) {
            cur.doSomething();
        }
    }

    @Benchmark
    public void forEachHashSet() {
        for(TestEnumSmall cur : hashSet) {
            cur.doSomething();
        }
    }

    @Benchmark
    public void forEachReferenceHashSet() {
        for(TestEnumSmall cur : referenceHashSet) {
            cur.doSomething();
        }
    }

    @Benchmark
    public void forEachReferenceArraySet() {
        for(TestEnumSmall cur : referenceArraySet) {
            cur.doSomething();
        }
    }

    @Benchmark
    public void forEachIntHashSet() {
        final TestEnumSmall[] values = TestEnumSmall.values();
        for(int cur : intHashSet) {
            TestEnumSmall curEnum = values[cur];
            curEnum.doSomething();
        }
    }

    @Benchmark
    public void forEachIntArraySet() {
        final TestEnumSmall[] values = TestEnumSmall.values();
        for(int cur : intArraySet) {
            TestEnumSmall curEnum = values[cur];
            curEnum.doSomething();
        }
    }
}
