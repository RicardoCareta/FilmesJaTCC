package com.os10.tcc.filmesja.Activitys;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.os10.tcc.filmesja.Model.MDCatalogo;
import com.os10.tcc.filmesja.Model.MDFavorito;
import com.os10.tcc.filmesja.R;
import com.os10.tcc.filmesja.control.ControlFavoritos;
import com.os10.tcc.filmesja.util.CONS;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavoritoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavoritoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritoFragment extends Fragment {

    public static MDFavorito[] mdFavoritos;

    public static String IDConta;


    private static ImageView[] imgs;
    private static GridLayout gridLayout;


    public static ImageView imageBase;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static Activity view;

    private OnFragmentInteractionListener mListener;

    public FavoritoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoritoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritoFragment newInstance(String param1, String param2) {
        FavoritoFragment fragment = new FavoritoFragment();
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
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ControlFavoritos f = new ControlFavoritos();
        f.CarregarFavoritos(FulanoActivity.ID, (AppCompatActivity) getActivity());

        this.view = getActivity();
        //Log.d("TCCCC", "começou o outro");

        gridLayout = (GridLayout)view.findViewById(R.id.gridLayout);

        gridLayout.removeAllViews();
    }

    public static void ConfigurarFavoritos(){
        int total = mdFavoritos.length;
        int column = 3;
        int row = total / column;

        imgs = new ImageView[total];

        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(row + 1);
        for(int i =0, c = 0, r = 0; i < total; i++, c++)
        {
            if(c == column)
            {
                c = 0;
                r++;
            }
            ImageView oImageView = new ImageView(gridLayout.getContext());
            //oImageView.setImageResource(R.drawable.ic_menu_camera);

            LayoutParams param =new LayoutParams();

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
            gridLayout.addView(oImageView);
            imgs[i] = oImageView;
        }


        for(int i = 0; i < mdFavoritos.length; i++) {
            Log.d(CONS.TAG, mdFavoritos[i].getMdCatalogo().getDefaultNome());
            if (mdFavoritos[i].getMdCatalogo().getImage() != null) {
                imgs[i].setId(i);
                imgs[i].setImageBitmap(Bitmap.createScaledBitmap(mdFavoritos[i].getMdCatalogo().getImage(), 600, 600, true));
            }
        }

        for(int i = 0; i < mdFavoritos.length; i++) {
            imgs[i].setOnClickListener(clickFilme);
        }

    }

   static View.OnClickListener clickFilme = new View.OnClickListener() {
        @Override
        public void onClick(View viewk) {
            Intent i = new Intent(view.getBaseContext(), ExibeFilmeActivity.class);
            MDCatalogo mdCatalogo = new MDCatalogo();

            mdCatalogo.setImage(mdFavoritos[viewk.getId()].getMdCatalogo().getImage());
            mdCatalogo.setDefaultNome(mdFavoritos[viewk.getId()].getMdCatalogo().getDefaultNome());
            mdCatalogo.setAno(2010);
            mdCatalogo.setDefaultSinopse(mdFavoritos[viewk.getId()].getMdCatalogo().getDefaultSinopse());
            mdCatalogo.setIDCat(Integer.parseInt(mdFavoritos[viewk.getId()].getIDFavorito()));
            ExibeFilmeActivity.mdCatalogo = mdCatalogo;

            ExibeFilmeActivity.IDConta = IDConta;

            view.startActivity(i);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorito, container, false);
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
