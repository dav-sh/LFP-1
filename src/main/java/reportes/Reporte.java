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
    private int[] contadores = {0,0,0,0,0,0,0};
    Token []tokens = Token.values();
    private ArrayList<String> palabras = new ArrayList<String>();

    public Reporte() {
       //constructor 
    }

    /**Este metodo es el encargado de manejar los contaderes de estado evaluando los parametros recibidos  */
    public void contadorEstados(int estadoActual,String palabra) {
        for(Token tmp : tokens) {
            if(tmp.getNumeroEstado()==estadoActual) {
                contadorEstados(tmp); //sumamos al contador
                palabras.add(palabra); //y agregamos la palabra a la lista de palabras
            }
        }
    }


    /**Este metodo se encarga de aumentar en 1 el contador de estados si se cumple la condicion */
    private void contadorEstados(Token tmp) {
    
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
                
            default:
                contadores[6]++;

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
                
            default:
                contador = contadores[6];

                break;
        }
        return contador;
    }

    /**Este metodo se encarga de devolver el array (int) de los contadores */
    public int[] getContadores(){
        return contadores;
    }

    /**Este metodo se encarga de devolver el array (String) de palabras 
     * almacenadas que cumplen con los tokens aceptados
     * */
    public String[] getPalabras(){
        String[] tmp = new String[palabras.size()];
        for(int i = 0; i < tmp.length; i++){
            tmp[i] = palabras.get(i);
        }
        return tmp;
    }



}
