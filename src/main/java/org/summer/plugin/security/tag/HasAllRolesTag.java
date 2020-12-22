package org.summer.plugin.security.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.RoleTag;

import java.util.Arrays;

/**
 * @author ZhuangJieYing
 * @date 2020/12/22
 */
public class HasAllRolesTag extends RoleTag {
    private static final String ROLE_NAMES_DELIMITER = ",";

    @Override
    protected boolean showTagBody(String roleNames) {
        boolean hasAllRole = false;
        Subject subject = getSubject();
        if (subject != null) {
            if (subject.hasAllRoles(Arrays.asList(roleNames.split(ROLE_NAMES_DELIMITER)))) {
                hasAllRole = true;
            }
        }
        return hasAllRole;
    }
}
