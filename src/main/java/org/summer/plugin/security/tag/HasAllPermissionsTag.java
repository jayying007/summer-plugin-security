package org.summer.plugin.security.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

/**
 * @author ZhuangJieYing
 * @date 2020/12/22
 */
public class HasAllPermissionsTag extends PermissionTag {

    private static final String PERMISSION_NAMES_DELIMITER = ",";

    @Override
    protected boolean showTagBody(String permNames) {
        boolean hasAllPermission = false;
        Subject subject = getSubject();
        if (subject != null) {
            if (subject.isPermittedAll(permNames.split(PERMISSION_NAMES_DELIMITER))) {
                hasAllPermission = true;
            }
        }
        return hasAllPermission;
    }
}
