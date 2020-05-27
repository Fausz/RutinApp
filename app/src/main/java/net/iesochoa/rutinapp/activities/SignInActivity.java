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

import net.iesochoa.rutinapp.R;

public class SignInActivity extends AppCompatActivity {

    //OBJETOS VIEW
    private Button btLogInLogin;
    private Button btSignUpLogin;
    private EditText etEmailLogin;
    private EditText etPasswordLogin;

    //VARIABLES DE LOS DATOS QUE VAMOS A LOGEAR
    private String email = "";
    private String password = "";

    //OBJETO FIREBASE AUTH PARA LA IDENTIFICACIÓN DE FIREBASE
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //INSTANCIA DE FIREBASE
        mAuth = FirebaseAuth.getInstance();

        //INSTANCIAS DE VIEWS
        btLogInLogin = (Button) findViewById(R.id.btLogInLogin);
        btSignUpLogin = (Button) findViewById(R.id.btSignUpLogin);
        etEmailLogin = (EditText) findViewById(R.id.etEmailLogin);
        etPasswordLogin = (EditText) findViewById(R.id.etPasswordLogin);

        //EVENTO ONCLICK AL PULSAR EL BOTÓN INICIAR SESIÓN
        btLogInLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SE OBTIENE EL VALOR DE LOS EDIT TEXT DEL LOGIN
                email = etEmailLogin.getText().toString();
                password = etPasswordLogin.getText().toString();

                //SI LOS CAMPOS ESTAN COMPLETOS
                if(!email.isEmpty() && !password.isEmpty()){
                    //HACEMOS LOGIN
                    loginUser();

                //SINO MOSTRAMOS MENSAJE DE ERROR
                }else{
                    Toast.makeText(SignInActivity.this,"Debes de rellenar todos los campos.",Toast.LENGTH_SHORT).show();
                }
            }
        });//FIN btLogIn

        //SI SE PULSA AL BOTON REGISTRO
        btSignUpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //INICIAMOS LA ACTIVITY DE REGISTRO
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });//FIN btSignUp

    }//FIN onCreate
    private void loginUser(){
        /**
         * MÉTODO PARA EL LOGEO DEL USUARIO
         */

        //FIREBASE COMPROBARÁ QUE EL CORREO Y CONTRASEÑA SEAN CORRECTOS
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //SI EL LOGEO SE REALIZÓ CON ÉXITO
                if(task.isSuccessful()){
                    //INICIAMOS ACTIVIDAD PRINCIPAL CON EL LOGEO ACTIVO
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));

                    //SINO MOSTRAMOS MENSAJE DE ERROR
                }else{
                    Toast.makeText(SignInActivity.this,"No se ha podido Iniciar Sesión.",Toast.LENGTH_SHORT).show();
                }
            }
        });//FIN mAuth
    }//FIN loginUser

    @Override
    protected void onStart(){
        super.onStart();

        //SI EL USUARIO YA HA INICIADO SESION
        if(mAuth.getCurrentUser() !=null){

            //INICIAMOS EL MENÚ PRINCIPAL DE LA APP
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
            finish();
        }
    }
}//FIN CLASE SignInActivity
