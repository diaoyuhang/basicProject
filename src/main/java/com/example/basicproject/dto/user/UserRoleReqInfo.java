package com.example.basicproject.dto.user;

import com.example.basicproject.dao.domain.UserRole;
import com.example.basicproject.utils.IdUtil;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserRoleReqInfo {

    @NotEmpty(message = "用户id不能为空")
    private String userId;
    private List<String> roleIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public List<UserRole> convertUserRole() {
        long userId = IdUtil.decode(this.userId).longValue();
        List<UserRole> res = new ArrayList<>();

        if (!CollectionUtils.isEmpty(this.roleIds)){
            for (String roleId : this.roleIds) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(IdUtil.decode(roleId).longValue());
                userRole.setUserId(userId);
                res.add(userRole);
            }
        }
        return res;
    }
}
