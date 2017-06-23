package com.nfky.datacenter.api.security;

import com.nfky.datacenter.api.dao.app.AppInfoRepository;
import com.nfky.datacenter.api.entity.app.AppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lyr on 2017/6/16.
 */
@Component
public class ApiClientDetailsService implements ClientDetailsService {

    @Autowired
    AppInfoRepository appInfoRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        AppInfo appInfo = appInfoRepository.findByAppId(s);
        List<String> roles = jdbcTemplate.query("select a.app_id,b.role from app_roles a join roles b" +
                " on a.role = b.id where a.app_id = ?", new Object[]{appInfo.getAppId()}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("role");
            }
        });

        List<String> resources = jdbcTemplate.query("select a.app_id,b.resource_id from app_resource a join resources b" +
                " on a.resource_id = b.id where a.app_id = ?", new Object[]{appInfo.getAppId()}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("resource_id");
            }
        });

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (roles != null) {
            for (String role : roles) {
                SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
                authorities.add(grantedAuthority);
            }
        }

        BaseClientDetails details = new BaseClientDetails();

        details.setClientId(appInfo.getAppId());
        details.setClientSecret(appInfo.getAppKey());
        details.setAuthorizedGrantTypes(Arrays.asList("client_credentials"));
        details.setAccessTokenValiditySeconds(7200);
        details.setScope(roles);
        details.setAuthorities(authorities);
        details.setResourceIds(resources);

        return details;
    }

}
