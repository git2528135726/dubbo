package com.alibaba.dubbo.rpc.protocol.hsm.message;

public enum MessageType {

    RSA_GEN_KEY("34"),                  //产生密钥对
    RSA_GEN_KEY_ACK("35"),              //产生密钥对返回
    RSA_PUB_ENCRYPT("30"),              //用公钥加密
    RSA_PRI_SIGN("37"),                 //用私钥签名
    RSA_PUB_TRANSFORM_ENCRYPT_LMK("GI");//DES(AES)密钥从公钥下加密转换为LMK下加密

    //=================SHA1 SHA256=================
    //public static final String SHA1 = "GT1";//475431
    //public static final String SHA256 = "GT2";//475432

    private final String type;

    private MessageType(final String type) {
        this.type = type;
    }

    public String getMessageType() {
        return this.type;
    }

    public static MessageType create(final String i) {

        for (final MessageType messageType : MessageType.values()) {
            if (messageType.type.equals(i)) {
                return messageType;
            }
        }
        throw new IllegalArgumentException("Invalid Message Type: " + i);
    }

}
