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
public class SetContainsLargeBenchmark {
    private final List<TestEnumLarge> enumValues = Arrays.asList(TestEnumLarge.values());
    private final List<Integer> enumOrdinals = enumValues.stream().map(Enum::ordinal).collect(Collectors.toList());

    private TestEnumLarge enumToGet = TestEnumLarge.DIAMOND_BLOCK;
    private int ordinalToGet = enumToGet.ordinal();

    private final EnumSet<TestEnumLarge> enumSet = EnumSet.allOf(TestEnumLarge.class);
    private final HashSet<TestEnumLarge> hashSet = new HashSet<>(enumValues);
    private final ReferenceSet<TestEnumLarge> referenceHashSet = new ReferenceOpenHashSet<>(enumValues);
    private final ReferenceSet<TestEnumLarge> referenceArraySet = new ReferenceArraySet<>(enumValues);
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
