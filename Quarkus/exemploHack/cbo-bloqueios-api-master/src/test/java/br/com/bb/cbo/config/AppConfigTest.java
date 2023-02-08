package br.com.bb.cbo.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class AppConfigTest {
	
    @Test
    @DisplayName("TEST Health call()")
    public void getRandomNumber() {
    	
    	AppConfig appConfig = new AppConfig();
    	
    	Assertions.assertNotNull(appConfig);
    }
}
