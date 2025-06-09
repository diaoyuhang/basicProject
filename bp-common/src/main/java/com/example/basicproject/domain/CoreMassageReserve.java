package com.example.basicproject.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * core_massage_reserve
 * @author 
 */
@Data
public class CoreMassageReserve {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id|core_user.id
     */
    private Long coreUserId;

    /**
     * 员工工号|massage_staff.user_id
     */
    private Long staffId;

    /**
     * 项目id|massage_project.id
     */
    private Long projectId;

    /**
     * 预约时间
     */
    private Date reserveTime;

    /**
     * 预约状态|1-生效，2-执行中，3-完成，4-取消
     */
    private Integer state;

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