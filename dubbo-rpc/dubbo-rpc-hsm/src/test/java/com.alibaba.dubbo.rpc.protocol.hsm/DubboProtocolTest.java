package com.alibaba.dubbo.rpc.protocol.hsm;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.remoting.Codec2;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.alibaba.dubbo.rpc.protocol.hsm.common.BitOperator;
import com.alibaba.dubbo.rpc.protocol.hsm.support.DemoService;
import io.netty.buffer.ByteBufUtil;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DubboProtocolTest {
    private Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
    private ProxyFactory proxy = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();

    @Test
    public void testDemoProtocol() throws Exception {
        //DemoService service = new DemoServiceImpl();
        //protocol.export(proxy.getInvoker(service, DemoService.class, URL.valueOf("dubbo://127.0.0.1:9020/" + DemoService.class.getName() + "?codec=exchange")));
        URL url = URL.valueOf("hsm://192.168.14.80:1818/" + DemoService.class.getName() + "?codec=hsm");
        Invoker<DemoService> invoker = protocol.refer(DemoService.class, url);
        DemoService service = proxy.getProxy(invoker);

        String c = "34" + "10240";
        byte[] ret = service.sayHello(c.getBytes("US-ASCII"));
        System.out.println("ret = " + ByteBufUtil.hexDump(ret));
    }

    @Test
    public void testUtil() {
        //String ocx = BitOperator.stringToAscii("3");
        //System.out.println("ocx：" + ocx);
        //System.out.println(ByteBufUtil.hexDump(ByteBufUtil.decodeHexDump("00000000")));
        //byte[] o = ByteBufUtil.decodeHexDump("001030303030303030303334313032343031");

        byte[] dst = new byte[8];
        BitOperator.long2AsciiBytes(0, dst, 0, 8);
        //String s= ByteBufUtil.hexDump(dst);

        long v = BitOperator.asciiBytes2Long(dst, 0, 8);
    }

}
