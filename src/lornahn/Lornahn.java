package lornahn;
import javax.swing.JOptionPane;

public class Lornahn {   
    
    public static JOptionPane popup = new JOptionPane(); 
    public static Arbol arb;
       
    public static Puesto crearPuesto(){ //nos permite crear un nuevo puesto para insertarlo en un nodo
        //creando y leyendo datos del  nuevo puesto
        Puesto nuevo = new Puesto(leerEnteros("Ingrese el identificador del nuevo puesto"), leerTexto("Ingrese el nombre del nuevo puesto"), leerTexto("Ingrese el nombre del titular del puesto"), leerEnteros("Ingrese el identificador del puesto jefe"));
        while(arb.existe(nuevo)){
               notificar("Redundancia de elementos","El identificador del puesto que acaba de ingresar ya existe!!");
               nuevo.setId_puesto(leerEnteros("Ingrese el identificador del nuevo puesto"));
        }
        while(!arb.existePadre(nuevo.getId_puesto())){
                notificar("Padre no encontrado","Identificador del puesto jefe al cual se hace referencia no existe");
                nuevo.setId_puesto_jefe(leerEnteros("Ingrese el identificador del puesto jefe"));
        }
        return nuevo;
    }
    
    public static void agregarArbol(Puesto elemento){ //agrega al arbol
        if (arb==null)
            arb.insertarEnRaiz(elemento);
        else
            arb.insertar(elemento);
    }
    
    public static int leerEnteros(String texto){
        try {
            return Integer.parseInt(popup.showInputDialog(null, texto, ".: LEYENDO DATOS :.", 3)); 
        } catch (Exception e) {
            popup.showMessageDialog(null, "Se ha producido un error en la lectura de datos \n\nError de tipo: "+e, ".: ERROR :.", 0);
        }
        return -1;
    }
    
    public static String leerTexto(String texto){
        try {
            return String.format("%s", JOptionPane.showInputDialog(null, texto, ".: LEYENDO DATOS :.", 3)); 
        } catch (Exception e) {
            popup.showMessageDialog(null, "Se ha producido un error \n en la lectura de datos\n\nError de tipo: "+e, ".: ERROR :.", 0);
        }
        return "";
    }
    
    public static void notificar(String titulo, String texto){
        popup.showMessageDialog(null, texto, titulo, 2);
    }
    
    
    public static void main(String[] args) {
        agregarArbol(crearPuesto());
        arb.imprimir();
    }
    
}
