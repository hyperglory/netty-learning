package simple.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author hyperglory
 * @date 2017/7/22 01:41
 */
public class NioTest2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("src/main/java/simple/nio/NioTest2.txt");
        FileChannel channel = fileInputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        channel.read(buffer);

        buffer.flip();

        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            System.out.print((char) b);
        }

        fileInputStream.close();
    }
}
