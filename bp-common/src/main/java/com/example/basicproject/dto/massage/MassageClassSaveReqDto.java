package com.example.basicproject.dto.massage;


import com.example.basicproject.domain.MassageClass;
import com.example.basicproject.dto.validGroup.Delete;
import com.example.basicproject.dto.validGroup.Insert;
import com.example.basicproject.dto.validGroup.Update;
import com.example.basicproject.utils.IdUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class MassageClassSaveReqDto {
    @NotEmpty(message = "id不能为空", groups = {Update.class, Delete.class})
    private String id;
    @NotEmpty(message = "标题不能为空", groups = {Update.class, Insert.class})
    private String title;

    private String description;
    @NotEmpty(message = "图片不能为空", groups = {Update.class, Insert.class})
    private String fileId;

    public MassageClass convertMassageClass() {
        MassageClass massageClass = new MassageClass();
        BeanUtils.copyProperties(this,massageClass,"id");
        if (!StringUtils.isEmpty(id)){
            massageClass.setId(IdUtil.decode(id).longValue());
        }
        return massageClass;
    }
}
