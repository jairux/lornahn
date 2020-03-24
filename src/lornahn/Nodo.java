/*              +-------------------------------------------------------------------------------------------------------------------------------------------------------------+
                 |   La clase Nodo Representa un elemento de la lista, usaremos la lista enlazada para crear la lista de nodos hijos       |
                 |   de cada uno de los nodos que vallamos creando                                                                                                                |
                +--------------------------------------------------------------------------------------------------------------------------------------------------------------+
                /
     n    n
     ( o_o)
   / (       )\
      |     |

*/

package lornahn;

public class Nodo {
    NodoArbol elemento;
    Nodo siguiente;
    
    public Nodo(NodoArbol elemento){
            this.elemento = elemento;
            this.siguiente = null;
    }    
    
    public NodoArbol getElemento(){
        return this.elemento;
    }
    
    public void setElemento(NodoArbol elemento){
        this.elemento = elemento;
    }
    
    public Nodo getSiguiente(){
        return this.siguiente;
    }
    
    public void setSiguiente(Nodo siguiente){
        this.siguiente = siguiente;
    }    
}
