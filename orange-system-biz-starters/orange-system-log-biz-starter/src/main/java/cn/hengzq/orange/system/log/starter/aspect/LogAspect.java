package cn.hengzq.orange.system.log.starter.aspect;

import cn.hengzq.orange.common.enums.support.OperationStatusEnum;
import cn.hengzq.orange.common.enums.support.RequestMethodEnum;
import cn.hengzq.orange.common.servlet.ServletHolder;
import cn.hengzq.orange.context.GlobalContextHelper;
import cn.hengzq.orange.system.common.biz.log.constant.LoginTypeEnum;
import cn.hengzq.orange.system.common.biz.log.vo.login.param.AddLoginLogParam;
import cn.hengzq.orange.system.common.biz.log.vo.operation.param.AddOperationLogParam;
import cn.hengzq.orange.system.common.biz.permission.vo.param.LoginParam;
import cn.hengzq.orange.system.log.starter.config.LogProperties;
import cn.hengzq.orange.system.log.starter.event.LoginLogEvent;
import cn.hengzq.orange.system.log.starter.event.OperationLogEvent;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.json.JSONUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.util.AntPathMatcher;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 日志拦截器
 *
 * @author hengzq
 */
@Slf4j
@Aspect
public class LogAspect {

    private final ApplicationContext applicationContext;

    private final LogProperties properties;

    private final AntPathMatcher pathMatcher;

    private static final Set<String> OPERATION_RECORD_SKIP_URL_LIST = Set.of(
            "/**/operation-log/**", "/**/login-log/**",
            "/**/v3/api-docs", "/**/v3/api-docs/**");
    private static final List<String> LOGIN_URL_LIST = List.of("/**/login");
    private static final List<String> LOGOUT_URL_LIST = List.of("/**/logout");

    public LogAspect(ApplicationContext applicationContext, LogProperties properties) {
        this.applicationContext = applicationContext;
        this.pathMatcher = new AntPathMatcher();
        this.properties = properties;
        if (CollUtil.isEmpty(this.properties.getIgnoreUrls())) {
            this.properties.setIgnoreUrls(OPERATION_RECORD_SKIP_URL_LIST);
        } else {
            this.properties.getIgnoreUrls().addAll(OPERATION_RECORD_SKIP_URL_LIST);
        }
    }

    @Around("@annotation(operation)")
    public Object record(ProceedingJoinPoint point, Operation operation) throws Throwable {
        // 请求开始时间
        LocalDateTime startTime = LocalDateTime.now();
        try {
            Object result = point.proceed();
            this.process(point, operation, startTime, result, null);
            return result;
        } catch (Throwable exception) {
            this.process(point, operation, startTime, null, exception);
            throw exception;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("operation log is complete.");
            }
        }
    }

    private void process(ProceedingJoinPoint point, Operation operation, LocalDateTime startTime, Object result, Throwable exception) {
        HttpServletRequest request = ServletHolder.getHttpServletRequest();
        if (Objects.isNull(request)) {
            log.warn("request is null.");
            return;
        }
        String requestURI = request.getRequestURI();
        if (isIgnoreUrl(request)) {
            return;
        }
        if (isMatch(requestURI, LOGIN_URL_LIST)) {
            applicationContext.publishEvent(new LoginLogEvent(generateAddLoginLogParam(request, LoginTypeEnum.LOGIN, point, startTime, exception)));
        } else if (isMatch(requestURI, LOGOUT_URL_LIST)) {
            applicationContext.publishEvent(new LoginLogEvent(generateAddLoginLogParam(request, LoginTypeEnum.LOGOUT, point, startTime, exception)));
        } else {
            applicationContext.publishEvent(new OperationLogEvent(generateAddOperationLogParam(request, operation, point, startTime, result, exception)));
        }
    }

    private AddOperationLogParam generateAddOperationLogParam(HttpServletRequest request, Operation operation, ProceedingJoinPoint point,
                                                              LocalDateTime startTime, Object result, Throwable exception) {
        LocalDateTime endTime = LocalDateTime.now();

        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?> declaringType = signature.getDeclaringType();
        Tag tag = declaringType.getAnnotation(Tag.class);

        return AddOperationLogParam.builder()
                .requestId(GlobalContextHelper.getRequestId())
                .resourceId(operation.operationId())
                .resourceName(tag.name() + "-" + operation.summary())
                .requestUrl(request.getRequestURI())
                .requestMethod(RequestMethodEnum.valueOf(request.getMethod()))
                .javaMethod(point.getSignature().toString())
                .javaMethodArgs(JSONUtil.toJsonStr(point.getArgs()))
                .javaMethodResult(JSONUtil.toJsonStr(result))
                .userId(GlobalContextHelper.getUserId())
                .userAgent(ServletHolder.getUserAgent(request))
                .startTime(startTime)
                .endTime(endTime)
                .userIp(getClientIp(request))
                .duration(LocalDateTimeUtil.between(startTime, endTime).toMillis())
                .status(Objects.isNull(exception) ? OperationStatusEnum.SUCCESS : OperationStatusEnum.FAIL)
                .stackTrace(Objects.nonNull(exception) ? ExceptionUtil.stacktraceToString(exception) : "")
                .build();
    }

    private AddLoginLogParam generateAddLoginLogParam(HttpServletRequest request, LoginTypeEnum typeEnum, ProceedingJoinPoint point, LocalDateTime startTime, Throwable exception) {
        String account = GlobalContextHelper.getUserInfo().getLoginAccount();
        if (StrUtil.isBlank(account)) {
            Object[] args = point.getArgs();
            LoginParam loginParam = (LoginParam) args[0];
            account = loginParam.getLoginAccount();
        }
        return AddLoginLogParam.builder()
                .requestId(GlobalContextHelper.getRequestId())
                .account(account)
                .type(typeEnum)
                .userId(GlobalContextHelper.getUserId())
                .userIp(getClientIp(request))
                .userAgent(ServletHolder.getUserAgent(request))
                .loginTime(startTime)
                .status(Objects.isNull(exception) ? OperationStatusEnum.SUCCESS : OperationStatusEnum.FAIL)
                .build();
    }

    String getClientIp(HttpServletRequest request) {
        String clientIP = JakartaServletUtil.getClientIP(request);
        return "0:0:0:0:0:0:0:1".equals(clientIP) ? "127.0.0.1" : clientIP;
    }


    /**
     * URL 是否匹配
     */
    private boolean isMatch(String url, List<String> list) {
        if (CollUtil.isEmpty(list) || StrUtil.isBlank(url)) {
            return false;
        }
        for (String pattern : list) {
            if (pathMatcher.match(pattern, url)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 忽略的URL
     */
    private boolean isIgnoreUrl(HttpServletRequest request) {
        if (CollUtil.isEmpty(this.properties.getIgnoreUrls())) {
            return false;
        }
        for (String pattern : this.properties.getIgnoreUrls()) {
            if (pathMatcher.match(pattern, request.getRequestURI())) {
                return true;
            }
        }
        return false;
    }
}
