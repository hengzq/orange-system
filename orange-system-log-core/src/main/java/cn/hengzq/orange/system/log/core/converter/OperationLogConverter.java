package cn.hengzq.orange.system.log.core.converter;

import cn.hengzq.orange.common.converter.Converter;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.log.common.vo.operation.OperationLogVO;
import cn.hengzq.orange.system.log.common.vo.operation.param.AddOperationLogParam;
import cn.hengzq.orange.system.log.core.entity.OperationLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hengzq
 */
@Mapper
public interface OperationLogConverter extends Converter {
    OperationLogConverter INSTANCE = Mappers.getMapper(OperationLogConverter.class);

    OperationLogEntity toEntity(AddOperationLogParam param);

    OperationLogVO toVO(OperationLogEntity entity);

    PageDTO<OperationLogVO> toPage(PageDTO<OperationLogEntity> page);
}
