package cn.y3h2.blog.web.controller.controller;

import cn.y3h2.blog.user.api.Test2Facade;
import cn.y3h2.blog.user.api.TestFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/y3h2/test")
public class TestController {

    @Autowired
    TestFacade testFacade;

    @Autowired
    Test2Facade test2Facade;

    @GetMapping
    public String test() {
        return testFacade.test("hahahaha, 牛逼");
    }

    @GetMapping("/text")
    public String text() {
        return test2Facade.test("傻逼东西");
    }

}
