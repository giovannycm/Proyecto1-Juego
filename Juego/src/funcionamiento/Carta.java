package funcionamiento;
public class Carta {
    private int palo;
    private int numero;
    private String truco;
    private boolean esTruco;
    private boolean esMago;
    private boolean esBufon;

    public Carta(boolean esTruco, int palo, int numero){
        if(!esTruco){
            if (numero < 14) {
                this.palo = palo;
                this.numero = numero;
                this.esTruco = esTruco;
            } else {
                 System.out.println("Numero de carta incorrecto");
            }
            
           
        }

    }

    public Carta(int tipo, boolean esTruco, String truco){
        if(esTruco){
            this.truco = truco;
            this.esTruco = esTruco;
        }
        if(tipo == 1){
            esMago = true;
        }else{
            esBufon = true;
        }
    }
    

    public String toString(){
        Cuprier c = new Cuprier();
        if(esTruco){
            return truco;
        }else{
            return numero + " " + c.palos[palo] ;
        }

        
        
    }

    public boolean isEsTruco() {
        return esTruco;
    }

    public int getNumero(){
        return numero;
    }

    public boolean isEsMago() {
        return esMago;
    }



    public boolean isEsBufon() {
        return esBufon;
    }


    public int getPalo() {
        return palo;
    }
    

    


}
