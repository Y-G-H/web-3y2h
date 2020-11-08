package cn.y3h2.blog.web.biz.test.impl;

import cn.y3h2.blog.user.common.dto.test.TestDTO;
import cn.y3h2.blog.web.biz.test.TestService;
import cn.y3h2.blog.web.intergration.test.TestServiceRpc;
import com.alibaba.dubbo.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.y3h2.blog.web.common.excaption.ExceptionFactory;

@Slf4j
@Service("testService")
public class TestSericeImpl implements TestService {

    @Autowired
    private TestServiceRpc testServiceRpc;

    @Override
    public TestDTO test(String str) {
        if (StringUtils.isBlank(str)) throw ExceptionFactory.getBusinessException("", "测试str为空");

        return testServiceRpc.test(str);
    }
}
