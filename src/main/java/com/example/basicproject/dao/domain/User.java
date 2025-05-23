package com.example.basicproject.dao.domain;

import java.util.Date;

/**
 * user
 * @author 
 */
public class User{

    public static final Integer OPEN_STATUS = 1;
    public static final Integer STOP_STATUS = 0 ;
    /**
     * 主键
     */
    private Long id;

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
    private Long avatar;

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
    /**
     * 创建人用户id
     */
    private Long creatorId;

    /**
     * 修改人用户id
     */
    private Long editorId;

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getEditorId() {
        return editorId;
    }

    public void setEditorId(Long editorId) {
        this.editorId = editorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getAvatar() {
        return avatar;
    }

    public void setAvatar(Long avatar) {
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

}