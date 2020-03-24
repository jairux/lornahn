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

    void insertar(Puesto puesto) {
        
    }

    NodoArbol add(NodoArbol puesto_actual, NodoArbol nuevo_puesto) {
        if(puesto_actual.subordinados == null && puesto_actual.getPuesto().getId_puesto() == nuevo_puesto.getPuesto().getId_puesto_jefe()){
            puesto_actual.subordinados = new NodoArbol[1];
            puesto_actual.subordinados[0] = nuevo_puesto;
            return puesto_actual;
        }
        if (puesto_actual != null && puesto_actual.getPuesto().getId_puesto() == nuevo_puesto.getPuesto().getId_puesto_jefe()) {
            if (puesto_actual.getSubordinados() == null) {
                puesto_actual.subordinados = new NodoArbol[1];
                puesto_actual.subordinados[0] = nuevo_puesto;
            } else {
                NodoArbol temp[] = new NodoArbol[puesto_actual.subordinados.length + 1];
                for (int i = 0; i < puesto_actual.subordinados.length; i++) {
                    temp[i] = puesto_actual.getSubordinado(i);
                }
                temp[temp.length - 1] = nuevo_puesto;
                puesto_actual.subordinados = temp;
                return puesto_actual;
            }
        } else if (puesto_actual != null && puesto_actual.getPuesto().getId_puesto() != nuevo_puesto.getPuesto().getId_puesto_jefe()) {
            if (puesto_actual.getSubordinados() != null) {
                for (int i = 0; puesto_actual!=null && i < puesto_actual.getSubordinados().length; i++) {
                    puesto_actual = add(puesto_actual.getSubordinado(i), nuevo_puesto);
                }
            } else {
                return null;
            }
        }
        return null;
    }
    
    NodoArbol buscar(int id_puesto) {
        return buscar(raiz, id_puesto);
    }

    NodoArbol buscar(NodoArbol k, int id_puesto_jefe) {
        if (k != null && k.getPuesto().getId_puesto() != id_puesto_jefe) {
            int i = 0;
            while (k != null && k.getPuesto().getId_puesto() != id_puesto_jefe) {
                k = buscar(k.getSubordinado(i), id_puesto_jefe);
                i++;
            }
        }
        return (k != null && k.getPuesto().getId_puesto() == id_puesto_jefe) ? k : null;
    }
    
  
    
    
    void imprimir(NodoArbol elemento, String espacio) {
        System.out.println(espacio + "Id: " + elemento.getPuesto().getId_puesto() + " - Puesto: " + elemento.getPuesto().getPuesto() + " - Titular: " + elemento.getPuesto().getEmpleado() + " - Jefe: " + elemento.getPuesto().getId_puesto_jefe());
        if (elemento.getSubordinados() != null) {
            espacio += "          ";
            for (int i = 0; i < elemento.getSubordinados().length; i++) {
                imprimir(elemento.getSubordinado(i), espacio);
            }
        }
    }
    
    String impresionPuesto(NodoArbol puesto){
        String text = "Id: "+puesto.getPuesto().getId_puesto()+"     "
                + "Descripcion: "+puesto.getPuesto().getPuesto()+" \n"
                + "Titular: "+puesto.getPuesto().getEmpleado()+"     "
                + "Id_jefe: "+puesto.getPuesto().getId_puesto_jefe()+"\n\n";
        for (int i = 0; puesto.getPuesto().tareas_pendientes != null && i < puesto.getPuesto().tareas_pendientes.length; i++) {
            text += "Tarea "+(i+1)+": "+puesto.getPuesto().tareas_pendientes[i].descripcion+"\n";
        }
        return text;
    }
    
    
    String impresion(NodoArbol elemento, String espacio, String texto) {
        texto+= espacio + "Id: " + elemento.getPuesto().getId_puesto() + " - Puesto: " + elemento.getPuesto().getPuesto() + " - Titular: " + elemento.getPuesto().getEmpleado() + " - Jefe: " + elemento.getPuesto().getId_puesto_jefe()+"\n";
        if (elemento.getSubordinados() != null) {
            espacio += "          ";
            for (int i = 0; i < elemento.getSubordinados().length; i++) {
                    texto +=  impresion(elemento.getSubordinado(i), espacio,"");
            }
        }
            return texto;
    }

    void borrar() {
    }

    void padre() {
    }

    boolean existe(int id_puesto) { //devuelve verdadero si el puesto buscado exite
        if (raiz == null) {
            return false;
        }
        if (raiz.getPuesto().getId_puesto() == id_puesto) {
            return true;
        } else {
            existe(raiz, id_puesto);
        }
        return false;
    }

    boolean existe(NodoArbol nodo, int id_puesto) {//trabaja en conjunto con el metodo existe anterior para verificar si el puesto que buscamos existe
        NodoArbol[] subs = nodo.getSubordinados();
        for (int i = 0; i < subs.length; i++) {
            if (subs[i].getPuesto().getId_puesto() == id_puesto) {
                return true;
            } else {
                existe(subs[i].getSubordinado(i), id_puesto);
            }
        }
        return false;
    }

    int altura() {
        return 0;
    }

    void vaciar() { //vacia nuestro arbol poniendo nuestra raiz en nulo, pese a que limpiamos el árbol, los nodos continuan existiendo en memoria pero ya no podemos acceder a ellos
        raiz = null;
    }

    boolean esVacio() {
        return raiz == null;
    }

    NodoArbol distinto() {
        return null;
    }

    public void insertarEnRaiz(Puesto puesto) {
        if (raiz != null) {
            raiz.setPuesto(puesto.getId_puesto(), puesto.getPuesto(), puesto.getEmpleado(), puesto.getId_puesto_jefe());
            raiz.subordinados = null;
        } else {
            raiz = new NodoArbol(puesto.getId_puesto(), puesto.getPuesto(), puesto.getEmpleado(), puesto.getId_puesto_jefe());
        }
    }
}
