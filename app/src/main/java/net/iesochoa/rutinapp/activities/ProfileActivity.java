package net.iesochoa.rutinapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import net.iesochoa.rutinapp.R;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private EditText etAge;
    private EditText etHeight;
    private EditText etWeight;
    private RadioGroup rgSex;
    private RadioButton rbMan;
    private RadioButton rbWoman;
    private Button btGuardar;

    //VARIABLES DE LOS DATOS QUE VAMOS A REGISTRAR
    private String name = "";
    private String email = "";
    private String password = "";
    private String sex = "";
    private String age = "";
    private String height = "";
    private String weight = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etAge = (EditText) findViewById(R.id.etAge);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etWeight = (EditText) findViewById(R.id.etWeight);
        rgSex = (RadioGroup) findViewById(R.id.rgSex);
        rbMan = (RadioButton) findViewById(R.id.rbMan);
        rbWoman = (RadioButton) findViewById(R.id.rbWoman);
        btGuardar = (Button) findViewById(R.id.btGuardar);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            /**
             * Evento que se usa al hacer click en el boton registro
             * @param view
             */
            @Override
            public void onClick(View view) {

                //SE ASIGNA EL VALOR DE LOS EDIT TEXT A LAS VARIABLES CREADAS AL PRINCIPIO
                age = etAge.getText().toString();
                height = etHeight.getText().toString();
                weight = etWeight.getText().toString();


/*
                //SI NINGÚN CAMPO DEL REGISTRO NO ESTA VACIO SE PROCEDE
                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){

                    if(password.length() >=6){
                        //SI EL USUARIO INTRODUCE 6 CARACTERES O MÁS SE PROCEDE AL REGISTRO
                        registerUser();
                    }else{
                        //SI NO HAY ALMENOS 6 CARACTERES NO SERÁ VALIDA LA CONTRASEÑA
                        Toast.makeText(ProfileActivity.this,"La contraseña tiene que tener almenos 6 caracteres",Toast.LENGTH_SHORT).show();
                    }
                    //SI HAY UN CAMPO DE REGISTRO VACÍO SE MUESTRA ERROR
                }else{
                    Toast.makeText(ProfileActivity.this,"Debes de rellenar todos los campos.",Toast.LENGTH_SHORT).show();
                }
                */

            }

        });//FIN EVENTO ONCLICK DE btGuardar



        Map<String,Object> map = new HashMap<>();

        //SE ASIGNAN LOS VALORES AL MAPA
        map.put("name", name);
        map.put("email", email);
        map.put("password", password);
        map.put("sex", sex);
        map.put("age", age);
        map.put("height", height);
        map.put("weight", weight);
    }
}
