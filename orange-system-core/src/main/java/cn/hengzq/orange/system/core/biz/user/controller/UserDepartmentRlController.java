package cn.hengzq.orange.system.core.biz.user.controller;


import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.permission.core.service.UserDepartmentRlService;
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
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/user-department-rl")
public class UserDepartmentRlController {

    private final UserDepartmentRlService userDepartmentRlService;

}
