package com.alibaba.dubbo.rpc.protocol.hsm;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.Assert;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.remoting.Codec2;
import com.alibaba.dubbo.remoting.Decodeable;
import com.alibaba.dubbo.remoting.buffer.ChannelBuffer;
import com.alibaba.dubbo.remoting.exchange.Response;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.RpcResult;

import java.io.IOException;
import java.util.Arrays;

public class DecodeableRpcResult extends RpcResult implements Codec2, Decodeable {

    private static final Logger log = LoggerFactory.getLogger(DecodeableRpcResult.class);

    private Response response;

    private byte[] body;  //header + command + error code + body

    private volatile boolean hasDecoded;

    public DecodeableRpcResult(Response response, byte[] body) {
        Assert.notNull(response, "response == null");
        this.response = response;
        this.body = body;
    }

    @Override
    public void encode(Channel channel, ChannelBuffer buffer, Object message) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object decode(Channel channel, ChannelBuffer buffer) throws IOException {
        //Type[] returnType = RpcUtils.getReturnTypes(invocation)
        //setException(Throwable e)
        //setAttachments(Map<String, String> map)
        setValue(body);
        return this;
    }

    @Override
    public void decode() throws Exception {
        if (!hasDecoded) {
            try {
                decode(null, null);
            } catch (Throwable e) {
                if (log.isWarnEnabled()) {
                    log.warn("Decode rpc result failed: " + e.getMessage(), e);
                }
                response.setStatus(Response.CLIENT_ERROR);
                response.setErrorMessage(StringUtils.toString(e));
            } finally {
                hasDecoded = true;
            }
        }
    }

}

