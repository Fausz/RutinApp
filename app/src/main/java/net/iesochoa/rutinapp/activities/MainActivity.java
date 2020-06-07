package net.iesochoa.rutinapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import net.iesochoa.rutinapp.R;

public class MainActivity extends AppCompatActivity {

    //OBJETOS VIEW MENÚ
    private Button btLogOutMenu;
    private Button btMenuItemHome;
    private Button btMenuItemProfile;
    private Button btMenuItemAbout;

    //OBJETOS VIEW MAIN
    private Button btWorkouts;
    private Button btNewWorkout;
    private Button btEditWorkout;
    private Button btDelWorkout;
    private ImageView ivIcSeeWorkouts;
    private ImageView ivIcNewWorkout;
    private ImageView ivIcEditWorkout;
    private ImageView ivIcDelWorkout;
    private ImageView ivLogoMain;

    //OBJETO FIREBASE AUTH PARA LA IDENTIFICACIÓN DE FIREBASE
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INSTANCIA DE FIREBASE DE LOGEO
        mAuth = FirebaseAuth.getInstance();

        //INSTANCIA DE VIEWS DEL MENÚ
        btLogOutMenu = (Button) findViewById(R.id.btMenuItemLogOut);
        btMenuItemHome = (Button) findViewById(R.id.btMenuItemHome);
        btMenuItemProfile = (Button) findViewById(R.id.btMenuItemProfile);
        btMenuItemAbout = (Button) findViewById(R.id.btMenuItemAbout);

        //INSTANCIA DE VIEWS DEL MAIN
        btLogOutMenu = (Button) findViewById(R.id.btMenuItemLogOut);
        btWorkouts = (Button) findViewById(R.id.btWorkoutsMain);
        btNewWorkout = (Button) findViewById(R.id.btNewWorkoutMain);
        btEditWorkout = (Button) findViewById(R.id.btEditWorkoutMain);
        btDelWorkout = (Button) findViewById(R.id.btDelWorkoutMain);
        ivLogoMain = (ImageView) findViewById(R.id.ivLogoMain);
        ivIcNewWorkout = (ImageView) findViewById(R.id.ivIcNewWorkout);
        ivIcEditWorkout = (ImageView) findViewById(R.id.ivIcEditWorkout);
        ivIcDelWorkout = (ImageView) findViewById(R.id.ivIcDelWorkout);
        ivIcSeeWorkouts = (ImageView) findViewById(R.id.ivIcSeeWorkouts);

        //EVENTO ONCLICK DEL BOTON EJERCICIOS
        btWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //REDIRECCIÓN A LA PÁGINA DE EJERCICIOS
                startActivity(new Intent(MainActivity.this, WorkoutsActivity.class));

            }
        });


        //EVENTO ONCLICK DEL BOTON CREAR RUTINA
        btNewWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"Función en mantenimiento, disculpa las molestias.",Toast.LENGTH_SHORT).show();

            }
        });


        //EVENTO ONCLICK DEL BOTON MODIFICAR RUTINA
        btEditWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"Función en mantenimiento, disculpa las molestias.",Toast.LENGTH_SHORT).show();

            }
        });

        //EVENTO ONCLICK DEL BOTON ELIMINAR RUTINA
        btDelWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"Función en mantenimiento, disculpa las molestias.",Toast.LENGTH_SHORT).show();

            }
        });
    }

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
                case R.id.btMenuItemLogOut:
                    cerrarSesion();
                    return true;

                //CASO BOTON INICIO
                case R.id.btMenuItemHome:
                    startActivity(new Intent(this, MainActivity.class));
                    return true;

                //CASO BOTON PERFIL
                case R.id.btMenuItemProfile:
                    Toast.makeText(MainActivity.this,"Función en mantenimiento, disculpa las molestias.",Toast.LENGTH_SHORT).show();
                    return true;

                //CASO BOTON SOBRE NOSOTROS
                case R.id.btMenuItemAbout:
                    Toast.makeText(MainActivity.this,"h en mantenimiento, disculpa las molestias.",Toast.LENGTH_SHORT).show();
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
