package com.meep.provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigProviderTest {

    private ConfigProvider objIConfigProvider;
    private String meepApiDevUrlTest = "meepApiDevUrlTest";

    @BeforeEach
    public void initTest(){
        objIConfigProvider = new ConfigProvider();
        ReflectionTestUtils.setField(objIConfigProvider,"meepApidevUrlFixed",meepApiDevUrlTest);
    }

    @Test
    public void shouldReturnValidValues(){
        assertThat(objIConfigProvider.getMeepApidevUrlFixed()).isEqualTo(meepApiDevUrlTest);
    }
}
