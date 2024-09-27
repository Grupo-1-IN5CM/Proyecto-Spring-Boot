package com.agenciaviajes.webapp.agenciaViajes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agenciaviajes.webapp.agenciaViajes.system.Main;

import javafx.application.Application;

@SpringBootApplication
public class AgenciaViajesApplication {

	public static void main(String[] args) {
		Application.launch(Main.class, args);
		SpringApplication.run(AgenciaViajesApplication.class, args);
	}

}
