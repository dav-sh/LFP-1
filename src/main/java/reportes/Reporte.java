package reportes;

import java.util.ArrayList;

import analizador.Token;

/** reportes
 * Clase encargada de llevar el control de los reportes, de cada token y lexema que se ingresa
 */
public class Reporte {
    /** 
     * Inicializa los contadores
     */
                             //I E P A D G !
    private int[] contadores= {0,0,0,0,0,0,0};
    Token []tokens = Token.values();
    private ArrayList<String> palabrasError = new ArrayList<>();
    private ArrayList<Integer> posicionError = new ArrayList<>();

    public Reporte() {
       //constructor 
    //    contadores[0]=0; //IDENTIFICADOR
    //    contadores[1]=0; //ENTERO
    //    contadores[2]=0; //PUNTUACION
    //    contadores[3]=0; //ARITMETICO
    //    contadores[4]=0; //DECIMAL
    //    contadores[5]=0; //AGRUPACION
    //    contadores[6]=0; //ERROR
       //contadores 
    }

    /**Este metodo es el encargado de manejar los contaderes de estado evaluando los parametros recibidos  
     * @param posicion
     * */
    public void contadorEstados(int estadoActual,String palabra, int posicion) {
        for(Token tmp : tokens) {
            if(tmp.getNumeroEstado()==estadoActual) {
                contadorEstados(tmp, palabra, posicion); //sumamos al contador
            }
        }
    }


    /**Este metodo se encarga de aumentar en 1 el contador de estados si se cumple la condicion 
     * @param posicion
     * */
    private void contadorEstados(Token tmp, String palabra, int posicion) {
        
        switch (tmp) {
            case IDENTIFICADOR:
                contadores[0]++;
                break;

            case IDENTIFICADOR2:
                contadores[0]++;

                break;

            case ENTERO:
                contadores[1]++;

                break;
            
            
            case PUNTUACION:
                contadores[2]++;

                break;

            case ARITMETICO:
                contadores[3]++;

                break;

            case DECIMAL:
                contadores[4]++;

                break;

            case AGRUPACION:
                contadores[5]++;

                break;
            
            case ERROR:
                contadores[6]++;
                palabrasError.add(palabra); //y agregamos la palabra a la lista de palabras
                posicionError.add(posicion);
                break;
            
            case ERROR2:
                contadores[6]++;
                palabrasError.add(palabra); //y agregamos la palabra a la lista de palabras
                posicionError.add(posicion);
                break;
            
            default:
                break;
        }
    }



    /**Este metodo se encarga de devolver el contador de estados recibiendo por parametro un token (enum) */
    public int getcontadorEstado(Token token) {
        int contador=0;
        switch (token) {
            case IDENTIFICADOR:
                contador = contadores[0];
                break;

            case IDENTIFICADOR2:
                contador = contadores[0];

                break;

            case ENTERO:
                contador = contadores[1];

                break;
            
            
            case PUNTUACION:
                contador = contadores[2];

                break;

            case ARITMETICO:
                contador = contadores[3];

                break;

            case DECIMAL:
                contador = contadores[4];

                break;

            case AGRUPACION:
                contador = contadores[5];

                break;
            
            case ERROR:
                contador = contadores[6];
                break;
            default:

                break;
        }
        return contador;
    }

    /**Este metodo se encarga de devolver el array (int) de los contadores */
    public int[] getContadores(){
        return contadores;
    }

    /**Este metodo se encarga de devolver el array (String) de palabras con ERROR o no ACEPTADAS
     * almacenadas que cumplen con los tokens aceptados
     * */
    public String[] getPalabrasError(){
        String[] tmp = new String[palabrasError.size()];
        for(int i = 0; i < tmp.length; i++){
            tmp[i] = palabrasError.get(i);
        }
        return tmp;
    }

    /**Este metodo se encarga de devolver el array (String) de la posicion con ERROR o no ACEPTADAS
     * almacenadas que cumplen con los tokens aceptados
     * */
    public String[] getPosicionError(){
        String[] pos = new String[posicionError.size()];
        for(int i = 0; i < pos.length; i++){
            pos[i] = posicionError.get(i).toString();
        }
        return pos;
    }

    /**Este metodo se encargara de resetar los valores de los contadores al ser llamado por el boton evaluar*/
    public void resetContadores(){
        for(int i = 0; i < contadores.length; i++){
            contadores[i] = 0;
        }
    }

    /**Este metodo se encargara de resetar los valores de los arrays*/

    public void resetArrays(){
        posicionError.clear();
        palabrasError.clear();
    }


}
