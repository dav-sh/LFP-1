package archivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

/*Clase encargada de leer el archivo de texto*/
public class ReadFile {
    JTextArea textarea;
    JFileChooser f;
    FileReader fr;
    BufferedReader rdr;


    public ReadFile(JFileChooser file, JTextArea textarea){
        this.f = file;
        this.textarea = textarea;

        try {
            fr = new FileReader(f.getSelectedFile().getPath());
            rdr = new BufferedReader(fr);
            //Aqui leemos el archivo y procedemos a mandar el string al text area ...
            leerArchivo();
            
            
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        }finally{
            System.out.println("Archivo cerrado");
        }

    }




    private void leerArchivo() {
        int count = 0;
        String linea;
        this.textarea.setText("");
        try {
            while((linea=rdr.readLine())!=null){
                count++;
                System.out.println(linea);
                updateText(count,linea);

            }
            
        } catch (Exception e) {
            System.out.println("Algo salio mal al leer el archivo de texto");
        }
    }




    

    private void updateText(int count, String text){
        this.textarea.append("Ln: "+count+".  "+text+"\n");
    }
}