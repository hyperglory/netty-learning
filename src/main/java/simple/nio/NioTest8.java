package simple.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * {@link ByteBuffer#allocateDirect(int)}
 * 堆外内存&&零拷贝
 * 通过调用JNI直接操作JVM堆外内存，以实现更高的内存操作和访问效率
 *
 * @author hyperglory
 * @date 2017/8/7 15:01
 */
public class NioTest8 {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("src/main/java/simple/nio/input2.txt");
        FileOutputStream outputStream = new FileOutputStream("src/main/java/simple/nio/output2.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(512);

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
