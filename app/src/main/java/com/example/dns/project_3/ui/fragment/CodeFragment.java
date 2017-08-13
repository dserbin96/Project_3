package com.example.dns.project_3.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dns.project_3.R;
import com.example.dns.project_3.ui.view.CodeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CodeFragment extends Fragment implements CodeView {

    @BindView(R.id.pass) EditText code_edit;
    @BindView(R.id.code_button) Button button;

    public static final String PHONE = "phone";
    private String phone;

    private CodeFragment.OnFragmentInteractionListener mListener;

    public static CodeFragment newInstance(String phone) {
        CodeFragment codeFragment = new CodeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PHONE,phone);
        codeFragment.setArguments(bundle);
        return codeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null)
        {
            phone = getArguments().getString(PHONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_code, container, false);
        ButterKnife.bind(this,view);
        button.setOnClickListener(v -> onButtonPressed());
        return view;
    }


    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onCodeFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof CodeFragment.OnFragmentInteractionListener) {
            mListener = (CodeFragment.OnFragmentInteractionListener) getActivity();
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

    public interface OnFragmentInteractionListener {
        void onCodeFragmentInteraction();
    }
}