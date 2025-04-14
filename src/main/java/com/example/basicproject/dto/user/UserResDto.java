package com.example.basicproject.dto.user;

import com.example.basicproject.dao.domain.User;
import com.example.basicproject.dto.SheetDto;
import com.example.basicproject.utils.IdUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserResDto {
    private String id;

    /**
     * 工号
     */
    private String employeeId;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别|0-女，1-男
     */
    private Integer gender;

    /**
     * 头像|file.id
     */
    private String avatar;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 部门id
     */
    private Long departmentId;

    /**
     * 状态|0-未激活，1-激活，2-停用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String editor;

    public static SheetDto excelSheetDto(String sheetName) {
        SheetDto sheetDto = new SheetDto();
        if (StringUtils.isNotEmpty(sheetName)){
            sheetDto.setSheetName(sheetName);
        }else{
            sheetDto.setSheetName("用户信息");
        }

        List<String> sheetCellHeadName = new ArrayList<>();
        Map<String, String>  sheetCellHeadNameKey = new HashMap<>();
        sheetDto.setSheetCellHeadName(sheetCellHeadName);
        sheetDto.setSheetCellHeadNameKey(sheetCellHeadNameKey);

        sheetCellHeadName.add("工号");
        sheetCellHeadName.add("用户名");
        sheetCellHeadName.add("性别|0-女，1-男");
        sheetCellHeadName.add("邮箱");
        sheetCellHeadName.add("部门");
        sheetCellHeadName.add("状态|0-未激活，1-激活，2-停用");
        sheetCellHeadName.add("创建时间");
        sheetCellHeadName.add("修改时间");

        sheetCellHeadNameKey.put("工号","employeeId");
        sheetCellHeadNameKey.put("用户名","name");
        sheetCellHeadNameKey.put("性别|0-女，1-男","gender");
        sheetCellHeadNameKey.put("邮箱","email");
        sheetCellHeadNameKey.put("部门","departmentId");
        sheetCellHeadNameKey.put("状态|0-未激活，1-激活，2-停用","status");
        sheetCellHeadNameKey.put("创建时间","createTime");
        sheetCellHeadNameKey.put("修改时间","editTime");

        return sheetDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public static UserResDto create(User user) {
        UserResDto userResDto = new UserResDto();
        BeanUtils.copyProperties(user, userResDto, "id", "avatar");
        userResDto.setId(IdUtil.encode(BigInteger.valueOf(user.getId())));
        if (user.getAvatar() != null) {
            userResDto.setAvatar(IdUtil.encode(BigInteger.valueOf(user.getAvatar())));
        }

        return userResDto;
    }


}
