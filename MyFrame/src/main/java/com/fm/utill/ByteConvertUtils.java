package main.java.com.fm.utill;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import android.util.Log;

public class ByteConvertUtils {  

    /** 
     * 长整形转byte数组 
     *  
     * @param n 
     *            长整形数字 
     * @return 转换后的数组 
     */  
    public static byte[] longToBytes(long n) {  
        byte[] b = new byte[8];  
        b[7] = (byte) (n & 0xff);  
        b[6] = (byte) (n >> 8 & 0xff);  
        b[5] = (byte) (n >> 16 & 0xff);  
        b[4] = (byte) (n >> 24 & 0xff);  
        b[3] = (byte) (n >> 32 & 0xff);  
        b[2] = (byte) (n >> 40 & 0xff);  
        b[1] = (byte) (n >> 48 & 0xff);  
        b[0] = (byte) (n >> 56 & 0xff);  
        return b;  
    }  

    public static short[] ByteToShortArray(byte[] byteArray)
    {
        short[] shortArray = new short[byteArray.length/2];
        ByteBuffer.wrap(byteArray).order(ByteOrder.BIG_ENDIAN).asShortBuffer().get(shortArray);
        return shortArray;
    }
    
    public static byte[] StringToByteArray(String houseNo)
    {

    	Log.i("houseNo.length","houseNo.length"+houseNo.length());
        byte[] byteArray=new byte[houseNo.length()];
        for(int i=0;i<houseNo.length();i++)
        {
        	byteArray[i]=Byte.parseByte(""+houseNo.charAt(i));
        }
        return byteArray;
    }
    
    /** 
     * 长整形转byte数组 
     *  
     * @param n 
     *            长整形数字 
     * @param array 
     *            转换后的结果 
     * @param offset 
     *            从第offset位开始转换 
     */  
    public static void longToBytes(long n, byte[] array, int offset) {  
        array[7 + offset] = (byte) (n & 0xff);  
        array[6 + offset] = (byte) (n >> 8 & 0xff);  
        array[5 + offset] = (byte) (n >> 16 & 0xff);  
        array[4 + offset] = (byte) (n >> 24 & 0xff);  
        array[3 + offset] = (byte) (n >> 32 & 0xff);  
        array[2 + offset] = (byte) (n >> 40 & 0xff);  
        array[1 + offset] = (byte) (n >> 48 & 0xff);  
        array[0 + offset] = (byte) (n >> 56 & 0xff);  
    }  

