package com.bigzhao.xml2axml;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.bigzhao.xml2axml.test.AXMLPrinter;

import java.io.*;

/**
 * Created by Roy on 16-4-27.
 */
public class AxmlUtils {

    public static String decode(byte[] data) {
        try(InputStream is=new ByteArrayInputStream(data)) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            AXMLPrinter.out = new PrintStream(os);
            AXMLPrinter.decode(is);
            byte[] bs = os.toByteArray();
            IOUtils.closeQuietly(os);
            AXMLPrinter.out.close();
            return new String(bs, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static String decode(File file) throws IOException {
        return decode(FileUtils.readFileToByteArray(file));
    }

    public static byte[] encode(String xml){
        try {
            Encoder encoder = new Encoder();
            return encoder.encodeString(null, xml);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static byte[] encode(File file){
        try {
            Encoder encoder = new Encoder();
            return encoder.encodeFile(null, file.getAbsolutePath());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
