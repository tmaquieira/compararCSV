package compararCSV;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArchivosUtils {

	public List<String> recorrerArchivo(String archivo) {
		 List<String> lista = new ArrayList<String>();
		
		try {
			// constructor of file class having file as argument
			File file = new File(archivo);
			Scanner sc = new Scanner(file);
		//lo mismo que el for para archivo1, esto lo hicimos para que se saltee la primer fila de NOMBRES RANDOM			
			boolean contador = false; 	
			while (sc.hasNextLine()) {
				if (contador == false) {
					sc.nextLine();
					contador = true;
				}else {
					lista.add(sc.nextLine());
					//	System.out.println(sc.nextLine());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}