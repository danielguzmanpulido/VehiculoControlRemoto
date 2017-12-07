package com.logica;

public class RCCar 
{
	//Declaraci�n de la variables de posici�n del carro a control remoto:
	private int posX;
	private int posY;
	
	//Constructor de la clase:
	public RCCar(int x, int y)
	{
		//Se inicializa la posici�n en X y Y:
		this.posX = x;
		this.posY = y;
	}
	
	//M�todo para ingresar un nuevo valor en la posici�n X:
	public void setPosX(int x)
	{
		this.posX = x;
	}
	
	//M�todo para ingresar un nuevo valor en la posici�n Y:
	public void setPosY(int y)
	{
		this.posY = y;
	}
	
	//M�todo para obtener la posici�n actual en X:
	public int getPosX()
	{
		return posX;
	}
	
	//M�todo para obtener la posici�n actual en Y:
	public int getPosY()
	{
		return posY;
	}
	
}
