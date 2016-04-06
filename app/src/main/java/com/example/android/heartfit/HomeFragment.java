package com.example.android.heartfit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.heartfit.calculos.Calculos;
import com.example.android.heartfit.model.Person;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HomeFragment extends Fragment {

    private Realm realm;

    public static final String TAG = MainActivity.class.getName();

    private Calculos calculos;
    Person person;
    private TextView textViewMetabolismoBasal;
    private TextView textViewCaloriasDia;
    private TextView textViewIMC;
    private TextView textViewReferenciaIMC;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment

        person = realm.where(Person.class).findFirst();
        Log.i(TAG, person.getName() + ":" + person.getEmail() + "--" + person.getId());
        Log.i(TAG, person.getAge() + ":");

        calculos = new Calculos();

        textViewMetabolismoBasal = (TextView) view.findViewById(R.id.metabolismo_basal);
        textViewCaloriasDia = (TextView) view.findViewById(R.id.calorias_dia);
        textViewIMC = (TextView) view.findViewById(R.id.imc);
        textViewReferenciaIMC = (TextView) view.findViewById(R.id.referencia_imc);
        double metabolismo_basal = calculos.getBasal(person.getGender(), person.getWeight(), person.getHeight(), person.getAge());
        double calorias_dia = calculos.getCalDia(person.getActivity_level(),metabolismo_basal);
        double imc = calculos.getIMC(person.getWeight(),person.getHeight());

        String reeferencia_imc = calculos.getReferenciaIMC(imc);
        textViewMetabolismoBasal.setText(""+metabolismo_basal);
        textViewCaloriasDia.setText(""+calorias_dia);
        textViewIMC.setText(String.format("%.2f",imc));
        textViewReferenciaIMC.setText(reeferencia_imc);




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
            realm = Realm.getDefaultInstance();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // Remember to close the Realm instance when done with it.
        realm.close();
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
