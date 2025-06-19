package im.mingxi.authsystem.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class DataUtil {
    public static byte[] readAllBytes(InputStream inp) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int read;
        while ((read = inp.read(buffer)) != -1) out.write(buffer, 0, read);
        return out.toByteArray();
    }

    private static final byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }
}