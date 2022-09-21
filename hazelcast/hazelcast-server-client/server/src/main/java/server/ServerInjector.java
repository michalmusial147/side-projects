package server;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerInjector {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
