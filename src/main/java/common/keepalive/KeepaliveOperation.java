package common.keepalive;

import common.Operation;
import common.OperationResult;

/**
 * @author LeiDongxing
 * created on 2020/5/17
 */
public class KeepaliveOperation extends Operation {
    private final long time;

    public KeepaliveOperation() {
        this.time = System.nanoTime();
    }

    @Override
    public OperationResult execute() {
        return new KeepaliveOperationResult(time);
    }
}
