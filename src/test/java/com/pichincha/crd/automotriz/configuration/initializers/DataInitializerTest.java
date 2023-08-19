package com.pichincha.crd.automotriz.configuration.initializers;

import com.pichincha.crd.automotriz.exceptions.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ResourceLoader;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataInitializerTest {

    @InjectMocks
    private DataInitializer initializer;

    @Mock
    private ResourceLoader resourceLoader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenAFileWhenGetCsvToBeanIsInvokedThenReturnList() throws Exception {
        //GIVEN
        String fileName = "data-test.csv";
        // WHEN
        List<TestData> tests = initializer.getCsvToBean(fileName, TestData.class);

        // THEN
        assertEquals(2, tests.size());
    }

    @Test
    void givenAFileMalformedWhenGetCsvToBeanIsInvokedThenThrowException() {
        //GIVEN
        String fileName = "data-error-test.csv";
        // WHEN - THEN
        assertThrows(ApplicationException.class, () -> {
            List<TestData> tests = initializer.getCsvToBean(fileName, TestData.class);
            assertEquals(0, tests.size());
        });
    }

    @Test
    void givenAListWhenValidateDuplicatesIsInvokedThenDoNothing() throws Exception {
        //GIVEN
        TestData testData = new TestData();
        testData.data = "test1";
        testData.id = 1L;
        List<TestData> list = new ArrayList<>();
        list.add(testData);
        // WHEN -THEN
        initializer.validateDuplicates(list, "data");
    }

    @Test
    void givenAListWithDuplicateDataWhenValidateDuplicatesIsInvokedThenThrowException() {
        //GIVEN
        TestData testData = new TestData();
        testData.data = "test1";
        testData.id = 1L;
        TestData testData2 = new TestData();
        testData2.data = "test1";
        testData2.id = 1L;
        List<TestData> list = new ArrayList<>();
        list.add(testData);
        list.add(testData2);
        // WHEN - THEN
        assertThrows(ApplicationException.class, () -> {
            initializer.validateDuplicates(list, "data");
        });
    }

    @Test
    void givenAListWithAInvalidFieldWhenValidateDuplicatesIsInvokedThenThrowException() {
        //GIVEN
        TestData testData = new TestData();
        testData.data = "test1";
        testData.id = 1L;
        TestData testData2 = new TestData();
        testData2.data = "test1";
        testData2.id = 1L;
        List<TestData> list = new ArrayList<>();
        list.add(testData);
        list.add(testData2);
        // WHEN - THEN
        assertThrows(ApplicationException.class, () -> {
            initializer.validateDuplicates(list, "hola");
        });
    }
}
