package com.pichincha.crd.automotriz.configuration.initializers;

import com.pichincha.crd.automotriz.repository.ClientRepository;
import com.pichincha.crd.automotriz.service.dto.entity.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class DataClientInitializerTest {

    @InjectMocks
    private DataClientInitializer initializer;

    @Mock
    private ClientRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenClientsWhenClientNoExistThenSave() {
        // given
        List<Client> clients = new ArrayList<>();
        Client client = new Client();
        client.setIdentification("ID1");
        clients.add(client);
        // when
        when(repository.existsByIdentification("ID1")).thenReturn(false);

        ReflectionTestUtils.invokeMethod(initializer, "save", clients);
        // then
        verify(repository, times(1)).save(client);
    }

    @Test
    void givenClientsWhenClientExistThenNoSave() {
        // given
        List<Client> list = new ArrayList<>();
        Client entity = new Client();
        entity.setIdentification("ID1");
        list.add(entity);
        // when
        when(repository.existsByIdentification("ID1")).thenReturn(true);

        ReflectionTestUtils.invokeMethod(initializer, "save", list);
        // then
        verify(repository, times(0)).save(entity);
    }

}
