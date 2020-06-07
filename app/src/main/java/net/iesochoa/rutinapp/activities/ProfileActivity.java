package net.iesochoa.rutinapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.iesochoa.rutinapp.R;

public class ProfileActivity extends AppCompatActivity {

    //VARIABLES CONTENEDORAS
    private TextView tvNameProfile;
    private TextView tvSubNameProfile;
    private TextView tvEmailProfile;

    //OBJETO FIREBASE AUTH PARA LA IDENTIFICACIÓN DE FIREBASE
    private FirebaseAuth mAuth;

    //OBJETO FIREBASE PARA EL MANEJO DE LA BASE DE DATOS REALTIME
    private DatabaseReference mDatabase;

    //VARIABLES DE LOS DATOS QUE VAMOS A REGISTRAR
    private String name = "";
    private String email = "";
    private String subName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //DECLARACIÓN DE REFERENCIA A LA BD
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //INSTANCIAS DE VIEWS
        tvNameProfile = (TextView) findViewById(R.id.tvNameProfile);
        tvSubNameProfile = (TextView) findViewById(R.id.tvSubNameProfile);
        tvEmailProfile = (TextView) findViewById(R.id.tvEmailProfile);

        getDetailsProfile();

    }

    void getDetailsProfile(){

        /**
         * MÉTODO QUE OBTIENE LOS DATOS DE FIREBASE
         */

        //REFERENCIA AL NODO EJERCICIOS DE FIREBASE
        mDatabase.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //SI EL NODO HIJO EXISTE
                if(dataSnapshot.exists()){
                    //RECORREMOS CON UN FOREACH TODOS LOS OBJETOS USERS
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        //GUARDAMOS EN VARIABLES EL CONTENIDO DEL OBJETO
                        email = ds.child("Email").getValue().toString();
                        name = ds.child("Name").getValue().toString();
                        subName = ds.child("SubName").getValue().toString();

                    }
                        //SETEO DE VARIABLES EN OBJETOS VIEW
                        tvNameProfile.setText(name);
                        tvSubNameProfile.setText(subName);
                        tvEmailProfile.setText(email);

                }else{
                    Toast.makeText(ProfileActivity.this,"No se ha encontrado el nodo buscado en la BD.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });//FIN EVENTO addValueEventListener

    }//FIN getDetailsAboutUs
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

    private void cerrarSesion(){
        /**
         * MÉTODO PARA CERRAR SESIÓN
         */

        //SE CIERRA SESIÓN
        mAuth.signOut();

        //REDIRECCIÓN A LA PÁGINA DE LOGIN
        startActivity(new Intent(ProfileActivity.this, SignInActivity.class));
        finish();
    }//FIN cerrarSesion
}
