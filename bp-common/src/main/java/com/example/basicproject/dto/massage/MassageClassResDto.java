package com.example.basicproject.dto.massage;

import com.example.basicproject.domain.MassageClass;
import com.example.basicproject.utils.IdUtil;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigInteger;
import java.util.Date;

@Data
public class MassageClassResDto {
    private String id;

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
    private String fileId;

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

    public static MassageClassResDto create(MassageClass massageClass) {
        MassageClassResDto massageClassResDto = new MassageClassResDto();
        BeanUtils.copyProperties(massageClass,massageClassResDto,"id");
        massageClassResDto.setId(IdUtil.encode(BigInteger.valueOf(massageClass.getId())));
        return massageClassResDto;
    }
}
