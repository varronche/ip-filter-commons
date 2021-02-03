package br.com.intelipost.ipfiltercommons;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HttpFeignLogsFilter implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String header = HttpLogsProperties.getRequestIdAttributes();
        template.header(HttpLogsProperties.REQUEST_ID, header);
    }
}