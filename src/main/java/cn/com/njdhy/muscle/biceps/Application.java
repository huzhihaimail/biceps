package cn.com.njdhy.muscle.biceps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("cn.com.njdhy.muscle.biceps.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
