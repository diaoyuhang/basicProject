package com.example.basicproject.controller.backend.role;

import com.example.basicproject.constant.Status;
import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.Pagination;
import com.example.basicproject.dto.ResultDto;
import com.example.basicproject.dto.role.PermissionResDto;
import com.example.basicproject.dto.role.RolePermissionReqDto;
import com.example.basicproject.dto.role.RoleReqDto;
import com.example.basicproject.dto.role.RoleResDto;
import com.example.basicproject.dto.validGroup.Delete;
import com.example.basicproject.dto.validGroup.Insert;
import com.example.basicproject.dto.validGroup.Update;
import com.example.basicproject.service.backend.PermissionService;
import com.example.basicproject.service.backend.RoleService;
import com.example.basicproject.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bk/role")
public class RoleController {

    //权限管理
    private static final Long PERMISSION_MENU_ID = 10L;
    //角色管理菜单
    private static final Long ROLE_MENU_ID = 1001L;
    //添加角色
    private static final Long ADD_ROLE_ID = 100101L;
    private static final Long EDIT_ROLE_ID = 100102L;
    private static final Long DELETE_ROLE_ID = 100103L;
    private static final Long ASSIGN_PERMISSION_ID = 100105L;

    private PermissionService permissionService;
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/getRoleList")
    public ResultDto<Pagination<List<RoleResDto>>> getRoleList(@RequestBody PageReqCondition<RoleReqDto> pageReqCondition) {
        if(!permissionService.validate(ROLE_MENU_ID)){
            return ResultDto.createFail(Status.PERMISSION_ERROR);
        }
        Pagination<List<RoleResDto>> res = roleService.getRoleList(pageReqCondition);
        return ResultDto.createSuccess(res);
    }

    @PostMapping("/addRole")
    public ResultDto<Boolean> addRole(@RequestBody @Validated(value = {Insert.class}) RoleReqDto roleReqDto) {
        if(!permissionService.validate(ADD_ROLE_ID)){
            return ResultDto.createFail(Status.PERMISSION_ERROR);
        }
        roleService.addRole(roleReqDto);
        return ResultDto.createSuccess(true);
    }

    @PostMapping("/editRole")
    public ResultDto<Boolean> editRole(@RequestBody @Validated(value = {Update.class}) RoleReqDto roleReqDto) {
        if(!permissionService.validate(EDIT_ROLE_ID)){
            return ResultDto.createFail(Status.PERMISSION_ERROR);
        }
        roleService.editRole(roleReqDto);
        return ResultDto.createSuccess(true);
    }

    @PostMapping("/deleteRole")
    public ResultDto<Boolean> deleteRole(@RequestBody @Validated(value = {Delete.class}) RoleReqDto roleReqDto) {
        if(!permissionService.validate(DELETE_ROLE_ID)){
            return ResultDto.createFail(Status.PERMISSION_ERROR);
        }
        roleService.deleteRole(roleReqDto);
        return ResultDto.createSuccess(true);
    }

    @PostMapping("/assignPermission")
    public ResultDto<Boolean> assignPermission(@RequestBody @Validated RolePermissionReqDto rolePermissionReqDto){
        if (!permissionService.validate(ASSIGN_PERMISSION_ID)) {
            return ResultDto.createFail(Status.PERMISSION_ERROR);
        }

        roleService.assignPermission(rolePermissionReqDto);
        return ResultDto.createSuccess(true);
    }

    @GetMapping("/allPermissionList")
    public ResultDto<List<PermissionResDto>> allPermissionList(){
        List<PermissionResDto> res = permissionService.allPermissionList();
        return ResultDto.createSuccess(res);
    }

    @GetMapping("/getExistPermission")
    public ResultDto<List<String>> getExistPermission(String roleId){
        List<String> pIds = roleService.getExistPermissionByRoleId(roleId);
        return ResultDto.createSuccess(pIds);
    }

    @GetMapping("/getUsersByRole")
    public ResultDto<List<String>> getUsersByRole(String roleId){
        List<String> res = roleService.getUsersByRole(IdUtil.decode(roleId).longValue());
        return ResultDto.createSuccess(res);
    }
}
