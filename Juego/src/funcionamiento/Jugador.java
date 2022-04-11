package funcionamiento;
/**
 * @author Giovanny Cruz Martinez
 * @author Dulce Julieta Mora Hernandez
 **/

public class Jugador{
    //Atributos
    private String nombre;
    private Carta[] mano;
    //private int numjugadores;
    private int apuestas;
    private int aciertos; //apuestas ganadas

    //Metodo constructor
    public Jugador(String nombre){
	this.nombre = nombre;
	
    }
    
    /**
     * Metodo puntosGanados que al dar un numero de apuestas comprueba si coincide o no con las apuestas ganadas para
     * asignar la puntuacion
     * @param apuestas Numero de juegos que cree que ganara
     * @param aciertos Numero de juegos ganados
     **/
    public int puntosGanados(int apuestas, int aciertos){
	int aux;
	if(apuestas == aciertos){
	    aux = 20 + (10 * aciertos);
	}else{
	    aux = -10 * (Math.abs(apuestas - aciertos));
	}	
	return aux;
    }

    /**
     * Metodo manoJugador que rellena la mano del jugador
     **/
    /*public void manoJugador(int numelemen){
	Integer[] mano = new Integer[numelemen];
	
	for(int i=0; i<number; i++){//for para rellenar el arreglo     
	    mano[i] = pila.peek(); 
	    }  
	return;
	}*/

    public void imprimirMano() {
      /*   String manita = "";

        for (int i = 0; i < mano.length; i++) {
            manita += i + mano[i].toString() +" | ";
        }

        System.out.println(manita); */

        for (int i = 0; i < mano.length; i++) {
            System.out.println(i +"  " +mano[i].toString());
        }

    }



    //Metodos setters y getters
    /**
     * Metodo que devuelve el nombre del jugador
     * @return El nombre del jugador
     **/
    public String getNombre(){
	return this.nombre;
    }
    
    /**
     * Metodo que modifica el nombre del jugador
     **/
    public void setNombre(String jugador){
	this.nombre = jugador;
    }
    
    /**
     * Metodo que devuelve el numero de apuestas
     * @return Numero de apuestas
     **/
    public int getApuestas(){
	return apuestas;
    }
    
    public void setMano(Carta[] mano) {
        this.mano = mano;
    }

    /**
     * Metodo que modifica el numero de apuestas
     **/
    public void setApuestas(int apuestas){
	this.apuestas = apuestas;
    }
    
    /**
     * Metodo que devuelve el numero de apuestas acertadas
     * @return Numero de apuestas acertadas
     **/
    public int getAciertos(){
	return aciertos;
    }

    /**
     * Metodo que modifica el numero de apuestas acertadas
     **/
    public void setAciertos(int aciertos){
	this.aciertos = aciertos;
    }

    /**
     * Metodo que devuelve la mano de cartas del jugador
     * @return Mano de cartas del jugador
     **/
    public Carta[] getMano(){
	return mano;
    }
    
}
