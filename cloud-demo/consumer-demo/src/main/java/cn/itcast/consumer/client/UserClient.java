package cn.itcast.consumer.client;

import cn.itcast.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service",fallback = UserClientFallback.class)
public interface UserClient {

    @GetMapping("user/{id}")
    /**
     * 通过 @PathVariable 可以将URL中占位符参数{xxx}绑定到处理器类的
     * 方法形参中@PathVariable(“xxx“)
     *
     * @RequestMapping(value=”user/{id}/{name}”)
     * 请求路径：http://localhost:8080/hello/show5/1/james
     */
    User queryById(@PathVariable("id")Long id);

}
