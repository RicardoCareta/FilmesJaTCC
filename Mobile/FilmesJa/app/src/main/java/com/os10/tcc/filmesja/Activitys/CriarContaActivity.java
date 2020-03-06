package com.os10.tcc.filmesja.Activitys;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.os10.tcc.filmesja.R;
import com.os10.tcc.filmesja.StaticValues.StaticIdiomas;
import com.os10.tcc.filmesja.control.ControlConta;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CriarContaActivity extends AppCompatActivity {

    public static int IDUsuario = 0;

    private static int RESULT_LOAD_IMAGE = 1;
    private static final int CAMERA_REQUEST = 1888;

    private static final int MY_PERMISSIONS_REQUEST_READ_STORAGE = 777;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 123;


    private ImageView imageView;

    private EditText txtNome;

    private Button txtData;

    private Spinner cbmIdiomaCriarConta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Não isso não é uma gambiarra, só uma maneira nada usual de fazer um botão desaparecer.
        fab.setVisibility(View.INVISIBLE);

        Log.d("EUAMOAG", String.valueOf(IDUsuario));

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_STORAGE);

        }


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_STORAGE);

        }

        Button btnCarregarImagem = (Button) findViewById(R.id.btnEscolherFoto);
        btnCarregarImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarregarImagem_Click();

            }
        });

        Button btnCriarConta = (Button) findViewById(R.id.btnCriarConta);
        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CriarConta_Click();
            }
        });

        txtNome = (EditText) findViewById(R.id.txtCriarContaNome);

        imageView = (ImageView) findViewById(R.id.imgCriarContaFoto);

        cbmIdiomaCriarConta = (Spinner) findViewById(R.id.cbmIdiomaCriarConta);

        CarregarComboIdiomas();

        txtData = (Button) findViewById(R.id.btnDataNasc);



        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);

                updateLabel(myCalendar);
            }
        };

        final long maxDate = Calendar.getInstance().getTime().getTime();

        txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog pickerDialog = new DatePickerDialog(view.getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                pickerDialog.getDatePicker().setMaxDate(maxDate);
                pickerDialog.show();
            }
        });
    }

    private void updateLabel(Calendar myCalendar){
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        txtData.setText(sdf.format(myCalendar.getTime()));
    }

    private void CriarConta_Click(){
        if (txtNome.getText().toString().trim().equals("")){
            ShowMessageError("Por favor preencha o nome");
            return;
        }

        if (txtData.getText().toString().equals("")){
            ShowMessageError("Por favor preencha a data de nascimento");
            return;
        }

        if(cbmIdiomaCriarConta.getSelectedItem().toString().equals("")){
            ShowMessageError("Por favor escolha um idioma");
            return;
        }

        Drawable drawable = imageView.getDrawable();
        BitmapDrawable bitmapDrawable;
        Bitmap bitmap;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();


        try{
            bitmapDrawable = ((BitmapDrawable) drawable);
            bitmap = bitmapDrawable.getBitmap();
            if(bitmap == null){
                ShowMessageError("Por favor selecione uma imagem");
                return;
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        }catch(Exception ex){
            Log.e("EUAMOAD", ex.getMessage());
            return;
        }

        byte[] imageDate = stream.toByteArray();
        String base64Image = Base64.encodeToString(imageDate, Base64.DEFAULT);

        int idIdioma = StaticIdiomas.ListaIdiomas[cbmIdiomaCriarConta.getSelectedItemPosition()].ID;

        ControlConta controlConta = new ControlConta();
        controlConta.InserirConta(txtNome.getText().toString(), base64Image, txtData.getText().toString(), String.valueOf(idIdioma), IDUsuario, this);
    }

    private void ShowMessageError(String mensagem, String titulo){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(titulo)
                .setMessage(mensagem)
                .show();
    }

    private void ShowMessageError(String mensagem){
        ShowMessageError(mensagem, "");
    }


    private void CarregarComboIdiomas(){

        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();

        for(int i = 0; i < StaticIdiomas.ListaIdiomas.length; i++){
            list.add(StaticIdiomas.ListaIdiomas[i].Nome);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cbmIdiomaCriarConta.setAdapter(adapter);
    }

    private void CarregarImagem_Click(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Escolha")
                .setMessage("Você deseja tirar ou escolher uma foto?")
                .setPositiveButton("Tirar", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }

                })
                .setNegativeButton("Escolher", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, RESULT_LOAD_IMAGE);
                    }

                })
                .show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }

}
