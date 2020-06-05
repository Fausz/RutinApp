package net.iesochoa.rutinapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import net.iesochoa.rutinapp.R;
import net.iesochoa.rutinapp.models.Workouts;

public class WorkoutsDetailsActivity extends AppCompatActivity {

    private Workouts view;
    private TextView tvName;
    private TextView tvGroup;
    private TextView tvDescription;
    //private ImageView ivImg;

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

        //SETEO DEL OBJETO RECIBIDO
        view = (Workouts) getIntent().getSerializableExtra(EXTRA_MOSTRAR_DATOS);

        ////INSTANCIAS DE VIEWS
        tvName = (TextView) findViewById(R.id.tvNameWkDetails);
        tvGroup = (TextView) findViewById(R.id.tvGroupWkDetails);
        tvDescription = (TextView) findViewById(R.id.tvDescriptionWkDetails);
        //ivImg = (ImageView) findViewById(R.id.ivImgWkDetails);


        tvName.setText(view.getName());
        tvGroup.setText(view.getGroup());
        tvDescription.setText(view.getDescription());
        //ivImg.setImageResource(view.getImg());
    }//FIN onCreate
}//FIN CLASE WorkoutsDetailsActivity
