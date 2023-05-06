package benchmark.set;

import benchmark.TestEnumLarge;
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
public class SetForEachLargeBenchmark {
    private final List<TestEnumLarge> enumValues = Arrays.asList(TestEnumLarge.values());
    private final List<Integer> enumOrdinals = enumValues.stream().map(Enum::ordinal).collect(Collectors.toList());

    private final EnumSet<TestEnumLarge> enumSet = EnumSet.allOf(TestEnumLarge.class);
    private final HashSet<TestEnumLarge> hashSet = new HashSet<>(enumValues);
    private final ReferenceSet<TestEnumLarge> referenceHashSet = new ReferenceOpenHashSet<>(enumValues);
    private final ReferenceSet<TestEnumLarge> referenceArraySet = new ReferenceArraySet<>(enumValues);
    private final IntSet intHashSet = new IntOpenHashSet(enumOrdinals);
    private final IntSet intArraySet = new IntArraySet(enumOrdinals);

    @Benchmark
    public void forEachEnumSet() {
        for(TestEnumLarge cur : enumSet) {
            cur.doSomething();
        }
    }

    @Benchmark
    public void forEachHashSet() {
        for(TestEnumLarge cur : hashSet) {
            cur.doSomething();
        }
    }

    @Benchmark
    public void forEachReferenceHashSet() {
        for(TestEnumLarge cur : referenceHashSet) {
            cur.doSomething();
        }
    }

    @Benchmark
    public void forEachReferenceArraySet() {
        for(TestEnumLarge cur : referenceArraySet) {
            cur.doSomething();
        }
    }

    @Benchmark
    public void forEachIntHashSet() {
        final TestEnumLarge[] values = TestEnumLarge.values();
        for(int cur : intHashSet) {
            TestEnumLarge curEnum = values[cur];
            curEnum.doSomething();
        }
    }

    @Benchmark
    public void forEachIntArraySet() {
        final TestEnumLarge[] values = TestEnumLarge.values();
        for(int cur : intArraySet) {
            TestEnumLarge curEnum = values[cur];
            curEnum.doSomething();
        }
    }
}
