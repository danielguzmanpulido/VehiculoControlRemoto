package com.logica;

public class Controller 
{
	
	//Se declaran dos variables, una de la clase Track que representa la pista y otra de la clase RCCar que representa el carro a control remoto:
	private Track surface;
	private RCCar remoteCar;
	
	//Constructor de la Clase
	public Controller(int n, int m)
	{
		//Se instancia la pista pasando como parametros el n�mero de filas y columnas para el arreglo multidimensional:
		surface = new Track(n, m);
		//Se instancia el carro a control remoto, con una posici�n en X de 0, pero una posici�n Y del m�ximo de la pista en columnas: 
		remoteCar = new RCCar((n - 1), 0);
	}
	
	//Este metodo realiza las validaciones del comando llamando otros metodos, y si es correcto, ejecuta el o los comandos respectivos:
	public int move(String command)
	{
		int result = 0;
		
		//Inicialmente se valida si existe al menos un punto y coma en el comando, as� se puede saber si es un comando individual o una cadena de comandos:
		if(command.contains(";"))
		{
			//Ya que el comando viene en cadena, se separa cada comando por el punto y coma:
			String[] commandParts = command.split("\\;");
			//Se guarda la posici�n original del carro para restablecer la posici�n si alguno de los comandos presenta un error:
			int originalX = remoteCar.getPosX();
			int originalY = remoteCar.getPosY();
			
			//Se comienza a iterar entre cada comando:
			for(int f = 0; f < commandParts.length; f++)
			{
				//Se valida que el comando tenga la estructura definida:
				if(checkCommand(commandParts[f]))
				{
					//Se ejecuta el comando:
					result = executeMovement(commandParts[f]);
					
					//Si la ejecuci�n retorna 3, esto indica que el movimiento sobrepasa los limites de la pista, as� que se debe reiniciar a la posici�n original el carro a control remoto:
					if(result == 3)
					{
						f = commandParts.length;
						remoteCar.setPosX(originalX);
						remoteCar.setPosY(originalY);
					}
				}
				else
				{
					//En este caso, el comando no tiene la estructura valida, as� que se retorna un 2 para indicar esto a la clase de la interfaz grafica, y se restablece la posici�n del carro a control remoto:
					f = commandParts.length;
					remoteCar.setPosX(originalX);
					remoteCar.setPosY(originalY);
					result = 2;
				}
			}
		}
		else
		{
			//En este caso, se encuentra que el comando es individual, por lo que se valida la estructura:
			if(checkCommand(command))
			{
				//Ahora se ejecuta el movimiento de acuerdo al comando:
				result = executeMovement(command);
			}
			else
			{
				//Esto indica que el comando no tiene la esctructura requerida, por lo que se retorna 2 para indicar a la interfaz grafica sobre esto:
				result = 2;
			}
		}
		
		return result;
		
	}
	
	//Este metodo verifica que el comando tenga el formato especificado:
	public Boolean checkCommand(String command)
	{
		//Se valida que el comando tenga la estructura especificada mediante una expresi�n regular la cual se explica a continuaci�n:
		//-El caracter "^" indica que no puede existir ning�n caracter antes de la expresi�n a evaluar.
		//-El rango [0-9]+ indica que debe existir un numero entero entre 0 y 9, y el "+" indica que esto se puede repetir, as� que es valido un n�mero con longitud mayor a 1 digito.
		//-El caracter "," indica que, en este punto de la cadena, debe existir exacamente una coma.
		//-El rango [NSEO] indica que puede existir solo un caracter N, S, E u O teniendo en cuenta que debe ser mayuscula, y que solamente sea 1 caracter, de otra forma no concordar�a.
		//- Finalmente, el caracter "$" indica que luego de la expresi�n a evaluar no puede existir otro caracter, es parecido al "^" pero validando que sea despu�s y no antes.
		String regex = "^[0-9]+,[NSEO]$";
		
		//Se retorna si coincide o no el comando con la expresi�n regular:
		return command.matches(regex);
	}
	
	//Este metodo ejecuta el movimiento del vehiculo a control remoto:
	public int executeMovement(String command)
	{
		//Se separa el n�mero de lineas a mover y la direcci�n por la coma:
		String[] commandPieces = command.split(",");
		//Se transforma las lineas a mover de String a Integer:
		int movement = Integer.parseInt(commandPieces[0]);
		int result = 0;
		
		//En este switch se valida la direcci�n del movimiento de acuerdo a las letras "N, Arriba", "S, Abajo", "E, Izquierda", "O, Derecha":
		switch(commandPieces[1])
		{
			case "N":
				//Si el movimiento es mayor a la posici�n en X del carro, el movimiento sobrepasa los limites de la pista, en caso contrario, se ejecuta sobrescribiendo la posici�n en X:
				if(movement > remoteCar.getPosX())
				{
					result = 3;
				}
				else
				{
					remoteCar.setPosX(remoteCar.getPosX() - movement);
					result = 1;
				}
				break;
			case "S":
				//Si el movimiento es mayor a la posici�n en X del carro, el movimiento sobrepasa los limites de la pista, en caso contrario, se ejecuta sobrescribiendo la posici�n en X:
				if(movement > (surface.getSurface()[0].length - remoteCar.getPosX() - 1))
				{
					result = 3;
				}
				else
				{
					remoteCar.setPosX(remoteCar.getPosX() + movement);
					result = 1;
				}
				break;
			case "E":
				//Si el movimiento es mayor a la posici�n en Y del carro, el movimiento sobrepasa los limites de la pista, en caso contrario, se ejecuta sobrescribiendo la posici�n en Y:
				if(movement > remoteCar.getPosY())
				{
					result = 3;
				}
				else
				{
					remoteCar.setPosY(remoteCar.getPosY() - movement);
					result = 1;
				}
				break;
			case "O":
				//Si el movimiento es mayor a la posici�n en Y del carro, el movimiento sobrepasa los limites de la pista, en caso contrario, se ejecuta sobrescribiendo la posici�n en Y:
				if(movement > (surface.getSurface()[1].length - remoteCar.getPosY() - 1))
				{
					result = 3;
				}
				else
				{
					remoteCar.setPosY(remoteCar.getPosY() + movement);
					result = 1;
				}
				break;
		}
		
		return result;
	}
	
	//Se retorna el arreglo multidimensional que representa la pista:
	public String[][] getTrack()
	{
		return surface.getSurface();
	}
	
	//Se retorna la posici�n X del carro a control remoto:
	public int getRCPosX()
	{
		return remoteCar.getPosX();
	}
	
	//Se retorna la posici�n Y del carro a control remoto:
	public int getRCPosY()
	{
		return remoteCar.getPosY();
	}

}
