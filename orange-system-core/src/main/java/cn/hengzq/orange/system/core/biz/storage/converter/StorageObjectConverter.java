package cn.hengzq.orange.system.core.biz.storage.converter;


import cn.hengzq.orange.common.converter.Converter;
import cn.hengzq.orange.storage.dto.UploadFileResult;
import cn.hengzq.orange.system.common.biz.storage.vo.StorageByteObjectVO;
import cn.hengzq.orange.system.common.biz.storage.vo.StorageObjectVO;
import cn.hengzq.orange.system.core.biz.storage.entity.StorageObjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StorageObjectConverter extends Converter {

    StorageObjectConverter INSTANCE = Mappers.getMapper(StorageObjectConverter.class);

    StorageObjectVO toVO(StorageObjectEntity entity);

    StorageObjectEntity toEntity(UploadFileResult result);

    StorageByteObjectVO toByteVO(StorageObjectEntity entity);
}
