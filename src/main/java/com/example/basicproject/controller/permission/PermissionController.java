package com.example.basicproject.controller.permission;

import com.example.basicproject.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionController {


    //权限管理菜单
    private static final Long PERMISSION_MENU_ID = 1001L;
    //操作用户权限
    private static final Long ADD_PERMISSION_ID = 100101L;
    private static final Long EDIT_PERMISSION_ID = 100102L;

}
