package cn.hengzq.orange.system.permission.core.controller;


import cn.hengzq.orange.system.permission.core.service.UserDepartmentRlService;
import cn.hengzq.orange.system.permission.common.constant.PermissionConstant;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hengzq
 */
@Tag(name = "用户部门关系管理")
@RestController
@AllArgsConstructor
@RequestMapping(PermissionConstant.V1_0_URL_PREFIX + "/user-department-rl")
public class UserDepartmentRlController {

    private final UserDepartmentRlService userDepartmentRlService;

}
