package com.bigzhao.xml2axml.chunks;

import com.bigzhao.xml2axml.IntWriter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Roy on 15-10-6.
 */
public class ResourceMapChunk extends Chunk<ResourceMapChunk.H>{

    public class H extends Chunk.Header{

        public H() {
            super(ChunkType.XmlResourceMap);
        }

        @Override
        public void writeEx(IntWriter w) throws IOException {

        }
    }

    public ResourceMapChunk(Chunk parent) {
        super(parent);
    }

    private LinkedList<Integer> ids;

    @Override
    public void preWrite() {
        List<StringPoolChunk.RawString> rss=stringPool().rawStrings;
        ids=new LinkedList<Integer>();
        for (StringPoolChunk.RawString r:rss){
            if (r.origin.id>=0){
                ids.add(r.origin.id);
            }else{
                break;
            }
        }
        header.size=ids.size()*4+header.headerSize;
    }

    @Override
    public void writeEx(IntWriter w) throws IOException {
        for (int i:ids) w.write(i);
    }
}
