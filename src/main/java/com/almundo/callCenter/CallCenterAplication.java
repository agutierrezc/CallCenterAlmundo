package com.almundo.callCenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase principal con la que se inicia la aplicacion de SpringBoot
 * @author felipe.gutierrez
 *
 */
@SpringBootApplication
@ComponentScan
public class CallCenterAplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(CallCenterAplication.class, args);
    }
}
