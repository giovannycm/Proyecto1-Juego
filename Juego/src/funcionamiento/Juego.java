package funcionamiento;

import java.util.Scanner;

public class Juego {
    static int paloTriunfo;
    static int paloLider = 4;
    static int cantJugadores;
    static int totalRondas;
    static int rondasRestantes;
    static int ronda = 1;
    static int truco = 1;
    static int totalTrucos = totalRondas;
    static Jugador jugadores[];
    static int barajeador;
    static int ganador;
    static int ganadorTruco;
    static Cuprier jaime = new Cuprier();

    public static void establecerJugadores(Scanner sc) {

        boolean validar = false;
        while (!validar) {
            System.out.println("Ingrese cantidad de jugadores");
            int a = sc.nextInt();

            if (a > 2 && a < 7) {
                System.out.println("Genial, diviertanse!!!");
                cantJugadores = a;
                validar = true;
            } else {
                System.out.println("Ingresaste una cantidad erronea, vuelva a ingresar una cantidad valida.");
            }
        }
        System.out.println("Jugaran " + cantJugadores + "usuarios");

        Jugador ju[] = new Jugador[cantJugadores];
        jugadores = ju;

        for (int i = 0; i < cantJugadores; i++) {
            System.out.println("Ingrese nombre jugador " + i);
            String nombre = sc.next();
            jugadores[i] = new Jugador(nombre);
        }

        //Imprime lista jugadores
        for (int i = 0; i < ju.length; i++) {
            System.out.println("Lista de jugadores");
            System.out.println(jugadores[i].getNombre());
        }

        totalRondas = 60 / cantJugadores;
        rondasRestantes = totalRondas;

        if (ronda == 1) {
            System.out.println("Bienvenidos jugadores, mi nombre es Jaime y seré su Cuprier el día de hoy.");
        }
    }

    public static void prep(Scanner sc) {

        //boolean ayuda = true;  // boolean que ayuda a probar cosas
        
        System.out.println("Esta es la ronda "+ ronda + " de " + totalRondas );

        // Bienvenida y baraja.
        
        jaime.crearBaraja();
        //System.out.println("Ronda: " + ronda);
        /*
         * System.out.println("Les muestro su baraja");
         * jaime.imprimirBaraja();
         */

        // Decidir quien barajea

        System.out.println("¿Quien barajea? Seleccione al jugador que reparte:");

        String jugadoresNom = "";
        for (int i = 0; i < jugadores.length; i++) {
            int indice = i ;
            jugadoresNom += indice + " " + jugadores[i].getNombre() + "  ";
        }
        System.out.println(jugadoresNom);
        boolean validar = false;
        while (!validar) {
            int a = sc.nextInt();
            if (a >= 0 && a < cantJugadores) {
                barajeador = a;
                System.out.println("Jugador " + jugadores[a].getNombre()+ " barajea.");
                validar = true;
                break;
            } else {
                System.out.println("Selecciona correctamente.");
            }
        }

        jaime.barajear();

        // Repartir manos a cada jugador
        for (int i = 0; i < jugadores.length; i++) {
            Carta[] mano = new Carta[ronda];
            for (int j = 0; j < ronda; j++) {
                mano[j] = jaime.repartir();
            }
            jugadores[i].setMano(mano);
        }

        //Definir Palo del triunfo        
        if (jaime.barajaRevuelta.longi == 0) {
            System.out.println("No hay palo del triunfo, ya no hay mas cartas");
            paloTriunfo = 4;
        } else {
            Carta temp = jaime.repartir();
            System.out.println("Saquemos una carta");
            System.out.println(temp.toString());
            
            //temp.isEsMago()
            if (temp.isEsMago()) {
                System.out.println("Jugador " + jugadores[barajeador].getNombre() + " escoge palo del triunfo: ");
                System.out.println("0 Corazón --- 1 Espada --- 2 Trebol --- 3 Diamante");
                //Agregar un validador
                
                int a = sc.nextInt();
                System.out.println("El palo del triunfo es: " + jaime.palos[a]);
                paloTriunfo = a;
            } else if( temp.isEsBufon()) {
                System.out.println("No hay palo del triunfo");
                paloTriunfo = 4;
            } else {
                paloTriunfo = temp.getPalo();
                System.out.println("El palo del triunfo es: " + jaime.palos[paloTriunfo]);
            }
        }
        
        
        
    }

    

