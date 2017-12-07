package com.interfaz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.logica.Controller;

public class Console 
{

	private BufferedReader buffer;
	private Controller controller;
	
	public Console() throws IOException
	{
		buffer = new BufferedReader(new InputStreamReader(System.in));
		
		setSurface();
		main();
		
	}
	
	public void setSurface() throws IOException
	{
		String regex = "^[0-9]+$";
		
		System.out.println("=========BIENVENIDO A LA APLICACION DE AUTOMOVIL POR CONTROL REMOTO=========");
		System.out.println("Por favor Ingrese el número de filas (o eje X) de la superficie de la pista:");
		String n = buffer.readLine();
		
		while(!n.matches(regex))
		{
			System.out.println("¡El número de filas debe ser un número entero, por favor intentelo de nuevo!:");
			n = buffer.readLine();
		}
		
		System.out.println("Por favor Ingrese el número de columnas (o eje Y) de la superficie de la pista:");
		String m = buffer.readLine();
		
		while(!m.matches(regex))
		{
			System.out.println("¡El número de columnas debe ser un número entero, por favor intentelo de nuevo!:");
			m = buffer.readLine();
		}
		
		controller = new Controller(Integer.parseInt(n), Integer.parseInt(m));
		
	}
	
	public void main() throws IOException
	{
		Boolean finish = false;
		String regex = "^[12]$";
		
		while(!finish)
		{
			System.out.println("==========================MENU PRINCIPAL==========================");
			System.out.println("1 --- Ejecutar Comando \n2 --- Salir");
			String entry = buffer.readLine();
			
			while(!entry.matches(regex))
			{
				System.out.println("¡Por favor ingrese un valor válido!:");
				entry = buffer.readLine();
			}
			
			switch(entry)
			{
				case "1":
					System.out.println("Por favor ingrese el Comando:");
					String command = buffer.readLine();
					
					int result = controller.move(command);
					
					switch(result)
					{
						case 1:
							System.out.println("El comando enviado fue: " + command);
							System.out.println("La posición final del Vehiculo fue: (" + controller.getRCPosX() + "," + controller.getRCPosY() + ")");
							break;
						case 2:
							System.out.println("¡Error en Formato de Comando!");
							break;
						case 3:
							System.out.println("¡Se ha detenido el Avance por Salir de los Limites!");
							break;
						default: 
							break;
					}
					
					break;
				case "2":
					System.out.println("====================APLICACION TERMINADA====================");
					finish = true;
					break;
				default:
					break;
			}
		}
	}
	
}
