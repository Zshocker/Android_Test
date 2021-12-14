package com.example.myapplication;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.myapplication.databinding.FragmentFirstBinding;

import java.util.Locale;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);*/
                Calculate_Debit();
            }
        });
    }
    public void Calculate_Debit(){
        EditText Bp=(EditText) binding.BP;
        EditText SN=(EditText) binding.SN;
        String bp = Bp.getText().toString().toLowerCase();
        String sn = SN.getText().toString().toLowerCase();
        double Debit,se=Math.log((1+Double.parseDouble(sn.replaceAll("[^0-9]",""))));
        if(bp.contains("hz")||sn.contains("db"))
        {
            Debit=Double.parseDouble(bp.replaceAll("[^0-9]",""));
            if(bp.contains("k"))Debit*=1000;
            else if(bp.contains("m"))Debit*=1000000;
            else if(bp.contains("g"))Debit*=1000000000;
            Debit*=se;
        }else {
            Debit=Double.parseDouble(bp.replaceAll("[^0-9]",""))*se;
        }
        String EQ="bits/s";
        if(Debit>1000)
        {
            if(Debit>1000000){
                if(Debit>1000000000)
                {
                    Debit/=1000000000;
                    EQ="G"+EQ;
                }else{
                    Debit/=1000000;
                    EQ="M"+EQ;
                }
            }else{
                Debit/=1000;
                EQ="K"+EQ;
            }
        }
        binding.MyRes.setText(Double.toString(Debit)+" "+EQ);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}