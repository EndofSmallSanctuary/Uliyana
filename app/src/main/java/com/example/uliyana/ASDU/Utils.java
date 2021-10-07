package com.example.uliyana.ASDU;

public class Utils {

    public static String bytesTOSIREN(byte[] initial){
        StringBuilder stringBuilder = new StringBuilder("");
        for(byte b: initial){
            stringBuilder.append(String.format("%02x",b));
        }
        return stringBuilder.toString();
    }

    public static String bytesToHex(byte[] bytes) {
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStringToByteArray(String s) {
        byte[] data = new byte[s.length()/2];
        for (int i = 0; i < data.length; i ++) {
            data[i] = (byte) ((Character.digit(s.charAt(i*2), 16) << 4)
                    + Character.digit(s.charAt(i*2 + 1), 16));
        }
        return data;
    }


}
