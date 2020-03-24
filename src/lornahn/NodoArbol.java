/*              +-------------------------------------------------------------------------------------------------------------------------------------------------------------+
                 |   La clase NodoArbol representa un elemento, es decir que aquí ser almacena la información de un nuevo puesto   |
                 |   en esta clase se contiene la información de un puesto y la lista de los hijos o subordinados de dicho puesto          |
                +--------------------------------------------------------------------------------------------------------------------------------------------------------------+
                /
     n    n
     ( o_o)
   / (       )\
      |     |

*/

package lornahn;

public class NodoArbol {
    Puesto puesto; //informacion acerca del puestoalmacenado en el nodo actual
    Lista hijos; //lista de hijos   que posee el nodo actual
    
    NodoArbol(int id_puesto, String puesto, String empleado, int id_puesto_jefe){//agregamos los datos del objeto al nodo actual
        this.puesto = new Puesto(id_puesto, puesto, empleado, id_puesto_jefe);
        this.hijos = new Lista();
    }
    
    //OPERACIONES CON LOS HIJOS DEL NODO
    NodoArbol getHijo(int i){
        Nodo n = hijos.get(i);
        return n!=null?n.getElemento(): null;
    }
    
    Lista getHijos(){
        return this.hijos; //nos devuelve la lista de los hijos del nodo actual
    }
    
    Puesto getPuesto(){
        return this.puesto;
    }
    
    void setPuesto(int id_puesto, String puesto, String empleado, int id_puesto_jefe){
        this.puesto = new Puesto(id_puesto, puesto, empleado, id_puesto_jefe); 
    }
    
    void agregarHijo(int id_puesto, String puesto, String empleado, int id_puesto_jefe){
        hijos.insertar(new NodoArbol(id_puesto, puesto, empleado, id_puesto_jefe));
    }
    
    boolean esHijo(){
        return false;
    }

    boolean esHoja(){
        return hijos==null; //devuelve true si el nodo no tiene hijos, caso contrario devuelve false
    }
}