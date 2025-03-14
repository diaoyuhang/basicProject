package com.example.basicproject.dto.user;

import com.example.basicproject.dao.domain.User;
import com.example.basicproject.dto.validGroup.Delete;
import com.example.basicproject.dto.validGroup.Insert;
import com.example.basicproject.dto.validGroup.Select;
import com.example.basicproject.dto.validGroup.Update;
import com.example.basicproject.utils.IdUtil;
import com.example.basicproject.utils.MD5Util;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public class UserReqDto {
    @NotEmpty(message = "用户id不能为空", groups = {Update.class, Delete.class})
    private String id;
    @NotEmpty(message = "工号不能为空", groups = {Select.class,Update.class, Insert.class})
    private String employeeId;
    @NotEmpty(message = "密码不能为空", groups = {Select.class,Update.class, Insert.class})
    private String password;
    @NotEmpty(message = "名称不能为空", groups = {Update.class, Insert.class})
    private String name;
    @NotNull(message = "性别必填", groups = {Update.class, Insert.class})
    private Integer gender;
    @NotEmpty(message = "头像不能为空", groups = {Update.class, Insert.class})
    private String avatar;

    private String email;

    @NotNull(message = "用户状态必填",groups = {Insert.class, Update.class})
    private Integer status;

    private List<Date> editTimeRange;

    private String sortField;

    private String sortOrder;

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public List<Date> getEditTimeRange() {
        return editTimeRange;
    }

    public void setEditTimeRange(List<Date> editTimeRange) {
        this.editTimeRange = editTimeRange;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User convertUser() {
        User user = new User();
        user.setEmployeeId(this.employeeId);
        if (!StringUtils.isBlank(this.password)) {
            user.setPassword(MD5Util.md5(this.password));
        }

        if (!StringUtils.isBlank(this.id)){
            user.setId(IdUtil.decode(this.id).longValue());
        }

        user.setName(this.name);
        user.setGender(this.gender);

        if (!StringUtils.isBlank(this.avatar)) {
            user.setAvatar(IdUtil.decode(this.avatar).longValue());
        }
        user.setStatus(this.status);
        user.setEmail(this.email);
        return user;
    }

}
