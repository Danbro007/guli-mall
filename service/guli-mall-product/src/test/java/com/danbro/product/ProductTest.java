package com.danbro.product;


import java.util.List;
import com.danbro.product.service.PmsCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2021/1/27 21:42
 * @Created by Administrator
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {
    @Autowired
    PmsCategoryService pmsCategoryService;

    @Test
    public void test1() {
        System.out.println("123");
    }

    @Test
    public void test2() {
        List<Long> cateLogPath = pmsCategoryService.findCateLogPath(255L);
        System.out.println(cateLogPath);
    }
}
