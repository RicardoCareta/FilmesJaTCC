package com.os10.tcc.filmesja.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.os10.tcc.filmesja.R;
import com.os10.tcc.filmesja.control.ControlCliente;
import com.os10.tcc.filmesja.control.ControlIdioma;

public class LoginActivity extends AppCompatActivity{

    //Txts
    private EditText txtEmail;
    private EditText txtSenha;

    //Btns
    private Button btnEntrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtSenha = (EditText) findViewById(R.id.txtSenha);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(clickEntrar);

        ControlIdioma controlIdioma = new ControlIdioma();
        controlIdioma.CarregarIdioma(this, 0);
    }

    View.OnClickListener clickEntrar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ControlCliente controlCliente = new ControlCliente();
            if(controlCliente.Logar(txtEmail.getText().toString(), txtSenha.getText().toString(),LoginActivity.this)){
                Toast.makeText(getBaseContext(), "Entrou", Toast.LENGTH_LONG).show();
            }else{
       //         Toast.makeText(getBaseContext(), "Exited", Toast.LENGTH_LONG).show();
            }
        }
    };
}
