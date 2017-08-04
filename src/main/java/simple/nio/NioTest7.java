package simple.nio;

import java.nio.ByteBuffer;

/**
 * read-only buffer
 * 可以随时将一个普通buffer调用{@link ByteBuffer#asReadOnlyBuffer()}方法返回一个只读buffer
 * 但不能将一个只读buffer转换为读写buffer
 *
 * @author hyperglory
 * @date 2017/8/4 17:15
 */
public class NioTest7 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println(buffer.getClass());

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        System.out.println(readOnlyBuffer.getClass());
    }
}
