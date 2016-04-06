package com.example.android.heartfit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.techery.properratingbar.ProperRatingBar;
import io.techery.properratingbar.RatingListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VasosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class VasosFragment extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;
    private ProperRatingBar progressBarVasosConsumidos;
    private Button btnIncrementarVasos;


    public VasosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_vasos, container, false);
        progressBarVasosConsumidos = (ProperRatingBar) view.findViewById(R.id.progressBarVasosConsumidos);
        progressBarVasosConsumidos.setListener(new RatingListener() {
            @Override
            public void onRatePicked(ProperRatingBar properRatingBar) {
                
            }
        });
        btnIncrementarVasos = (Button) view.findViewById(R.id.btn_incrementar_vasos);
        btnIncrementarVasos.setOnClickListener(this);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public void incrementarVasos(){
        int oldValue = progressBarVasosConsumidos.getRating();
        Log.d("VasosFragment",""+oldValue);
        if(oldValue <=8){
            progressBarVasosConsumidos.setRating(oldValue++);

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_incrementar_vasos:
                incrementarVasos();
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
