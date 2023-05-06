package benchmark.map;

import benchmark.TestEnumLarge;
import it.unimi.dsi.fastutil.ints.*;
import it.unimi.dsi.fastutil.objects.*;
import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MapGetLargeBenchmark {
    private TestEnumLarge enumToGet = TestEnumLarge.DIAMOND_BLOCK;
    private int ordinalToGet = enumToGet.ordinal();

    private final EnumMap<TestEnumLarge, TestEnumLarge> enumMap = new EnumMap<>(TestEnumLarge.class);
    private final HashMap<TestEnumLarge, TestEnumLarge> hashMap = new HashMap<>();
    private final Reference2ReferenceMap<TestEnumLarge, TestEnumLarge> referenceHashMap = new Reference2ReferenceOpenHashMap<>();
    private final Reference2ReferenceMap<TestEnumLarge, TestEnumLarge> referenceArrayMap = new Reference2ReferenceArrayMap<>();
    private final Int2ReferenceMap<TestEnumLarge> intHashMap = new Int2ReferenceArrayMap<>();
    private final Int2ReferenceMap<TestEnumLarge> intArrayMap = new Int2ReferenceArrayMap<>();

    @Setup
    public void setup() {
        for(TestEnumLarge cur : TestEnumLarge.values()) {
            enumMap.put(cur, cur);
            hashMap.put(cur, cur);
            referenceHashMap.put(cur, cur);
            referenceArrayMap.put(cur, cur);
            intHashMap.put(cur.ordinal(), cur);
            intArrayMap.put(cur.ordinal(), cur);
        }
    }

    @Benchmark
    public Object containsEnumSet() {
        return enumMap.get(enumToGet);
    }

    @Benchmark
    public Object containsHashSet() {
        return hashMap.get(enumToGet);
    }

    @Benchmark
    public Object containsReferenceHashSet() {
        return referenceHashMap.get(enumToGet);
    }

    @Benchmark
    public Object containsReferenceArraySet() {
        return referenceArrayMap.get(enumToGet);
    }

    @Benchmark
    public Object containsIntHashSet() {
        return intHashMap.get(ordinalToGet);
    }

    @Benchmark
    public Object containsIntArraySet() {
        return intArrayMap.get(ordinalToGet);
    }
}
