
package Rutas;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
	
	public static void findFile(File folder, File destino) {
		
		
		if (folder.length() == 0) {
			return;
		} else if (folder.isDirectory()) {
		
			for (File child : folder.listFiles()) {
				if (!child.isDirectory()) {
					//System.out.println(child.getName());
					String nombreArchivo = "\\"+child.getName();
					copiarFicheros(child,destino,nombreArchivo);
				}
				if (child.isDirectory()) {
					for (File children : child.listFiles()) {
						
						//System.out.println(children.getName());
						copiarFicheros(children,destino,children.getName());
						
					}
				}

				findFile(child, destino);
			}
			
		}
		
		

	}
	
	public static boolean copiarFicheros(File origen, File destino, String fichero) {
		//File origin = new File(origen);
        File destination = new File(destino+"\\"+fichero);
        if (origen.exists()) {
        	try {
					InputStream in = new FileInputStream(origen);
		            OutputStream out = new FileOutputStream(destination);
		            byte[] buf = new byte[1024];
		               int len;
		
		               while ((len = in.read(buf)) > 0) {
		                       out.write(buf, 0, len);
		               }
		       
		               in.close();
		               out.close();
		               return true;
		       }  catch (IOException ioe) {
		    	   ioe.printStackTrace();
		           return false;
		       } 
        }else {
            return false;
        }
	}
	
	public static void main ( String [] args)
	 {
		
		 String Origen = JOptionPane.showInputDialog(null,"Indica la ruta de origen: ");
		 String Destino = JOptionPane.showInputDialog(null,"Indica la ruta de destino: ");
		 File rutaOrigen = new File(Origen);
		 File rutaDestino = new File(Destino);
		 findFile(rutaOrigen, rutaDestino);
		 
		 JOptionPane.showMessageDialog(null,"Ficheros copiados correctamente a la ruta: " + Destino);
		
	 }
	
	
}
