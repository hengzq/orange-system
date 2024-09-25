package cn.hengzq.orange.system.api.log;

import cn.hengzq.orange.system.common.biz.log.vo.login.param.AddLoginLogParam;

public interface LoginLogApi {

    Long add(AddLoginLogParam param);
}
