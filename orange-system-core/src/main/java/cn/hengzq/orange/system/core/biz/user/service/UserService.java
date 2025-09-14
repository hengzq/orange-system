package cn.hengzq.orange.system.core.biz.user.service;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.user.dto.UserDetailResponse;
import cn.hengzq.orange.system.common.biz.user.dto.UserResponse;
import cn.hengzq.orange.system.common.biz.user.dto.request.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author hengzq
 */
public interface UserService {


    String createUser(UserCreateRequest param);


    void deleteUserById(String id);

    void updateUserById(String id, UserUpdateRequest param);

    /**
     * 更新密码
     */
    Boolean updatePassword(UpdatePasswordParam param);

    /**
     * 重置密码
     */
    void resetPasswordById(String id, UserResetPasswordRequest request);


    Optional<UserResponse> getUserById(String id);


    /**
     * 根据用户ID查询用户
     */
    UserDetailResponse getById(String userId, UserDetailQueryParam param);

    /**
     * 根据登录账号查询用户
     */
    UserResponse getByLoginAccount(String loginAccount);

    Map<String, String> getNameMapByIds(Set<String> ids);

    /**
     * 分页查询
     */
    PageDTO<UserResponse> page(UserPageRequest query);

    List<UserResponse> list(UserQueryRequest query);
}
