package cn.hengzq.orange.system.core.biz.log.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.common.util.CollUtils;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.api.log.LoginLogApi;
import cn.hengzq.orange.system.common.biz.log.vo.login.LoginLogVO;
import cn.hengzq.orange.system.common.biz.log.vo.login.param.AddLoginLogParam;
import cn.hengzq.orange.system.common.biz.log.vo.login.param.LoginLogPageParam;
import cn.hengzq.orange.system.core.biz.log.converter.LoginLogConverter;
import cn.hengzq.orange.system.core.biz.log.entity.LoginLogEntity;
import cn.hengzq.orange.system.core.biz.log.mapper.LoginLogMapper;
import cn.hengzq.orange.system.core.biz.log.service.LoginLogService;
import cn.hengzq.orange.system.core.biz.user.service.UserService;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class LoginLogServiceImpl implements LoginLogService, LoginLogApi {

    private final LoginLogMapper loginLogMapper;

    private final UserService userService;

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
                        .orderByDesc(LoginLogEntity::getLoginTime)
        );
        PageDTO<LoginLogVO> pageDTO = LoginLogConverter.INSTANCE.toPage(page);
        if (Objects.isNull(pageDTO) || CollUtil.isEmpty(pageDTO.getRecords())) {
            return pageDTO;
        }
        List<LoginLogVO> records = pageDTO.getRecords();
        Map<Long, String> userNameMap = userService.getNameMapByIds(CollUtils.convertSet(records, LoginLogVO::getUserId));
        records.forEach(record -> {
            record.setUserName(userNameMap.get(record.getUserId()));
        });
        return pageDTO;
    }

    @Override
    public LoginLogVO getById(Long id) {
        LoginLogEntity entity = loginLogMapper.selectById(id);
        LoginLogVO vo = LoginLogConverter.INSTANCE.toVO(entity);
        Assert.nonNull(vo, GlobalErrorCodeConstant.GLOBAL_PARAMETER_ID_IS_INVALID);

        Map<Long, String> userNameMap = userService.getNameMapByIds(Set.of(vo.getUserId()));
        vo.setUserName(userNameMap.get(vo.getUserId()));
        return vo;
    }
}
