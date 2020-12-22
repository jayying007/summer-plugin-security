package org.summer.plugin.security;

import org.summer.framework.helper.ConfigHelper;
import org.summer.framework.util.ReflectionUtil;

/**
 * @author ZhuangJieYing
 * @date 2020/12/22
 */
public class SecurityConfig {
    public static String getRealms() {
        return ConfigHelper.getString(SecurityConstant.REALMS);
    }

    public static SummerSecurity getSummerSecurity() {
        String className = ConfigHelper.getString(SecurityConstant.SUMMER_SECURITY);
        return (SummerSecurity) ReflectionUtil.newInstance(className);
    }

    public static String getJdbcAuthcQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_AUTHC_QUERY);
    }

    public static String getJdbcRolesQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_ROLES_QUERY);
    }

    public static String getJdbcPermissionsQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_PERMISSIONS_QUERY);
    }

    public static boolean isCache() {
        return ConfigHelper.getBoolean(SecurityConstant.CACHE);
    }
}
