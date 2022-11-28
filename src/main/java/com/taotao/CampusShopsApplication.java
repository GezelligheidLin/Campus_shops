package com.taotao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author KaiJie
 * Date: 2022/10/24
 */
@SpringBootApplication
@Slf4j
public class CampusShopsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusShopsApplication.class, args);
        log.info("淘了淘个商城项目启动成功");
    }
    
}
