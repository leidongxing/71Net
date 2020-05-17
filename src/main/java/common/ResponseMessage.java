package common;

/**
 * @author LeiDongxing
 * created on 2020/5/17
 */
public class ResponseMessage extends Message<OperationResult> {
    @Override
    public Class getMessageBodyDecodeClass(int opcode) {
        return OperationType.fromOpCode(opcode).getOperationClazz();
    }
}
