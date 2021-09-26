package analizador;

public enum Token {
    ENTERO(2,"ENTERO"), //ENTERO CORTO EX. 2
    PUNTUACION(3, "PUNTUACION"),
    ARITMETICO(4, "ARITMETICO"),
    IDENTIFICADOR(5, "IDENTIFICADOR"),
    ENTEROL(5, "ENTERO"),  //ES EL ENTERO PERO LARGO EX.  2222
    DECIMAL(8,"DECIMAL"),
    AGRUPACION(9, "AGRUPACION")
    ;
    
    public int getNumeroEstado(){
        return this.estado;
    }

    public String getNombreEstado(){
        return this.nombre;
    }

    private int estado;
    private String nombre;
    
    //     2,         3,              4,              5,          6,         8,              9
    //{"C-Entero","D-Puntuacion","E-Aritmetico","F-Indicador","G-Entero+","I-Float","J-Agrupacion"}

    private Token(int estado, String nombre){
        this.estado = estado;
        this.nombre = nombre;
    }

}


