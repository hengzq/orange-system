package cn.hengzq.orange.system.core.biz.permission.service;


import cn.hengzq.orange.system.common.biz.permission.vo.TokenVO;
import cn.hengzq.orange.system.common.biz.permission.vo.param.LoginParam;

/**
 * @author hengzq
 */
public interface AuthService {


    TokenVO login(LoginParam param);


    String passwordEncrypt(String password);
}
