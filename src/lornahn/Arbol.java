/*

                +---------------------------------------------------------------------------------------------------------------------------------------------------------+
                 |   La clase Arbol contiene los metodos para trabajar el arbol n-ario, puede decirse que es el arbol en si mismo      |                    |
                +---------------------------------------------------------------------------------------------------------------------------------------------------------+
                /
     n    n
     ( o_o)
   / (       )\
      |     |

*/
package lornahn;

public class Arbol {
    NodoArbol raiz; //es el primer nodo de nuestro arbol
    
    
    void insertar(Puesto puesto){
            NodoArbol nodoPadre = buscar(puesto.getId_puesto_jefe()); //buscamos el puesto padre el cual se enlazará al nuevo puesto
            if (nodoPadre!=null) {
                nodoPadre.agregarHijo(puesto.getId_puesto(), puesto.getPuesto(),puesto.getEmpleado(),puesto.getId_puesto_jefe());
        }
    }
    
    NodoArbol buscar(int id_puesto_jefe){
        return buscar(raiz, id_puesto_jefe);
    }
    
    NodoArbol buscar(NodoArbol k, int id_puesto_jefe){
         if (k!=null && k.getPuesto().getId_puesto() != id_puesto_jefe) {
              int i = 0;
              while(k!=null &&id_puesto_jefe != k.getPuesto().getId_puesto()){
                  k = buscar(k.getHijo(i), id_puesto_jefe);
                  i++;
              }
        }
         return (k!=null && k.getPuesto().getId_puesto() ==id_puesto_jefe)?k:null;
    }
    
    void imprimir(){}
    void borrar(){}
    void padre(){}
    
    boolean existePadre(int id_puesto_padre){ //devuelve verdadero si el identificador que buscamos existe
        if(raiz == null) return false;
        if(raiz.getPuesto().getId_puesto() == id_puesto_padre) return true;
        else existePadre(raiz, id_puesto_padre);
        return false;
    }
    
    boolean existePadre(NodoArbol nodo, int id_puesto_padre){// trabaja en conjunto con el metodo existePadre anterior para verificar si el identificador que buscamos existe
        Lista lista = nodo.getHijos();
        for (int i = 0; i < lista.longitud(); i++) {
            if (lista.get(i).getElemento().getPuesto().getId_puesto() == id_puesto_padre) {
                return true;
            }else{
                existePadre(lista.get(i).getElemento(), id_puesto_padre);
            }
        }
        return false;
    }
    
    
    boolean existe(Puesto elemento){ //devuelve verdadero si el puesto buscado exite
        if(raiz == null) return false;
        if(raiz.getPuesto() == elemento) return true;
        else existe(raiz, elemento);
        return false;
    }
    
    boolean existe(NodoArbol nodo, Puesto elemento){//trabaja en conjunto con el metodo existe anterior para verificar si el puesto que buscamos existe
        Lista lista = nodo.getHijos();
        for (int i = 0; i < lista.longitud(); i++) {
            if (lista.get(i).getElemento().getPuesto() == elemento) {
                return true;
            }else{
                existe(lista.get(i).getElemento(), elemento);
            }
        }
        return false;
    }
    
    int altura(){
        return 0;
    }
    
    
    void vaciar(){ //vacia nuestro arbol poniendo nuestra raiz en nulo, pese a que limpiamos el árbol, los nodos continuan existiendo en memoria pero ya no podemos acceder a ellos
        raiz = null;
    }

    boolean esVacio(){
        return raiz == null;
    }
    
    
    NodoArbol distinto(){
        return null;
    }
    
    public void insertarEnRaiz(Puesto puesto){
        if (raiz!=null) {
            raiz.setPuesto(puesto.getId_puesto(), puesto.getPuesto(),puesto.getEmpleado(), puesto.getId_puesto_jefe());
        }else{
            raiz = new NodoArbol(puesto.getId_puesto(),puesto.getPuesto(), puesto.getEmpleado(), puesto.getId_puesto_jefe());
        }
    }
}
