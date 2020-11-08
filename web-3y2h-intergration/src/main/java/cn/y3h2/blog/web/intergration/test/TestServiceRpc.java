package cn.y3h2.blog.web.intergration.test;

import cn.y3h2.blog.user.api.TestFacade;
import cn.y3h2.blog.user.api.domain.TestParam;
import cn.y3h2.blog.user.common.dto.test.TestDTO;
import cn.y3h2.blog.user.common.model.Response;
import cn.y3h2.blog.web.common.excaption.ExceptionFactory;
import cn.y3h2.blog.web.common.excaption.RpcException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestServiceRpc {

    @Autowired
    private TestFacade testFacade;

    public TestDTO test(String str) throws RpcException {
        try {
            TestParam param = new TestParam();
            param.setStr(str);

            Response<TestDTO> response = testFacade.test(param);

            if (response == null) {
                log.warn("TestServiceRpc#test RPC return is null, str: {}", str);
            }
            if (!response.isSuccess()) {
                log.warn("TestServiceRpc#test RPC fail cause: {}, str: {}", response.getMessage(), str);
                throw ExceptionFactory.getRpcException("", "");
            }
            return response.getData();
        } catch (Exception e) {
            log.error("TestServiceRpc#test error:{}",e);
            throw e;
        }
    }

}
