package net.iesochoa.rutinapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import net.iesochoa.rutinapp.R;
import net.iesochoa.rutinapp.models.Workouts;

public class WorkoutsDetailsActivity extends AppCompatActivity {

    //OBJETO WORKOUTS
    private Workouts view;

    //VARIABLES TIPO VIEW
    private TextView tvName;
    private TextView tvGroup;
    private TextView tvDescription;
    private ImageView ivImg;

    private FirebaseAuth mAuth;

    //VARIABLE GLOBAL CON LA CUAL SE RECIBE EL OBJETO A MOSTRAR EN ESTA ACTIVITY
    public static final String EXTRA_MOSTRAR_DATOS = "net.iessochoa.rutinapp.details";

    public WorkoutsDetailsActivity(){
        /**
         * CONSTRUCTOR POR DEFECTO
         */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts_details);

        //INSTANCIA DE FIREBASE DE LOGEO
        mAuth = FirebaseAuth.getInstance();
        //SETEO DEL OBJETO RECIBIDO
        view = (Workouts) getIntent().getSerializableExtra(EXTRA_MOSTRAR_DATOS);

        ////INSTANCIAS DE VIEWS
        tvName = (TextView) findViewById(R.id.tvNameWkDetails);
        tvGroup = (TextView) findViewById(R.id.tvGroupWkDetails);
        tvDescription = (TextView) findViewById(R.id.tvDescriptionWkDetails);
        ivImg = (ImageView) findViewById(R.id.ivImgWkDetails);

        //SETEO DE LOS VIEWS
        tvName.setText(view.getName());
        tvGroup.setText(view.getGroup());
        tvDescription.setText(view.getDescription());

        //GLIDE PARA LA CARGA DE IMAGEN A ESTA ACTIVIDAD DESDE EL OBJETO RECIBIDO EN EL VIEW DEL LAYOUT
        Glide.with(this).load(view.getImg()).into(ivImg);

    }//FIN onCreate

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
        startActivity(new Intent(WorkoutsDetailsActivity.this, SignInActivity.class));
        finish();
    }//FIN cerrarSesion
}//FIN CLASE WorkoutsDetailsActivity
