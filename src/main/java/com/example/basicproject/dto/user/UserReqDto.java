package com.example.basicproject.dto.user;

import com.example.basicproject.constant.BaseConstant;
import com.example.basicproject.dao.domain.User;
import com.example.basicproject.utils.IdEncryptUtil;
import com.example.basicproject.utils.MD5Util;
import com.example.basicproject.utils.UserHelperUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

public class UserReqDto {
    @NotEmpty(message = "工号不能为空")
    private String employeeId;
    @NotEmpty(message = "密码不能为空")
    private String password;

    private String name;

    private Integer gender;

    private String avatar;

    private String email;


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

        user.setName(this.name);
        user.setGender(this.gender);
        if (!StringUtils.isBlank(this.avatar)) {
            user.setAvatar(IdEncryptUtil.decode(this.avatar).longValue());
        }
        user.setEmail(this.email);
        return user;
    }

    public User convertNewUser() {
        User user = this.convertUser();
        user.setCreateTime(new Date());
        user.setEditTime(new Date());
        user.setCreator(BaseConstant.SYSTEM_USER);
        user.setEditor(BaseConstant.SYSTEM_USER);
        return user;
    }
}
