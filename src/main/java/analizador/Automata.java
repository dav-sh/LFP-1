package analizador;

public class Automata {
    String texto="cosa toy bien\n  salto";
    int posicion;


    public Automata() { 
        leeTexto();
    }

    public void leeTexto(){
        while(posicion<texto.length()){
            leePalabra();
        }
    }

    public void leePalabra(){
        boolean continuar = true;
        while(posicion<texto.length() && continuar){
            if(Character.isSpaceChar(texto.charAt(posicion))){
                continuar = false;
                System.out.println("Este es un espacio");
            }
            else if(Character.toString(texto.charAt(posicion)).equals("\n")){
                System.out.println("Este es un salto de linea");
            }
            else{
                System.out.println(texto.charAt(posicion));
            }
            posicion++;
        }
    }


}
