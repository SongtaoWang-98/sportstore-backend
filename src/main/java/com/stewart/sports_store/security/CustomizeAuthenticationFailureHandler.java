package com.stewart.sports_store.security;

import com.alibaba.fastjson.JSON;
import com.stewart.sports_store.enums.LoginCode;
import com.stewart.sports_store.util.ResultVOUtil;
import com.stewart.sports_store.vo.ResultVO;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        ResultVO result = null;
        if (e instanceof AccountExpiredException) {
            //账号过期
            result = ResultVOUtil.fail(LoginCode.USER_ACCOUNT_EXPIRED);
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            result = ResultVOUtil.fail(LoginCode.USER_CREDENTIALS_ERROR);
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            result = ResultVOUtil.fail(LoginCode.USER_CREDENTIALS_EXPIRED);
        } else if (e instanceof DisabledException) {
            //账号不可用
            result = ResultVOUtil.fail(LoginCode.USER_ACCOUNT_DISABLE);
        } else if (e instanceof LockedException) {
            //账号锁定
            result = ResultVOUtil.fail(LoginCode.USER_ACCOUNT_LOCKED);
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = ResultVOUtil.fail(LoginCode.USER_ACCOUNT_NOT_EXIST);
        }else{
            //其他错误
            result = ResultVOUtil.fail(LoginCode.COMMON_FAIL);
        }

        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }

}
