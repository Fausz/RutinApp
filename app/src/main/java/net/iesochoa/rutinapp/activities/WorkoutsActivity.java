package net.iesochoa.rutinapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.Query;

import net.iesochoa.rutinapp.R;
import net.iesochoa.rutinapp.adapters.WorkoutsAdapter;
import net.iesochoa.rutinapp.models.Workouts;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkoutsActivity extends AppCompatActivity {

    //OBJETO ADAPTER
    private WorkoutsAdapter mAdapter;

    //OBJETO RECYCLER VIEW
    private RecyclerView mRecyclerView;

    //ARRAYLIST DE EJERCICIOS
    private ArrayList<Workouts> workoutsList = new ArrayList<>();

    //OBJETO FIREBASE PARA EL MANEJO DE LA BASE DE DATOS REALTIME
    private DatabaseReference mDatabase;

    //OBJETO FIREBASE AUTH PARA LA IDENTIFICACIÓN DE FIREBASE
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        //DECLARACIÓN DE REFERENCIA A LA BD
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //INSTANCIA DE FIREBASE DE LOGEO
        mAuth = FirebaseAuth.getInstance();

        //OBTENEMOS REFERENCIAS
        mRecyclerView = (RecyclerView) findViewById(R.id.rvListWorkouts);

        //POSICIONAMOS LAS LISTAS UNA ENCIMA DE OTRA
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //MÉTODO QUE OBTIENE LOS OBJETOS WORKOUTS DE FIREBASE
        getWorkoutsFromFirebase();


    }//FIN onCreate

    private void getWorkoutsFromFirebase() {
        /**
         * MÉTODO QUE OBTIENE LOS DATOS DE FIREBASE
         */

        //REFERENCIA AL NODO EJERCICIOS DE FIREBASE
        mDatabase.child("Workouts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //SI EL NODO HIJO EXISTE
                if (dataSnapshot.exists()) {

                    //RECORREMOS CON UN FOREACH TODOS LOS OBJETOS WORKOUTS
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        //GUARDAMOS EN VARIABLES EL CONTENIDO DE CADA OBJETO
                        String name = ds.child("Name").getValue().toString();
                        String group = ds.child("Group").getValue().toString();
                        String description = ds.child("Description").getValue().toString();
                        String img = ds.child("Img").getValue().toString();

                        //SE AÑADE UN NUEVO OBJETO A LA LISTA
                        workoutsList.add(new Workouts(name, group, description, img));
                    }


                    //INSTANCIA DEL ADAPTER
                    mAdapter = new WorkoutsAdapter(getApplicationContext(), workoutsList, R.layout.item_workouts);

                    //RECYCLER VIEW IMPLEMENTA EL ADAPTADOR
                    mRecyclerView.setAdapter(mAdapter);

                } else {
                    Toast.makeText(WorkoutsActivity.this, "No se ha encontrado el nodo buscado en la BD.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });//FIN EVENTO addValueEventListener
    }//FIN getWorkoutsFromFirebase


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //OPCIONES DEL MENÚ

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * MÉTODO PARA INFLAR EL MENÚ
         */
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * MÉTODO PARA ASOCIAR UN METODO ONCLICK EN EL VIEW QUE SE ASOCIE SU ID.
         */
        switch (item.getItemId()) {
            //CASO BOTON CERRAR SESIÓN
            case R.id.btMenuItemLogOut:
                cerrarSesion();
                return true;

            //CASO BOTON INICIO
            case R.id.btMenuItemHome:
                startActivity(new Intent(this, MainActivity.class));
                return true;

            //CASO BOTON PERFIL
            case R.id.btMenuItemProfile:
                startActivity(new Intent(this, ProfileActivity.class));
                return true;

            //CASO BOTON SOBRE NOSOTROS
            case R.id.btMenuItemAbout:
                startActivity(new Intent(this, AboutUsActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }//FIN onOptionsItemSelected

    private void cerrarSesion() {
        /**
         * MÉTODO PARA CERRAR SESIÓN
         */

        //SE CIERRA SESIÓN
        mAuth.signOut();

        //REDIRECCIÓN A LA PÁGINA DE LOGIN
        startActivity(new Intent(WorkoutsActivity.this, SignInActivity.class));
        finish();
    }//FIN cerrarSesion
}//FIN WorkoutsActivity
