package hodei.naiz.teammoraleemail.config;

import hodei.naiz.teammoraleemail.services.subscriber.EmailServiceMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by Hodei Eceiza
 * Date: 1/20/2022
 * Time: 10:56
 * Project: TeamMoraleEmail
 * Copyright: MIT
 */
@Configuration
public class PubSubConfiguration {

    @Bean
    public ReactiveRedisOperations<String, EmailServiceMessage> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        RedisSerializer<EmailServiceMessage> valueSerializer = new Jackson2JsonRedisSerializer<>(EmailServiceMessage.class);
        RedisSerializationContext<String, EmailServiceMessage> serializationContext = RedisSerializationContext.<String,EmailServiceMessage>newSerializationContext(RedisSerializer.string())
                .value(valueSerializer)
                .build();
        return new ReactiveRedisTemplate<>(lettuceConnectionFactory, serializationContext);
    }

}
