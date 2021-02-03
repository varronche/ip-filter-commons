package br.com.intelipost.ipfiltercommons;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Order(1)
@Component
@Slf4j
public class HttpServletLogsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestId = request.getHeader(HttpLogsProperties.REQUEST_ID);

        if (StringUtils.isEmpty(requestId)){
            requestId = UUID.randomUUID().toString();
        }

        request.setAttribute(HttpLogsProperties.REQUEST_ID, requestId);
        response.setHeader(HttpLogsProperties.REQUEST_ID, requestId);

        MDC.put(HttpLogsProperties.REQUEST_ID, requestId);

        filterChain.doFilter(request, response);
    }
}
