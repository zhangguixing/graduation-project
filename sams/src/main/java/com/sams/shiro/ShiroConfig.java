//package com.sams.shiro;
//
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.cache.ehcache.EhCacheManager;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//
//import javax.servlet.Filter;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class ShiroConfig {
//
//    @Bean(name = "shiroFilter")
//    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
//        shiroFilter.setSecurityManager(securityManager);
//        // 登录配置
//        shiroFilter.setLoginUrl("/login");
//        shiroFilter.setSuccessUrl("/");
//        shiroFilter.setUnauthorizedUrl("/error?code=403");
//        // 自定义过滤器
//        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
//        filtersMap.put("myLoginFilter", new MyLoginFilter());
//        shiroFilter.setFilters(filtersMap);
//        // 拦截配置
//        Map<String, String> filterChainDefinitions = new LinkedHashMap<>();
//        filterChainDefinitions.put("/assets/**", "anon");
//        filterChainDefinitions.put("/module/**", "anon");
//        filterChainDefinitions.put("/api/**", "anon");
//        filterChainDefinitions.put("/druid/**", "anon");
//        filterChainDefinitions.put("/login", "anon");
//        filterChainDefinitions.put("/logout", "logout");
//        filterChainDefinitions.put("/**", "myLoginFilter,authc");
//        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitions);
//        return shiroFilter;
//    }
//
//    @Bean(name = "userRealm")
//    @DependsOn("lifecycleBeanPostProcessor")
//    public UserRealm userRealm() {
//        UserRealm userRealm = new UserRealm();
//        userRealm.setCredentialsMatcher(credentialsMatcher());
//        return userRealm;
//    }
//
//    @Bean(name = "securityManager")
//    public DefaultWebSecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(userRealm());
//        securityManager.setCacheManager(cacheManager());
//        return securityManager;
//    }
//
//    @Bean(name = "cacheManager")
//    public EhCacheManager cacheManager() {
//        EhCacheManager cacheManager = new EhCacheManager();
//        cacheManager.setCacheManagerConfigFile("classpath:shiro/ehcache-shiro.xml");
//        return cacheManager;
//    }
//
//    @Bean(name = "credentialsMatcher")
//    public HashedCredentialsMatcher credentialsMatcher() {
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        credentialsMatcher.setHashAlgorithmName("md5");  //散列算法
//        credentialsMatcher.setHashIterations(3);  //散列次数
//        return credentialsMatcher;
//    }
//
//    @Bean(name = "lifecycleBeanPostProcessor")
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
//        return lifecycleBeanPostProcessor;
//    }
//
//    /**
//     * shiro里实现的Advisor类,用来拦截注解的方法 .
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager());
//        return advisor;
//    }
//
//    @Bean
//    @DependsOn({"lifecycleBeanPostProcessor"})
//    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }
//}
