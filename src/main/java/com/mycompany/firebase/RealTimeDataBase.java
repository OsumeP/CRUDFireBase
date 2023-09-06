package com.mycompany.firebase;

import com.google.api.core.ApiFuture;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.NonNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Patarroyo
 */
public class RealTimeDataBase {

    static Scanner input = new Scanner(System.in);

    static public void AgregarDatos() {
        try {
            DatabaseReference ref = Conexion.fbDataBase.getReference("server/saving-data");
            DatabaseReference studensRef = ref.child("estudiantes");
            Map<String, User> studens = new HashMap<>();
            studens.put(
                    "80737015", new User("Cesar David Patarroyo", "March 23, 1983", "Salamandros"));
            studens.put(
                    "1034282941", new User("Juan David Patarroyo", "January 6, 2006", "Osumep"));
            studens.put(
                    "1034282942", new User("Astrid Sanchez", "January 6, 2006", "Osumep"));
            studensRef.setValueAsync(studens);
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RealTimeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Estudiantes registrados correctamente");

//        try {
//             CountDownLatch countDownLatch = new CountDownLatch(1);
//            countDownLatch.await();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(RealTimeDataBase.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("Ingrese el nombre del nuevo usuario: ");
//        String name = input.nextLine();
//        System.out.println("Ingrese la fecha de nacimiento del nuevo usuario: ");
//        String birthDay = input.nextLine();
//        System.out.println("Ingrese el nickname del nuevo usuario: ");
//        String nickname = input.nextLine();
//
//        DatabaseReference usersRef = ref.child("users");
//        Map<String, Object> users = new HashMap<>();
//        usersRef.updateChildrenAsync(users);
//        usersRef.child(name).setValueAsync(new User(birthDay, nickname));
    }

    public static void EliminarDatos() {
        try {
            final DatabaseReference dataBase = Conexion.fbDataBase.getReference("server/saving-data");
            dataBase.child("estudiantes/80737015").removeValueAsync();
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RealTimeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Registro estudiante eliminado");

    }

    public static void UpdateDatos() {
        try {
            final DatabaseReference dataBase = Conexion.fbDataBase.getReference("server/saving-data");
            Map<String, Object> userUpdate = new HashMap<>();
            userUpdate.put("estudiantes/1034282941/name", "Cesar David");
            dataBase.updateChildrenAsync(userUpdate);
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RealTimeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Registro Estudiante Actualizado");
    }

    static public void LeerDatos() {
        try {
            DatabaseReference ref = Conexion.fbDataBase.getReference("server/saving-data/estudiantes");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Object estudiante = dataSnapshot.getValue();
                    System.out.println("Lectura de estudiante: ");
                    System.out.println(estudiante);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RealTimeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Inicia lectura de estudiantes");
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User estudiante = dataSnapshot.getValue(User.class
//                );
//                System.out.println(estudiante);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // ...
//            }
//        });
//
//        ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                User newPost = dataSnapshot.getValue(User.class
//                );
//                System.out.println("Author: " + newPost.name);
//                System.out.println("Title: " + newPost.birthDay);
//                System.out.println("Previous Post ID: " + prevChildKey);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
    }

    static public void LeerDatoFinal() {
        try {
            DatabaseReference ref = Conexion.fbDataBase.getReference("server/saving-data/estudiantes");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Object estudiante = dataSnapshot.getValue();
                    System.out.println("Lectura de estudiantes Final: ");
                    System.out.println(estudiante);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // ...
                }
            });
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RealTimeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Lectura de dato final");
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User estudiante = dataSnapshot.getValue(User.class
//                );
//                System.out.println(estudiante);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // ...
//            }
//        });
//
//        ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                User newPost = dataSnapshot.getValue(User.class
//                );
//                System.out.println("Author: " + newPost.name);
//                System.out.println("Title: " + newPost.birthDay);
//                System.out.println("Previous Post ID: " + prevChildKey);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
    }
}
