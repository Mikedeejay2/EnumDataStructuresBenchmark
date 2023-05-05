package benchmark;

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
public class SetForEachBenchmark {
    private final List<TestEnum> enumValues = Arrays.asList(TestEnum.values());
    private final List<Integer> enumOrdinals = enumValues.stream().map(Enum::ordinal).collect(Collectors.toList());

    private final EnumSet<TestEnum> enumSet = EnumSet.allOf(TestEnum.class);
    private final HashSet<TestEnum> hashSet = new HashSet<>(enumValues);
    private final ReferenceSet<TestEnum> referenceHashSet = new ReferenceOpenHashSet<>(enumValues);
    private final ReferenceSet<TestEnum> referenceArraySet = new ReferenceArraySet<>(enumValues);
    private final IntSet intHashSet = new IntOpenHashSet(enumOrdinals);
    private final IntSet intArraySet = new IntArraySet(enumOrdinals);

    @Benchmark
    public void forEachEnumSet() {
        for(TestEnum cur : enumSet) {
            cur.doSomething();
        }
    }

    @Benchmark
    public void forEachHashSet() {
        for(TestEnum cur : hashSet) {
            cur.doSomething();
        }
    }

    @Benchmark
    public void forEachReferenceHashSet() {
        for(TestEnum cur : referenceHashSet) {
            cur.doSomething();
        }
    }

    @Benchmark
    public void forEachReferenceArraySet() {
        for(TestEnum cur : referenceArraySet) {
            cur.doSomething();
        }
    }

    @Benchmark
    public void forEachIntHashSet() {
        for(int cur : intHashSet) {
            TestEnum curEnum = TestEnum.values()[cur];
            curEnum.doSomething();
        }
    }

    @Benchmark
    public void forEachIntArraySet() {
        for(int cur : intArraySet) {
            TestEnum curEnum = TestEnum.values()[cur];
            curEnum.doSomething();
        }
    }
}
