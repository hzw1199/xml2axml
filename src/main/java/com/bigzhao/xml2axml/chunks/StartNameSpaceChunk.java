package com.bigzhao.xml2axml.chunks;

import com.bigzhao.xml2axml.IntWriter;

import java.io.IOException;

/**
 * Created by Roy on 15-10-5.
 */
public class StartNameSpaceChunk extends Chunk<StartNameSpaceChunk.H>{

    public StartNameSpaceChunk(Chunk parent) {
        super(parent);
    }

    public class H extends Chunk.NodeHeader{
        public H() {
            super(ChunkType.XmlStartNamespace);
            size=0x18;
        }
    }

    public String prefix;
    public String uri;

    @Override
    public void writeEx(IntWriter w) throws IOException {
        w.write(stringIndex(null,prefix));
        w.write(stringIndex(null,uri));
    }
}
