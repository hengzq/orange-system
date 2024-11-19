package cn.hengzq.orange.system.core.biz.storage.entity;

import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import cn.hengzq.orange.mybatis.handler.EnumCodeTypeHandler;
import cn.hengzq.orange.system.common.biz.storage.constant.StorageModeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_storage_object", autoResultMap = true)
public class StorageObjectEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "mode", typeHandler = EnumCodeTypeHandler.class)
    private StorageModeEnum mode;

    @TableField("original_name")
    private String originalName;

    @TableField("relative_path")
    private String relativePath;

    @TableField("type")
    private String type;

    @TableField("size")
    private Long size;

    @TableField("download_url")
    private String downloadUrl;

    @TableField("preview_url")
    private String previewUrl;
}
