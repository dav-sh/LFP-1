package analizador;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import java.awt.*;
import java.util.ArrayList;

public class Buscador {
    String texto=null;
    String palabra;
    int posicion=0;
    ArrayList<Integer> posInicial = new ArrayList<>();
    ArrayList<Integer> posFinal = new ArrayList<>();

    JTextArea textarea=null;
    public Buscador(JTextArea textarea, String palabra) {
        this.texto = textarea.getText();
        this.palabra = palabra;
        this.textarea = textarea;
        getBusqueda();
        pinta();
        //busqueda();
    }

    /*
    *Metodo encargado de buscar la palabra en el texto mientras recorre cada char de la palabra del texto a analizar
    */
    public void getBusqueda() {
    //StringBuilder textoSub = new StringBuilder();

    //Aqui continua leyendo el texto
        while(posicion<texto.length()){
            boolean continuar = true;
            StringBuilder palabraSub = new StringBuilder();
            DefaultHighlightPainter pinta = new DefaultHighlightPainter(Color.CYAN); 
            Highlighter highlighter = textarea.getHighlighter();
            
            //Aqui completa una palabra
            while(continuar && posicion<texto.length()) {
                char tmp = texto.charAt(posicion);
                System.out.println(tmp+" pos: "+posicion);

                if(Character.toString(tmp).equals("\n") || Character.isSpaceChar(tmp)){
                    continuar=false;
                    palabraSub.append(tmp);
                    
                }
                else{
                    palabraSub.append(tmp);
                }
                posicion++;
            }
            int posicionInicio = posicion-palabraSub.length();
            System.out.println("Posicion Inicio"+ posicionInicio);
            System.out.println(palabraSub+" analizando posicion");
            int[] posiSub= subrayar(palabraSub.toString(), posicionInicio);
            posInicial.add(posiSub[0]);
            posFinal.add(posiSub[1]);
            





            //textoSub.append(subrayar(palabraSub.toString()));




            //System.out.println(textoSub);
        }

        //textarea.setText(textoSub.toString());
        //System.out.println(textoSub);
    }


    /**
     * Este metodo nos subraya la palabra si encuentra coincidencia alguna
     * @param texto
     * @return
    */ 
    public int[] subrayar(String pEstudiar,int posicionInicio){
        int[] devuelve=new int[2];
        //DefaultHighlightPainter pinta = new DefaultHighlightPainter(Color.CYAN); 
        //Highlighter highlighter = textarea.getHighlighter();
        int longitud=0;
        if(pEstudiar.contains(palabra)){
            //int inicia = pEstudiar.indexOf(palabra);
            longitud = posicionInicio + palabra.length();
            System.out.println("pos inicial"+ posicionInicio);
            System.out.println("Se encontro la palabra, en posicion: " + posicionInicio+"," +longitud);
            /*
            try {
                highlighter.addHighlight(inicia, longitud, pinta);
            } catch (BadLocationException e) {
				e.printStackTrace();
                System.out.println("F");
			}
            */
        }
        devuelve[0]=posicionInicio;
        devuelve[1]=longitud;
        //devuelve = pEstudiar;
        return devuelve;
    }




    public void busqueda(){
        DefaultHighlightPainter pinta = new DefaultHighlightPainter(Color.CYAN); 
        Highlighter highlighter = textarea.getHighlighter();
        while(textarea.getText().contains(palabra)){
            int inicia = textarea.getText().indexOf(palabra);
            int longitud = inicia + palabra.length();
            try {
                highlighter.addHighlight(inicia, longitud, pinta);
            } catch (BadLocationException e) {
				e.printStackTrace();
                System.out.println("F");
			}
        }
    }


    public void pinta(){
        DefaultHighlightPainter pinta = new DefaultHighlightPainter(Color.CYAN); 
        Highlighter highlighter = textarea.getHighlighter();
        System.out.println("Tiempo de pintar");
        System.out.println("posiciones guardadas "+ posInicial.size());
        for(int i = 0; i < posInicial.size(); i++){

            try {
                highlighter.addHighlight(posInicial.get(i), posFinal.get(i), pinta);
                System.out.println("pinte en posicion"+posInicial.get(i)+ ","+posFinal.get(i));
            } catch (BadLocationException e) {
                e.printStackTrace();
                System.out.println("F");
            }


        }
        
    }
}
