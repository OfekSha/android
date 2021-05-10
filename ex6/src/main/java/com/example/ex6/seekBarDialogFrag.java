package com.example.ex6;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ex5x.R;

import java.util.zip.Inflater;

public class seekBarDialogFrag extends DialogFragment implements  SeekBar.OnSeekBarChangeListener{
    View sb;
    int mNum;
    int	numberOfZeros=0;
    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    static seekBarDialogFrag newInstance(int zeros) {
        seekBarDialogFrag f = new seekBarDialogFrag();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("zeros", zeros);
        f.setArguments(args);
        return f;
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser)
        {
            numberOfZeros= progress/20;
            //TextView tv = (TextView) (getActivity().findViewById(R.id.sol));

            //if(!tv.getText().toString().equals(""))
                //solutionFormatter(masterResult);

            TextView  Etv = (TextView) (sb.findViewById(R.id.eTv));
            Etv.setText(String.format("Example %."+numberOfZeros+"f",123.0));

        }
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        numberOfZeros = getArguments().getInt("zeros");
        final LayoutInflater inflater = LayoutInflater.from(getActivity());
        sb = inflater.inflate(R.layout.my_seekbar, null);
        ((SeekBar)sb.findViewById(R.id.sb)).setOnSeekBarChangeListener(this);
        ((SeekBar)sb.findViewById(R.id.sb)).setProgress(numberOfZeros*20);

        sb.findViewById(R.id.subLayout).setPadding(300,0,0,0);

        TextView  Etv = (TextView) (sb.findViewById(R.id.eTv));
        Etv.setText(String.format("Example %."+numberOfZeros+"f",123.0));
        return new AlertDialog.Builder(getActivity())
                .setMessage("Set the numbers precision" )
                .setTitle("seekBar")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                ((MainActivity)getActivity()).getFragB().updateZeroNumbers(numberOfZeros);
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        }
                )
                .setView(sb)
                .create();


    }
}