package Casino.ID;

import java.util.concurrent.atomic.AtomicInteger;

public final class IDCreator {
    private static final AtomicInteger atomicIntegerTable = new AtomicInteger(1000);
    private static final AtomicInteger atomicIntegerPlayer = new AtomicInteger(10000);
    public static int getUniqueTableID() {
        return atomicIntegerTable.incrementAndGet();
    }
    public static int getUniquePlayerID() {
        return atomicIntegerPlayer.incrementAndGet();
    }
}
