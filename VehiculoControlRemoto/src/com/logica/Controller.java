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
	
	public int move(String command)
	{
		String regex = ";";
		int result = 0;
		
		if(command.matches(regex))
		{
			
		}
		else
		{
			if(checkCommand(command))
			{
				String[] commandPieces = command.split(",");
				int movement = Integer.parseInt(commandPieces[0]);
				
				switch(commandPieces[1])
				{
					case "N":
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
						if(movement < remoteCar.getPosX())
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
						break;
					case "O":
						break;
				}
			}
			else
			{
				result = 2;
			}
		}
		
		return result;
		
	}
	
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
