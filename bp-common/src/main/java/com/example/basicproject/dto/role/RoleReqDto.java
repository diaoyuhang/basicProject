package com.example.basicproject.dto.role;

import com.example.basicproject.domain.Role;
import com.example.basicproject.dto.validGroup.Insert;
import com.example.basicproject.dto.validGroup.Update;
import com.example.basicproject.utils.IdUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Delete;

public class RoleReqDto {
    @NotEmpty(message = "id不能为空", groups = {Update.class, Delete.class})
    private String id;
    @NotEmpty(message = "角色名称不能为空", groups = {Update.class, Insert.class})
    private String roleName;
    @NotNull(message = "状态必选", groups = {Update.class, Insert.class})
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Role convertRole() {
        Role role = new Role();
        if (!StringUtils.isEmpty(this.roleName)) {
            role.setRoleName(this.roleName);
        }

        if (!StringUtils.isEmpty(this.id)) {
            role.setId(IdUtil.decode(this.id).longValue());
        }
        role.setStatus(status);

        return role;
    }
}
