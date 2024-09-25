package cn.hengzq.orange.system.core.biz.menu.service;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.menu.vo.ButtonVO;
import cn.hengzq.orange.system.common.biz.menu.vo.param.AddButtonParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.ButtonListParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.ButtonPageParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.UpdateButtonParam;

import java.util.List;

/**
 * @author hengzq
 */
public interface ButtonService {

    PageDTO<ButtonVO> page(ButtonPageParam param);

    Long add(AddButtonParam param);

    Boolean updateById(Long id, UpdateButtonParam param);

    List<ButtonVO> list(ButtonListParam param);

    Boolean removeById(Long id);

    ButtonVO getById(Long id);
}