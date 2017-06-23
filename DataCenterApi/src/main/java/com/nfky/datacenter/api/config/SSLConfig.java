package com.nfky.datacenter.api.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * https配置
 *
 * Created by lyr on 2017/6/9.
 */
@Configuration
public class SSLConfig {

    @Value("${server.ssl.urls}")
    String urls;
    @Value("${server.non-ssl-port}")
    int port;
    @Value("${server.port}")
    int sslPort;

    @Bean
    public EmbeddedServletContainerFactory servletContainer(Connector httpConnector) {
        TomcatEmbeddedServletContainerFactory container = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                if(!StringUtils.isEmpty(urls)) {
                    String[] urlArr = urls.split(",");
                    for(String url : urlArr) {
                        collection.addPattern(url);
                    }
                }
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        container.addAdditionalTomcatConnectors(httpConnector);
        return container;
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(port);
        connector.setSecure(false);
        connector.setRedirectPort(sslPort);
        return connector;
    }
}
