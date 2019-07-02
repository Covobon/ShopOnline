package com.smartshop;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.*;


@SpringBootApplication
public class SmartshopApplication {

	public static void main(String[] args) {

		SpringApplication.run(SmartshopApplication.class, args);
	}
}

