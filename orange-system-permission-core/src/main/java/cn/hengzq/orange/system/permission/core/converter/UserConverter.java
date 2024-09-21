package cn.hengzq.orange.system.permission.core.converter;

import cn.hengzq.orange.common.converter.Converter;
import cn.hengzq.orange.common.dto.LoginUserInfo;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.permission.core.entity.UserEntity;
import cn.hengzq.orange.system.permission.common.vo.user.UserVO;
import cn.hengzq.orange.system.permission.common.vo.user.param.AddUserParam;
import cn.hengzq.orange.system.permission.common.vo.user.param.UpdateUserParam;
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

    UserEntity toEntity(UserVO userVO);

    UserEntity toEntity(AddUserParam request);

    UserEntity toEntity(UpdateUserParam request);

    UserVO toVO(UserEntity entity);

    List<UserVO> toListTreeVO(List<UserVO> UserVOList);

    List<UserVO> toListV0(List<UserEntity> entityList);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "param.nickname", target = "nickname")
    @Mapping(source = "param.email", target = "email")
    @Mapping(source = "param.gender", target = "gender")
    @Mapping(source = "param.phone", target = "phone")
    UserEntity toUpdateEntity(UserEntity entity, UpdateUserParam param);

    PageDTO<UserVO> toPage(PageDTO<UserEntity> page);

    @Mapping(source = "user.id", target = "userId")
    LoginUserInfo toLoginUserInfo(UserVO user);
}
