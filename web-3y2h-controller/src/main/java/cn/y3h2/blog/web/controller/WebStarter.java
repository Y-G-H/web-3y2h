package cn.y3h2.blog.web.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
        "cn.y3h2.blog.web.intergration",
        "cn.y3h2.blog.web.biz",
        "cn.y3h2.blog.web.controller"
})
@Configuration
@EnableScheduling
@ImportResource("classpath:/spring/dubbo.xml")
public class WebStarter {
    public static void main(String[] args) {
        SpringApplication.run(WebStarter.class, args);
    }

}
