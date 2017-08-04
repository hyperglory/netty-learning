package simple.nio;

import java.nio.ByteBuffer;

/**
 * @author hyperglory
 * @date 2017/8/4 16:04
 */
public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(15);
        buffer.putLong(5000000000L);
        buffer.putDouble(14.123);
        buffer.putChar('嘿');
        buffer.putShort((short) 2);
        buffer.putChar('哈');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());
    }
}
