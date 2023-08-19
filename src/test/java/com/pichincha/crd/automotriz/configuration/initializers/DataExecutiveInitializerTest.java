package com.pichincha.crd.automotriz.configuration.initializers;

import com.pichincha.crd.automotriz.repository.ExecutiveRepository;
import com.pichincha.crd.automotriz.service.dto.entity.Executive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DataExecutiveInitializerTest {

    @InjectMocks
    private DataExecutiveInitializer initializer;

    @Mock
    private ExecutiveRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenExecutivesWhenExecutiveNoExistThenSave() {
        // given
        List<Executive> list = new ArrayList<>();
        Executive entity = new Executive();
        entity.setIdentification("ID1");
        entity.setYardId(1L);
        list.add(entity);
        // when
        when(repository.existsByIdentification("ID1")).thenReturn(false);

        ReflectionTestUtils.invokeMethod(initializer, "save", list);
        // then
        verify(repository, times(1)).save(entity);
    }

    @Test
    void givenExecutivesWhenExecutiveExistThenUpdate() {
        // given
        List<Executive> list = new ArrayList<>();
        Executive entity = new Executive();
        entity.setIdentification("ID1");
        entity.setYardId(1L);
        list.add(entity);
        // when
        when(repository.existsByIdentification("ID1")).thenReturn(true);

        ReflectionTestUtils.invokeMethod(initializer, "save", list);
        // then
        verify(repository, times(1)).save(entity);
    }
}
