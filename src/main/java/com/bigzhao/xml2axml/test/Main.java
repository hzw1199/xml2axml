package com.bigzhao.xml2axml.test;

import android.content.Context;
import com.bigzhao.xml2axml.Encoder;
import org.apache.commons.io.FileUtils;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by Roy on 15-10-6.
 */
public class Main {
    public static void main(String args[]) throws IOException, XmlPullParserException {
        if (args[0].startsWith("e")) {
            encode(args[1],args[2]);
        }else if (args[0].startsWith("d")){
            decode(args[1],args[2]);
        }else if (args[0].startsWith("t")) {
            decode(args[1], args[2]);
            encode(args[2], args[3]);
            decode(args[3], args[4]);
        }
    }

    public static void encode(String in,String out) throws IOException, XmlPullParserException {
        Encoder e = new Encoder();
        byte[] bs = e.encodeFile(new Context(), in);
        FileUtils.writeByteArrayToFile(new File(out), bs);
    }

    public static void decode(String in,String out) throws FileNotFoundException {
        AXMLPrinter.out=new PrintStream(new File(out));
        AXMLPrinter.main(new String[]{in});
        AXMLPrinter.out.close();
    }
}
