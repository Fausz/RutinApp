package net.iesochoa.rutinapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.iesochoa.rutinapp.R;

public class AboutUsActivity extends AppCompatActivity {

    //OBJETO FIREBASE AUTH PARA LA IDENTIFICACIÓN DE FIREBASE
    private FirebaseAuth mAuth;

    //VARIABLES CONTENEDORAS
    private String address;
    private String center;
    private String course;
    private String description;
    private String name;
    private String numPhone;
    private String subName;

    //VARIABLES TIPO VIEW
    private TextView tvCenter;
    private TextView tvCurse;
    private TextView tvAddress;
    private TextView tvDescription;
    private TextView tvName;
    private TextView tvSubname;
    private TextView tvNum;

    //OBJETO FIREBASE PARA EL MANEJO DE LA BASE DE DATOS REALTIME
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //DECLARACIÓN DE REFERENCIA A LA BD
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //INSTANCIAS DE VIEWS
        tvCenter = (TextView) findViewById(R.id.tvCenterAboutUs);
        tvCurse = (TextView) findViewById(R.id.tvCurseAboutUs);
        tvAddress = (TextView) findViewById(R.id.tvAddressAboutUs);
        tvDescription = (TextView) findViewById(R.id.tvDescriptionAboutUs);
        //tvLocation = (TextView) findViewById(R.id.tvL);
        tvName = (TextView) findViewById(R.id.tvNameAboutUs);
        tvSubname = (TextView) findViewById(R.id.tvSubNameAboutUs);
        tvNum = (TextView) findViewById(R.id.tvNumAboutUs);

        //DECLARACIÓN DE REFERENCIA A LA BD
        mDatabase = FirebaseDatabase.getInstance().getReference();

        getDetailsAboutUs();
    }//FIN onCreate

    void getDetailsAboutUs(){

        /**
         * MÉTODO QUE OBTIENE LOS DATOS DE FIREBASE
         */

        //REFERENCIA AL NODO EJERCICIOS DE FIREBASE
        mDatabase.child("Creator").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //SI EL NODO HIJO EXISTE
                if(dataSnapshot.exists()){

                        //GUARDAMOS EN VARIABLES EL CONTENIDO DEL OBJETO
                        address = dataSnapshot.child("Address").getValue().toString();
                        center = dataSnapshot.child("Center").getValue().toString();
                        course = dataSnapshot.child("Course").getValue().toString();
                        description = dataSnapshot.child("Description").getValue().toString();
                        name = dataSnapshot.child("Name").getValue().toString();
                        numPhone = dataSnapshot.child("NumPhone").getValue().toString();
                        subName = dataSnapshot.child("SubName").getValue().toString();

                        //SETEO DE VARIABLES EN OBJETOS VIEW
                        tvName.setText(name);
                        tvSubname.setText(subName);
                        tvDescription.setText(description);
                        tvCurse.setText(course);
                        tvCenter.setText(center);
                        tvAddress.setText(address);
                        tvNum.setText(numPhone);


                }else{
                    Toast.makeText(AboutUsActivity.this,"No se ha encontrado el nodo buscado en la BD.",Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(AboutUsActivity.this, SignInActivity.class));
        finish();
    }//FIN cerrarSesion

}//FIN CLASE AboutUsActivity
