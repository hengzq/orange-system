package cn.hengzq.orange.system.api.biz.log;

import cn.hengzq.orange.system.common.biz.log.vo.login.param.AddLoginLogParam;

public interface LoginLogApi {

    String add(AddLoginLogParam param);
}
