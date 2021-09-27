package analizador;

import javax.swing.JTextArea;
import reportes.Reporte;



/**
 * Clase encargada de analizar las palabras del texto en pantalla. 
*/
public class Automata {
    String texto;
    int posicion;
    int estadoActual = 0;
    Token []tokens = Token.values();
    Reporte reporte = null;
    //int estadosAceptacion[] = {1,2,3,4,5,7,8}; //B,C,D,E,F,I,J
    //String [] estadosAceptacionT={"B-Identificador","C-Entero","D-Puntuacion","E-Aritmetico","F-Indentificador+","I-Decimal","J-Agrupacion"};
    //git stash para eliminar cambios no guardados con commit

    /*constructor de la clase automata*/
    public Automata(JTextArea textArea, Reporte report) { 
        this.reporte = report;
        texto=textArea.getText();
        leeTexto();
    }

    /** 
     * A=0  B=1  C=2  D=3   E=4   F=5  H=6 I=7 J=8
     * ERROR = -1
     */

    int [][] estados = new int[9][6];
    {
        //  L                          D                    .                       P                      A                      G
        //A
        estados[0][0]= 1;       estados[0][1]= 2;     estados[0][2]= -1 ;   estados[0][3]= 3  ;     estados[0][4]= 4  ;   estados[0][5]= 8;   
        //B
        estados[1][0]= 5  ;     estados[1][1]= 5  ;   estados[1][2]= -1 ;   estados[1][3]= -1 ;     estados[1][4]= -1 ;   estados[1][5]= -1 ;   
        //C 
        estados[2][0]= -1 ;     estados[2][1]= 2  ;   estados[2][2]= 7  ;   estados[2][3]= -1 ;     estados[2][4]= -1 ;   estados[2][5]= -1 ;   
        //D 
        estados[3][0]= -1 ;     estados[3][1]= -1 ;   estados[3][2]= -1 ;   estados[3][3]= -1 ;     estados[3][4]= -1 ;   estados[3][5]= -1 ;   
        //E
        estados[4][0]= -1 ;     estados[4][1]= -1 ;   estados[4][2]= -1 ;   estados[4][3]= -1 ;     estados[4][4]= -1 ;   estados[4][5]= -1 ;   
        //F 
        estados[5][0]= 5  ;     estados[5][1]= 5  ;   estados[5][2]= -1 ;   estados[5][3]= -1 ;     estados[5][4]= -1 ;   estados[5][5]= -1 ;   
        //H 
        estados[6][0]= -1 ;     estados[6][1]= 7  ;   estados[6][2]= -1 ;   estados[6][3]= -1 ;     estados[6][4]= -1 ;   estados[6][5]= -1 ;   
        //I
        estados[7][0]= -1 ;     estados[7][1]= 7  ;   estados[7][2]= -1 ;   estados[7][3]= -1 ;     estados[7][4]= -1 ;   estados[7][5]= -1 ;   
        //J  
        estados[8][0]= -1 ;     estados[8][1]= -1 ;   estados[8][2]= -1 ;   estados[8][3]= -1 ;     estados[8][4]= -1 ;   estados[8][5]= -1 ;   
    }






    /*revisamos el movimiento en la matriz*/
    public int getNextEstado(int estadoActual, int tipoCaracter){
        int result = -1; //si fuera error        

        if(tipoCaracter>=0 && tipoCaracter<=5 && estadoActual!=-1){ //solo hay 6 tipos de caracter por lo mismo se coloca el 5 porque inicia en 0
            result= estados[estadoActual][tipoCaracter];
        }

        return result;
    }


    /*metodo para devoler el simbolo o texto del estado final*/

    public String getEstadoActual(int estadoActual) {
        String result = "Error "+estadoActual;
        for(Token tmp : tokens) {
            if(tmp.getNumeroEstado()==estadoActual) {
                result = tmp.getNombreEstado();
                break;
            }
        }
        return result;
    }







    /*revisa dentro de nuestro alfabeto*/
    public int getIntTipoCaracter(char caracter) {
        //Definimos el alfabeto restante
        char[] puntuacion = {'.',',',';',':'};
        char[] aritmeticos = {'+', '-', '*','/','%'};
        char[] agrupacion = {'(', ')', '[', ']', '{', '}'};
        int result = -1;



        if(Character.isLetter(caracter)){
            result = 0;
        }else if(Character.isDigit(caracter)){
            result = 1;
        }else if(caracter == '.' && estadoActual!=0){
            result = 2;
        }    
        else{
            //System.out.println("probando");
            for(char tmp : puntuacion){
                if(tmp == caracter){
                    result = 3;
                    break;
                }
            }
            for(char tmp : aritmeticos){
                if(tmp==caracter){
                    
                    result = 4;
                    break;
                }
            }
            for(char tmp : agrupacion){
                if(tmp==caracter){
                    result = 5;
                    break;
                }
            }
        }
        
        return result;
    }






    





    /*metodo encargado de continuar leer el texto mientras recorre cada char de la palabra a analizar*/
    public void leeTexto(){
        while(posicion<texto.length()){
            leePalabra();
        }
    }





    /*metodo encargado de analizar cada char de la palabr*/
    public void leePalabra(){
        String palabra="";
        estadoActual =0;
        boolean continuar = true;
        while(posicion<texto.length() && continuar){
            
            char c = texto.charAt(posicion);
            palabra+=c;

            if(Character.isSpaceChar(c) ||  Character.toString(c).equals("\n")){  //Aqui se evaluan los espacios y saltos de linea
                continuar = false;
                if(Character.toString(texto.charAt(posicion)).equals("\n")){
                    System.out.println("Este es un salto de linea");

                }else{
                    System.out.println("Este es un espacio");
                }
            }
            else{
                int estadoTemporal = getNextEstado(estadoActual, getIntTipoCaracter(c)); //el segundo valor es el caracter que mandamos (texto) que se convierte a int en el metodo getIntTipoCaracter
                //Aqui va el automata
                System.out.println("estado actual "+estadoActual+" Caracter: "+c +" estado temporal(siguiente) "+ estadoTemporal);
                //System.out.println(texto.charAt(posicion));
                estadoActual = estadoTemporal;
            }
            posicion++;
        }
        //System.out.println("termino en el estado actual "+estadoActual + "-->"+getEstadoFinal(estadoActual)+" palabra: "+ palabra);
        System.out.println("termino en el estado actual "+estadoActual + "-->"+ getEstadoActual(estadoActual)+" palabra: "+ palabra);
        reporte.contadorEstados(estadoActual,palabra);

        //addReport(estadoActual,palabra);
    }
    



    public void addReport(int estadoActual,String palabra){
        reporte.contadorEstados(estadoActual,palabra);

    }


}
