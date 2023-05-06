package benchmark.set;

import benchmark.TestEnumSmall;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.ReferenceArraySet;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SetContainsSmallBenchmark {
    private final List<TestEnumSmall> enumValues = Arrays.asList(TestEnumSmall.values());
    private final List<Integer> enumOrdinals = enumValues.stream().map(Enum::ordinal).collect(Collectors.toList());

    private TestEnumSmall enumToGet = TestEnumSmall.ANDESITE;
    private int ordinalToGet = enumToGet.ordinal();

    private final EnumSet<TestEnumSmall> enumSet = EnumSet.allOf(TestEnumSmall.class);
    private final HashSet<TestEnumSmall> hashSet = new HashSet<>(enumValues);
    private final ReferenceSet<TestEnumSmall> referenceHashSet = new ReferenceOpenHashSet<>(enumValues);
    private final ReferenceSet<TestEnumSmall> referenceArraySet = new ReferenceArraySet<>(enumValues);
    private final IntSet intHashSet = new IntOpenHashSet(enumOrdinals);
    private final IntSet intArraySet = new IntArraySet(enumOrdinals);

    @Benchmark
    public boolean containsEnumSet() {
        return enumSet.contains(enumToGet);
    }

    @Benchmark
    public boolean containsHashSet() {
        return hashSet.contains(enumToGet);
    }

    @Benchmark
    public boolean containsReferenceHashSet() {
        return referenceHashSet.contains(enumToGet);
    }

    @Benchmark
    public boolean containsReferenceArraySet() {
        return referenceArraySet.contains(enumToGet);
    }

    @Benchmark
    public boolean containsIntHashSet() {
        return intHashSet.contains(ordinalToGet);
    }

    @Benchmark
    public boolean containsIntArraySet() {
        return intArraySet.contains(ordinalToGet);
    }
}
