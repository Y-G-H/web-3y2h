package cn.y3h2.blog.web.controller.biz;

import cn.y3h2.blog.user.common.dto.test.TestDTO;
import cn.y3h2.blog.web.biz.test.TestService;
import cn.y3h2.blog.web.common.dto.common.req.TestParam;
import cn.y3h2.blog.web.controller.vo.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestBiz {

    @Autowired
    private TestService testService;

    public TestVO test(TestParam param) {
        TestDTO test = testService.test(param.getStr());

        TestVO testVO = new TestVO();
        testVO.setStr(test.getStr());
        return testVO;
    }

}
