package funcionamiento;

/**
 * @author Giovanny Cruz Martinez
 * @author Dulce Julieta Mora Hernandez
 **/

public class Cola<T> extends PushPop<T>{

    /**
     * Agrega un elemento dado al final de la cola
     **/
    public void push(T elemento){
	if(elemento == null){
	    throw new IllegalArgumentException("");
	}
	Nodo aux = new Nodo(elemento);
	if(isEmpty()){
	    this.cabeza=ultimo=aux;
	    longi++;
	    return;
	}
	ultimo.siguiente = aux;
	ultimo = aux;
	longi++;
	return;
    }

    
    /**
     * Metodo que regresa un clon de la cola
     *
     * @return Un clon de la cola
     **/
    public Cola<T> clone(){
	Cola<T> nueva = new Cola<T>();
	if(this.isEmpty()){
	    return nueva;
	}
	nueva.push(this.cabeza.elemento);
	Nodo n = this.cabeza;
	while(n.siguiente != null){
	    nueva.push(n.siguiente.elemento);
	    n = n.siguiente;
	}
	return nueva;
    }
    
    /**
     *
     **/
    public String toString(){
	if(this.isEmpty()){
	    return "";
	}
	String regreso = this.cabeza.elemento.toString();
	Nodo n = this.cabeza;
	while(n.siguiente != null){
	    regreso += ", " + n.siguiente.elemento.toString();
	    n = n.siguiente;
	}
	return regreso;
    }
    
}
