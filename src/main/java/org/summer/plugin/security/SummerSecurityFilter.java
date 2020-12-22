package org.summer.plugin.security;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.CachingSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.summer.plugin.security.realm.SummerCustomRealm;
import org.summer.plugin.security.realm.SummerJdbcRealm;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author ZhuangJieYing
 * @date 2020/12/22
 */
public class SummerSecurityFilter extends ShiroFilter {
    @Override
    public void init() throws Exception {
        super.init();
        WebSecurityManager webSecurityManager = super.getSecurityManager();
        setRealms(webSecurityManager);
        setCache(webSecurityManager);
    }

    private void setRealms(WebSecurityManager webSecurityManager) {
        String securityRealms = SecurityConfig.getRealms();
        if (securityRealms != null) {
            String[] securityRealmArray = securityRealms.split(",");
            if (securityRealmArray.length > 0) {
                Set<Realm> realms = new LinkedHashSet<>();
                for (String securityRealm : securityRealmArray) {
                    if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)) {
                        addJdbcRealm(realms);
                    } else if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_CUSTOM)) {
                        addCustomRealm(realms);
                    }
                }
                RealmSecurityManager realmSecurityManager = (RealmSecurityManager) webSecurityManager;
                realmSecurityManager.setRealms(realms);
            }
        }
    }

    private void addJdbcRealm(Set<Realm> realms) {
        SummerJdbcRealm summerJdbcRealm = new SummerJdbcRealm();
        realms.add(summerJdbcRealm);
    }

    private void addCustomRealm(Set<Realm> realms) {
        SummerSecurity summerSecurity = SecurityConfig.getSummerSecurity();
        SummerCustomRealm summerCustomRealm = new SummerCustomRealm(summerSecurity);
        realms.add(summerCustomRealm);
    }

    private void setCache(WebSecurityManager webSecurityManager) {
        if (SecurityConfig.isCache()) {
            CachingSecurityManager cachingSecurityManager = (CachingSecurityManager) webSecurityManager;
            CacheManager cacheManager = new MemoryConstrainedCacheManager();
            cachingSecurityManager.setCacheManager(cacheManager);
        }
    }
}
