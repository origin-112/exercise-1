package com.rui.spzx.manager.properties;

import io.minio.messages.Prefix;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.properties
 * @className: UserProperties
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/14 16:29
 * @version: 1.0
 */
@Data
@ConfigurationProperties(prefix = "spzx.auth")
public class UserProperties {
    private List<String> noAuthUrls;
}
