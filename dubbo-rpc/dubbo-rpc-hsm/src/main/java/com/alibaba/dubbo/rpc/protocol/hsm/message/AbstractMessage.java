package com.alibaba.dubbo.rpc.protocol.hsm.message;

public class AbstractMessage implements Message {

    @Override
    public MessageType getMessageType() {
        return null;
    }

    public interface ACK extends Message {
        ErrorCode getErrorCode();
    }
}
