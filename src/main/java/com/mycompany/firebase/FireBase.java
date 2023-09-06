package com.mycompany.firebase;

/**
 *
 * @author David Patarroyo
 */
public class FireBase {
    public static void main(String[] args) {
        Conexion.conectarBase();
        //RealTimeDataBase.LeerDatos();
        RealTimeDataBase.AgregarDatos();
        RealTimeDataBase.EliminarDatos();
        RealTimeDataBase.UpdateDatos();
        RealTimeDataBase.LeerDatoFinal();
        
    }
}
