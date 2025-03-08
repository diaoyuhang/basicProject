package com.example.basicproject.dto.role;

import com.example.basicproject.dao.domain.RolePermission;
import com.example.basicproject.utils.IdUtil;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class RolePermissionReqDto {

    @NotEmpty(message = "角色id不能为空")
    private String roleId;

    private List<String> permissionId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(List<String> permissionId) {
        this.permissionId = permissionId;
    }

    public List<RolePermission> convertRolePermission() {
        List<RolePermission> res = new ArrayList<>();
        Long rId = IdUtil.decode(this.roleId).longValue();
        if (!CollectionUtils.isEmpty(this.permissionId)){
            for (String id : this.permissionId) {
                RolePermission rolePermission = new RolePermission();
                Long pId = IdUtil.decode(id).longValue();
                rolePermission.setRoleId(rId);
                rolePermission.setPermissionId(pId);
                res.add(rolePermission);
            }
        }
        return res;
    }
}
