package cn.y3h2.blog.web.controller.controller;

import cn.y3h2.blog.web.common.WebResponse;
import cn.y3h2.blog.web.common.dto.common.req.TestParam;
import cn.y3h2.blog.web.controller.annotation.WebResponseHandler;
import cn.y3h2.blog.web.controller.biz.TestBiz;
import cn.y3h2.blog.web.controller.vo.TestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/y3h2/test")
public class TestController {

    @Autowired
    private TestBiz testBiz;

    /**
     * 这里需要有注释，解释这个接口的作用
     * @param param
     * @return
     */
    @PostMapping("/str")
    @WebResponseHandler
    public WebResponse<TestVO> test(@RequestBody TestParam param) {
        return WebResponse.ok(testBiz.test(param));
    }

}
