package net.iesochoa.rutinapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        //DECLARACIÓN DE REFERENCIA A LA BD
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //OBTENEMOS REFERENCIAS
        mRecyclerView = (RecyclerView) findViewById(R.id.rvListWorkouts);

        //POSICIONAMOS LAS LISTAS UNA ENCIMA DE OTRA
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //MÉTODO QUE OBTIENE LOS OBJETOS WORKOUTS DE FIREBASE
        getWorkoutsFromFirebase();


    }//FIN onCreate
    private void getWorkoutsFromFirebase(){
        /**
         * MÉTODO QUE OBTIENE LOS DATOS DE FIREBASE
         */

        //REFERENCIA AL NODO EJERCICIOS DE FIREBASE
        mDatabase.child("Workouts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //SI EL NODO HIJO EXISTE
                if(dataSnapshot.exists()){

                    //RECORREMOS CON UN FOREACH TODOS LOS OBJETOS WORKOUTS
                    for(DataSnapshot ds : dataSnapshot.getChildren()){

                        //GUARDAMOS EN VARIABLES EL CONTENIDO DE CADA OBJETO
                        String name = ds.child("Name").getValue().toString();
                        String group = ds.child("Group").getValue().toString();
                        String description = ds.child("Description").getValue().toString();
                        String img = ds.child("Img").getValue().toString();

                        //SE AÑADE UN NUEVO OBJETO A LA LISTA
                        workoutsList.add(new Workouts(name,group,description,img));
                    }

                    //mAdapter = new WorkoutsAdapter(workoutsList, R.layout.item_workouts);

                    //INSTANCIA DEL ADAPTER
                    mAdapter = new WorkoutsAdapter(getApplicationContext(),workoutsList,R.layout.item_workouts);

                    //RECYCLER VIEW IMPLEMENTA EL ADAPTADOR
                    mRecyclerView.setAdapter(mAdapter);

                }else{
                    Toast.makeText(WorkoutsActivity.this,"No se ha encontrado el nodo buscado en la BD.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });//FIN EVENTO addValueEventListener
    }//FIN getWorkoutsFromFirebase
}//FIN WorkoutsActivity
