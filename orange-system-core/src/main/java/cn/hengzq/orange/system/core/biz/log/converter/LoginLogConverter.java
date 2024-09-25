package cn.hengzq.orange.system.core.biz.log.converter;

import cn.hengzq.orange.common.converter.Converter;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.log.vo.login.LoginLogVO;
import cn.hengzq.orange.system.common.biz.log.vo.login.param.AddLoginLogParam;
import cn.hengzq.orange.system.core.biz.log.entity.LoginLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hengzq
 */
@Mapper
public interface LoginLogConverter extends Converter {
    LoginLogConverter INSTANCE = Mappers.getMapper(LoginLogConverter.class);

    LoginLogEntity toEntity(AddLoginLogParam param);

    PageDTO<LoginLogVO> toPage(PageDTO<LoginLogEntity> page);

    LoginLogVO toVO(LoginLogEntity entity);
}
