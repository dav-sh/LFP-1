package archivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

/*Clase encargada de leer el archivo de texto*/
public class Reader {
    public Reader(JFileChooser file) throws IOException {
        JFileChooser f = file;
        FileReader fr = null;
        BufferedReader reader = null;

        try {
            fr = new FileReader(f.getSelectedFile().getPath());
            reader = new BufferedReader(fr);
            //Aqui leemos el archivo y procedemos a mandar el string al text area ...
            leerArchivo(reader);
            
            
        } catch (FileNotFoundException e) {
            
            System.out.println("ALgo salio mal");
            System.out.println("Archivo no encontrado");
        }finally{
            
            System.out.println("Archivo cerrado");
        }

    }

    private void leerArchivo(BufferedReader reader) {
        String linea;
        try {
            while((linea=reader.readLine())!=null){
                System.out.println(linea);

            }
            
        } catch (Exception e) {
            System.out.println("Algo salio mal al leer el archivo de texto");
        }
    }
}