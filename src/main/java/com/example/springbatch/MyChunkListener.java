package com.example.springbatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyChunkListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext context) {
    }

    @Override
    public void afterChunk(ChunkContext context) {

    }

    @Override
    public void afterChunkError(ChunkContext context) {

    }
}
