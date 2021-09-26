package analizador;

import javax.swing.JTextArea;

public class Automata {
    String texto;
    int posicion;
    int estadoActual = 0;
    int estadosAceptacion[] = {2,3,4,5,6,8,9}; //C,D,E,F,G,I,J
    String [] estadosAceptacionT={"C-Entero","D-Puntuacion","E-Aritmetico","F-Indicador","G-Entero+","I-Float","J-Agrupacion"};

    /** 
     * A=0  B=1  C=2  D=3   E=4   F=5   G=6 H=7 I=8 J=9
     * ERROR = -1
     */
    int [][] estados = new int[10][6];
    {
        //  L                          D                    .                       P                      A                      G
        //A
        estados[0][0]= 1;       estados[0][1]= 2;     estados[0][2]= -1 ;   estados[0][3]= 3  ;     estados[0][4]= 4  ;   estados[0][5]= 9;   
        //B
        estados[1][0]= 5  ;     estados[1][1]= 5  ;   estados[1][2]= -1 ;   estados[1][3]= -1 ;     estados[1][4]= -1 ;   estados[1][5]= -1 ;   
        //C 
        estados[2][0]= -1 ;     estados[2][1]= 6  ;   estados[2][2]= 7  ;   estados[2][3]= -1 ;     estados[2][4]= -1 ;   estados[2][5]= -1 ;   
        //D 
        estados[3][0]= -1 ;     estados[3][1]= -1 ;   estados[3][2]= -1 ;   estados[3][3]= -1 ;     estados[3][4]= -1 ;   estados[3][5]= -1 ;   
        //E
        estados[4][0]= -1 ;     estados[4][1]= -1 ;   estados[4][2]= -1 ;   estados[4][3]= -1 ;     estados[4][4]= -1 ;   estados[4][5]= -1 ;   
        //F 
        estados[5][0]= 5  ;     estados[5][1]= 5  ;   estados[5][2]= -1 ;   estados[5][3]= -1 ;     estados[5][4]= -1 ;   estados[5][5]= -1 ;   
        //G
        estados[6][0]= -1 ;     estados[6][1]= 6  ;   estados[6][2]= -1 ;   estados[6][3]= -1 ;     estados[6][4]= -1 ;   estados[6][5]= -1 ;   
        //H 
        estados[7][0]= -1 ;     estados[7][1]= 8  ;   estados[7][2]= -1 ;   estados[7][3]= -1 ;     estados[7][4]= -1 ;   estados[7][5]= -1 ;   
        //I
        estados[8][0]= -1 ;     estados[8][1]= 8  ;   estados[8][2]= -1 ;   estados[8][3]= -1 ;     estados[8][4]= -1 ;   estados[8][5]= -1 ;   
        //J  
        estados[9][0]= -1 ;     estados[9][1]= -1 ;   estados[9][2]= -1 ;   estados[9][3]= -1 ;     estados[9][4]= -1 ;   estados[9][5]= -1 ;   

    }

    //revisamos el movimiento en la matriz
    public int getNextEstado(int estadoActual, int tipoCaracter){
        int result = -1; //si fuera error        

        if(tipoCaracter>=0 && tipoCaracter<=5 && estadoActual!=-1){
            result= estados[estadoActual][tipoCaracter];
        }

        return result;
    }


    //metodo para devoler el simbolo o texto del estado final
    public String getEstadoFinal(int estadoActualFinal){
        String respuesta = "Error: " + estadoActualFinal;
        int pos = 0;
        for(int estado : estadosAceptacion){
            if(estadoActualFinal==estado){
                respuesta = estadosAceptacionT[pos];
                break;
            }
            pos++;
        }
        return respuesta;
    }








    //revisa dentro de nuestro alfabeto
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
            
        }else if(caracter=='.'){
            result = 2;
        }    
        else{
            System.out.println("probando");
            for(char tmp : puntuacion){
                if(tmp==caracter){
                    result = 3;
                    System.out.println("Si se evaluo");
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




    public Automata(JTextArea textArea) { 
        texto=textArea.getText();
        leeTexto();
    }



    public void leeTexto(){
        while(posicion<texto.length()){
            leePalabra();
        }
    }

    public void leePalabra(){
        String palabra="";
        estadoActual =0;
        boolean continuar = true;
        while(posicion<texto.length() && continuar){
            
            char c = texto.charAt(posicion);
            palabra+=c;
            if(Character.isSpaceChar(c) ||  Character.toString(c).equals("\n")){
                //Aqui se evaluan los espacios y saltos de linea
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
        System.out.println("termino en el estado actual "+estadoActual + "-->"+getEstadoFinal(estadoActual)+" palabra: "+ palabra);
    }


}
