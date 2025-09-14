package cn.hengzq.orange.system.core.biz.permission.service;


import cn.hengzq.orange.system.common.biz.permission.dto.LoginResponse;
import cn.hengzq.orange.system.common.biz.permission.dto.request.LoginRequest;

/**
 * @author hengzq
 */
public interface AuthService {


    LoginResponse login(LoginRequest param);


    String passwordEncrypt(String password);
}
