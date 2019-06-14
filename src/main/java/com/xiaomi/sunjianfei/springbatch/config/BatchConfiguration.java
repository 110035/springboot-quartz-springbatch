package com.xiaomi.sunjianfei.springbatch.config;

import com.xiaomi.sunjianfei.springbatch.listener.JobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.xiaomi.sunjianfei.quartz.QuartzConfiguration;
import com.xiaomi.sunjianfei.springbatch.model.User;
import com.xiaomi.sunjianfei.springbatch.processor.MyProcessor;
import com.xiaomi.sunjianfei.springbatch.reader.MyReader;
import com.xiaomi.sunjianfei.springbatch.writer.MyWriter;

import javax.annotation.Resource;

@Configuration
@EnableBatchProcessing
@Import({QuartzConfiguration.class})
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Resource
    private JobListener jobListener;            //简单的JOB listener

    /*创建job*/
    @Bean
    public Job jobMethod() {
        return jobBuilderFactory.get("lzjJob")
                .start(stepMethod())
                .listener(jobListener)
                .build();
    }

    /*创建step*/
    @Bean
    public Step stepMethod() {
        return stepBuilderFactory.get("myStep")
                .<User, User>chunk(10)
                .reader(new MyReader())
                .processor(new MyProcessor())
                .writer(new MyWriter())
                .allowStartIfComplete(true)
                .build();
    }


}
