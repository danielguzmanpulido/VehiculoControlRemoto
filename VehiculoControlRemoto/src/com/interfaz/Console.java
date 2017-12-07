package com.interfaz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.logica.Controller;

public class Console 
{
	//Se declaran dos variables, un buffer de lectura de los comandos y el controlador:
	private BufferedReader buffer;
	private Controller controller;
	
	//Constructor de la Clase:
	public Console() throws IOException
	{
		//Se instancia el buffer:
		buffer = new BufferedReader(new InputStreamReader(System.in));
		
		//Se ejecutan dos m�todos:
		setSurface();
		main();
		
	}
	
	//Este m�todo permite leer las dimensiones de la pista:
	public void setSurface() throws IOException
	{
		//Expresi�n regular para validar que el comando ingresado sea un entero:
		String regex = "^[0-9]+$";
		
		//Se pide el n�mero de filas:
		System.out.println("=========BIENVENIDO A LA APLICACION DE AUTOMOVIL POR CONTROL REMOTO=========");
		System.out.println("Por favor Ingrese el n�mero de filas (o eje X) de la superficie de la pista:");
		String n = buffer.readLine();
		
		//En este bucle se solicita el n�mero de filas hasta que el comando ingresado sea un entero:
		while(!n.matches(regex))
		{
			System.out.println("�El n�mero de filas debe ser un n�mero entero, por favor intentelo de nuevo!:");
			n = buffer.readLine();
		}
		
		//Se pide el n�mero de columnas:
		System.out.println("Por favor Ingrese el n�mero de columnas (o eje Y) de la superficie de la pista:");
		String m = buffer.readLine();
		
		//En este bucle se solicita el n�mero de columnas hasta que el comando ingresado sea un entero:
		while(!m.matches(regex))
		{
			System.out.println("�El n�mero de columnas debe ser un n�mero entero, por favor intentelo de nuevo!:");
			m = buffer.readLine();
		}
		
		//Se instancia el controlador pasando como parametros el n�mero de filas y columnas ingresados por consola:
		controller = new Controller(Integer.parseInt(n), Integer.parseInt(m));
		
	}
	
	//Este m�todo muestra el men� principal y se encarga de mostrar al usuario una respuesta impresa en consola de acuerdo al resultado de los m�todos del controlador:
	public void main() throws IOException
	{
		//Boolean para determinar si la ejecuci�n debe terminar:
		Boolean finish = false;
		//Expresi�n regular que determina si el comando ingresado en consola es 1 o 2, solo un digito:
		String regex = "^[12]$";
		
		//Este bucle permite mostrar el men� principal indefinidamente hasta que el usuario decida terminar la ejecuci�n:
		while(!finish)
		{
			//Se captura la acci�n a tomar:
			System.out.println("==========================MENU PRINCIPAL==========================");
			System.out.println("1 --- Ejecutar Comando \n2 --- Salir");
			String entry = buffer.readLine();
			
			//Se solicita la acci�n en un bucle hasta que coincida con la expresi�n regular:
			while(!entry.matches(regex))
			{
				System.out.println("�Por favor ingrese un valor v�lido!:");
				entry = buffer.readLine();
			}
			
			//En este switch se determina que acciones hay que tomar de acuerdo a la acci�n ingresada:
			switch(entry)
			{
				case "1":
					//Se solicita el ingreso del comando:
					System.out.println("Por favor ingrese el Comando:");
					String command = buffer.readLine();
					
					//Se llama el metodo de la clase Controller, el cual ejecuta la l�gica:
					int result = controller.move(command);
					
					//En este switch se determina que se debe imprimir de acuerdo al resultado del metodo:
					switch(result)
					{
						case 1:
							//El 1 indica que el o los comandos fueron ejecutos exitosamente, as� que se imprime el comando ejecutado, la posici�n final del carro a control remoto y una representaci�n grafica de la posici�n de este carro:
							System.out.println("El comando enviado fue: [" + command + "]");
							System.out.println("La posici�n final del Vehiculo fue: (" + (controller.getTrack()[0].length - controller.getRCPosX() - 1) + "," + controller.getRCPosY() + ")\n");
							System.out.println("Representaci�n Grafica de la Posici�n Actual del Vehiculo:");
							System.out.println("Leyenda: R --- Vehiculo, X --- Espacio en Blanco\n");
							
							//En este for solamente se busca la posici�n grafica anterior del carro y se elimina:
							for(int i = 0; i < controller.getTrack()[0].length; i++)
							{
								for(int j = 0; j < controller.getTrack()[1].length; j++)
								{
									if(controller.getTrack()[i][j].equals("R"))
									{
										controller.getTrack()[i][j] = "X";
									}
								}
							}
							
							//Esta linea imprime la nueva posici�n del carro en el arreglo multidimensional:
							controller.getTrack()[controller.getRCPosX()][controller.getRCPosY()] = "R";
							
							//Se imprime el arreglo multidimensional:
							for(int i = 0; i < controller.getTrack()[0].length; i++)
							{
								for(int j = 0; j < controller.getTrack()[1].length; j++)
								{
									System.out.print(controller.getTrack()[i][j] + " ");
									if(controller.getTrack()[i][j].equals("R"))
									{
										controller.getTrack()[i][j] = "X";
									}
								}
								
								System.out.println("");
							}
							
							break;
						case 2:
							//El 2 indica que el formato establecido para el comando es incorrecto, por lo que se imprime el mensaje:
							System.out.println("�Error en Formato de Comando!");
							break;
						case 3:
							//El 3 indica que el movimiento sobrepasa los limites de la pista, por lo que se debe imprimir este mensaje:
							System.out.println("�Se ha detenido el Avance por Salir de los Limites!");
							break;
						default: 
							break;
					}
					
					break;
				case "2":
					//Si el usuario ingreso 2 como acci�n, se da por terminada la aplicaci�n cambio a verdadero el boolean instanciado anteriormente para romper el bucle while:
					System.out.println("====================APLICACION TERMINADA====================");
					finish = true;
					break;
				default:
					break;
			}
		}
	}
	
}
