package common;

import lombok.Data;

/**
 * @author LeiDongxing
 * created on 2020/5/17
 */
@Data
public class MessageHeader {
    private int version = 1;
    private int opCode;
    private long streamId;
}
