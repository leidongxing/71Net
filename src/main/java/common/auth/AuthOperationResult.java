package common.auth;

import common.OperationResult;
import lombok.Data;

/**
 * @author LeiDongxing
 * created on 2020/5/17
 */
@Data
public class AuthOperationResult extends OperationResult {
    private final boolean passAuth;
}
