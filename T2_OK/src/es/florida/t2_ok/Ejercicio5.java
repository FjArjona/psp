package es.florida.t2_ok;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ejercicio5 {
//	Implementar una modificación del programa número 4 para que llame dos veces
//	consecutivas al programa 3 (con números distintos), lea los resultados de los ficheros
//	generados y los muestre por pantalla

	public static void main(String[] args) {
		
		int numero1 = 1;
		int numero2 = 10;
		String nombreFichero1 = "resultado1.txt";
		
		int numero3 = 11;
		int numero4 = 20;
		String nombreFichero2 = "resultado2.txt";
		
		//Llamada 1
		String clase = "es.florida.t2_ok.Ejercicio3";
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String classname = clase;
		
		ArrayList<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(classname);
		command.add(String.valueOf(numero1));
		command.add(String.valueOf(numero2));
		command.add(nombreFichero1);
		
		System.out.println("Comando1 que se pasa a ProcessBuilder: " +command);
		System.out.println("Comando1 a ejecutar en cmd: " + command.toString().replace(",",""));
		
		ProcessBuilder builder = new ProcessBuilder(command);
		try {
			builder.start();
//			Process p = builder.start();
//			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Llamada 2
		ArrayList<String> command2 = new ArrayList<>();
		command2.add(javaBin);
		command2.add("-cp");
		command2.add(classpath);
		command2.add(classname);
		command2.add(String.valueOf(numero3));
		command2.add(String.valueOf(numero4));
		command2.add(nombreFichero2);
		
		System.out.println("Comando2 que se pasa a ProcessBuilder: " +command2);
		System.out.println("Comando2 a ejecutar en cmd: " + command2.toString().replace(",",""));
		
		ProcessBuilder builder2 = new ProcessBuilder(command2);
		try {
			builder2.start();
//			Process p = builder.start();
//			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean ficherosLeidos = false;
		while(!ficherosLeidos) {
			File fichero1 = new File(nombreFichero1);
			File fichero2 = new File(nombreFichero2);
			FileReader fr1;
			FileReader fr2;
			try  {
				fr1 = new FileReader(fichero1);
				BufferedReader br1 = new BufferedReader(fr1);
				String resultado1 = br1.readLine();
				br1.close();
				fr1.close();
				
				fr2 = new FileReader(fichero2);
				BufferedReader br2 = new BufferedReader(fr2);
				String resultado2 = br2.readLine();
				br2.close();
				fr2.close();
				
				System.out.println("Resultado leído del fichero1: " + resultado1);
				System.out.println("Resultado leído del fichero2: " + resultado2);
				ficherosLeidos = true;
				
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
		
	}

}
