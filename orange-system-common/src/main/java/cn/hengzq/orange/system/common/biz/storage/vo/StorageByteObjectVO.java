package cn.hengzq.orange.system.common.biz.storage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@Schema(description = "系统 - 存储 - 字节数组VO")
public class StorageByteObjectVO implements Serializable {

    @Schema(description = "文件ID")
    private Long id;

    @Schema(description = "文件原名称")
    private String originalName;

    @Schema(description = "新名称")
    private String newName;

    @Schema(description = "文件类型")
    private String type;

    @Schema(description = "文件大小")
    private Long size;

    @Schema(description = "文件URL")
    private String url;

    @Schema(description = "字节数组")
    private byte[] content;
}
