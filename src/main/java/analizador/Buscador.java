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

    /** 
     * Constructor que recibe por parametro un nuevo textarea donde sera mostrado el texto analizado y recibe la palabra a buscar  
    */
    public Buscador(JTextArea textarea, String palabra) {
        this.texto = textarea.getText();
        this.palabra = palabra;
        this.textarea = textarea;
        getBusqueda();
        pinta();
    }

    /*
    *Metodo encargado de extraer una palabra en el texto para su posterior analisis
    */
    public void getBusqueda() {
    //Aqui continua leyendo el texto
        while(posicion<texto.length()){
            boolean continuar = true;
            StringBuilder palabraSub = new StringBuilder();
            
            //Aqui completa una palabra
            while(continuar && posicion<texto.length()) {
                char tmp = texto.charAt(posicion);
                //System.out.println(tmp+" pos: "+posicion);

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
            //System.out.println("Posicion Inicio"+ posicionInicio);
            //System.out.println(palabraSub+" analizando posicion");


            //recibimos un array con las posiciones donde se pudo o no encontrar la coincidencia
            int[] posiSub= subrayar(palabraSub.toString(), posicionInicio);
            //if(posiSub[0]!=0 && posiSub[1]!=0){
                posInicial.add(posiSub[0]);
                posFinal.add(posiSub[1]);
            //}
            

        }
    }


    /**
     * Este metodo nos devuelve el array de las posiciones en donde se encuentra la coincidencia
     * @param pEstudiar es el texto obtenido del textarea
     * @return regresa el array de posiciones en donde se encuentra la coincidencia
    */ 
    public int[] subrayar(String pEstudiar,int posicionInicio){
        int[] devuelve=new int[2];

        int longitud=0;
        if(pEstudiar.contains(palabra)){
            for(int i=0;i<pEstudiar.length();i++){
                if(pEstudiar.charAt(i)==palabra.charAt(0)){
                    System.out.println("coincidencia en i "+i);
                    if(inicioCorrecto(pEstudiar,i)){
                        posicionInicio = posicionInicio + i;
                        break;

                    }
                }
            }


            longitud = posicionInicio + palabra.length();
            //System.out.println("pos inicial"+ posicionInicio);
            System.out.println("Se encontro la palabra, en posicion: " + posicionInicio+"," +longitud);
        }
        devuelve[0]=posicionInicio;
        devuelve[1]=longitud;
        return devuelve;
    }

    /**
     * Corrige el inidice inicial de subrayado, por si el string a buscar esta dentro de otro string
     * @param pEstudiar es la palabra la cual se esta estudiando o analizando, proveniente del texto general
     * @param i el indice enviador por el metodo anterior
     * @return un booleano si nos indica que es el inicio
     */
    private boolean inicioCorrecto(String pEstudiar, int i) {
        boolean inicio = false;
        for(int j=0;j<palabra.length();j++){
            if(palabra.charAt(j)==pEstudiar.charAt(i+j)){
                inicio=true;    
            }
            else{
                inicio=false;
                break;
            }
        }
        return inicio;
    }

    /*
    *Este metodo se encarga de subrayar o pintar las coincidencias encontradas 
    */
    public void pinta(){
        DefaultHighlightPainter pinta = new DefaultHighlightPainter(Color.CYAN); 
        Highlighter highlighter = textarea.getHighlighter();
        //System.out.println("Tiempo de pintar");
        System.out.println("posiciones guardadas "+ posInicial.size());
        for(int i = 0; i < posInicial.size(); i++){
            
            try {
                highlighter.addHighlight(posInicial.get(i), posFinal.get(i), pinta);
                //System.out.println("pinte en posicion"+posInicial.get(i)+ ","+posFinal.get(i));
            } catch (BadLocationException e) {
                e.printStackTrace();
                System.out.println("F, algo salio mal, No se pudo subrayar porque no hay coincidencias");
            }

            
        }
        
    }
}
