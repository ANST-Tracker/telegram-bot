package com.anst.sd.telegram.fw;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {
    "com.anst.sd.telegram.adapter.*"
})
public class FeignConfiguration {
}
