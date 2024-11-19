package cn.hengzq.orange.system.common.biz.storage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "系统 - 存储 - 文件上传结果")
public class UploadResult implements Serializable {

    @Schema(description = "相对路径")
    private String relativePath;

    @Schema(description = "文件类型")
    private String type;

    @Schema(description = "文件大小")
    private Long size;
}
