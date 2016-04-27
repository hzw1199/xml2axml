package com.bigzhao.xml2axml;

import android.content.Context;

import com.bigzhao.xml2axml.chunks.Chunk;
import com.bigzhao.xml2axml.chunks.StringPoolChunk;
import com.bigzhao.xml2axml.chunks.TagChunk;
import com.bigzhao.xml2axml.chunks.XmlChunk;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.*;
import java.util.HashSet;

/**
 * Created by Roy on 15-10-4.
 */
public class Encoder {

    public static class Config{
        public static StringPoolChunk.Encoding encoding= StringPoolChunk.Encoding.UNICODE;
        public static int defaultReferenceRadix=16;
    }

    public byte[] encodeFile(Context context,String filename) throws XmlPullParserException, IOException {
        XmlPullParserFactory f=XmlPullParserFactory.newInstance();
        f.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,true);
        XmlPullParser p=f.newPullParser();
        p.setInput(new FileInputStream(filename),"UTF-8");
        return encode(context,p);
    }

    public byte[] encodeString(Context context,String xml) throws XmlPullParserException, IOException {
        XmlPullParserFactory f=XmlPullParserFactory.newInstance();
        f.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,true);
        XmlPullParser p=f.newPullParser();
        p.setInput(new StringReader(xml));
        return encode(context,p);
    }

    public byte[] encode(Context context,XmlPullParser p) throws XmlPullParserException, IOException {
        if (context==null) context=new Context();
        XmlChunk chunk=new XmlChunk(context);
        //HashSet<String> strings=new HashSet<String>();
        TagChunk current=null;
        for (int i=p.getEventType();i!=XmlPullParser.END_DOCUMENT;i=p.next()){
            switch (i){
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                   /* strings.add(p.getName());
                    strings.add(p.getPrefix());
                    strings.add(p.getNamespace());
                    int ac=p.getAttributeCount();
                    for (int j=0;j<ac;++j){
                        strings.add(p.getAttributeName(j));
                        strings.add(p.getAttributePrefix(j));
                        strings.add(p.getAttributeValue(j));
                    }
                    ac=p.getNamespaceCount(p.getDepth());
                    for (int j=p.getNamespaceCount(p.getDepth()-1);j<ac;++j){
                        strings.add(p.getNamespacePrefix(j));
                        strings.add(p.getNamespaceUri(j));
                    }*/
                    current=new TagChunk(current==null?chunk:current,p);
                    break;
                case XmlPullParser.END_TAG:
                    Chunk c=current.getParent();
                    current=c instanceof TagChunk?(TagChunk)c:null;
                    break;
                case XmlPullParser.TEXT:
                    //strings.add(p.getText());
                    break;
                default:
                    break;

            }
        }
        //for (String s:strings) if (s!=null) chunk.stringPool.addString(s);
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        IntWriter w=new IntWriter(os);
        chunk.write(w);
        w.close();
        return os.toByteArray();
    }
}
