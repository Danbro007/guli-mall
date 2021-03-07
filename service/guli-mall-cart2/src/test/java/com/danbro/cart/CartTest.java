package com.danbro.cart;

import java.math.BigDecimal;
import javax.annotation.Resource;
import com.danbro.cart.controller.vo.CartItemVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Classname CartTest
 * @Description TODO
 * @Date 2021/3/7 12:20
 * @Created by Administrator
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest {
    @Resource
    StringRedisTemplate redisTemplate;
    @Test
    public void test1(){
        String cartKey = "123";
        BoundHashOperations<String, Long, CartItemVo> hashOperations = redisTemplate.boundHashOps(cartKey);
        CartItemVo cartItemVo = new CartItemVo().setCheck(true).setPrice(BigDecimal.ONE).setSkuId(1L).setTitle("title");
        hashOperations.put(1L,cartItemVo);
        CartItemVo cartItemVo1 = hashOperations.get(1L);
        System.out.println(cartItemVo1);
    }
}
