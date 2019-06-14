package com.xiaomi.sunjianfei.springbatch.tasklet;

/**
 * Created by sunjianfei on 2019/6/13.
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
public class RandomFailTasket extends PrintTextTasklet {

    public RandomFailTasket(String text) {
        super(text);
    }

    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
            throws Exception {
        double seed = Math.random();
        log.error("seed={}",seed);
        if ( false && seed < 0.5){
            throw new Exception("fail");
            //return BatchStatus.FAILED;
            //return RepeatStatus.CONTINUABLE; //用来控制重试
        }
        return RepeatStatus.FINISHED;
    }

}