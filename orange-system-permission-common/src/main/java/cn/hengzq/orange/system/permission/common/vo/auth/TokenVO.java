package cn.hengzq.orange.system.permission.common.vo.auth;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
  *
 */
@Data
@Schema(description = "Token响应信息")
@NoArgsConstructor
@AllArgsConstructor
public class TokenVO implements Serializable {

    @Schema(description = "Token")
    private String token;

}
