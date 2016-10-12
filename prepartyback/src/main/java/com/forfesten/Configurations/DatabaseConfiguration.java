package com.forfesten.Configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;

/**
 * Created by heer on 12/10/2016.
 */
@Configuration
public class DatabaseConfiguration {

    @Value(value = "${database.username}")
    private String databaseUser;
    @Value(value = "${database.password}")
    private String databasePassword;
    @Value(value = "${database.host_ip}")
    private String databaseHost;
    @Value(value = "${database.port}")
    private String databasePort;
    @Value(value = "${database.name}")
    private String databaseName;


    @Bean(name="NetlunchJdbcTemplate")
    public JdbcTemplate netlunchJdbcTemplate(){
        return new JdbcTemplate(databaseDataSource());
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix="datasource.primary")
    public SimpleDriverDataSource databaseDataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {

            dataSource.setDriver(new Driver());
            dataSource.setUrl("jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + databaseName);
            dataSource.setUsername(databaseUser);
            dataSource.setPassword(databasePassword);
        } catch (SQLException e) {
            System.out.println("\n\n DRIVER PROBLEM \n\n");
            e.printStackTrace();
        }
        return dataSource;
    }
    
}
