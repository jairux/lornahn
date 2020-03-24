/*

                +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
                 |   La clase Lista nos permite crear una lista enlazada donde almacenaremos la información de los nodos hijos de un                  |
                 |   determinado nodo de nuestro árbol                                                                                                                                                       |
                +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
                /
     n    n
     ( o_o)
   / (       )\
      |     |

*/

package lornahn;

public class Lista {
        Nodo primero;
        
        void insertar(NodoArbol elemento){
            if (primero == null) {
                 primero = new Nodo(elemento);
            }else{
                Nodo k = primero;
                primero.setSiguiente(k);
            }
        }
        
        int longitud(){ // 
            return longitud(primero);
        }
        
        public int longitud(Nodo elemento){ //devuelve la cantidad de nodos hijos en la lista que posee el Nodo elemento
            int resultado = 0;
            if (elemento!=null) {
                if (elemento.getSiguiente() == null) {
                    resultado = 1;
                } else {
                    resultado = 1+longitud(elemento.getSiguiente());
                }
            }
            return resultado;
        }
        
        Nodo get(int numero){ //devuelve el elemento de la lista que se encuentra en la posición numero
            Nodo elemento = this.primero;
            for (int i = 0; i < numero && elemento!=null; i++) {
                elemento = elemento.getSiguiente();
            }
            return elemento;
        }
        
        boolean Buscar(NodoArbol elemento){
            boolean resultado = false;
            Nodo k = primero;
            if(k!=null){
                while(k.getElemento()!= elemento && k.getSiguiente()!=null){
                    k = k.getSiguiente();
                }
                resultado = k.getElemento() == elemento; //devuelve true si el elemento buscado es igual al elemento actual en el nodo
            }
            return resultado;
        }
        
        void imprimir(){
            Nodo k = primero;
            while(k!=null){
                System.out.print(k.getElemento()+" -> ");
                k = k.getSiguiente();
            }
        }
        
        Nodo posHijo(int x){
            Nodo k = primero;
            for (int i = 0; i <= x; i++) {
                if (k.getSiguiente()!=null) {
                      k = k.getSiguiente();
                }
            }
            return k;
        }
}
