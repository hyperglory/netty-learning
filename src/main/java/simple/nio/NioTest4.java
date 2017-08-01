package simple.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author hyperglory
 * @date 2017/8/1 17:35
 */
public class NioTest4 {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("src/main/java/simple/nio/input.txt");
        FileOutputStream outputStream = new FileOutputStream("src/main/java/simple/nio/output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (true) {
            // clear()在此是必要的，否则会无限写入数据
            buffer.clear();

            int read = inputChannel.read(buffer);
            System.out.println(read);

            if (read == -1) {
                break;
            }

            buffer.flip();

            outputChannel.write(buffer);
        }

        inputChannel.close();
        outputChannel.close();
    }
}
