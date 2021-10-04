package ventanas;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import analizador.Token;
import reportes.Reporte;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelReporte extends JPanel{
    JTextArea textarea;
    Token token;
    boolean existeError;
    Reporte reporte;
    JTable table;
    public PanelReporte(boolean b, Reporte reporte) {
        this.existeError = b;
        this.reporte = reporte;
        imprimeInfo();
    }

    public void imprimeInfo(){
        // Definimos el layout
        this.setLayout(new BorderLayout());
        
        //Logica del reporte
        pintaTabla(existeError);
    }
    
    
    public void pintaTabla(boolean existeError){
        String [] tipoToken = null;
        String []nombreLexema=null;
        String []colToken=null;
        String []filToken=null;
        String texto=null;
        int numCols=4;
        if(existeError){
            nombreLexema=reporte.getPalabrasError();
            colToken=reporte.getPosicionColumnaError();
            filToken=reporte.getPosicionFilaError();
            texto="No. Error";

        }else{
            tipoToken = reporte.getTokensAgregados();
            nombreLexema=reporte.getPalabrasGuardadas();
            colToken=reporte.getPosicionColumna();
            filToken=reporte.getPosicionFila();
            texto="TIPO TOKEN";
        }

        //Creamos el Jtable y le agregamos el scroll
        table = new JTable(nombreLexema.length,numCols);
        JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);    

        //Definimos nombre de las columnas
        table.getColumnModel().getColumn(0).setHeaderValue(texto);
        table.getColumnModel().getColumn(1).setHeaderValue("LEXEMA");
        table.getColumnModel().getColumn(2).setHeaderValue("COLUMNA");
        table.getColumnModel().getColumn(3).setHeaderValue("FILA");

        //imprimeInfo
        for(int i= 0; i <nombreLexema.length;i++){ //fila
            for(int j= 0; j < numCols;j++){ //columnas
                if(j==0){ //
                    if(existeError){
                        table.setValueAt(i+1, i, j);

                    }else{
                        table.setValueAt(tipoToken[i],i,j);
                    }
                }
                else if(j==1){
                    table.setValueAt(nombreLexema[i], i, j);
                }
                else if(j==2){
                    table.setValueAt(colToken[i], i, j);

                }else if(j==3){
                    table.setValueAt(filToken[i], i, j);
                }
            }
        }
        if(existeError){
            this.add(scrollPane, BorderLayout.CENTER);

        }else{
            JButton button = new JButton("Click me");
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // Aqui genera el otro reporte xd
                    
                }
                
            });
            this.add(scrollPane, BorderLayout.CENTER);
            this.add(button, BorderLayout.SOUTH);
        }
    }


    
}
