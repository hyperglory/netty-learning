package simple.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * {@link FileChannel#map(FileChannel.MapMode, long, long)}
 *
 * @author hyperglory
 * @date 2017/8/8 17:32
 */
public class NioTest9 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/java/simple/nio/NioTest9.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        buffer.put(0, (byte) 'a');
        buffer.put(3, (byte) 'b');

        randomAccessFile.close();
    }
}
