package funcionamiento;

import java.util.Random;


public class Cuprier {


    String[] palos = {"Corazón", "Espadas", "Trebol", "Diamante","No hay"};
    Carta[] baraja = new Carta[60];
    Pila<Carta> barajaRevuelta = new Pila<>();
    Carta[] barajaTirada = new Carta[60];
    int cartasTiradas =0;

    public Cuprier(){

    }




    public void crearBaraja(){
        
        int i = 0;
    
        while (i < 13) {
            baraja[i] = new Carta(false, 0, i + 1 );
            baraja[i+13] = new Carta(false, 1, i + 1 );
            baraja[i+26] = new Carta(false, 2, i + 1 );
            baraja[i+39] = new Carta(false, 3, i + 1 );
            i++;
        }

        i = 0;
        while (i < 4){
            int j = i + 1;
            baraja[52 + i] = new Carta(1, true, "Mago " + j);
            baraja[56 + i] = new Carta(2, true, "Bufon " + j);
            i++;
        
        }


/*         i = 0;
        while (i < 60) {
            
            System.out.println(i + " " +baraja[i].toString());
            i++;
        }  */
    
   

    }

    public void barajear(){
        

        Random rand = new Random();
        for(int j = 59; j > 0; j--){
            int num = rand.nextInt(j);
            Carta temp = baraja[num];
            baraja[num] = baraja[j];
            baraja[j] = temp;            
        }
        
        for (int i = 0; i < baraja.length; i++) {
            barajaRevuelta.push(baraja[i]);
        }
    
    }

    public void imprimirBaraja(){
        int i = 0;
        String barLinea = "";
        while (i < 60) {
            barLinea += baraja[i].toString() + "  |";
            
            i++;
        } 
        System.out.println(barLinea);

/*        while(i < 60){
            //System.out.println(i + " " + baraja[i].toString());
            System.out.println(baraja[i].toString());
            i++;
        }
 */

    } 

    public Carta repartir(){
        Carta temp = barajaRevuelta.pop();
        barajaTirada[cartasTiradas] = temp;
        cartasTiradas++;
        return temp;
    }
    
  
    
}
