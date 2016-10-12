package com.forfesten;

import com.mysql.cj.jdbc.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.SQLException;

/**
 * Created by heer on 12/10/2016.
 */
@Configuration
public class DatabaseConfiguration {

    private String databaseUser="root";
    private String databasePassword="";
    private String databaseHost="localhost";
    private String databasePort="3306";
    private String databaseName="preparty_test";


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
