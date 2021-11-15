package com.singlemusic.example;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.time.ZoneId;
import java.util.TimeZone;

import static com.wix.mysql.distribution.Version.v8_latest;

@Slf4j
@Configuration
public class EmbeddedMysqlConfiguration {
    private  EmbeddedMysql embeddedMysql;

    @PostConstruct
    public void startMysql() {
        MysqldConfig config = MysqldConfig.aMysqldConfig(v8_latest)
                .withPort(3307)
                .withTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")))
                .withUser("test", "test")
                .build();

        SchemaConfig schemaConfig = SchemaConfig.aSchemaConfig("test_database")
                .build();

        embeddedMysql = EmbeddedMysql.anEmbeddedMysql(config)
                .addSchema(schemaConfig)
                .start();
    }

    @PreDestroy
    public void stopMysql() {
        if (null != embeddedMysql) {
            embeddedMysql.stop();
        }
    }
}
