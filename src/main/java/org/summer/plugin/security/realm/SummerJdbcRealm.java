package org.summer.plugin.security.realm;

import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.summer.framework.helper.DatabaseHelper;
import org.summer.plugin.security.SecurityConfig;
import org.summer.plugin.security.password.Md5CredentialsMatcher;

/**
 * @author ZhuangJieYing
 * @date 2020/12/22
 */
public class SummerJdbcRealm extends JdbcRealm {
    public SummerJdbcRealm() {
        super.setDataSource(DatabaseHelper.getDataSource());
        super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
        super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
        super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());
        super.setPermissionsLookupEnabled(true);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());
    }
}
