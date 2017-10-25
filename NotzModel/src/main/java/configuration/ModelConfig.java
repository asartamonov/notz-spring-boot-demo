package configuration;

import domain.note.jpahelper.LocalDateTimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {
    @Bean
    public LocalDateTimeConverter dateTimeConverter() {
        return new LocalDateTimeConverter();
    }
}
