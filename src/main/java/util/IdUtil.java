package util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author LeiDongxing
 * created on 2020/5/17
 */
public class IdUtil {
    private static final AtomicLong IDX = new AtomicLong();

    private IdUtil(){
        //no instance
    }

    public static long nextId(){
        return IDX.incrementAndGet();
    }
}
