package benchmark.map;

import benchmark.TestEnumSmall;
import it.unimi.dsi.fastutil.ints.Int2ReferenceArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ReferenceMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceArrayMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import org.openjdk.jmh.annotations.*;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MapGetSmallBenchmark {
    private TestEnumSmall enumToGet = TestEnumSmall.ANDESITE;
    private int ordinalToGet = enumToGet.ordinal();

    private final EnumMap<TestEnumSmall, TestEnumSmall> enumMap = new EnumMap<>(TestEnumSmall.class);
    private final HashMap<TestEnumSmall, TestEnumSmall> hashMap = new HashMap<>();
    private final Reference2ReferenceMap<TestEnumSmall, TestEnumSmall> referenceHashMap = new Reference2ReferenceOpenHashMap<>();
    private final Reference2ReferenceMap<TestEnumSmall, TestEnumSmall> referenceArrayMap = new Reference2ReferenceArrayMap<>();
    private final Int2ReferenceMap<TestEnumSmall> intHashMap = new Int2ReferenceArrayMap<>();
    private final Int2ReferenceMap<TestEnumSmall> intArrayMap = new Int2ReferenceArrayMap<>();

    @Setup
    public void setup() {
        for(TestEnumSmall cur : TestEnumSmall.values()) {
            enumMap.put(cur, cur);
            hashMap.put(cur, cur);
            referenceHashMap.put(cur, cur);
            referenceArrayMap.put(cur, cur);
            intHashMap.put(cur.ordinal(), cur);
            intArrayMap.put(cur.ordinal(), cur);
        }
    }

    @Benchmark
    public Object containsEnumMap() {
        return enumMap.get(enumToGet);
    }

    @Benchmark
    public Object containsHashMap() {
        return hashMap.get(enumToGet);
    }

    @Benchmark
    public Object containsReferenceHashMap() {
        return referenceHashMap.get(enumToGet);
    }

    @Benchmark
    public Object containsReferenceArrayMap() {
        return referenceArrayMap.get(enumToGet);
    }

    @Benchmark
    public Object containsIntHashMap() {
        return intHashMap.get(ordinalToGet);
    }

    @Benchmark
    public Object containsIntArrayMap() {
        return intArrayMap.get(ordinalToGet);
    }
}
