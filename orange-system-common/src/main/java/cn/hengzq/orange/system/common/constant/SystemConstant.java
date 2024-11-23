package cn.hengzq.orange.system.common.constant;


import cn.hengzq.orange.common.constant.VersionConstant;

/**
 * 系统服务常量
 *
 * @author hengzq
 */
public interface SystemConstant {

    /**
     * V1.0 URL 前缀
     */
    String V1_0_URL_PREFIX = "/orange-system/" + VersionConstant.V1_0;

    /**
     * V2.0 URL 前缀
     */
    String V2_0_URL_PREFIX = "/orange-system/" + VersionConstant.V2_0;


    /**
     * orange-system 服务的名称.需要保证和 spring.application.name 保持一致
     */
    String SERVICE_NAME = "ORANGE-SYSTEM-SERVICE-V1";


    String FILE_TEMP_DIRECTORY = "orange";
}