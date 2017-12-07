package com.logica;

public class RCCar 
{
	//Declaración de la variables de posición del carro a control remoto:
	private int posX;
	private int posY;
	
	//Constructor de la clase:
	public RCCar(int x, int y)
	{
		//Se inicializa la posición en X y Y:
		this.posX = x;
		this.posY = y;
	}
	
	//Método para ingresar un nuevo valor en la posición X:
	public void setPosX(int x)
	{
		this.posX = x;
	}
	
	//Método para ingresar un nuevo valor en la posición Y:
	public void setPosY(int y)
	{
		this.posY = y;
	}
	
	//Método para obtener la posición actual en X:
	public int getPosX()
	{
		return posX;
	}
	
	//Método para obtener la posición actual en Y:
	public int getPosY()
	{
		return posY;
	}
	
}
