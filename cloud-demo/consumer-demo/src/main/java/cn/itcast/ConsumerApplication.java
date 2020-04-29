package cn.itcast;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.RunnableFuture;

//@EnableCircuitBreaker//服务降级中的熔断
//@SpringBootApplication
//@EnableDiscoveryClient

@EnableFeignClients
@SpringCloudApplication//上面三个都有了
public class ConsumerApplication {


    /**
     * RestTemplate可以调用三种Http
     * @return
     */
//    @Bean
//    @LoadBalanced//负载均衡，会内置一个拦截器拦截RestTemplate的Http请求
//    public RestTemplate restTemplate() {
//        // 这次我们使用了OkHttp客户端,只需要注入工厂即可
//        //return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
//        return new RestTemplate();
//    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }
}