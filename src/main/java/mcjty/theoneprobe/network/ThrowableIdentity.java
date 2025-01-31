package mcjty.theoneprobe.network;

import mcjty.theoneprobe.config.ConfigSetup;
import mcjty.theoneprobe.TheOneProbe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ThrowableIdentity {
    private final String identifier;

    private static final Map<ThrowableIdentity, Long> catchedThrowables = new HashMap<>();

    public static void registerThrowable(Throwable e) {
        ThrowableIdentity identity = new ThrowableIdentity(e);
        long curtime = System.currentTimeMillis();
        if (catchedThrowables.containsKey(identity)) {
            long lasttime = catchedThrowables.get(identity);
            if (curtime < lasttime + ConfigSetup.loggingThrowableTimeout) {
                // If this exception occurred less than some time ago we don't report it.
                return;
            }
        }
        catchedThrowables.put(identity, curtime);
        TheOneProbe.setup.getLogger().debug("The One Probe catched error: ", e);
    }

    public ThrowableIdentity(Throwable e) {
        String message = e.getMessage();
        StringBuilder builder = new StringBuilder(message == null ? "<null>" : message);
        StackTraceElement[] st = e.getStackTrace();
        for (int i = 0; i < Math.min(3, st.length); i++) {
            builder
                    .append(st[i].getClassName())
                    .append(st[i].getFileName())
                    .append(st[i].getMethodName())
                    .append(st[i].getLineNumber());
        }
        identifier = builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThrowableIdentity that = (ThrowableIdentity) o;

        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return identifier != null ? identifier.hashCode() : 0;
    }
}
