package com.singlemusic.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;


@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes =  {EmbeddedMysqlConfiguration.class, Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@EnableConfigurationProperties
public class DatabaseTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testDatabase() {
        Query query = entityManager.createNativeQuery("SELECT 1");
        Assertions.assertEquals(BigInteger.valueOf(1L), query.getSingleResult());
    }
}
