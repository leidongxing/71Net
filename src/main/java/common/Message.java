package common;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import util.JsonUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author LeiDongxing
 * created on 2020/5/17
 */
@Data
public abstract class Message<T extends MessageBody> {
    private MessageHeader messageHeader;
    private T messageBody;

    public T getMessageBody() {
        return messageBody;
    }

    public void encode(ByteBuf byteBuf) {
        byteBuf.writeInt(messageHeader.getVersion());
        byteBuf.writeLong(messageHeader.getStreamId());
        byteBuf.writeInt(messageHeader.getOpCode());
        byteBuf.writeBytes(JsonUtil.toJson(messageBody).getBytes());
    }


    public abstract Class<T> getMessageBodyDecodeClass(int opcode);


    public void decode(ByteBuf msg) {
        int version = msg.readInt();
        long streamId = msg.readLong();
        int opCode = msg.readInt();

        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setVersion(version);
        messageHeader.setStreamId(streamId);
        this.messageHeader = messageHeader;

        Class<T> bodyClazz=getMessageBodyDecodeClass(opCode);
        this.messageBody = JsonUtil.fromJson(msg.toString(StandardCharsets.UTF_8), bodyClazz);
    }
}
