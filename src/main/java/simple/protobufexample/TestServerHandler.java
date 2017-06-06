package simple.protobufexample;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hyperglory
 * @date 2017/6/6 11:33
 */
@ChannelHandler.Sharable
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {
        System.out.println(msg);
    }
}
