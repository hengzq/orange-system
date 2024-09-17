package cn.hengzq.orange.system.permission.core.service;


import cn.hengzq.orange.system.permission.common.vo.auth.TokenVO;
import cn.hengzq.orange.system.permission.common.vo.auth.param.LoginParam;

/**
 * @author hengzq
 */
public interface AuthService {


    TokenVO login(LoginParam param);


    String passwordEncrypt(String password);
}
