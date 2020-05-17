package common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LeiDongxing
 * created on 2020/5/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class OperationResult extends MessageBody {
}
