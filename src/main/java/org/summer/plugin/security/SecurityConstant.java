package org.summer.plugin.security;

/**
 * @author ZhuangJieYing
 * @date 2020/12/22
 */
public interface SecurityConstant {
    String REALMS = "summer.plugin.security.realms";
    String REALMS_JDBC = "jdbc";
    String REALMS_CUSTOM = "custom";

    String SUMMER_SECURITY = "summer.plugin.security.custom.class";

    String JDBC_AUTHC_QUERY = "summer.plugin.security.jdbc.authc_query";
    String JDBC_ROLES_QUERY = "summer.plugin.security.jdbc.roles_query";
    String JDBC_PERMISSIONS_QUERY = "summer.plugin.security.jdbc.permissions_query";

    String CACHE = "summer.plugin.security.cache";
}
