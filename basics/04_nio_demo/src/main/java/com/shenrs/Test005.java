package com.shenrs;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @author shenrs
 * @Description 加密器与解码器
 * @create 2018-09-05 14:47
 **/
public class Test005 {
    public static void main(String[] args) throws CharacterCodingException {
        // 获取编码器
        Charset charset = Charset.forName("GBK");
        // 获取加密器
        CharsetEncoder encoder = charset.newEncoder();
        // 获取解码器
        CharsetDecoder decoder = charset.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("牛皮哄哄哈哈..");
        // 读模式
        charBuffer.flip();
        // 编码加密
        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println("加密后的数据：" + new String(bytes, 0, bytes.length));

        // 开启只读模式
        byteBuffer.flip();
        // 编码解密
        CharBuffer cbf = decoder.decode(byteBuffer);
        System.out.println("解密后的数据：" + cbf.toString());

    }
}
