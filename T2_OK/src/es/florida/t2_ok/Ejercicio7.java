package es.florida.t2_ok;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio7 {
//	Crear otra ampliación del programa 2 para que redirija la salida de la ejecución del programa
//	1 a su flujo de ejecución y lo escriba en un fichero (pista: utilizar redirectOutput).

	public static void main(String[] args) {
		
		int numero1=1;
		int numero2 = 10;
		String nombreFichero = "resultado_ej7.txt";
		File fichero = new File(nombreFichero);
		
		String clase = "es.florida.t2_ok.Ejercicio1";
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String classname = clase;
		
		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(classname);
		command.add(String.valueOf(numero1));
		command.add(String.valueOf(numero2));
		
		System.out.println("Comando que se pasa a ProcessBuilder: " +command);
		System.out.println("Comando a ejecutar en cmd: " + command.toString().replace(",",""));
		
		ProcessBuilder builder = new ProcessBuilder(command);
		builder.redirectOutput(fichero);
		try {
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
