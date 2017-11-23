package attendance_manager.utils;

import java.util.UUID;

public class BusKeyGen {

    public static String nextKey() {
        return UUID.randomUUID().toString();
    }
}