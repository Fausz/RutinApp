package net.iesochoa.rutinapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private Button btSignUp;
    private Button btLogIn;
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;

    //VARIABLES DE LOS DATOS QUE VAMOS A REGISTRAR
    private String name = "";
    private String email = "";
    private String password = "";

    //OBJETO FIREBASE AUTH PARA LA IDENTIFICACIÓN DE FIREBASE
    FirebaseAuth mAuth;

    //OBJETO FIREBASE PARA EL MANEJO DE LA BASE DE DATOS REALTIME
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        //INSTANCIAS DE FIREBASE
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //INSTANCIAS DE VIEWS
        btSignUp = findViewById(R.id.btSignUp);
        btLogIn = findViewById(R.id.btLogIn);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            /**
             * Evento que se usa al hacer click en el boton registro
             * @param view
             */
            @Override
            public void onClick(View view) {

                //SE ASIGNA EL VALOR DE LOS EDIT TEXT A LAS VARIABLES CREADAS AL PRINCIPIO
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                //SI NINGÚN CAMPO DEL REGISTRO NO ESTA VACIO SE PROCEDE
                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){

                    if(password.length() >=6){
                        //SI EL USUARIO INTRODUCE 6 CARACTERES O MÁS SE PROCEDE AL REGISTRO
                        registerUser();
                    }else{
                        //SI NO HAY ALMENOS 6 CARACTERES NO SERÁ VALIDA LA CONTRASEÑA
                        Toast.makeText(SignUpActivity.this,"La contraseña tiene que tener almenos 6 caracteres",Toast.LENGTH_SHORT).show();
                    }
                //SI HAY UN CAMPO DE REGISTRO VACÍO SE MUESTRA ERROR
                }else{
                    Toast.makeText(SignUpActivity.this,"Debes de rellenar todos los campos.",Toast.LENGTH_SHORT).show();
                }
            }

        });//FIN EVENTO ONCLICK DE btSignUp

    }//FIN onCreate

    private void registerUser(){
        /**
         *MÉTODO PARA REGISTRO DEL USUARIO
         */
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            /**
             * EVENTO QUE COMPRUEBA QUE SE EFECTUE LA VALIDACIÓN
             * @param task
             */
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //SI EL REGISTRO SE REALIZÓ CON ÉXITO
                if(task.isSuccessful()){

                    //SE CREA UN OBJETO TIPO MAPA DE VALORES
                    Map<String,Object> map = new HashMap<>();

                    //SE ASIGNAN LOS VALORES AL MAPA
                    map.put("name", name);
                    map.put("email", email);
                    map.put("password", password);

                    //OBTENEMOS LA ID DEL USUARIO QUE SE ACABA DE CREAR
                    String id = mAuth.getCurrentUser().getUid();

                    //ALMACENAMOS LOS DATOS EN LA BD DE FIREBASE MEDIANTE UN NODO HIJO, ASIGNANDOLE EL MAPA DE VALORES CON LA ID Y LOS VALORES DEL REGISTRO
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            //SI SE HAN GUARDADO LOS DATOS DE USUARIO
                            if(task2.isSuccessful()){
                                //REDIRIGIMOS A UNA ACTIVITY
                                startActivity(new Intent(SignUpActivity.this,ProfileActivity.class));
                                finish();
                            }else{
                                //SINO MOSTRAMOS MENSAJE DE ERROR------------------------------------------------------------------------------------------------------
                                Toast.makeText(SignUpActivity.this,"No se ha podido registrar este Usuario.",Toast.LENGTH_SHORT).show();
                                //------------------------------------------------------------------------------------------------------------------------------------------------------------
                            }
                        }
                    });

                }else{
                    //SINO SE MUESTRA MENSAJE AL USUARIO DICIENDO QUE NO SE PUDO REGISTRAR AL USUARIO
                    Toast.makeText(SignUpActivity.this,"No se ha podido registrar este Usuario.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}//FIN CLASE SignUpActivity
