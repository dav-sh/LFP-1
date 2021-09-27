package archivos;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/*Clase encargada de seleccionar el archivo de texto */
public class FileOpen {
    JTextArea textarea;
    JTextArea Tlabel;
    public FileOpen(JTextArea textarea, JTextArea tlabel) {
        this.textarea = textarea;
        this.Tlabel = tlabel;
        file();  
    }


    /*Este metodo sirve para abrir el explorador de archivos y seleccionar el archivo de texto con extension .txt*/
    public void file(){
        JFileChooser fileChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivos de texto (.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(fileChooser);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                fileChooser.getSelectedFile().getName());
                try {
                    new ReadFile(fileChooser, this.textarea,Tlabel);
                } catch (Exception e) {
                    System.out.println("No se envio el archivo o no se pudo elegir correctament");    
                }
        }

    }
}