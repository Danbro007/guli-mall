package com.danbro.seckill;

import com.danbro.seckill.service.SecKillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Pattern;

/**
 * @author Danrbo
 * @Classname SecKillTest
 * @Description TODO
 * @Date 2021/3/16 14:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SecKillTest {
    @Autowired
    SecKillService secKillService;

    @Test
    public void test() {
        String regx = "\\d_" + 1364075483166224386L;
        boolean matches = Pattern.matches(regx, "12323_1364075483166224386");
        System.out.println(matches);

    }
}
