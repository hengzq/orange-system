package cn.hengzq.orange.system.log.core.converter;

import cn.hengzq.orange.common.converter.Converter;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.log.common.vo.login.LoginLogVO;
import cn.hengzq.orange.system.log.common.vo.login.param.AddLoginLogParam;
import cn.hengzq.orange.system.log.core.entity.LoginLogEntity;
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
}
