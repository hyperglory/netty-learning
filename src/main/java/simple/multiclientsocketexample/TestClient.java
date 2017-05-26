package simple.multiclientsocketexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author hyperglory
 * @date 2017/5/25 14:25
 */
public class TestClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new TestClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();

            // 获取控制台的输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // 死循环，将客户端输入信息发送到服务端
            for (;;) {
                channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
            }

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
