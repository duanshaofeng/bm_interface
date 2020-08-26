package com.bm.https.jdbc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {


    /**
     * 数据源 1
     */
    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "primary.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }



    /**
     * 数据源 1 的 JdbcTemplate
     */
    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 数据源 2
     */

    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "second.datasource")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }



    /**
     * 数据源 2 的 JdbcTemplate
     */
    @Bean(name = "secondJdbcTemplate")
    public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
