/*
                +-------------------------------------------------------------------------------------------------------------------------------------------------------------+
                 |   La clase Puesto contiene la información de un puesto en particular, aqui almacenaremos la información del            |
                 |   con el que estamos trabajando                                                                                                                                              |
                +--------------------------------------------------------------------------------------------------------------------------------------------------------------+
                /
     n    n
     ( o_o)
   / (       )\
      |     |



*/

package lornahn;

public class Puesto {
        int id_puesto = -1;
        String puesto = "";
        String empleado = "";
        int id_puesto_jefe = -1;
        tareas[] tareas_pendientes = null;
        
     public Puesto(int id_puesto, String puesto, String empleado, int id_puesto_jefe) {
        this.id_puesto = id_puesto;
        this.puesto = puesto;
        this.empleado = empleado;
        this.id_puesto_jefe = id_puesto_jefe;
    }
    
    public Puesto(){}
    
    public int getId_puesto(){
        return this.id_puesto;
    }
    
    public void setId_puesto(int id_puesto){
        this.id_puesto = id_puesto;
    }

    public String getPuesto() {
        return this.puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
    
    public int getId_puesto_jefe(){
        return this.id_puesto_jefe;
    }
    
    public void setId_puesto_jefe(int id_puesto_jefe){
        this.id_puesto_jefe = id_puesto_jefe;
    }
    
     //OPERACIONES CON LAS TAREAS PENDIENTES
    
    public tareas[] getTareas_pendientes() {
        return this.tareas_pendientes;
    }
    
    public void listarTareas(){
        System.out.println("Las tareas pendientes del empleado: "+empleado);
        System.out.println("cuyo puesto es: "+puesto+" son: ");
        for (int i = tareas_pendientes.length-1; i == 0; i--) {
            System.out.println((i+1)+". "+ this.tareas_pendientes[i]);
        }
    }
    
    public void insertarTareas_pendientes(String tarea){
        if(tareas_pendientes == null) {
            tareas_pendientes = new tareas[1];
            tareas_pendientes[0]= new tareas(tarea);
        } else {
            tareas[] temp = new tareas[tareas_pendientes.length+1];
            for(int i = 0; i < tareas_pendientes.length; i++){
                temp[i] = tareas_pendientes[i];
            }
            temp[tareas_pendientes.length] = new tareas(tarea);
            this.tareas_pendientes = temp;
        }
    }
    
    public String eliminarTareas_pendientes() {
        String notificacion = "";
        if (tareas_pendientes == null) {
            notificacion = "El empleado del puesto actual no tiene tareas pendientes";
        } else if (tareas_pendientes.length == 1) {
            tareas_pendientes = null;
             notificacion = "Tarea Eliminada exitosamente";
        } else {
            tareas[] temp = new tareas[tareas_pendientes.length - 1];
            for (int i = 1; i < tareas_pendientes.length; i++) {
                temp[i - 1] = tareas_pendientes[i];
            }
            tareas_pendientes = new tareas[temp.length];
            for(int i = 0; i < temp.length; i++){
                tareas_pendientes[i] = temp[i];
            }
             notificacion = "Tarea Eliminada exitosamente";
        }
        return notificacion;
    }
    
}
