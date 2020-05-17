package common.order;

import common.Operation;
import common.OperationResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LeiDongxing
 * created on 2020/5/17
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderOperation extends Operation {
    private int tableId;
    private String dish;

    @Override
    public OperationResult execute() {
        System.out.println("order's executing startup with orderRequest: " + toString());
        System.out.println("order's executing complete");
        return new OrderOperationResult(tableId, dish, true);
    }
}
