package com.example.basicproject.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * massage_staff
 * @author 
 */
@Data
public class MassageStaff {
    /**
     * 主键，员工工号|user.id
     */
    private Long userId;

    /**
     * 从业人员描述
     */
    private String staffDesc;

    /**
     * 评分
     */
    private Double score;

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