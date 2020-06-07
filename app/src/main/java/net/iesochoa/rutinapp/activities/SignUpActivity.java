package net.iesochoa.rutinapp.activities;

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

import net.iesochoa.rutinapp.R;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    //OBJETOS VIEW
    private Button btSignUp;
    private Button btSignIn;
    private EditText etName;
    private EditText etSubName;
    private EditText etEmail;
    private EditText etPassword1;
    private EditText etPassword2;

    //VARIABLES DE LOS DATOS QUE VAMOS A REGISTRAR
    private String name = "";
    private String email = "";
    private String password1 = "";
    private String password2 = "";
    private String subName = "";

    //OBJETO FIREBASE AUTH PARA LA IDENTIFICACIÓN DE FIREBASE
    FirebaseAuth mAuth;

    //OBJETO FIREBASE PARA EL MANEJO DE LA BASE DE DATOS REALTIME
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //INSTANCIAS DE FIREBASE
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //INSTANCIAS DE VIEWS
        btSignUp = (Button) findViewById(R.id.btSignUp);
        btSignIn = (Button) findViewById(R.id.btSignIn);
        etName = (EditText) findViewById(R.id.etNameLogUp);
        etSubName = (EditText) findViewById(R.id.etSubNameLogUp);
        etEmail = (EditText) findViewById(R.id.etEmailLogUp);
        etPassword1 = (EditText) findViewById(R.id.etPassword1LogUp);
        etPassword2 = (EditText) findViewById(R.id.etPassword2LogUp);

        //EVENTO ONCLICK AL PULSAR EL BOTON REGISTRO
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
                password1 = etPassword1.getText().toString();
                password2= etPassword2.getText().toString();
                subName = etSubName.getText().toString();


                //SI NINGÚN CAMPO DEL REGISTRO NO ESTA VACIO SE PROCEDE
                if(!name.isEmpty() && !email.isEmpty() && !password1.isEmpty() && !password2.isEmpty() && !subName.isEmpty()) {

                    if (password1.length() >= 6 || password2.length() >= 6) {
                        if(password1.equals(password2)){
                            //SI EL USUARIO INTRODUCE 6 CARACTERES O MÁS SE PROCEDE AL REGISTRO
                            registerUser();
                        } else {
                            Toast.makeText(SignUpActivity.this, "La contraseñas no son iguales.", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        //SI NO HAY ALMENOS 6 CARACTERES NO SERÁ VALIDA LA CONTRASEÑA
                        Toast.makeText(SignUpActivity.this, "La contraseña tiene que tener almenos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                    //SI HAY UN CAMPO DE REGISTRO VACÍO SE MUESTRA ERROR
                }else{
                    Toast.makeText(SignUpActivity.this,"Debes de rellenar todos los campos.",Toast.LENGTH_SHORT).show();
                }
            }

        });//FIN EVENTO ONCLICK DE btSignUp

        //EVENTO ONCLICK AL PULSAR EL BOTON DE ACCEDER A MI CUENTA
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //INICIAMOS ACTIVIDAD DE LOGEO
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });
    }//FIN onCreate

    private void registerUser(){
        /**
         *MÉTODO PARA EL REGISTRO DEL USUARIO
         */

        //FIREBASE COMPROBARÁ QUE EL CORREO Y CONTRASEÑA SEAN CORRECTOS
        mAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
                    map.put("Name", name);
                    map.put("SubName", subName);
                    map.put("Email", email);
                    map.put("Password", password1);

                    //OBTENEMOS LA ID DEL USUARIO QUE SE ACABA DE CREAR
                    String id = mAuth.getCurrentUser().getUid();

                    //ALMACENAMOS LOS DATOS EN LA BD DE FIREBASE MEDIANTE UN NODO HIJO, ASIGNANDOLE EL MAPA DE VALORES CON LA ID Y LOS VALORES DEL REGISTRO
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            //SI SE HAN GUARDADO LOS DATOS DE USUARIO CON EXITO
                            if(task2.isSuccessful()){
                                //REDIRIGIMOS A UNA ACTIVITY
                                //PONER PROFILEACTIVITY PARA TERMINAR EL REGISTRO---------------------------------------------------------------------------------------------------------
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                finish();
                            }else{
                                //SINO MOSTRAMOS MENSAJE DE ERROR
                                Toast.makeText(SignUpActivity.this,"No se ha podido registrar este Usuario en la BD.",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });//FIN mDatabase

                }else{
                    //SINO SE MUESTRA MENSAJE AL USUARIO DICIENDO QUE NO SE PUDO REGISTRAR AL USUARIO
                    Toast.makeText(SignUpActivity.this,"No se ha podido registrar este Usuario.",Toast.LENGTH_SHORT).show();
                }
            }//fin onComplete
        });//FIN mAuth
    }//FIN registerUser
    @Override
    protected void onStart(){
        super.onStart();

        //SI EL USUARIO YA HA INICIADO SESION
        if(mAuth.getCurrentUser() !=null){

            //INICIAMOS EL MENÚ PRINCIPAL DE LA APP
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        }
    }

}//FIN CLASE SignUpActivity
