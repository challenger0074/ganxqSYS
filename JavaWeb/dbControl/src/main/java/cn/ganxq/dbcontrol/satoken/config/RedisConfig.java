package cn.ganxq.dbcontrol.satoken.config;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置类，更换默认序列化器
 *
 * @author Peter Cheung
 * @since 2023-07-28 13:51:55
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    /**
     * 创建 RedisTemplate Bean，用于操作 Redis 数据库。
     *
     * @param connectionFactory Redis 连接工厂
     * @return RedisTemplate 实例
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置 RedisTemplate 的连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        // 创建 StringRedisSerializer 实例，用于序列化和反序列化 Redis 的键和哈希键
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 创建 GenericJackson2JsonRedisSerializer 实例，用于序列化和反序列化 Redis 的值和哈希值
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        // 设置默认序列化器为 StringRedisSerializer
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        // 设置 RedisTemplate 的键序列化器为 StringRedisSerializer
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // 设置 RedisTemplate 的哈希键序列化器为 StringRedisSerializer
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // 设置 RedisTemplate 的值序列化器为 GenericJackson2JsonRedisSerializer
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        // 设置 RedisTemplate 的哈希值序列化器为 GenericJackson2JsonRedisSerializer
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        return redisTemplate;
    }
}

