package micro.service.demo.clients;

import micro.service.demo.config.Feignconfigs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(value = "javademo", configuration = Feignconfigs.class)
public interface IJavaDemoClient {
    @RequestMapping(value = "/user/introduce", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    Object getUserIntroduce();
}
