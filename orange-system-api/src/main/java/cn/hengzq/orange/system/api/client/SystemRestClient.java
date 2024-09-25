package cn.hengzq.orange.system.api.client;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.constant.SecurityConstant;
import cn.hengzq.orange.common.exception.ServiceException;
import cn.hengzq.orange.context.ApplicationContextHelper;
import cn.hengzq.orange.context.GlobalContextHelper;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@Slf4j
public class SystemRestClient {

    private SystemRestClient() {
    }

    public static RestClient getInstance() {
        return SystemRestClientHolder.INSTANCE;
    }

    public static HttpServiceProxyFactory httpServiceProxyFactory() {
        RestClientAdapter adapter = RestClientAdapter.create(getInstance());
        return HttpServiceProxyFactory.builderFor(adapter).build();
    }

    private static class SystemRestClientHolder {
        private static final RestClient INSTANCE = getRestClient();

        public static RestClient getRestClient() {
            NacosDiscoveryClient discoveryClient = ApplicationContextHelper.getBean(NacosDiscoveryClient.class);
            List<ServiceInstance> instances = discoveryClient.getInstances(SystemConstant.SERVICE_NAME);
            if (instances.isEmpty()) {
                log.warn("No available instances for service: {}", SystemConstant.SERVICE_NAME);
                throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_INTERNAL_SERVER_ERROR);
            }

            ServiceInstance instance = instances.get(0);
            return RestClient.builder()
                    .requestFactory(new HttpComponentsClientHttpRequestFactory())
                    .defaultHeader(SecurityConstant.AUTHORIZATION, "Bearer " + GlobalContextHelper.getToken())
                    .baseUrl("http://" + instance.getHost() + ":" + instance.getPort() + "/")
                    .build();
        }
    }
}
