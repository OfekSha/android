package com.example.ex6;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

public class ExitDialogFrag extends DialogFragment {

    int mNum;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    static ExitDialogFrag newInstance() {
        ExitDialogFrag f = new ExitDialogFrag();
        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setMessage("Are you sure?" )
                .setTitle("Exit")
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                               getActivity().finish();
                            }
                        }
                )
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        }
                )
                .create();
    }
}
