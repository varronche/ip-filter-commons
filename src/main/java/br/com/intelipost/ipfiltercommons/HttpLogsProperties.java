package br.com.intelipost.ipfiltercommons;

import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HttpLogsProperties {
    public static final String REQUEST_ID = "Request-Id";

    public static String getRequestIdAttributes(){

        String requestId;
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

        if(requestAttributes == null){
            requestId = MDC.get(HttpLogsProperties.REQUEST_ID);
        }else {
            HttpServletRequest request = requestAttributes.getRequest();
            requestId = request.getAttribute(HttpLogsProperties.REQUEST_ID).toString();
        }
        return requestId;
    }
}
