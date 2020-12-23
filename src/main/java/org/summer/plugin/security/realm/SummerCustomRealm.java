package org.summer.plugin.security.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.summer.plugin.security.SecurityConstant;
import org.summer.plugin.security.SummerSecurity;
import org.summer.plugin.security.password.Md5CredentialsMatcher;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZhuangJieYing
 * @date 2020/12/22
 */
public class SummerCustomRealm extends AuthorizingRealm {
    private final SummerSecurity summerSecurity;

    public SummerCustomRealm(SummerSecurity summerSecurity) {
        this.summerSecurity = summerSecurity;
        super.setName(SecurityConstant.REALMS_CUSTOM);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());
    }

    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token == null) {
            throw new AuthenticationException("parameter token is null");
        }

        String username = ((UsernamePasswordToken) token).getUsername();

        String password = summerSecurity.getPassword(username);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
        authenticationInfo.setPrincipals(new SimplePrincipalCollection(username, super.getName()));
        authenticationInfo.setCredentials(password);
        return authenticationInfo;
    }

    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("parameter principals is null");
        }

        String username = (String) super.getAvailablePrincipal(principals);

        Set<String> roleNameSet = summerSecurity.getRoleNameSet(username);

        Set<String> permissionNameSet = new HashSet<>();
        if (roleNameSet != null && roleNameSet.size() > 0) {
            for (String roleName : roleNameSet) {
                Set<String> currentPermissionNameSet = summerSecurity.getPermissionNameSet(roleName);
                permissionNameSet.addAll(currentPermissionNameSet);
            }
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleNameSet);
        authorizationInfo.setStringPermissions(permissionNameSet);
        return authorizationInfo;
    }
}
