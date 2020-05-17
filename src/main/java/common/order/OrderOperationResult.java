package common.order;

import common.OperationResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LeiDongxing
 * created on 2020/5/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderOperationResult extends OperationResult {
    private final int tableId;
    private final String dish;
    private final boolean complete;
}
