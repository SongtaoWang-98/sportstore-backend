package com.stewart.sports_store.security;

import com.alibaba.fastjson.JSON;
import com.stewart.sports_store.enums.LoginCode;
import com.stewart.sports_store.util.ResultVOUtil;
import com.stewart.sports_store.vo.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {
        ResultVO result = ResultVOUtil.fail(LoginCode.USER_NOT_LOGIN);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
