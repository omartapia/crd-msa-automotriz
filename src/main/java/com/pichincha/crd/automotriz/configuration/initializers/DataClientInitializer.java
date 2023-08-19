package com.pichincha.crd.automotriz.configuration.initializers;

import com.pichincha.crd.automotriz.repository.ClientRepository;
import com.pichincha.crd.automotriz.service.dto.ClientDto;
import com.pichincha.crd.automotriz.service.dto.entity.Client;
import com.pichincha.crd.automotriz.service.mapper.ClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataClientInitializer extends DataInitializer implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DataClientInitializer.class);

    @Autowired
    private ClientRepository repository;

    @Override
    public void run(String... args) {
        try {
            List<ClientDto> clients = getCsvToBean("data-client.csv", ClientDto.class);
            validateDuplicates(clients, "identification");
            save(ClientMapper.INSTANCE.dtoListToEntityList(clients));
            logger.info("Persisted [{}] clients.", repository.count());
        } catch (Exception exception) {
            logger.error("Error to initialize client data, error {}", exception);
            throw new RuntimeException("Client Initializer " + exception.getMessage());
        }
    }

    private void save(List<Client> clients) {
        clients.forEach(client -> {
            if(!repository.existsByIdentification(client.getIdentification())) {
                repository.save(client);
            }
        });
    }
}
