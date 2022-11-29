package Casino.ID;

import java.util.concurrent.atomic.AtomicInteger;

public final class IDCreator {
    private static final AtomicInteger atomicInteger = new AtomicInteger(10000);
    public static int getUniqueID() {
        return atomicInteger.incrementAndGet();
    }
}
