package com.wjx.myblog.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    /**
     * 创建一个线程池，最多同时运行 1 个任务，任务队列大小为 10，超时时间为 60 秒。
     */
    @Bean(name="threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor customThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // 核心线程数
        executor.setMaxPoolSize(5); // 最大线程数
        executor.setQueueCapacity(10); // 任务队列大小
        executor.setKeepAliveSeconds(60); // 空闲线程存活时间
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy()); // 超时时的拒绝策略：丢弃最旧的任务
        executor.initialize();
        return executor;
    }
}
