package com.mycompany.firebase;

import java.util.Scanner;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import java.io.FileInputStream;
import java.io.IOException;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Patarroyo
 */
public class Conexion {
    /**
     * Inicializa la instancia del Store para conexiones a FireBase
     */
    static public Firestore fbStore;
    static public FirebaseDatabase fbDataBase;
    static private Scanner input = new Scanner(System.in);

    static public void conectarBase() {
        try {
            FileInputStream credential = new FileInputStream("tutorial.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(credential))
                    .setDatabaseUrl("https://ejemplo1-de1e9-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp fbApp = FirebaseApp.initializeApp(options);
            fbStore = FirestoreClient.getFirestore();
            fbDataBase = FirebaseDatabase.getInstance();
            System.out.println("Se realizó la conexión con la base de datos");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static public boolean insertarRegistro(String coleccion,String documento, Map<String, Object> data) {
        try {
            ApiFuture<WriteResult> doc = fbStore.collection(coleccion).document(documento).set(data);
            System.out.println(coleccion + " guardado correctamente");
            return true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return false;
    }
    
    static public void testInsercion(){
        Map<String, Object> datos1 = new HashMap<String, Object>();
        System.out.println("Ingrese el primer nombre: ");
        String nombre1 = input.nextLine();
        System.out.println("Ingrese el segundo nombre: ");
        String nombre2 = input.nextLine();
        System.out.println("Ingrese el primer apellido: ");
        String apellido1 = input.nextLine();
        System.out.println("Ingrese el segundo apellido: ");
        String apellido2 = input.nextLine();
        datos1.put("Nombre1", nombre1);
        datos1.put("Nombre2", nombre2);
        datos1.put("Apellido1", apellido1);
        datos1.put("Apellido2", apellido2);
        System.out.println("Ingrese el nombre de la colección: ");
        String coleccion = input.nextLine();
        System.out.println("Ingrese el documento: ");
        String id = input.nextLine();
        
        insertarRegistro(coleccion, id, datos1);
    }
    
    static public boolean actualizarRegistro(String coleccion,String documento, Map<String, Object> data) {
        try {
            ApiFuture<WriteResult> doc = fbStore.collection(coleccion).document(documento).update(data);
            System.out.println(coleccion + " guardado correctamente");
            return true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return false;
    }
    
    static public void testActualizacion(){
        try {
            System.out.println("Ingrese el nombre de la colección a editar: ");
            String coleccion = input.nextLine();
            System.out.println("Ingrese uno de los siguientes documentos: ");
            ApiFuture<QuerySnapshot> doc = fbStore.collection(coleccion).get();
            List<QueryDocumentSnapshot> documents = doc.get().getDocuments();
            for (QueryDocumentSnapshot document : documents){
               System.out.println(document.getId());
            }
            String id = input.nextLine();
            Map<String, Object> datos1 = new HashMap<String, Object>();
            System.out.println("Ingrese el primer nombre: ");
            String nombre1 = input.nextLine();
            System.out.println("Ingrese el segundo nombre: ");
            String nombre2 = input.nextLine();
            System.out.println("Ingrese el primer apellido: ");
            String apellido1 = input.nextLine();
            System.out.println("Ingrese el segundo apellido: ");
            String apellido2 = input.nextLine();
            datos1.put("Nombre1", nombre1);
            datos1.put("Nombre2", nombre2);
            datos1.put("Apellido1", apellido1);
            datos1.put("Apellido2", apellido2);
            
            actualizarRegistro(coleccion, id, datos1);
        } catch (InterruptedException ex) {
            System.out.println("Error: " + ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
