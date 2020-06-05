package net.iesochoa.rutinapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import net.iesochoa.rutinapp.R;

public class MainActivity extends AppCompatActivity {

    //OBJETOS VIEW
    private Button btLogOutMenu;
    private Button btWorkouts;

    //OBJETO FIREBASE AUTH PARA LA IDENTIFICACIÓN DE FIREBASE
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INSTANCIA DE FIREBASE
        mAuth = FirebaseAuth.getInstance();

        //INSTANCIA DE VIEWS
        btWorkouts = (Button) findViewById(R.id.btWorkouts);

        //EVENTO ONCLICK DEL BOTON EJERCICIOS
        btWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //REDIRECCIÓN A LA PÁGINA DE EJERCICIOS
                startActivity(new Intent(MainActivity.this, WorkoutsActivity.class));

            }
        });
    }//FIN onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * MÉTODO PARA INFLAR EL MENÚ, ESTO AGREGA ELEMENTOS A LA BARRA DE ACCIÓN SI ESTÁ PRESENTE.
         */
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }//FIN onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * MÉTODO PARA ASOCIAR UN METODO ONCLICK EN EL VIEW QUE SE ASOCIE SU ID.
         */
        switch (item.getItemId()) {
            //CASO BOTON CERRAR SESIÓN
            case R.id.btLogOutMenu:
                cerrarSesion();
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
        startActivity(new Intent(MainActivity.this, SignInActivity.class));
        finish();
    }//FIN cerrarSesion


}//FIN CLASE MAIN ACTIVITY
