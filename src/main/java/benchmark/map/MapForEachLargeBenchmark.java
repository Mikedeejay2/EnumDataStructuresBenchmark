package benchmark.map;

import benchmark.TestEnumLarge;
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
public class MapForEachLargeBenchmark {
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
    public void forEachEnumMap() {
        for(TestEnumLarge cur : enumMap.keySet()) {
            enumMap.get(cur).doSomething();
        }
    }

    @Benchmark
    public void forEachHashMap() {
        for(TestEnumLarge cur : hashMap.keySet()) {
            hashMap.get(cur).doSomething();
        }
    }

    @Benchmark
    public void forEachReferenceHashMap() {
        for(TestEnumLarge cur : referenceHashMap.keySet()) {
            referenceHashMap.get(cur).doSomething();
        }
    }

    @Benchmark
    public void forEachReferenceArrayMap() {
        for(TestEnumLarge cur : referenceArrayMap.keySet()) {
            referenceArrayMap.get(cur).doSomething();
        }
    }

    @Benchmark
    public void forEachIntHashMap() {
        for(int cur : intHashMap.keySet()) {
            intHashMap.get(cur).doSomething();
        }
    }

    @Benchmark
    public void forEachIntArrayMap() {
        for(int cur : intArrayMap.keySet()) {
            intArrayMap.get(cur).doSomething();
        }
    }
}
