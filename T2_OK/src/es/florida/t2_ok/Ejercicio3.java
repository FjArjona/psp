package es.florida.t2_ok;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio3 {
//	Crear una modificación del programa número 1 para que escriba el resultado en un fichero
//	en disco.

	public static void main(String[] args) {
		int numero1 = Integer.parseInt(args[0]);
		int numero2 = Integer.parseInt(args[1]);
		String nombreFichero = args[2];
		
		int suma = 0;
		for (int i = numero1; i <= numero2; i++) {
			suma = suma + i;
		}
		System.out.println("La suma de " + numero1 + " más " + numero2 + " da: " + suma);
		File fichero = new File(nombreFichero);
		FileWriter fw;
		BufferedWriter bw;
		System.out.println("Iniciar escritura en fichero....");
		try {
			fw = new FileWriter(fichero);
			bw = new BufferedWriter(fw);
			bw.write(String.valueOf(suma));
			bw.close();
			fw.close();
			System.out.println("Fichero escrito con éxito.");
			
		}catch(IOException e) {
			e.printStackTrace();	
		}
	}

}
