package common.auth;

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
public class AuthOperation extends Operation {
    private final String userName;
    private final String password;


    @Override
    public OperationResult execute() {
        if ("admin".equalsIgnoreCase(this.userName)) {
            return new AuthOperationResult(true);
        }
        return new AuthOperationResult(false);
    }
}