    public static int validaCarta(Scanner sc, Jugador jugador){
        System.out.println("Escoja su carta: ");
        boolean valido = false;
        int x = 0;
        while (!valido) {
            int a = sc.nextInt();
            if(a>= 0 && a < jugador.getMano().length  ){
                x = a;
                valido = true;
            }else {
                System.out.println("Invalido, intente de nuevo.");
            }
        }

        return x;
    }

    public static void apuestas(Scanner sc) {
        for (int i = 0; i < jugadores.length; i++) {
            System.out.println("Jugador ' " + jugadores[i].getNombre() + " '  haga su apuesta:  ");
            //Agregar validador
            boolean valido = false;
           
            while (!valido) {           
                int apuesta = sc.nextInt();     
                if (apuesta >= 0 && apuesta <= ronda) {
                    jugadores[i].setApuestas(apuesta);
                    valido = true;
                } else {
                    System.out.println("Ingresa una apuesta correcta. ");
                    
                }
            }
        }
        //Imprimir apuesta
        for (int i = 0; i < jugadores.length; i++) {
            System.out.println("El jugador "+ jugadores[i].getNombre() + " aposto: " + jugadores[i].getApuestas());
        }
    }

    public static int cambiarIndice(int x){
        if(x == 0){
            x = cantJugadores -1;
        }else{
            x = x -1;
        }

        return x;
    }

    public static void trucos(Scanner sc){
        System.out.println("Truco " + truco + "de " + ronda);

        Carta[] cartasTiradas = new Carta[cantJugadores];
        Jugador[] jugadorTiro = new Jugador[cantJugadores];
        //int inicio ;
        int i;
        if(truco == 1){
            i = cambiarIndice(barajeador);
        }else{
            i = ganadorTruco;
        }

        
        int j =0;
        for(int z = 0; z < cantJugadores; z++ ) {
            System.out.println("------------------------------------");
            System.out.println("Jugador: "+ jugadores[i].getNombre());
            jugadores[i].imprimirMano();
            System.out.println("Palo del triunfo: " + jaime.palos[paloTriunfo]+ "    Palo del lider: " + jaime.palos[paloLider]);
            System.out.println("¿Que carta tirara?");
             //Validor
             boolean tiradaCorrecta = false;
             int indiceCarta;
                //Se valida que tire correctamente la carta
             while (!tiradaCorrecta) {
                indiceCarta = validaCarta(sc, jugadores[i]);
                if (jugadaValida(jugadores[i].getMano(), indiceCarta)) {
                    cartasTiradas[j] = jugadores[i].getMano()[indiceCarta];
                    jugadores[i].sacarCarta(indiceCarta);
                    tiradaCorrecta = true;
                } else {
                    System.out.println("Jugada incorrecta, debes tirar una carta del palo lider. (Si tienes)");
                }
            }
            if(!cartasTiradas[j].isEsTruco()){
                if (paloLider == 4) {
                    paloLider = cartasTiradas[j].getPalo();
                }
            }
            jugadorTiro[j] = jugadores[i];
            j++;

            i = cambiarIndice(i);
            
           
        } 
        System.out.println("Palo del Triunfo: " + jaime.palos[paloTriunfo] );
        System.out.println("Palo lider: " + jaime.palos[paloLider] );
        for (int j2 = 0; j2 < jugadorTiro.length; j2++) {
            System.out.println(jugadorTiro[j2].getNombre() + "Tiro  " + cartasTiradas[j2]);
        }
        ganadorTruco(cartasTiradas, jugadorTiro);
        paloLider= 4;

        truco++;

    }

