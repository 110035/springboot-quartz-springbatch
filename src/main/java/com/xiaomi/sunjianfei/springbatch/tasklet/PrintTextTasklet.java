package com.xiaomi.sunjianfei.springbatch.tasklet;

/**
 * Created by sunjianfei on 2019/6/13.
 */

import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.concurrent.TimeUnit;

@Slf4j
public class PrintTextTasklet implements Tasklet {

    public LoadingCache<String, String> cahceBuilder = CacheBuilder.newBuilder()
            .maximumSize(10)
            .refreshAfterWrite(20, TimeUnit.SECONDS)
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification removalNotification) {
                    log.error("过期移除:{}", removalNotification.getKey());
                }
            })
            .build(new CacheLoader<String, String>() {

                @Override
                public String load(String key) throws Exception {
                    String value = key + "_" + "end";
                    log.error("加进缓存:{}={}", key, value);

                    return value;
                }
            });


    private final String text;

    public PrintTextTasklet(String text) {
        this.text = text;
    }


    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
            throws Exception {

        log.info(cahceBuilder.get(text));

        return RepeatStatus.FINISHED;
    }

}