package simple.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author hyperglory
 * @date 2017/7/22 01:49
 */
public class NioTest3 {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/simple/nio/NioTest3.txt");
        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        byte[] messages = "hello world welcome".getBytes();

        for (int i = 0; i < messages.length; i++) {
            buffer.put(messages[i]);
        }

        buffer.flip();
        channel.write(buffer);

        fileOutputStream.close();
    }
}
