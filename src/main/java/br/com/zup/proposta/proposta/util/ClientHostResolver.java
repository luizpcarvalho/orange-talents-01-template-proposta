package br.com.zup.proposta.proposta.util;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ClientHostResolver {

    private HttpServletRequest request;

    public ClientHostResolver(HttpServletRequest request) {
        this.request = request;
    }

    public String resolve(HttpServletRequest request) {

        String xRealIp = request.getHeader("X-Real-IP");
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        String remoteAddr = request.getRemoteAddr();

        if (xRealIp != null)
            return xRealIp;

        return ObjectUtils.firstNonNull(xForwardedFor, remoteAddr);
    }

}
