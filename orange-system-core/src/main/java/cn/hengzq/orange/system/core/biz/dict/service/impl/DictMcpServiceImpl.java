package cn.hengzq.orange.system.core.biz.dict.service.impl;

import cn.hengzq.orange.common.service.mcp.McpServerService;
import cn.hengzq.orange.common.util.CollUtils;
import cn.hengzq.orange.system.common.biz.dict.dto.data.DictDataVO;
import cn.hengzq.orange.system.common.biz.dict.dto.type.DictTypeDetailVO;
import cn.hengzq.orange.system.common.biz.dict.dto.type.DictTypeResponse;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypeSearchRequest;
import cn.hengzq.orange.system.core.biz.dict.converter.DictTypeConverter;
import cn.hengzq.orange.system.core.biz.dict.service.DictDataService;
import cn.hengzq.orange.system.core.biz.dict.service.DictTypeService;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictMcpServiceImpl implements McpServerService {

    private final DictTypeService dictTypeService;

    private final DictDataService dictDataService;

    /**
     * 获取系统中所有字典的完整列表，包含每种字典的元信息及其关联的字典数据项。
     * 每个字典类型包含：唯一标识（id）、显示名称（name）、类型编码（dictType）、启用状态（enabled: true 表示启用）、
     * 是否为系统预设（preset: true 表示由系统预定义）、描述（description），以及一个嵌套的字典数据项列表（dictDataList）。
     * 每个字典数据项包含：标签（dictLabel）、值（dictValue）、排序（sort）、回显样式（showStyle）、启用状态、预设标志和描述。
     * 在需要获取所有字典分类及其完整数据条目（例如用于表单渲染、数据校验、配置查询或下拉选项生成）时使用此函数。
     */
    @Tool(
            name = "dict_list",
            description = "Retrieves a comprehensive list of all dictionary in the system, including both type metadata and their associated dictionary data entries. " +
                    "Each dictionary type object contains: unique identifier (id), display name (name), type code (dictType), enabled status (enabled: true means active), " +
                    "whether it is a system preset (preset: true indicates predefined by system), description, " +
                    "and a nested list of dictionary data items (dictDataList). " +
                    "Each dictionary data item includes: label (dictLabel), value (dictValue), sort order, style (showStyle), enable status, preset flag, and description. " +
                    "Use this function when you need to retrieve the full structure of all dictionary categories and their corresponding data entries, " +
                    "such as for form rendering, data validation, configuration lookup, or dropdown option generation"
    )
    public List<DictTypeDetailVO> dictList() {
        List<DictTypeResponse> list = dictTypeService.search(DictTypeSearchRequest.builder().build());
        List<DictTypeDetailVO> detailList = DictTypeConverter.INSTANCE.toListDetail(list);
        if (CollUtil.isEmpty(detailList)) {
            return List.of();
        }
        Map<String, List<DictDataVO>> dataMap = dictDataService.getDictDataMapByTypes(CollUtils.convertList(list, DictTypeResponse::getDictType));
        detailList.forEach(item -> {
            item.setDictDataList(dataMap.get(item.getDictType()));
        });
        return detailList;
    }

}
