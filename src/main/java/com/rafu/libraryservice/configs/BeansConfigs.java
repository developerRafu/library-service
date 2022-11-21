package com.rafu.libraryservice.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfigs {
  @Bean
  public ModelMapper get() {
    return new ModelMapper();
  }
}
