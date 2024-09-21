package cn.hengzq.orange.system.log.core.service.impl;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.log.common.vo.login.LoginLogVO;
import cn.hengzq.orange.system.log.common.vo.login.param.AddLoginLogParam;
import cn.hengzq.orange.system.log.common.vo.login.param.LoginLogPageParam;
import cn.hengzq.orange.system.log.core.converter.LoginLogConverter;
import cn.hengzq.orange.system.log.core.entity.LoginLogEntity;
import cn.hengzq.orange.system.log.core.mapper.LoginLogMapper;
import cn.hengzq.orange.system.log.core.service.LoginLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class LoginLogServiceImpl implements LoginLogService {

    private final LoginLogMapper loginLogMapper;


    @Override
    public Long add(AddLoginLogParam param) {
        LoginLogEntity entity = LoginLogConverter.INSTANCE.toEntity(param);
        return loginLogMapper.insertOne(entity);
    }

    @Override
    public PageDTO<LoginLogVO> page(LoginLogPageParam param) {
        PageDTO<LoginLogEntity> page = loginLogMapper.selectPage(param,
                CommonWrappers.<LoginLogEntity>lambdaQuery()
                        .eqIfPresent(LoginLogEntity::getAccount, param.getAccount())
        );
        return LoginLogConverter.INSTANCE.toPage(page);
    }
}
