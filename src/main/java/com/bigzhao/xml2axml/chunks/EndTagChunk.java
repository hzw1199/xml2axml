package com.bigzhao.xml2axml.chunks;

import com.bigzhao.xml2axml.IntWriter;

import java.io.IOException;

/**
 * Created by Roy on 15-10-5.
 */
public class EndTagChunk extends Chunk<EndTagChunk.H>{
    public class H extends Chunk.NodeHeader{

        public H() {
            super(ChunkType.XmlEndElement);
            this.size=24;
        }
    }

    public StartTagChunk start;
    public EndTagChunk(Chunk parent,StartTagChunk start) {
        super(parent);
        this.start=start;
    }

    @Override
    public void writeEx(IntWriter w) throws IOException {
        w.write(stringIndex(null,start.namespace));
        w.write(stringIndex(null,start.name));
    }
}
