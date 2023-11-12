package es.florida.aev1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Manufacture implements Runnable {
	
	private static final int MAX_MAQUINAS = 8;
	private int piecesInProcess;
	private int numThreads = 1;
	static List<String> manufacturedPieces = Collections.synchronizedList( new ArrayList<>());
	
	
    // Constructor de la clase Manufacture
	public Manufacture () {
		manufacturedPieces = new ArrayList<>();
	}
	/**
     * Método para fabricar una pieza.
     * @param pieceType Tipo de la pieza a fabricar.
     * @param quantity Cantidad de piezas a fabricar.
     * @param saveToFile Booleano que indica si se guardará la información en un archivo.
     * @param fileName Nombre del archivo donde se guardará la información.
     * @param numTotalPieces Número total de piezas a fabricar.
     */
	public void fabricarPieza(String pieceType, int quantity, boolean saveToFile, String fileName, int numTotalPieces) { 
	  	   
		for (int i = 0; i< quantity; i++) {
			piecesInProcess++;
       	   String pieceInfo = pieceType + "_" +  getCurrentDateTime() ;
           // Si hay demasiadas piezas en proceso, se espera para fabricar más
			while(piecesInProcess >= MAX_MAQUINAS) {
				try {
					Thread.sleep(100);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
            // Simula el proceso de fabricación
			procesoFabricacion(getFabricateTime(pieceType), pieceInfo);
			piecesInProcess--;
            // Se escribe en consola o en archivo según la preferencia
       	   if(!saveToFile) {
       		   writeToConsole(pieceInfo);
       	   } else {
       		   writeToFile(fileName, pieceInfo);
       	   }  
       }
        // Si se alcanza el número total de piezas fabricadas, se escriben en un archivo
		if (numTotalPieces <= manufacturedPieces.size()) {
			try {
				writePiecesToFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}	
	
    /**
     * Simula el proceso de fabricación de una pieza, agregándola a la lista de piezas fabricadas.
     * @param tiempoFabricacion Tiempo de fabricación de la pieza.
     * @param pieceInfo Información de la pieza fabricada.
     */
	public static void procesoFabricacion(int tiempoFabricacion, String pieceInfo) {
		 long tiempoInicio = System.currentTimeMillis();
		 long tiempoFin = tiempoInicio + tiempoFabricacion; // Tiempo de fabricacion en milisegundos
		 int iteraciones = 0;
		 while (System.currentTimeMillis() < tiempoFin) {
		 // Realizar iteraciones para consumir procesador (simula ocupacion de maquina)
		 iteraciones++;
		 }
		 // Agrega la pieza fabricada a la lista
		 manufacturedPieces.add(pieceInfo);
		System.out.println(pieceInfo);
	}
	
	 /**
     * Obtiene el tiempo necesario para fabricar una pieza según su tipo.
     * @param pieceType Tipo de la pieza a fabricar.
     * @return Tiempo requerido para la fabricación.
     */
    private int getFabricateTime(String pieceType) {
        switch (pieceType) {
            case "I":
                return 1000;
            case "O":
                return 2000;
            case "T":
                return 3000;
            case "J":
                return 4000;
            case "L":
            	return 4000;
            case "S":
                return 5000;
            case "Z":
            	return 5000;
            default:
                return 0;
        }
    }
    
    /**
     * Obtiene la fecha y hora actual formateada como un string.
     * @return String con la fecha y hora actual formateada.
     */
    private String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return currentDateTime.format(formatter);
    }
    
    /**
     * Imprime en la consola la información de la pieza.
     * @param pieceInfo Información de la pieza a mostrar en la consola.
     */
    private void writeToConsole(String pieceInfo) {
		   System.out.println(pieceInfo);
    	
    }
    
    /**
     * Escribe la información de la pieza en un archivo.
     * @param fileName Nombre del archivo donde se guardará la información.
     * @param pieceInfo Información de la pieza a escribir en el archivo.
     * @throws IOException 
     */
    private void writeToFile(String fileName, String pieceInfo)  {
//    	System.out.println(pieceInfo);
    	String regFileName = fileName + ".txt";      

    	String clase = "es.florida.aev1";
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String classname = clase;
		
		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(classname);
	    command.add(pieceInfo);

	    try {
	    
	        // Ejecutar el proceso
	        ProcessBuilder builder = new ProcessBuilder(command);
	        Process process = builder.start();
	        process.waitFor(); // Esperar a que el proceso termine
	        // Abrir el archivo en modo de apéndice y escribir la salida del proceso
	        try 
    		(FileWriter fileWriter = new FileWriter(regFileName, true);
    		BufferedWriter writer = new BufferedWriter(fileWriter)){
	        	writer.write(pieceInfo + "\n");
	        }  
        }catch(Exception e) {
        	e.printStackTrace();
        }	
    }
    
    /**
     * Escribe la lista de piezas fabricadas en un archivo.
     * @throws IOException Excepción en caso de error al escribir en el archivo.
     */
    public void writePiecesToFile() throws IOException {
    	String timeStamp = getCurrentDateTime();
    	String logFileName = "LOG_" + timeStamp + ".txt";
    	System.out.println("El archivo: " + logFileName + " ha sido creado correctamente"); //Nos sirve para saber cuando ha terminado

    	try (FileWriter fileWriter = new FileWriter(logFileName, true);
        		BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (String piece : manufacturedPieces) {
                writer.write(piece + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

	@Override
	public void run () {
		
	}
}