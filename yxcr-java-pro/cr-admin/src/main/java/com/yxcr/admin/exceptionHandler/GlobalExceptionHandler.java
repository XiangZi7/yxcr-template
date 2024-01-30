package com.yxcr.admin.exceptionHandler;


import com.yxcr.common.enums.apiResultEnum;
import com.yxcr.common.model.ApiResult;
import com.yxcr.common.model.BusinessException;
import com.yxcr.common.model.NotLogException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 全局异常拦截
    @ExceptionHandler(Exception.class)
    public ApiResult<?> handlerException(Exception e) {
        logger.error("全局异常捕获，异常消息:" + e.getMessage());
        saveLog();
        return ApiResult.error(e.getMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    public ApiResult<?> handlerNotLoginException(NotLoginException e) {
        logger.error("登录失效异常:" + e.getMessage());
        saveLog();
        return ApiResult.error(apiResultEnum.NOT_LOGIN.getCode(), apiResultEnum.NOT_LOGIN.getDesc());
    }

    @ExceptionHandler(NotPermissionException.class)
    public ApiResult<?> handlerNotPermissionException(NotPermissionException e) {
        logger.error("权限不足异常:" + e.getMessage());
        saveLog();
        return ApiResult.error(apiResultEnum.NOT_PERMISSION.getCode(), apiResultEnum.NOT_PERMISSION.getDesc());
    }

    @ExceptionHandler(BusinessException.class)
    public ApiResult<?> handlerBusinessException(BusinessException e) {
        logger.error("业务逻辑异常: " + e.getErrorMsg());
        saveLog();
        return ApiResult.error(e.getErrorCode(), e.getErrorMsg());
    }

    @ExceptionHandler(NotLogException.class)
    public ApiResult<?> handlerNotLogException(NotLogException e) {
        return ApiResult.error(e.getErrorCode(), e.getErrorMsg());
    }

    @ExceptionHandler(NotRoleException.class)
    public ApiResult<?> handlerNotRoleException(NotRoleException e) {
        logger.error("角色未知异常: " + e.getMessage());
        saveLog();
        return ApiResult.error(apiResultEnum.NOT_PERMISSION.getCode(), apiResultEnum.NOT_PERMISSION.getDesc());
    }

    private void saveLog() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            logger.info("=============== handlerException-START ===============");
            logger.info("请求IP：" + request.getRemoteAddr());
            logger.info("请求地址：" + request.getRequestURL().toString());
            logger.info("请求方式：" + request.getMethod());
            logger.info("=============== 请求内容-END ===============");
        }
    }
}