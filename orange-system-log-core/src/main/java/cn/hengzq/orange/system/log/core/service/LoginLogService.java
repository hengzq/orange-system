package cn.hengzq.orange.system.log.core.service;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.log.common.vo.login.LoginLogVO;
import cn.hengzq.orange.system.log.common.vo.login.param.AddLoginLogParam;
import cn.hengzq.orange.system.log.common.vo.login.param.LoginLogPageParam;

/**
 * @author hengzq
 */
public interface LoginLogService {

    /**
     * 添加新的日志记录
     */
    Long add(AddLoginLogParam param);

    /**
     * 分页查询
     */
    PageDTO<LoginLogVO> page(LoginLogPageParam param);

}
