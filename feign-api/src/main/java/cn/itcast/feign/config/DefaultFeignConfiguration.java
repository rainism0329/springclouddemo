package cn.itcast.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @version 1.0
 * @program: cloud-demo
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/12/19 8:58 PM
 */
public class DefaultFeignConfiguration {

    @Bean
    public Logger.Level logLeve(){
        return Logger.Level.BASIC;
    }
}
