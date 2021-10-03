package ventanas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import analizador.Token;
import reportes.Reporte;
import java.awt.*;


public class PanelReporte extends JPanel{
    JTextArea textarea;
    Token token;
    boolean existeError;
    Reporte reporte;
    public PanelReporte(boolean b, Reporte reporte) {
        this.existeError = b;
        this.reporte = reporte;
        imprimeInfo();
    }

    public void imprimeInfo(){
        // Definimos el layout
        this.setLayout(new BorderLayout());

        //creamos el JtextArea y definimos sus valores, agregandolas al panel correspondiente
        textarea = new JTextArea();
        textarea.setEditable(false);

        //creamos el scroll por si fuera necesario
        JScrollPane scrollPane = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        this.add(scrollPane, BorderLayout.CENTER);
 
        //Logica del reporte
        if(existeError){
            token = Token.ERROR;
            System.out.println("Estoy con el token "+ token.getNombreEstado());

            //Devuelve el contador del token error
            textarea.setText(token.getNombreEstado() + " " + reporte.getcontadorEstado(token)+"\n\n\n\n");

            //IMPRIME el listado de palabras ERRONEAS con su posicion
            int pos = 0;
            for(String tmp : reporte.getPalabrasError()){
                textarea.append("Lexema: "+ tmp+" |Columna "+reporte.getPosicionColumnaError()[pos] + " |fila "+reporte.getPosicionFilaError()[pos]+"\n");
                pos++;
            } 
        }
        else{
            //Devuelve el contador de tokens validos
            for(Token tmp : Token.values()){
                
                if(tmp!=Token.IDENTIFICADOR2 && tmp!=Token.ERROR2 && tmp!=Token.ERROR){
                    textarea.append(tmp.getNombreEstado() + " " + reporte.getcontadorEstado(tmp)+"\n");  

                }
            }

            //IMPRIME el listado de palabras ACEPTADAS con su respectiva posicion
            int pos = 0;
            textarea.append("\n\nLEXEMA                               COLUMNA           FILA\n\n");
            for(String tmp : reporte.getPalabrasGuardadas()){
                textarea.append("Lexema: "+ tmp+" |Columna "+reporte.getPosicionColumna()[pos] + " |fila "+reporte.getPosicionFila()[pos]+"\n");
                pos++;
            } 

        }
    }
    
}
