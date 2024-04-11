package com.rui.spzx.manager;

import com.rui.spzx.manager.properties.UserProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager
 * @className: ManagerApplication
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/2 15:54
 * @version: 1.0
 */
@SpringBootApplication
@ComponentScan("com.rui.spzx")
@EnableConfigurationProperties(value = {UserProperties.class })
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class , args);
    }
}
