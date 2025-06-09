package com.example.basicproject.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * massage_project_staff_relation
 * @author 
 */
@Data
public class MassageProjectStaffRelation implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 员工工号|massage_staff.user_id
     */
    private Long staffId;

    /**
     * 项目id|massage_project.id
     */
    private Long projectId;

    /**
     * 创建人用户id
     */
    private Long creatorId;

    /**
     * 修改人用户id
     */
    private Long editorId;

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

}