package cn.y3h2.blog.web.controller.aop;

import cn.y3h2.blog.web.common.WebResponse;
import cn.y3h2.blog.web.common.constants.Constants;
import cn.y3h2.blog.web.common.enums.MessageEnums;
import cn.y3h2.blog.web.controller.annotation.WebResponseHandler;
import cn.y3h2.blog.web.common.excaption.RpcException;
import cn.y3h2.blog.web.common.excaption.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Slf4j
@Order(1)
@Component
/**
* @ClassName WebResponseHandlerAspect
* @Author kongming
* @Date 2020/11/8 5:18 下午
* @Description 统一异常处理aop
*/
public class WebResponseHandlerAspect {

    public static final String SEPARATE_SHARP = "#";

    @Pointcut("@annotation(cn.y3h2.blog.web.controller.annotation.WebResponseHandler)")
    public void bizPublicMethod() {
        //pointcut
    }

    @Around("bizPublicMethod()&&@annotation(commonResponseVoHandler)")
    public Object doAround(ProceedingJoinPoint pjp, WebResponseHandler commonResponseVoHandler) {
        String methodName = pjp.getTarget().getClass().getName() + SEPARATE_SHARP + pjp.getSignature().getName();
        try {
            Object o = pjp.proceed();
            return o;
        } catch (BusinessException e) {
            log.warn("{}:{} occur BusinessException:{}", methodName, commonResponseVoHandler.error(), e);
            return WebResponse.fail(e.getErrorCode(),  e.getErrorMsg());
        }catch (RpcException e){
            log.error("{}:{} occur RpcException:{}", methodName, commonResponseVoHandler.error(), e);
            return WebResponse.fail(e.getCode(),  e.getMessage());
        } catch (Exception e) {
            log.error("{}:{} occur Exception:{}", methodName, commonResponseVoHandler.error(), e);
            return WebResponse.fail(MessageEnums.SYS_ERROR.getCode(), Constants.EXCEPTION_MSG);
        } catch (Throwable e) {
            log.error("{}:{} occur error:{}", methodName, commonResponseVoHandler.error(), e);
            return WebResponse.fail(MessageEnums.SYS_ERROR.getCode(), Constants.EXCEPTION_MSG);
        }
    }
}
