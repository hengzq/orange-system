package cn.hengzq.orange.system.common.biz.permission.dto;


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
public class LoginResponse implements Serializable {

    @Schema(description = "Token")
    private String token;

    @Schema(description = "过期时间（秒）. 默认：7200S")
    private long expiresIn;

    public static LoginResponse of(String token) {
        return new LoginResponse(token, 2 * 60 * 60);
    }

    public static LoginResponse of(String token, long expiresIn) {
        return new LoginResponse(token, expiresIn);
    }


}
