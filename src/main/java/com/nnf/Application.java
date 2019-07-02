package com.nnf;

import com.nnf.domain.*;
import com.nnf.repository.*;
import com.nnf.storage.StorageProperties;
import com.nnf.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@ComponentScan("com.nnf")
@EntityScan("com.nnf")
@EnableJpaRepositories("com.nnf")
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService, CauseRepository causeRepository, OccurrenceRepository occurrenceRepository, PainTypeRepository painTypeRepository, PositionRepository positionRepository, RemedyRepository remedyRepository, TemperatureRepository temperatureRepository) {
        return (args) -> {

            storageService.deleteAll();
            storageService.init();

        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
