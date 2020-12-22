package org.summer.plugin.security;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * @author ZhuangJieYing
 * @date 2020/12/22
 */
public class SummerSecurityPlugin implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> handlesTypes, ServletContext servletContext) throws ServletException {
        // 设置初始化参数
        servletContext.setInitParameter("shiroConfigLocations", "classpath:summer-security.ini");
        // 注册 Listener
        servletContext.addListener(EnvironmentLoaderListener.class);
        // 注册 Filter
        FilterRegistration.Dynamic summerSecurityFilter = servletContext.addFilter("SummerSecurityFilter", SummerSecurityFilter.class);
        summerSecurityFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
