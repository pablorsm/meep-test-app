package com.meep.provider;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class ConfigProvider {

    @Value(value="${meep.apidev.url.fixed}")
    private String meepApidevUrlFixed;
    
}
