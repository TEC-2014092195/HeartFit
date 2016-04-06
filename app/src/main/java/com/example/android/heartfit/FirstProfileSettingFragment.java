package com.example.android.heartfit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.android.heartfit.model.Person;
import com.jaredrummler.materialspinner.MaterialSpinner;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstProfileSettingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FirstProfileSettingFragment extends Fragment implements HomeFragment.OnFragmentInteractionListener,
        RadioGroup.OnCheckedChangeListener, View.OnClickListener{
    public static final String TAG = FirstProfileSettingFragment.class.getName();

    private OnFragmentInteractionListener mListener;
    private View view;
    private MainActivity mActivity;
    private Person tempPerson;
    private Person person;
    private Realm realm;
    private RadioGroup radioGroupGender;
    private EditText editTextAge;
    private EditText editTextWeight;
    private EditText editTextHeight;

    public FirstProfileSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first_profile_setting, container, false);

        person = realm.where(Person.class).findFirst();
        tempPerson = new Person();
        //Gender
        radioGroupGender = (RadioGroup) view.findViewById(R.id.radioGroupGender);
        editTextAge = (EditText) view.findViewById(R.id.editTextAge);
        editTextWeight = (EditText) view.findViewById(R.id.editTextWeight);
        editTextHeight = (EditText) view.findViewById(R.id.editTextHeight);
        MaterialSpinner spinner = (MaterialSpinner) view.findViewById(R.id.spinnerLevelActivity);
        spinner.setItems("Sedentario", "Actividad Ligera", "Actividad Moderada", "Actividad Intensa", "Actividad Muy Intensa");
        spinner.setSelectedIndex(0);
        tempPerson.setActivity_level("Sedentario");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                tempPerson.setActivity_level(item);
            }
        });

        Button btn_siguiente = (Button) view.findViewById(R.id.btn_siguiente);
        btn_siguiente.setOnClickListener(this);



        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        // Remember to close the Realm instance when done with it.
        realm.close();
    }

    public void callHomeFragment(){
        realm.beginTransaction();
        person.setGender(tempPerson.getGender());
        person.setAge(tempPerson.getAge());
        person.setActivity_level(tempPerson.getActivity_level());
        person.setWeight(tempPerson.getWeight());
        person.setHeight(tempPerson.getHeight());
        realm.commitTransaction();

        mActivity.enableDisableDrawer(DrawerLayout.LOCK_MODE_UNLOCKED);
        HomeFragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_main, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public boolean validateAreSupportedVaues(){
        int age = Integer.parseInt(editTextAge.getText().toString());
        double weight = Double.parseDouble(editTextWeight.getText().toString());
        double height = Double.parseDouble(editTextHeight.getText().toString());
        if (validateAge(age) && validateWeight(weight) && validateHeight(height)){
            tempPerson.setAge(age);
            tempPerson.setWeight(weight);
            tempPerson.setHeight(height);
            return true;
        }else{
            return false;
        }

    }

    public boolean validateHeight(double pHeight){
        int minHeight = 0;
        int maxHeight = 1000;
        if(minHeight < pHeight && pHeight < maxHeight){
            return true;
        }else {
            Snackbar.make(view, "Altura Inválida", Snackbar.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean validateWeight(double pWeight){
        int minWeight = 0;
        int maxWeight = 1000;
        if(minWeight < pWeight && pWeight < maxWeight){
            return true;
        }else {
            Snackbar.make(view, "Peso Inválido", Snackbar.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean validateAge(int pAge){
        int minAge = 0;
        int maxAge = 150;
        if(minAge < pAge && pAge < maxAge){
            return true;
        }else {
            Snackbar.make(view, "Edad Inválida", Snackbar.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean validateAreNotEmptyTextFields(){
        if(editTextAge.getText().toString().trim().equals("")){
            Snackbar.make(view, "La edad es un campo requerido", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (editTextWeight.getText().toString().trim().equals("")){
            Snackbar.make(view, "El peso es un campo requerido", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (editTextHeight.getText().toString().trim().equals("")){
            Snackbar.make(view, "La altura es un campo requerido", Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            mActivity = (MainActivity) context;
            realm = Realm.getDefaultInstance();
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.radioButtonMale:
                tempPerson.setGender("Hombre");
                break;

            case R.id.radioButtonFemale:
                tempPerson.setGender("Mujer");
                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_siguiente:
                if (validateAreNotEmptyTextFields()){
                    if(validateAreSupportedVaues()){
                        callHomeFragment();
                    }
                }
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
