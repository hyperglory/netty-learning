package simple.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author hyperglory
 * @date 2017/8/28 17:34
 */
public class NioTest12 {
    public static void main(String[] args) throws IOException {
        int[] ports = new int[5];

        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();

        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            SocketChannel socketChannel = serverSocketChannel.accept();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(ports[i]);
            socketChannel.bind(inetSocketAddress);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("监听端口: " + ports[i]);
        }

        while (true) {
            int numbers = selector.select();
            System.out.println("numbers: " + numbers);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            System.out.println("selectedKeys: " + selectionKeys);

            Iterator<SelectionKey> iter = selectionKeys.iterator();

            while (iter.hasNext()) {
                SelectionKey selectionKey = iter.next();

                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);

                    // 将该selectionKey移除，这一步特别重要，否则之后的遍历会抛异常
                    iter.remove();

                    System.out.println("获得客户端连接: " + socketChannel);
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    int bytesRead = 0;
                    ByteBuffer buffer = ByteBuffer.allocate(512);

                    while (true) {
                        buffer.clear();

                        int read = socketChannel.read(buffer);

                        if (read <= 0) {
                            break;
                        }

                        buffer.flip();

                        socketChannel.write(buffer);

                        bytesRead += read;
                    }

                    System.out.println("读取: " + bytesRead + ", 来自于: " + socketChannel);

                    // 将该selectionKey移除，这一步特别重要，否则之后的遍历会抛异常
                    iter.remove();
                }
            }
        }
    }
}
