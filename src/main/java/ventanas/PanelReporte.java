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
import java.util.ArrayList;


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
        this.setLayout(new GridBagLayout());
        
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
        GridBagConstraints c = new GridBagConstraints();
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
        //Definimos su posicion
        c.gridx=0; c.gridy =0;c.gridwidth=2;c.gridheight=2;
        c.weighty=1.0; c.weightx=1.0; c.fill=GridBagConstraints.BOTH;


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
            this.add(scrollPane, c);
            c.gridx=0; c.gridy =0;c.gridwidth=0;c.gridheight=0;


        }else{
            this.add(scrollPane,c);
            c.gridx=0; c.gridy =0;c.gridwidth=0;c.gridheight=0;
            c.weighty=0.0; c.weightx=0.0;


            JButton button = new JButton("Reporte Lexemas");
            button.setPreferredSize(new Dimension(20,50));
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // Aqui genera el otro reporte xd
                    System.out.println("soy reporte de lexemas");

                    new ReporteLexema(reporte.getPalabrasGuardadas(), reporte.getTokensAgregados());
                }
                
            });
            

            c.gridx=0; c.gridy =3;c.gridwidth=1;c.gridheight=1;
            this.add(button,c);
            c.gridx=0; c.gridy =0;c.gridwidth=0;c.gridheight=0;



            JButton button2 = new JButton("Reporte Tokens");
            button2.setPreferredSize(new Dimension(20,50));
            button2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // Aqui genera el otro reporte xd
                    System.out.println("soy reporete de tokens");
                    new ReporteToken(getReporteTokens());

                }
                
            });
            c.gridx=1; c.gridy =3;c.gridwidth=1;c.gridheight=1;
            
            this.add(button2,c);
            c.gridx=0; c.gridy =0;c.gridwidth=0;c.gridheight=0;
        }
    }

    public String[][] getReporteTokens() {
        ArrayList<String> tokens = new ArrayList<>();
        ArrayList<Integer> contador = new ArrayList<>();
        for(Token tmp : Token.values()) {
            if(tmp!=Token.ERROR && tmp!=Token.ERROR2 && tmp!=Token.IDENTIFICADOR2){
                tokens.add(tmp.getNombreEstado());
                contador.add(reporte.getcontadorEstado(tmp));
            }
        }

        String[][] reporteTok= new String[tokens.size()][2];
        for(int i=0; i<tokens.size(); i++){
            reporteTok[i][0] = tokens.get(i);
            reporteTok[i][1] = contador.get(i).toString();
        }

        
        return reporteTok;
    }


    
}
