package cn.hengzq.orange.system.common.biz.storage.vo.param;

import cn.hengzq.orange.system.common.biz.storage.constant.StorageErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "系统 - 存储对象 - 上传对象存储")
public class StorageObjectUploadParam implements Serializable {

    @NotBlank(message = StorageErrorCode.ORIGINAL_NAME_CANNOT_NULL_CODE)
    @Schema(description = "文件原名称")
    private String originalName;

    @NotEmpty(message = StorageErrorCode.ORIGINAL_CONTENT_CANNOT_NULL_CODE)
    @Schema(description = "存储内容")
    private byte[] content;

}
