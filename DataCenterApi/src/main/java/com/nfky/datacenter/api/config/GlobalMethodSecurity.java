package com.nfky.datacenter.api.config;

import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

/**
 * Created by lyr on 2017/6/19.
 */
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class GlobalMethodSecurity extends GlobalMethodSecurityConfiguration{

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }
}
