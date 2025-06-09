package com.example.basicproject.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * massage_class
 * @author 
 */
@Data
public class MassageClass {
    public static final Integer OPEN_STATE=1;
    public static final Integer STOP_STATE=0;



    /**
     * 主键
     */
    private Long id;

    /**
     * 分类名称
     */
    private String title;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 封面图片
     */
    private Long fileId;
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