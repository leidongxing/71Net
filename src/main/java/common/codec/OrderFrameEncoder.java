package common.codec;

import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @author LeiDongxing
 * created on 2020/5/17
 */
public class OrderFrameEncoder extends LengthFieldPrepender {
    public OrderFrameEncoder(int lengthFieldLength) {
        super(2);
    }
}
