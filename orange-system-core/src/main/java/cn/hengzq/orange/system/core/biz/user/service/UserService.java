package cn.hengzq.orange.system.permission.core.service;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.user.vo.UserDetailVO;
import cn.hengzq.orange.system.common.biz.user.vo.UserVO;
import cn.hengzq.orange.system.common.biz.user.vo.param.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hengzq
 */
public interface UserService {


    Long add(AddUserParam param);


    Boolean updateById(Long id, UpdateUserParam param);


    /**
     * 更新密码
     */
    Boolean updatePassword(UpdatePasswordParam param);

    /**
     * 重置密码
     */
    Boolean resetPassword(ResetPasswordParam param);

    /**
     * 根据用户ID查询用户
     */
    UserDetailVO getById(Long userId, UserDetailQueryParam param);

    /**
     * 根据登录账号查询用户
     */
    UserVO getByLoginAccount(String loginAccount);


    /**
     * 分页查询
     */
    PageDTO<UserVO> page(UserPageParam query);

    List<UserVO> list(UserListParam query);

    Map<Long, String> getNameMapByIds(Set<Long> ids);

    Boolean removeById(Long id);
}
