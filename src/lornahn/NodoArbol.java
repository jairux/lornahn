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
    NodoArbol[] subordinados; //arreglo de hijos que posee el nodo actual
    
    NodoArbol(int id_puesto, String puesto, String empleado, int id_puesto_jefe){//agregamos los datos del objeto al nodo actual
        this.puesto = new Puesto(id_puesto, puesto, empleado, id_puesto_jefe);
        this.subordinados = null;
    }
    
    NodoArbol(NodoArbol elemento){
        this.puesto = elemento.getPuesto();
        this.subordinados = null;
    }
    
    NodoArbol(Puesto elemento){
        this.puesto = new Puesto(elemento.getId_puesto(), elemento.getPuesto(), elemento.getEmpleado(), elemento.getId_puesto_jefe());
        this.subordinados = null;
    }
    
    
    //OPERACIONES CON LOS HIJOS DEL NODO
    NodoArbol getSubordinado(int i){
        if(subordinados==null)return null;
        else return subordinados[i];
    }
    
    NodoArbol[] getSubordinados(){
        return subordinados; //nos devuelve el arreglo de los hijos del nodo actual
    }
    
    Puesto getPuesto(){
        return this.puesto;
    }
    
    NodoArbol getJefe(NodoArbol a, int id_puesto_jefe){
        if(a!=null && a.getPuesto().getId_puesto() == id_puesto_jefe) {
            return a;
        }else if(a!=null && a.getPuesto().getId_puesto() != id_puesto_jefe){
            if (a.getSubordinados() != null){
                for (int i = 0; i < a.getSubordinados().length; i++) {
                    getJefe(a.getSubordinado(i), id_puesto_jefe);
                }
            }else{
                return null;
            }
        }
        return null;
    }
    
    NodoArbol agregarSubordinado(NodoArbol puesto, NodoArbol jefe){
        if (jefe.getSubordinados() == null) {
            jefe.subordinados = new NodoArbol[1];
            subordinados[0] = new NodoArbol(puesto);
        }else{
            NodoArbol[] temp = new NodoArbol[jefe.getSubordinados().length+1];
            for(int i = 0; i < jefe.getSubordinados().length; i++){
                  temp[i] = jefe.getSubordinado(i);
            }
            temp[jefe.getSubordinados().length+1] = new NodoArbol(puesto);
            jefe.subordinados  = new NodoArbol[temp.length];
            for (int i = 0; i < temp.length; i++) {
                  jefe.subordinados[i] = temp[i];
            }
        }
        return jefe;
    }
    
    void setPuesto(int id_puesto, String puesto, String empleado, int id_puesto_jefe){
        this.puesto = new Puesto(id_puesto, puesto, empleado, id_puesto_jefe); 
    }
    
//    void agregarPuesto(int id_puesto, String puesto, String empleado, int id_puesto_jefe, NodoArbol jefe){
//        if(subordinados == null){
//            subordinados = new NodoArbol[1];
//            subordinados[0] = new NodoArbol(id_puesto, puesto, empleado, id_puesto_jefe);
//        }else{
//            NodoArbol jefe = getJefe(new NodoArbol(id_puesto,puesto,empleado,id_puesto_jefe), id_puesto_jefe);
//            if (jefe != null) jefe = agregarSubordinado(new NodoArbol(id_puesto, puesto, empleado, id_puesto_jefe), jefe);
//        }
//    }
    
    boolean esHijo(){
        return false;
    }

    boolean esHoja(){
        return subordinados==null; //devuelve true si el nodo no tiene hijos, caso contrario devuelve false
    }
}