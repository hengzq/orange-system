package cn.hengzq.orange.system.permission.core.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.LoginUserInfo;
import cn.hengzq.orange.common.exception.ServiceException;
import cn.hengzq.orange.common.util.JwtToken;
import cn.hengzq.orange.context.GlobalContextHelper;
import cn.hengzq.orange.system.permission.core.convert.UserConverter;
import cn.hengzq.orange.system.permission.core.service.AuthService;
import cn.hengzq.orange.system.permission.core.service.UserService;
import cn.hengzq.orange.system.permission.common.vo.auth.TokenVO;
import cn.hengzq.orange.system.permission.common.vo.auth.param.LoginParam;
import cn.hengzq.orange.system.permission.common.vo.user.UserVO;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final static String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOqITyWTbOVaoX+JZe6q3bTcmNcnfHwj/AzNrdYR+wCno6esdCGqJWLviTle3wwl9TOsyBimDy7gQph+l3" +
            "+vQEQT6Xcmy1x9Jez63ZH2O97EDS7IZ3/5g9jq81JqFCDd/aMJiFn2+nsHEXRdJkIj459KVbUI9+fJRTKn1KI3IlOnAgMBAAECgYAQhq5dDXhc6Pf8Tqg6GxwoRGsYrhWdgcOe+1dGgFnZf/aOOB6uyJh90jcvLbo11u7iR0iKRBlJyk+nAzSVsLmsb9K0wfwovoQlZP4RAK1ET3RP3fLEJtG6WYSpbncVSs8okSrrDUE9hNxV8UtW6Y145liEIxH15lguHf4Jyil0MQJBAP+H8PZhmC6IvJ7jldtKmfd/EByF2F/kFhTfh4nBnGqrMeMyIBm8bCOasFSsOy6OZTFy9dii3ri99vwk+L3GY/MCQQDq9oB/FJPuG3mxRAvrJG+qe8C/6fblPpLfAHLIWISRO2peWt7Ud6r9FUQb6zbqcj/7wliLrx6OeQQdbkKvneJ9AkEApqQNpW+B4h7z+x5qFQdyny+y3xb+Q5KoP9aCOnkTu5CHSSXgP0hcsV9ozN9A/RyJq5TP9QZJ/uqLjmXB/WjKtwJBALjQLaBHslf+uoipSmqpnT/O2Xza7f3Ba0sHEkHuBlAqGO+gsFcUzaUF/i2rpOVh+lvvsTAmDXXpUEhJ+yAhow0CQQD83srI9BbYuYM+uG78BWNxAg9/NQCzX4r3xiSDlfd+ouaKS/jkEohXvX8rEvFYre/+3LRA4jJRm4RHmwpkJwnq";

    private final static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDqiE8lk2zlWqF/iWXuqt203JjXJ3x8I/wMza3WEfsAp6OnrHQhqiVi74k5Xt8MJfUzrMgYpg8u4EKYfpd/r0BEE+l3JstcfSXs" +
            "+t2R9jvexA0uyGd/+YPY6vNSahQg3f2jCYhZ9vp7BxF0XSZCI+OfSlW1CPfnyUUyp9SiNyJTpwIDAQAB";

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;


    @Override
    public TokenVO login(LoginParam param) {
        GlobalContextHelper.setContext(LoginUserInfo.builder().tenantId(param.getTenantId()).build());
        UserVO user = userService.getByUsername(param.getUsername());
        if (user == null) {
            log.error("account is error. account:{}", param.getUsername());
            throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_AUTH_ACCOUNT_ERROR);
        }
        RSA rsa = new RSA(PRIVATE_KEY, null);
        String password = rsa.decryptStr(param.getPassword(), KeyType.PrivateKey);
        boolean matches = passwordEncoder.matches(password, user.getPassword());
        if (!matches) {
            log.error("password is error.");
            throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_AUTH_PASSWORD_ERROR);
        }
        LoginUserInfo userInfo = UserConverter.INSTANCE.toLoginUserInfo(user);
        GlobalContextHelper.setContext(userInfo);
        return new TokenVO(JwtToken.createToken(userInfo));
    }


    @Override
    public String passwordEncrypt(String password) {
        if (StrUtil.isBlank(password)) {
            return null;
        }
        RSA rsa = new RSA(null, PUBLIC_KEY);
        return rsa.encryptBase64(password, KeyType.PublicKey);
    }


//    private void getPermissions(List<RoleEntity> roles, List<String> menuPermissionList, List<String> buttonPermissionList) {
//        if (CollectionUtils.isEmpty(roles)) {
//            log.info("roles is empty.");
//            return;
//        }
//        List<RoleResourceRlEntity> roleResourceRlEntityList = roleResourceRlMapper.queryByRoleIds(CollUtils.convertList(roles, RoleEntity::getId));
//        if (CollUtil.isEmpty(roleResourceRlEntityList)) {
//            return;
//        }
//        List<Long> menuIds = roleResourceRlEntityList.stream().filter(item -> ResourceTypeEnum.MENU.equals(item.getResourceType())).map(RoleResourceRlEntity::getResourceId).toList();
//        if (CollUtil.isNotEmpty(menuIds)) {
//            List<MenuEntity> menuEntityList = menuMapper.selectBatchIds(menuIds);
//            if (CollUtil.isNotEmpty(menuEntityList)) {
//                menuPermissionList.addAll(CollUtils.convertList(menuEntityList, MenuEntity::getPermission));
//            }
//        }
//        List<Long> buttonIds = roleResourceRlEntityList.stream().filter(item -> ResourceTypeEnum.BUTTON.equals(item.getResourceType())).map(RoleResourceRlEntity::getResourceId).toList();
//        if (CollUtil.isNotEmpty(buttonIds)) {
//            List<ButtonEntity> buttonEntityList = buttonMapper.selectBatchIds(buttonIds);
//            if (CollUtil.isNotEmpty(buttonEntityList)) {
//                buttonPermissionList.addAll(CollUtils.convertList(buttonEntityList, ButtonEntity::getPermission));
//            }
//        }
//    }
//
//
}
