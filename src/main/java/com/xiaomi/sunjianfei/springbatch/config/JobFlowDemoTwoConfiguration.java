package com.xiaomi.sunjianfei.springbatch.config;

/**
 * Created by sunjianfei on 2019/6/13.
 */

import com.xiaomi.sunjianfei.springbatch.listener.JobListener;
import com.xiaomi.sunjianfei.springbatch.tasklet.PrintTextTasklet;
import com.xiaomi.sunjianfei.springbatch.tasklet.RandomFailTasket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@EnableBatchProcessing
@Slf4j
public class JobFlowDemoTwoConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Resource
    private JobListener jobListener;            //简单的JOB listener


    @Bean
    public Job job() {
        return jobBuilderFactory.get("my_flow").
                flow(stepA()).on("FAILED").to(stepC()).
                from(stepA()).on("*").to(stepB()).next(stepC()).end().listener(jobListener).build();
    }

    @Bean
    public Step stepA() {
        return stepBuilderFactory.get("stepA")
                .tasklet(new RandomFailTasket("stepA")).build();
    }

    @Bean
    public Step stepB() {
        return stepBuilderFactory.get("stepB")
                .tasklet(new PrintTextTasklet("stepB")).build();
    }

    @Bean
    public Step stepC() {

        return stepBuilderFactory.get("stepC")
                .tasklet(new PrintTextTasklet("stepC")).build();
    }

    /*@Bean
    public Step jobFlowDemoTwoStep1() {
        return stepBuilderFactory.get("jobFlowDemoTwoStep1")
                .tasklet(((stepContribution, chunkContext) -> {
                    log.info("jobFlowDemoTwoStep1");
                    return RepeatStatus.FINISHED;
                })).build();
    }

    @Bean
    public Step jobFlowDemoTwoStep2() {
        return stepBuilderFactory.get("jobFlowDemoTwoStep2")
                .tasklet(((stepContribution, chunkContext) -> {
                    log.info("jobFlowDemoTwoStep2");
                    return RepeatStatus.FINISHED;
                })).build();
    }

    @Bean
    public Step jobFlowDemoTwoStep3() {
        return stepBuilderFactory.get("jobFlowDemoTwoStep3")
                .tasklet(((stepContribution, chunkContext) -> {
                    log.info("jobFlowDemoTwoStep3");
                    return RepeatStatus.FINISHED;
                })).build();
    }

    @Bean
    public Flow jobFlowDemoFlow1() {
        return new FlowBuilder<Flow>("jobFlowDemoFlow1")
                .start(jobFlowDemoTwoStep1())
                .next(jobFlowDemoTwoStep2())
                .build();
    }

    @Bean
    public Job jobFlowDemoTwoJob() {
        log.info("jobFlowDemoTwoJob");
        return jobBuilderFactory.get("my_flow")
                .incrementer(new RunIdIncrementer())
                .start(jobFlowDemoFlow1())
                .next(jobFlowDemoTwoStep3()).end()
                .listener(jobListener)
                .build();
    }
*/
}