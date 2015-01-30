package com.fx.oss.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * HiMVC单元测试基类。
 * 
 * @author Weiyong Huang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/application-test-config.xml"})
public class OssUnitT {
}
