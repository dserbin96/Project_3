package com.example.dns.project_3.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dns.project_3.R;
import com.example.dns.project_3.ui.view.PhoneView;
import com.example.dns.project_3.usecase.PhoneUseCaseCallback;
import com.example.dns.project_3.presenter.PhonePresenter;
import com.example.dns.project_3.presenter.PhonePresenterInit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneFragment extends Fragment implements PhoneView {

    private final static String PREFERS_AUTHORIZATION = "Authorization";
    private final static String SETTING_PHONE = "Phone";

    @BindView(R.id.phone_value) EditText editText;
    @BindView(R.id.phone_button) Button button;
    private SharedPreferences preferences;
    private PhoneFragment.OnFragmentInteractionListener mListener;
    private PhonePresenter phonePresenter;
    private PhoneUseCaseCallback phoneUseCaseCollback;

    public static PhoneFragment newInstance() {
        return new PhoneFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phone, container, false);
        ButterKnife.bind(this, view);
        phonePresenter = new PhonePresenterInit(this);
        phoneUseCaseCollback = new PhoneUseCaseCallback();

        preferences = getContext().getSharedPreferences(PREFERS_AUTHORIZATION,Context.MODE_PRIVATE);
        editText.setText(preferences.getString(SETTING_PHONE,"0"));

        button.setOnClickListener(v -> phoneUseCaseCollback.execute(phonePresenter,
                editText.getText().toString()));
        return view;
    }

    public void onButtonPressed(String phone) {
        if (mListener != null) {
            mListener.onPhoneFragmentInteraction(phone);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof PhoneFragment.OnFragmentInteractionListener) {
            mListener = (PhoneFragment.OnFragmentInteractionListener) getActivity();
        } else {
            throw new RuntimeException(context
                   + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showError(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void show() {
        String phone = editText.getText().toString();
        if(phone.length() == 12) {
            SharedPreferences.Editor prefEditor = preferences.edit();
            prefEditor.putString(SETTING_PHONE,phone );
            prefEditor.apply();

            onButtonPressed(phone);
        }

        else {
            Toast.makeText(getContext(),"Введите правильно номер",Toast.LENGTH_SHORT).show();
        }
    }

    public interface OnFragmentInteractionListener {
        void onPhoneFragmentInteraction(String phone);
    }
}
