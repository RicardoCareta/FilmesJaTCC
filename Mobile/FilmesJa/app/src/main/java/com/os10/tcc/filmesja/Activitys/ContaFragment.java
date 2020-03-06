package com.os10.tcc.filmesja.Activitys;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.os10.tcc.filmesja.R;
import com.os10.tcc.filmesja.StaticValues.StaticIdiomas;
import com.os10.tcc.filmesja.control.ControlConta;
import com.os10.tcc.filmesja.control.ControlIdioma;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_IMG_PERFIL = "imgPerfil";
    private static final String ARG_ID = "idConta";
    private static final String ARG_DATA = "data";

    private EditText txtNome;
    private Button txtData;
    private Button btnSalvar;
    private Button btnExcluirConta;

    private String data;

    private ImageView imgPerfil;

    private static Spinner slcIdioma;
    private static Context context;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String baseCodeImage;
    private String idConta;

    private OnFragmentInteractionListener mListener;

    public ContaFragment() {

    }

    public static ContaFragment newInstance(String param1, String param2, String param3, String dataParam) {
        ContaFragment fragment = new ContaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_IMG_PERFIL, param2);
        args.putString(ARG_ID, param3);
        args.putString(ARG_DATA, dataParam);
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            baseCodeImage = getArguments().getString(ARG_IMG_PERFIL);
            idConta = getArguments().getString(ARG_ID);
            data = getArguments().getString(ARG_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conta, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){



        txtNome = (EditText) getView().findViewById(R.id.txtNome);
        txtNome.setText(mParam1);

        txtData = (Button) getView().findViewById(R.id.txtDataConta);
        imgPerfil = (ImageView) getView().findViewById(R.id.imgPerfilConta);

        btnSalvar = (Button) getView().findViewById(R.id.btnSalvarConta);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickSalvar();
            }
        });

        btnExcluirConta = (Button) getView().findViewById(R.id.btnExcluirConta);
        btnExcluirConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickExcluir();
            }
        });

        if(baseCodeImage != null){

            byte[] lista = Base64.decode(baseCodeImage, Base64.DEFAULT);

            Bitmap imageBitmap = BitmapFactory.decodeByteArray(lista, 0, lista.length);
            RoundedBitmapDrawable imageRedonda = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
            //imageRedonda.setCornerRadius(200);
            imageRedonda.setCircular(true);

            imgPerfil.setImageDrawable(imageRedonda);
        }

        slcIdioma = (Spinner) getView().findViewById(R.id.cbmIdioma);

        context = getContext();
        ControlIdioma controlIdioma = new ControlIdioma();
        controlIdioma.CarregarIdioma((AppCompatActivity) getActivity(), ControlIdioma.CONTA_FRAGMENT);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Log.d("TCC_LOG", data);

        final Calendar myCalendar = Calendar.getInstance();
        try {
            myCalendar.setTime(sdf.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);

                updateLabel(myCalendar);
            }
        };

        updateLabel(myCalendar);


        txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    private void ClickSalvar(){
       // ControlConta
    }

    private void ClickExcluir(){
        ControlConta c = new ControlConta();
        c.ExcluirConta(idConta, (AppCompatActivity) getActivity());
    }

    public static void CarregarComboIdiomas(){

        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();

        for(int i = 0; i < StaticIdiomas.ListaIdiomas.length; i++){
            list.add(StaticIdiomas.ListaIdiomas[i].Nome);
        }

        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        slcIdioma.setAdapter(adapter);
    }



    private void updateLabel(Calendar myCalendar){
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        txtData.setText(sdf.format(myCalendar.getTime()));
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
