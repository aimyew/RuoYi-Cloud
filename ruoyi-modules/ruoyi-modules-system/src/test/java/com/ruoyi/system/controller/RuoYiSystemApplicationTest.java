package com.ruoyi.system.controller;

import com.ruoyi.system.RuoYiSystemApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * BaseTest
 *
 * @date 2022/10/8 16:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RuoYiSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class RuoYiSystemApplicationTest {
    @Test
    public void baseTest() {
        log.info("RuoYiSystemApplicationTest.baseTest()");
    }
}
