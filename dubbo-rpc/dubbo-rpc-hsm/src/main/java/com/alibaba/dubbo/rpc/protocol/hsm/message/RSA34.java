package com.alibaba.dubbo.rpc.protocol.hsm.message;

public class RSA34 extends AbstractMessage {

    private final String keyLength;

    public RSA34(String keyLength) {
        this.keyLength = keyLength;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.RSA_GEN_KEY;
    }

    public class RSA34ACK implements ACK {

        private final String privateKeyLength; //私钥长度，4个字符
        private final String privateKey;       //LMK加密的私钥密文
        private final String publicKey;        //ANS.1 DER编码的公钥

        private final String errorCode;        //

        public RSA34ACK( final String privateKeyLength,
                        final String privateKey,
                        final String publicKey,
                        final String errorCode) {
            this.privateKeyLength = privateKeyLength;
            this.privateKey = privateKey;
            this.publicKey = publicKey;
            this.errorCode = errorCode;
        }

        @Override
        public MessageType getMessageType() {
            return MessageType.RSA_GEN_KEY_ACK;
        }

        @Override
        public ErrorCode getErrorCode() {
            return ErrorCode.create(errorCode);
        }
    }

}
