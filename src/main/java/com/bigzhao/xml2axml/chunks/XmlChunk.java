package com.bigzhao.xml2axml.chunks;

import android.content.Context;

import com.bigzhao.xml2axml.DefaultReferenceResolver;
import com.bigzhao.xml2axml.IntWriter;
import com.bigzhao.xml2axml.ReferenceResolver;

import java.io.IOException;

/**
 * Created by Roy on 15-10-4.
 */



public class XmlChunk extends Chunk<XmlChunk.H>{
    public XmlChunk(Context context) {
        super(null);
        this.context=context;
    }

    public class H extends Chunk.Header {

        public H() {
            super(ChunkType.Xml);
        }

        @Override
        public void writeEx(IntWriter w) throws IOException {

        }
    }
    public StringPoolChunk stringPool=new StringPoolChunk(this);
    public ResourceMapChunk resourceMap=new ResourceMapChunk(this);
    public TagChunk content;

    @Override
    public void preWrite() {
        header.size=header.headerSize+content.calc()+stringPool.calc()+resourceMap.calc();
    }

    @Override
    public void writeEx(IntWriter w) throws IOException {
        stringPool.write(w);
        resourceMap.write(w);
        content.write(w);
    }

    @Override
    public XmlChunk root() {
        return this;
    }

    private ReferenceResolver referenceResolver;
    @Override
    public ReferenceResolver getReferenceResolver() {
        if (referenceResolver==null) referenceResolver= new DefaultReferenceResolver();
        return referenceResolver;
    }
}
