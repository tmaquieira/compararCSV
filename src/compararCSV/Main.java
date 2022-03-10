package compararCSV;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) {
	// TODO Auto-generated method stub
	//System.out.println("Hola");

     ArchivosUtils archivoUtils = new ArchivosUtils();
	 List<String> archivo1 = archivoUtils.recorrerArchivo("C:\\Users\\tmaqu\\Documents\\GitHub\\compararCSV\\USUARIO_DIARIO_MODO_ROOT.csv");
	 List<String> archivo2 = archivoUtils.recorrerArchivo("C:\\Users\\tmaqu\\Documents\\GitHub\\compararCSV\\personas_bip.csv");
	 
	 List<String> archivo1editado = new ArrayList<String>();
	 List<String> archivo2editado = new ArrayList<String>();
	
	 List<String> archivo3 = new ArrayList<String>();
	 List<String> archivo4 = new ArrayList<String>();

	 //recorre cada fila de archivo2 (el problemático xD), la splitea en 2 partes que se ubican en el nuevo 
	 //Array String en 2 posiciones[0,1], y en la nueva Lista Editada archivo2editado agrega cada elemento del for 
	 //ubicado en la posición 1
 
	 for (String fila : archivo1) {
     	 String[] recorte = fila.split(","); 
		 archivo1editado.add(recorte[0]);
     }
	 
	 
	 for (String fila : archivo2) {
     	 String[] recorte = fila.split(","); 
		 archivo2editado.add(recorte[1]);
     }
     //System.out.println(archivo2);
	  
     //aca se dividen las aguas, el primer for recorre el archivo de malvi, selecciona un registro, y se mete en el
	 //segundo for, para buscar equivalencias con el .csv más grande. si las hay, las agrega a archivo3
	 //si no las hay, las agrega a archivo 4
	 //el boolean que empieza en false sirve para que, en caso de No coincidir los registros del primer archivo con el grande, 
	 //se agreguen a archivo4. Es importante que inicie en false, para re-empezar el proceso de busqueda desde el primer archivo con su siguiente fila
	 for (String fila : archivo1editado) {
		 boolean variableBooleana = false;
		 for (String filita : archivo2editado) {
			 if(fila.equals(filita)) {
				variableBooleana = true;
			 	archivo3.add(fila);
			 	break;
			 }
	     }
		 if (variableBooleana == false) {
			 archivo4.add(fila);
		 } 
     }
	// System.out.println(archivo3);	
	// System.out.println(archivo4);	
	
	//genera el .csv de documentos CON BIP.
	    try (PrintWriter writer = new PrintWriter(new File("Respuesta1ConBIP.csv"))) {
	      StringBuilder sb = new StringBuilder();
	 //ingresa el String en la primer celda disponible
	      sb.append("Nro. Doc.");
	 //salta a la siguiente fila abajo
	      sb.append('\n');
	//recorre el archivo3, agrega fila por fila
	      	for (String fila : archivo3) {
	      		sb.append(fila);
	      		sb.append('\n');
	      	}
	      	//genera la extensión .csv
	      	writer.write(sb.toString());
	      System.out.println("listo!"); //avisa xD

	    } catch (FileNotFoundException e) {
	      System.out.println(e.getMessage()); //reporte en caso de errores
	    }
	
	    
	    
	    try (PrintWriter writer = new PrintWriter(new File("Respuesta2SinBIP.csv"))) {
		      StringBuilder sb = new StringBuilder();
		      sb.append("Nro. Doc.");
		  	sb.append('\n');
		      //recorre el archivo procesado, agrega fila por fila y salta a la siguiente posición
		      	for (String fila : archivo4) {
		      		sb.append(fila);
		      		sb.append('\n');
		      	}
		      	//crea el .csv
		      	writer.write(sb.toString());
		      System.out.println("listo!");

		    } catch (FileNotFoundException e) {
		      System.out.println(e.getMessage());
		    }
			
	}
}