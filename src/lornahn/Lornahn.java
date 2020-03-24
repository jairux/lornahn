package lornahn;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Lornahn {   
    
    public static JOptionPane popup = new JOptionPane(); 
    public static Arbol arb = new Arbol();
    
    
    
    //MANEJO DE FICHEROS
    public static void leerFichero(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        try {
          archivo = new File("data.txt");
          fr = new FileReader(archivo);
          br = new BufferedReader(fr);
          
          String linea;
          while((linea=br.readLine())!=null){
              String[] lin = linea.split("-");
              System.out.println(lin.length);
              System.out.println(lin[0]);
              System.out.println(lin[1]);
              System.out.println(lin[2]);
              System.out.println(lin[3]);
              agregarArbol(new Puesto(Integer.parseInt(lin[0]), lin[1], lin[2], Integer.parseInt(lin[3])));
          }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (null!= fr)fr.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    public static void escribirFichero(String texto){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try{
            fichero = new FileWriter("data.txt");
            pw = new PrintWriter(fichero);
            pw.print(texto);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(null != fichero) fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    
    
    
    public static Puesto crearPuesto(){ //nos permite crear un nuevo puesto para insertarlo en un nodo
        //creando y leyendo datos del  nuevo puesto
        Puesto nuevo = new Puesto(leerEnteros("Ingrese el identificador del nuevo puesto"), leerTexto("Ingrese el nombre del nuevo puesto"), leerTexto("Ingrese el nombre del titular del puesto"), leerEnteros("Ingrese el identificador del puesto jefe"));
       if (arb.esVacio()){
               nuevo.setId_puesto_jefe(0);
        }else{
//            while(arb.existe(nuevo)){
//               notificar("Redundancia de elementos","El identificador del puesto que acaba de ingresar ya existe!!");
//               nuevo.setId_puesto(leerEnteros("Ingrese el identificador del nuevo puesto"));
//            }
//            while(!arb.existePadre(nuevo.getId_puesto_jefe())){
//                notificar("Padre no encontrado","Identificador del puesto jefe al cual se hace referencia no existe");
//                nuevo.setId_puesto_jefe(leerEnteros("Ingrese el identificador del puesto jefe"));
//           }
        }
        return nuevo;
    }
    
    public static void agregarArbol(Puesto elemento){ //agrega al arbol
        if (arb.raiz == null)
            arb.insertarEnRaiz(elemento);
        else
            arb.add(arb.raiz, new NodoArbol(elemento));
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
    
    
    public static void elementosPrevios(){
        agregarArbol(new Puesto(1, "Presidente","Lorna",0));
        agregarArbol(new Puesto(2, "Tesorero", "Luis",1));
        agregarArbol(new Puesto(3, "Secretario","Eduardo",1));
        agregarArbol(new Puesto(4, "Subtesorero 1","Andrea",2));
        agregarArbol(new Puesto(5, "Subtesorero 2","Luis",2));
        agregarArbol(new Puesto(6, "Vicepresidente","Jairin",1));
        
        arb.buscar(1).puesto.insertarTareas_pendientes("Reunion informativa");
        arb.buscar(1).puesto.insertarTareas_pendientes("Resion de informes");
        arb.buscar(2).puesto.insertarTareas_pendientes("Presupuestacion");
    }
    
    public static void operacionNodo(){
        int opt = -1;
        NodoArbol puesto = arb.buscar(leerEnteros("Ingrese el id del puesto"));
        while (opt != 0) {
            opt = leerEnteros("Ingrese una opcion: \n\n1. Ver detalles Puesto\n2. Agregar Tarea\n3. Eliminar Tarea\n0. Salir");
            switch (opt) {
                case 1:
                    notificar(".: DETALLES DE PUESTO :.", arb.impresionPuesto(puesto));
                    break;
                case 2:
                     arb.buscar(puesto.getPuesto().getId_puesto()).puesto.insertarTareas_pendientes(leerTexto("Ingrese la descripcion de la tarea a agregar: "));
                     puesto = arb.buscar(puesto.getPuesto().getId_puesto());
                     break;
                case 3:
                     notificar(".: ELIMINACION :.",arb.buscar(puesto.getPuesto().getId_puesto()).puesto.eliminarTareas_pendientes());
                     puesto = arb.buscar(puesto.getPuesto().getId_puesto());
                     break;
                case 0: 
                    break;
                default:
                    notificar(".: INFORMACION:.", "LA OPCION INGRESADA ES INVALIDA");
            }
        }
    }
    
    public static void main(String[] args) {
        elementosPrevios();
        int op = 0;
        while(true){
            op = leerEnteros("Ingrese una opcion: \n\n1. Agregar elemento al arbol.\n2. Imprimir Arbol\n3. Operaciones con puesto\n4. Guardar en archivo\n5. Leer de archivo\n0. Salir");
            switch(op){
                case 1:
                    agregarArbol(crearPuesto());
                    break;
                case 2: 
                    String texto;
                    if(arb.raiz==null) texto = "Arbol Vacio";
                    else texto = arb.impresion(arb.raiz,"","");
                    notificar(".: ARBOL ACTUAL :.", texto);
                    break;
                case 3:
                    operacionNodo();
                    break;
                case 4:
                    escribirFichero(arb.writeFichero(arb.raiz,""));
                    notificar(".: GUARDANDO EN FICHERO :.","Guardado");
                    break;
                case 5:
                    leerFichero();
                    break;
                case 0:
                    notificar(".: Salida :.","Gracias por usar la aplicacion :\'v");
                    System.exit(0);
                    break;
                default:
                    notificar(".: Error :.","Ha ingresado una opcion invalida:\'v");
            }      
        }
    }
    
}
