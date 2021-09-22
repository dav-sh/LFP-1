package archivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;


/**Clase en encargada de guardar el documento de texto */
public class SaveFile {
    JFileChooser fileChooser;
    JTextArea textArea;

    public SaveFile(JFileChooser fileChooser2, JTextArea textarea) {
        this.textArea = textarea;
        this.fileChooser = fileChooser2;
        saveText();
    }


    public void saveText(){
        fileChooser.setApproveButtonText("Guardar");
        //File archivo = fileChooser.getSelectedFile(); //sirve apra crear un archivo pero no especifica la extension
        
        File archivoR = fileChooser.getSelectedFile(); //obtenemos la ruta del archivo a crear
        if(archivoR.exists()){
            System.out.println("El archivo ya existe: " + archivoR.getAbsolutePath());
        }else{
            new File(archivoR.getAbsolutePath() + ".txt");
            System.out.println("El archivo ha sido creado: "+archivoR.getAbsolutePath());
            // File archivo = new File(fileChooser.getSelectedFile()+".txt"); //sirve para crear un archivo y especifica la extension

        }
        
        writeText(archivoR);

        
    }




    public void writeText(File archivo){
        FileWriter writer=null;
        try {
            writer = new FileWriter(archivo, true);
            writer.write("Holaaaaa");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Algo salio mal xd");
        }
    }



}
