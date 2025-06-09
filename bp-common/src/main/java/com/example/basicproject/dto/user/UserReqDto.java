package com.example.basicproject.dto.user;

import com.example.basicproject.domain.User;
import com.example.basicproject.dto.BaseConditionDto;
import com.example.basicproject.dto.validGroup.Delete;
import com.example.basicproject.dto.validGroup.Insert;
import com.example.basicproject.dto.validGroup.Select;
import com.example.basicproject.dto.validGroup.Update;
import com.example.basicproject.utils.IdUtil;
import com.example.basicproject.utils.MD5Util;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
public class UserReqDto extends BaseConditionDto {
    @NotEmpty(message = "用户id不能为空", groups = {Update.class, Delete.class})
    private String id;
    @NotEmpty(message = "工号不能为空", groups = {Select.class,Update.class, Insert.class})
    private String employeeId;
    @NotEmpty(message = "密码不能为空", groups = {Select.class, Insert.class})
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
