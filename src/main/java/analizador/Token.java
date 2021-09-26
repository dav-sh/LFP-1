package analizador;

public enum Token {


    IDENTIFICADOR(1, "IDENTIFICADOR"), //IDENTIFICADOR CORTO EX. A 
    ENTERO(2,"ENTERO"), //ENTERO CORTO EX. 2
    PUNTUACION(3, "PUNTUACION"),
    ARITMETICO(4, "ARITMETICO"),
    IDENTIFICADOR2(5, "IDENTIFICADOR"),
    DECIMAL(6,"DECIMAL"),
    AGRUPACION(7, "AGRUPACION")
    ;
    
    public int getNumeroEstado(){
        return this.estado;
    }

    public String getNombreEstado(){
        return this.nombre;
    }

    private final int estado;
    private final String nombre;
    
    /**Constructor
     * Aqui se inicializan los valores a cada enum
     */
    private Token(int estado, String nombre){
        this.estado = estado;
        this.nombre = nombre;
    }

}


