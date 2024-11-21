package org.interview.springboot;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class InterviewSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewSpringbootApplication.class, args);
    }

}
