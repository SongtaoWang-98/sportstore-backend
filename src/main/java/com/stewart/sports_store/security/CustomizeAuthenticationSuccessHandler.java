package com.stewart.sports_store.security;

import com.alibaba.fastjson.JSON;
import com.stewart.sports_store.enums.LoginCode;
import com.stewart.sports_store.util.ResultVOUtil;
import com.stewart.sports_store.vo.ResultVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Authentication authentication) throws IOException, ServletException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        ResultVO result = ResultVOUtil.success(name);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