    /**
     * 将指定byte数组以16进制的形式打印到控制台
     * @param hint String
     * @param b byte[]
     * @return void
     */
    public static String printHexString( byte[] b) {
        StringBuffer returnValue = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase() + " ");
            returnValue.append(hex.toUpperCase() + " ");
        }

        return "[" + returnValue.toString() + "]";
    }

    /**
     *
     * @param b byte[]
     * @return String
     */
    public static String Bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }
    
    /*
     * 字节数组转换成Int数    读取后四个字节的内容长度
     */
    public static int byte2Int(byte[] b) {
        int intValue = 0;
        for (int i = 0; i < b.length; i++) {
            intValue += (b[i] & 0xFF) << (8 * (i));
        }
        return intValue;
    }
    
    /** 
     * bytes 转 Long 
     *  
     * @param array 
     *            要转换的byte 
     * @return long长整形数字 
     */  
    public static long bytesToLong(byte[] array) {  
        return ((((long) array[0] & 0xff) << 56) | (((long) array[1] & 0xff) << 48) | (((long) array[2] & 0xff) << 40)  
                | (((long) array[3] & 0xff) << 32) | (((long) array[4] & 0xff) << 24)  
                | (((long) array[5] & 0xff) << 16) | (((long) array[6] & 0xff) << 8) | (((long) array[7] & 0xff) << 0));  
    }  
    
    public static short[] bytesToShorts(byte byteBuffer[]) {
        int len = byteBuffer.length/2;
        short[] output = new short[len];
        int j = 0;

        for (int i = 0; i < len; i++) {
            output[i] = (short) (byteBuffer[j++] << 8);
            output[i] |= (byteBuffer[j++] & 0xff);

//            output[i] = (short) (byteBuffer[j++] << 8);
//            output[i] += ((0x7f &byteBuffer[j]) + (byteBuffer[j] < 0 ? 128 : 0));
//            j++;
            
        }
        return output;
    }


    public static byte[] shortsToBytes(short shortBuffer[]) {
        int len = shortBuffer.length;
        byte[] output = new byte[len*2];
        int j = 0;

        for (int i = 0; i < len; i++) {
            output[j++] = (byte) (shortBuffer[i] >>> 8);
            output[j++] = (byte) (0xff & shortBuffer[i]);
        }
        return output;
    }
    
    /**
     * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF,
     * 0xD9}
     * 
     * @param src
     *            String
     * @return byte[]
     */
    public static byte[] hexString2Bytes(String src) {
        if (src == null || "".equals(src)) {
            return null;
        }
        byte[] tmp = src.getBytes();
        int len = tmp.length / 2;
        if (len <= 0) {
            return null;
        }
        byte[] ret = new byte[len];

        for (int i = 0; i < len; i++) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }
    
    /**
     * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
     * 
     * @param src0
     *            byte
     * @param src1
     *            byte
     * @return byte
     */
    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
                .byteValue();
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
                .byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    /** 
     * byte数组转长整形数字 
     *  
     * @param array 
     *            要转换的byte数组 
     * @param offset 
     *            从第offset开始转换 
     * @return 转换后的长整形数字 
     */  
    public static long bytesToLong(byte[] array, int offset) {  
        return ((((long) array[offset + 0] & 0xff) << 56) | (((long) array[offset + 1] & 0xff) << 48)  
                | (((long) array[offset + 2] & 0xff) << 40) | (((long) array[offset + 3] & 0xff) << 32)  
                | (((long) array[offset + 4] & 0xff) << 24) | (((long) array[offset + 5] & 0xff) << 16)  
                | (((long) array[offset + 6] & 0xff) << 8) | (((long) array[offset + 7] & 0xff) << 0));  
    }  

    public static byte[] intToBytes(int n) {  
        byte[] b = new byte[4];  
        b[3] = (byte) (n & 0xff);  
        b[2] = (byte) (n >> 8 & 0xff);  
        b[1] = (byte) (n >> 16 & 0xff);  
        b[0] = (byte) (n >> 24 & 0xff);  
        return b;  
    }  

    public static void intToBytes(int n, byte[] array, int offset) {  
        array[3 + offset] = (byte) (n & 0xff);  
        array[2 + offset] = (byte) (n >> 8 & 0xff);  
        array[1 + offset] = (byte) (n >> 16 & 0xff);  
        array[offset] = (byte) (n >> 24 & 0xff);  
    }  

    /** 
     * @param b 
     * @return 
     */  
    public static int bytesToInt(byte b[]) {  
        return b[3] & 0xff | (b[2] & 0xff) << 8 | (b[1] & 0xff) << 16 | (b[0] & 0xff) << 24;  
    } 
    
    /** 
     * @param b 
     * @return 
     */  
    public static int bytesToInt2(byte b[]) {  
        return b[1] & 0xff | (b[0] & 0xff) << 8;  
    }  

    /** 
     * byte 数组转 int 
     *  
     * @param b 
     *            byte数组 
     * @param offset 
     *            从数组的第几位开始转 
     * @return 整形 
     */  
    public static int bytesToInt(byte b[], int offset) {  
        return b[offset + 3] & 0xff | (b[offset + 2] & 0xff) << 8 | (b[offset + 1] & 0xff) << 16  
                | (b[offset] & 0xff) << 24;  
    }  

    /** 
     * 无符号整形转数组 
     *  
     * @param n 
     *            要转换的整形 
     * @return byte数组 
     */  
    public static byte[] uintToBytes(long n) {  
        byte[] b = new byte[4];  
        b[3] = (byte) (n & 0xff);  
        b[2] = (byte) (n >> 8 & 0xff);  
        b[1] = (byte) (n >> 16 & 0xff);  
        b[0] = (byte) (n >> 24 & 0xff);  

        return b;  
    }  

    public static void uintToBytes(long n, byte[] array, int offset) {  
        array[3 + offset] = (byte) (n);  
        array[2 + offset] = (byte) (n >> 8 & 0xff);  
        array[1 + offset] = (byte) (n >> 16 & 0xff);  
        array[offset] = (byte) (n >> 24 & 0xff);  
    }  

    public static long bytesToUint(byte[] array) {  
        return ((long) (array[3] & 0xff)) | ((long) (array[2] & 0xff)) << 8 | ((long) (array[1] & 0xff)) << 16  
                | ((long) (array[0] & 0xff)) << 24;  
    }  

    public static long bytesToUint(byte[] array, int offset) {  
        return ((long) (array[offset + 3] & 0xff)) | ((long) (array[offset + 2] & 0xff)) << 8  
                | ((long) (array[offset + 1] & 0xff)) << 16 | ((long) (array[offset] & 0xff)) << 24;  
    }  

    public static byte[] shortToBytes(short n) {  
        byte[] b = new byte[2];  
        b[1] = (byte) (n & 0xff);  
        b[0] = (byte) ((n >> 8) & 0xff);  
        return b;  
    }  

    public static void shortToBytes(short n, byte[] array, int offset) {  
        array[offset + 1] = (byte) (n & 0xff);  
        array[offset] = (byte) ((n >> 8) & 0xff);  
    }  

    public static short bytesToShort(byte[] b) {  
        return (short) (b[1] & 0xff | (b[0] & 0xff) << 8);  
    }  

    public static short bytesToShort(byte[] b, int offset) {  
        return (short) (b[offset + 1] & 0xff | (b[offset] & 0xff) << 8);  
    }  

    public static byte[] ushortToBytes(int n) {  
        byte[] b = new byte[2];  
        b[1] = (byte) (n & 0xff);  
        b[0] = (byte) ((n >> 8) & 0xff);  
        return b;  
    }  

    public static void ushortToBytes(int n, byte[] array, int offset) {  
        array[offset + 1] = (byte) (n & 0xff);  
        array[offset] = (byte) ((n >> 8) & 0xff);  
    }  

    public static int bytesToUshort(byte b[]) {  
        return b[1] & 0xff | (b[0] & 0xff) << 8;  
    }  

    public static int bytesToUshort(byte b[], int offset) {  
        return b[offset + 1] & 0xff | (b[offset] & 0xff) << 8;  
    }  

    public static byte[] ubyteToBytes(int n) {  
        byte[] b = new byte[1];  
        b[0] = (byte) (n & 0xff);  
        return b;  
    }  

    public static void ubyteToBytes(int n, byte[] array, int offset) {  
        array[0] = (byte) (n & 0xff);  
    }  

    public static int bytesToUbyte(byte[] array) {  
        return array[0] & 0xff;  
    }  

    public static int bytesToUbyte(byte[] array, int offset) {  
        return array[offset] & 0xff;  
    }  
    // char 类型、 float、double 类型和 byte[] 数组之间的转换关系还需继续研究实现。  


}
