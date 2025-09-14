package cn.hengzq.orange.system.core.biz.user.converter;

import cn.hengzq.orange.common.converter.Converter;
import cn.hengzq.orange.common.dto.LoginUserInfo;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.user.dto.UserResponse;
import cn.hengzq.orange.system.common.biz.user.dto.request.UserCreateRequest;
import cn.hengzq.orange.system.common.biz.user.dto.request.UserUpdateRequest;
import cn.hengzq.orange.system.core.biz.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户转换器
 *
 * @author hengzq
 */
@Mapper
public interface UserConverter extends Converter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserEntity toEntity(UserResponse userResponse);

    UserEntity toEntity(UserCreateRequest request);

    UserEntity toEntity(UserUpdateRequest request);

    UserResponse toVO(UserEntity entity);

    List<UserResponse> toListTreeVO(List<UserResponse> userResponseList);

    List<UserResponse> toListV0(List<UserEntity> entityList);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "param.name", target = "name")
    @Mapping(source = "param.email", target = "email")
    @Mapping(source = "param.gender", target = "gender")
    @Mapping(source = "param.phone", target = "phone")
    UserEntity toUpdateEntity(UserEntity entity, UserUpdateRequest param);

    PageDTO<UserResponse> toPage(PageDTO<UserEntity> page);

    @Mapping(source = "entity.id", target = "userId")
    LoginUserInfo toLoginUserInfo(UserEntity entity);
}
