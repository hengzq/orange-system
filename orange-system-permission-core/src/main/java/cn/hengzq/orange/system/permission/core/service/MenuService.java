package cn.hengzq.orange.system.permission.core.service;


import cn.hengzq.orange.system.permission.common.vo.menu.MenuDetailVO;
import cn.hengzq.orange.system.permission.common.vo.menu.MenuVO;
import cn.hengzq.orange.system.permission.common.vo.menu.param.AddMenuParam;
import cn.hengzq.orange.system.permission.common.vo.menu.param.MenuListParam;
import cn.hengzq.orange.system.permission.common.vo.menu.param.UpdateMenuParam;

import java.util.List;


/**
 * @author hengzq
 */
public interface MenuService {

    /**
     * 根据条件查询菜单数据
     */
    List<MenuDetailVO> list(MenuListParam param);

    /**
     * 新增菜单
     */
    Long add(AddMenuParam param);

    /**
     * 根据ID更新
     */
    Boolean updateById(Long id, UpdateMenuParam param);

    /**
     * 根据ID删除数据
     */
    Boolean removeById(Long id);

    MenuVO getById(Long id);

}
