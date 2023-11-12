package es.florida.t2_ok;

public class Ejercicio1 {
	//Realiza un programa en Java que dados dos números enteros, devuelva por pantalla la suma
	//de todos los números que hay entre ellos (incluyéndolos).

	public static void main(String[] args) {
			int numero1 = Integer.parseInt(args[0]);
			int numero2 = Integer.parseInt(args[1]);
			int suma = 0;
			for (int i = numero1; i <= numero2; i++) {
				suma = suma + i;
			}
			System.out.println("La suma de " + numero1 + " mas " + numero2 + " da: " + suma);
	}

}
