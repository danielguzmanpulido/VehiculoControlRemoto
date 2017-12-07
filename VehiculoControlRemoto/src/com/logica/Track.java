package com.logica;

public class Track 
{

	//Se declara un arreglo multidimensional:
	private String[][] surface;
	
	//Constructor de la Clase:
	public Track(int n, int m)
	{
		//Se instancia el arreglo multidimensional con la posición de filas (n) y columnas (m) especificado:
		surface = new String[n][m];
		
		//Se llena el arreglo multidimensional con X´s:
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < m; j++)
			{
				surface[i][j] = "X";
			}
		}
	}
	
	//Se retorna el arreglo multidimensional:
	public String[][] getSurface()
	{
		return surface;
	}
	
}
