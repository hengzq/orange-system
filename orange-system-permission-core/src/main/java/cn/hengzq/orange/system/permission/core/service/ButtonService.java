package cn.hengzq.orange.system.permission.core.service;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.permission.common.vo.button.ButtonVO;
import cn.hengzq.orange.system.permission.common.vo.button.param.AddButtonParam;
import cn.hengzq.orange.system.permission.common.vo.button.param.ButtonListParam;
import cn.hengzq.orange.system.permission.common.vo.button.param.ButtonPageParam;
import cn.hengzq.orange.system.permission.common.vo.button.param.UpdateButtonParam;

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
