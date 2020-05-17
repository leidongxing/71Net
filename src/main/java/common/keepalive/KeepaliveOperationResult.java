package common.keepalive;

import common.OperationResult;
import lombok.Data;

/**
 * @author LeiDongxing
 * created on 2020/5/17
 */
@Data
public class KeepaliveOperationResult extends OperationResult {
    private final long time;
}
