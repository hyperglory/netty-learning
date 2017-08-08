package simple.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * {@link FileChannel#lock(long, long, boolean)}
 *
 * @author hyperglory
 * @date 2017/8/8 17:47
 */
public class NioTest10 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/java/simple/nio/NioTest10.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        FileLock fileLock = fileChannel.lock(3, 6, true);

        System.out.println("valid: " + fileLock.isValid());
        System.out.println("lock type: " + fileLock.isShared());

        fileLock.release();

        randomAccessFile.close();
    }
}
