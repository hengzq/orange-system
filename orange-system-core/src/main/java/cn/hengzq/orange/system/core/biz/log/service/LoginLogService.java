package cn.hengzq.orange.system.core.biz.log.service;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.log.vo.login.LoginLogVO;
import cn.hengzq.orange.system.common.biz.log.vo.login.param.AddLoginLogParam;
import cn.hengzq.orange.system.common.biz.log.vo.login.param.LoginLogPageParam;

/**
 * @author hengzq
 */
public interface LoginLogService {

    /**
     * 添加新的日志记录
     */
    String add(AddLoginLogParam param);

    /**
     * 分页查询
     */
    PageDTO<LoginLogVO> page(LoginLogPageParam param);

    LoginLogVO getById(String id);
}