    public static void puntajeTruco(){
        String aciertos = "Puntos {";
        for (int i = 0; i < jugadores.length; i++) {
            aciertos += jugadores[i].getNombre() + " " + jugadores[i].getAciertos() +" | ";
        }
        aciertos += "}";
        System.out.println(aciertos);
    }

    public static void ganadorTruco(Carta[] cartas, Jugador[] jugadores){
        //Primera validación 

        boolean hayMago = false;
        int jugadorMago = 0;
        
        boolean hayPaloTriunfo = false;
        int jugadorPaloTriunfo = 0;
        int numeroCartaPT = 0;
        
        boolean hayPaloLider = false;
        int jugadorPaloLider = 0;
        int numeroCartaPL = 0;

        boolean hayBufon = false;
        int jugadorBufon = 0;

        int jugadorPaloAlto = 0;
        int numeroCartaPA = 0;

        int jugadorGanador;
        
        for (int i = 0; i < cartas.length; i++) {
            if (cartas[i].isEsMago()) {
                jugadorMago = i;
                hayMago = true;
                break;
            }else if(paloTriunfo != 4   && cartas[i].getPalo() == paloTriunfo){
                hayPaloTriunfo = true;
                if (cartas[i].getNumero() > numeroCartaPT) {
                    jugadorPaloTriunfo = i;
                    numeroCartaPT = cartas[i].getNumero();
                } 
            }else if(cartas[i].getPalo() == paloLider){
                hayPaloLider = true;
                if (cartas[i].getPalo() > numeroCartaPL) {
                    jugadorPaloLider = i;
                    numeroCartaPL = cartas[i].getNumero();
                }
            }else if(cartas[i].isEsBufon()){
                if(!hayBufon){
                    jugadorBufon = i;
                    hayBufon = true;
                }
            }else{
                if(cartas[i].getNumero() > numeroCartaPA){
                    jugadorPaloAlto = i;
                    numeroCartaPA = cartas[i].getNumero();
                }
            }
        }

        if (hayMago) {
            jugadorGanador = jugadorMago;
        }else if(hayPaloTriunfo){
            jugadorGanador = jugadorPaloTriunfo;
        }else if(hayPaloLider){
            jugadorGanador = jugadorPaloLider;
        }else if(hayBufon){
            jugadorGanador = jugadorBufon;
        }else{
            jugadorGanador = jugadorPaloAlto;
        }
        ganadorTruco = jugadorGanador;

        System.out.println("El ganador del truco es: "+ jugadores[jugadorGanador].getNombre());
        System.out.println("*********************************************************");
        jugadores[jugadorGanador].setAciertos( jugadores[jugadorGanador].getAciertos() +1);
        puntajeTruco();
    }


    public static boolean jugadaValida(Carta[] mano, int indice){
        boolean siTienes = false;
        for (int i = 0; i < mano.length; i++) {
            if(mano[i].getPalo() == paloLider ){
                siTienes = true;
            }
        }

        if(mano[indice].isEsTruco()){
            return true;
        }else{
            if(mano[indice].getPalo() == paloTriunfo){
                return true;
            }else if(mano[indice].getPalo() == paloLider){
                return true;
            }else if(!siTienes){
                return true;
            }else {
                return false;
            }
        }       
    }


    public static void ronda(Scanner sc){
        prep(sc);
        apuestas(sc);
        
        System.out.println("Comencemos con los trucos.");
        
        for (int i = 0; i < ronda; i++) {
            trucos(sc);
        }
        truco = 1;
        ronda++;
        paloTriunfo = 4;
    }


    public static void juegoCompleto(Scanner sc){
        establecerJugadores(sc);
        for (int i = 0; i < totalRondas; i++) {
            ronda(sc);
            System.out.println("¿Desea continuar con la siguiente ronda?");
            System.out.println("SI NO QUIERE DIGITE  0, SI DESEA CONTINUAR PRESIONE CUALQUIER OTRO NUMERO");
            int x = sc.nextInt();
            if (x == 0) {
                //Mostrar ganador
                break;
            }
        }
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
       
        //prep(sc);
        juegoCompleto(sc);
       
        
        sc.close();
    }

}
