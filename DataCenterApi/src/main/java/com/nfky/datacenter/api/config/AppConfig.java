package com.nfky.datacenter.api.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.nfky.datacenter.api.DataCenterApiApp;
import org.apache.ibatis.session.ExecutorType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by lyr on 2017/6/14.
 */
@Configuration
public class AppConfig {

    @Bean
    public EhCacheManagerFactoryBean commonCacheManagerFactory() {
        EhCacheManagerFactoryBean cache = new EhCacheManagerFactoryBean();
        cache.setConfigLocation(new ClassPathResource("ehcache/ehcache.xml"));
        return cache;
    }

    @Bean(name = "commonCacheManager")
    public EhCacheCacheManager commonCacheManager() {
        EhCacheCacheManager commonCache = new EhCacheCacheManager();
        commonCache.setCacheManager(commonCacheManagerFactory().getObject());

        return commonCache;
    }

    @Bean
    public DruidDataSource dataSource(Environment env) throws Exception {
        Properties jdbcProp = new Properties();
        Resource resource = new ClassPathResource("properties/jdbc.properties");
        jdbcProp.load(resource.getInputStream());
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.configFromPropety(jdbcProp);
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(30);

        return taskExecutor;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DruidDataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionF = new SqlSessionFactoryBean();
        sqlSessionF.setDataSource(dataSource);
        sqlSessionF.setConfigLocation(new ClassPathResource("mybatis/mybatis.xml"));
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //只加载一个绝对匹配Resource，且通过ResourceLoader.getResource进行加载
        Resource[] resources = resolver.getResources("classpath*:com/nfky/**/mapper/map-*.xml");
        sqlSessionF.setMapperLocations(resources);
        sqlSessionF.setFailFast(true);
        sqlSessionF.setTypeAliases(new Class[]{java.io.Serializable.class});

        return sqlSessionF;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(DruidDataSource dataSource) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory(dataSource).getObject(), ExecutorType.SIMPLE);

        return sqlSessionTemplate;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DruidDataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);

        return transactionManager;
    }

    @Bean
    Properties properties() throws Exception {
        Properties prop = new Properties();
        Resource resource = new ClassPathResource("properties/sysconfig.properties");
        prop.load(resource.getInputStream());
        return prop;
    }

}
