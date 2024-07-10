package cn.rzpt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@SpringBootApplication
public class DddOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(DddOneApplication.class, args);
        log.info("项目启动成功");
    }

}
