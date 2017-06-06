package simple.protobufexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hyperglory
 * @date 2017/6/6 11:40
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
                .setName("glory").setAge(24).setAddress("Beijing")
                .build();

        ctx.channel().writeAndFlush(person);
    }
}
