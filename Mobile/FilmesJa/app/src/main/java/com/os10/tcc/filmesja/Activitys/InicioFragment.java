package com.os10.tcc.filmesja.Activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.os10.tcc.filmesja.Model.MDCatalogo;
import com.os10.tcc.filmesja.R;
import com.os10.tcc.filmesja.control.ControlCatalogo;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InicioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment {

    public static String[] IDMidia;
    public static Bitmap[] ImagemMidia;
    public static String[] IDCategoria;

    public static MDCatalogo[] mdCatalogos;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static GridLayout gridLayoutFilmes;
    private static ImageView[] imgs;

    private static Activity view;

    public static EditText txtPesquisar;

    private static String IDConta;

    private OnFragmentInteractionListener mListener;

    public InicioFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            IDConta = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        gridLayoutFilmes = (GridLayout) view.findViewById(R.id.gridLayoutFilmes);

        this.view = getActivity();

        ControlCatalogo c = new ControlCatalogo();
        c.CarregarTopMidia((AppCompatActivity) getActivity());

        txtPesquisar = (EditText) view.findViewById(R.id.txtPesquisarMidia);

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public static void ConfigurarTopMidia(){
        int total = IDMidia.length;
        int column = 3;
        int row = total / column;

        imgs = new ImageView[total];

        gridLayoutFilmes.removeAllViews();

        gridLayoutFilmes.setColumnCount(column);
        gridLayoutFilmes.setRowCount(row + 1);
        for(int i =0, c = 0, r = 0; i < total; i++, c++)
        {
            if(c == column)
            {
                c = 0;
                r++;
            }
            ImageView oImageView = new ImageView(gridLayoutFilmes.getContext());

            GridLayout.LayoutParams param =new GridLayout.LayoutParams();

            Display display = view.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

            int tamanhoWidthCada = width / 3;
            int tamanhoHeightCada = height / 3;

            param.height = tamanhoHeightCada;
            param.width = tamanhoWidthCada;

            param.rightMargin = 5;
            param.topMargin = 5;
            param.setGravity(Gravity.CENTER);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            oImageView.setLayoutParams (param);
            gridLayoutFilmes.addView(oImageView);
            imgs[i] = oImageView;
        }


        for(int i = 0; i < ImagemMidia.length; i++) {
            if (ImagemMidia[i] != null) {
                imgs[i].setId(i);
                imgs[i].setImageBitmap(Bitmap.createScaledBitmap(ImagemMidia[i], 600, 600, true));
                imgs[i].setOnClickListener(clickFilme);
            }
        }
    }

    static View.OnClickListener clickFilme = new View.OnClickListener() {
        @Override
        public void onClick(View viewk) {
            Intent i = new Intent(view.getBaseContext(), ExibeFilmeActivity.class);
            MDCatalogo mdCatalogo = new MDCatalogo();

            mdCatalogo.setImage(mdCatalogos[viewk.getId()].getImage());
            mdCatalogo.setDefaultNome(mdCatalogos[viewk.getId()].getDefaultNome());
            mdCatalogo.setAno(2010);
            mdCatalogo.setDefaultSinopse(mdCatalogos[viewk.getId()].getDefaultSinopse());
            mdCatalogo.setIDCat(Integer.parseInt(IDMidia[viewk.getId()]));

            ExibeFilmeActivity.mdCatalogo = mdCatalogo;
            ExibeFilmeActivity.IDConta = IDConta;
            view.startActivity(i);
        }
    };
}
