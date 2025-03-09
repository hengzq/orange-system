package cn.hengzq.orange.system.common.biz.storage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@Schema(description = "系统 - 存储 - 存储对象VO")
public class StorageObjectVO implements Serializable {

    @Schema(description = "文件ID")
    private Long id;

    @Schema(description = "文件原名称")
    private String originalName;

    @Schema(description = "新名称")
    private String fileName;

    @Schema(description = "文件类型")
    private String type;

    @Schema(description = "文件大小 单位：字节")
    private Long size;
}
