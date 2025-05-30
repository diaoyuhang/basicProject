package com.example.basicproject.dto.role;

import com.example.basicproject.domain.Permission;
import com.example.basicproject.utils.IdUtil;
import org.springframework.beans.BeanUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PermissionResDto {

    private String id;

    /**
     * 父节点id|permission.id
     */
    private String parentId;

    /**
     * 路径
     */
    private String path;

    /**
     * 权限主题
     */
    private String title;

    /**
     * 类型|1-菜单权限，2-按钮权限
     */
    private Integer type;

    /**
     * 菜单级别
     */
    private Integer level;

    private List<PermissionResDto> children = new ArrayList<>();

    public static PermissionResDto create(Permission permission) {
        PermissionResDto permissionResDto = new PermissionResDto();
        BeanUtils.copyProperties(permission, permissionResDto, "id,parentId");
        if (permission.getId() != null) {
            permissionResDto.setId(IdUtil.encode(BigInteger.valueOf(permission.getId())));
        }

        if (permission.getParentId() != null) {
            permissionResDto.setParentId(IdUtil.encode(BigInteger.valueOf(permission.getParentId())));
        }
        return permissionResDto;
    }

    public static List<PermissionResDto> buildTree(List<Permission> allPermission) {
        List<PermissionResDto> permissionResList = allPermission.stream().map(PermissionResDto::create).collect(Collectors.toList());
        Map<String, List<PermissionResDto>> map = new HashMap<>();
        for (PermissionResDto permission : permissionResList) {
            if (map.containsKey(permission.getParentId())){
                map.get(permission.getParentId()).add(permission);
            }else{
                List<PermissionResDto> ps = new ArrayList<>();
                ps.add(permission);
                map.put(permission.getParentId(),ps);
            }
        }

        List<PermissionResDto> oneLevelPs = permissionResList.stream().filter(p -> p.getLevel().equals(Permission.ONE_LEVEL)).collect(Collectors.toList());
        return buildLayer(oneLevelPs,map);
    }

    public static List<PermissionResDto> buildLayer(List<PermissionResDto> permissions,Map<String, List<PermissionResDto>> map){
        List<PermissionResDto> res = new ArrayList<>();
        for (PermissionResDto permission : permissions) {
            res.add(build(permission,map));
        }
        return res;
    }

    private static PermissionResDto build(PermissionResDto permission, Map<String, List<PermissionResDto>> map) {
        if (map.containsKey(permission.getId())){
            List<PermissionResDto> permissions = map.get(permission.getId());
            permission.getChildren().addAll(buildLayer(permissions,map));
        }
        return permission;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<PermissionResDto> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionResDto> children) {
        this.children = children;
    }
}
