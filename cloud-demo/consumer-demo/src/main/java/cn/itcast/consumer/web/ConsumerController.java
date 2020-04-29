package cn.itcast.consumer.web;

import cn.itcast.consumer.client.UserClient;
import cn.itcast.consumer.pojo.User;
//import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer")
@Slf4j
//@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {

//    @Autowired
//    private RestTemplate restTemplate;//调用三种Http

//    @Autowired
//    private RibbonLoadBalancerClient client;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @Autowired
    private UserClient userClient;

    @GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userClient.queryById(id);

    }


//    @GetMapping("{id}")
    //@HystrixCommand(fallbackMethod = "queryByIdFallback")//失败之后的指令，开启服务的线程隔离和降级处理
    /*@HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })*/
//    @HystrixCommand(commandProperties = {
//                    //熔断器请求次数阈值，触发熔断的最小请求次数
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
//                    //休眠时长，熔断器打开后会等一段休眠时间之后进入half open状态，放一部分请求过去
//                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
//                    //错误百分比
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
//            })
//    public String queryById(@PathVariable("id") Long id) {
//        if(id%2==0){
//            throw new  RuntimeException("");
//        }
//        String url="http://user-service/user/"+id;
//        //User user = restTemplate.getForObject(url, User.class);
//        String user = restTemplate.getForObject(url, String.class);
//        return user;
//    }

    public String queryByIdFallback(Long id) {
        log.error("查询用户信息失败，id:{}",id);
        return "服务器忙";
    }


    public String defaultFallback() {
        return "服务器忙";
    }


//    @GetMapping("{id}")
//    public User queryById(@PathVariable("id") Long id) {
//        //Map<String,Map<String,ServiceInstance>>,传入服务id，获得实例集合
//        //同一个服务（user-service）下的多个应用（tomcat）称为实例
//
//        //根据服务id去获取实例(拉取)
//        //List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
//        //从实例中取出ip和端口
//        //ServiceInstance instance = instances.get(0);//这里可以随机选择一个,从一堆中选一个就是负载均衡
//
//        //随机，轮询，hash,
//        //默认轮询
////        ServiceInstance instance = client.choose("user-service");
////        String url="http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id;
////        System.out.println(url);
//
//        String url="http://user-service/user/"+id;
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
//    }
}