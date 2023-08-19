package com.pichincha.crd.automotriz.configuration.initializers;

import com.pichincha.crd.automotriz.repository.BrandRepository;
import com.pichincha.crd.automotriz.service.dto.entity.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class DataBrandInitializerTest {

    @InjectMocks
    private DataBrandInitializer initializer;

    @Mock
    private BrandRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenClientsWhenClientNoExistThenSave() {
        // given
        List<Brand> list = new ArrayList<>();
        Brand entity = new Brand();
        entity.setDescription("ID1");
        list.add(entity);
        // when
        when(repository.existsByDescription("ID1")).thenReturn(false);

        ReflectionTestUtils.invokeMethod(initializer, "save", list);
        // then
        verify(repository, times(1)).save(entity);
    }

    @Test
    void givenClientsWhenClientExistThenNoSave() {
        // given
        List<Brand> list = new ArrayList<>();
        Brand entity = new Brand();
        entity.setDescription("ID1");
        list.add(entity);
        // when
        when(repository.existsByDescription("ID1")).thenReturn(true);

        ReflectionTestUtils.invokeMethod(initializer, "save", list);
        // then
        verify(repository, times(0)).save(entity);
    }

}
