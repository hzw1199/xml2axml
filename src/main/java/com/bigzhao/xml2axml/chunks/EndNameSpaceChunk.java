package com.bigzhao.xml2axml.chunks;

import com.bigzhao.xml2axml.IntWriter;

import java.io.IOException;

/**
 * Created by Roy on 15-10-5.
 */
public class EndNameSpaceChunk extends Chunk<EndNameSpaceChunk.H>{



    public class H extends Chunk.NodeHeader{
        public H() {
            super(ChunkType.XmlEndNamespace);
            size=0x18;
        }
    }
    public StartNameSpaceChunk start;
    public EndNameSpaceChunk(Chunk parent, StartNameSpaceChunk start) {
        super(parent);
        this.start=start;
    }
    @Override
    public void writeEx(IntWriter w) throws IOException {
        start.writeEx(w);
    }
}