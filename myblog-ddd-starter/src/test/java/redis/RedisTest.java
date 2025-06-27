package redis;

import com.wjx.myblog.SpringbootMyblogApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMyblogApplication.class)
@Slf4j
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads2() {
        //添加缓存键值对name:mijiu并设置过期时间为1小时
        stringRedisTemplate.opsForValue().set("name1", "miumiu", 100, TimeUnit.SECONDS);
        System.out.println(stringRedisTemplate.opsForValue().get("name"));

    }
}