package common.codec;

import common.ResponseMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author LeiDongxing
 * created on 2020/5/17
 */
public class OrderProtocolEncoder extends MessageToMessageEncoder<ResponseMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseMessage responseMessage, List<Object> list) throws Exception {
        ByteBuf byteBuf=channelHandlerContext.alloc().buffer();
        responseMessage.encode(byteBuf);

        list.add(byteBuf);
    }
}
