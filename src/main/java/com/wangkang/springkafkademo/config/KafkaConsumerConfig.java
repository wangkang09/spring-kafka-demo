package com.wangkang.springkafkademo.config;

import com.wangkang.springkafkademo.inbound.kafka.LoggingConsumerAwareRebalanceListener;
import com.wangkang.springkafkademo.inbound.kafka.LoggingRecordFilterStrategy;
import com.wangkang.springkafkademo.inbound.kafka.LoggingRecordInterceptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

/**
 * 泛型必须是 Obj 不然不行，，
 */
@Configuration
@Slf4j
@AllArgsConstructor
public class KafkaConsumerConfig {

    @Bean
    public LoggingConsumerAwareRebalanceListener loggingConsumerAwareRebalanceListener() {
        return new LoggingConsumerAwareRebalanceListener();
    }

    @Bean
    public LoggingRecordFilterStrategy<Object, Object> loggingRecordFilterStrategy() {
        return new LoggingRecordFilterStrategy<>();
    }

    @Bean
    public LoggingRecordInterceptor<Object, Object> loggingRecordInterceptor() {
        return new LoggingRecordInterceptor<>();
    }

    /**
     * 消费重试及兜底策略。消费失败：发送到 DLT top
     *
     * @param template
     * @return
     */
    @Bean
    public DefaultErrorHandler errorHandler(KafkaTemplate template) {
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(template);
        //兜底失败也不报错
        recoverer.setFailIfSendResultIsError(false);
//        DefaultErrorHandler errorHandler = new DefaultErrorHandler(recoverer, new FixedBackOff(1000L, 1));
        DefaultErrorHandler errorHandler = new DefaultErrorHandler((record, exception) -> {
            log.info("失败后兜底.topic:{},value:{},offset:{}", record.topic(), record.value(), record.offset(), exception);
            recoverer.accept(record, exception);
        }, new FixedBackOff(1000L, 1));
        //则提供有序记录恢复的能力，确保成功处理的记录得到正确确认
        errorHandler.setCommitRecovered(true);
        //在处理完成后确认偏移量，确保记录不会被重复处理
        errorHandler.setAckAfterHandle(true);
        //用于处理错误后重新调整偏移量，以便消息能够被适当地重试
        errorHandler.setSeekAfterError(true);
        return errorHandler;
    }
}
