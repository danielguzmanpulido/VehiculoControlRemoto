package com.interfaz;

import java.io.IOException;

public class Launcher {

	//Se declara una variable de la consola:
	private static Console console;
	
	public static void main(String[] args) throws IOException 
	{
		//Se instancia la variable de la consola, llamando a su constructor y comenzando la ejecución del programa en consola:
		console = new Console();
	}

}
