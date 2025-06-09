package com.example.basicproject.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * massage_project
 * @author 
 */
@Data
public class MassageProject implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 分类描述
     */
    private String projectDesc;

    /**
     * 按摩分类id|massage_class.id
     */
    private Long massageClassId;

    /**
     * 按摩时长
     */
    private Long duration;

    /**
     * 封面图片
     */
    private Long fileId;

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