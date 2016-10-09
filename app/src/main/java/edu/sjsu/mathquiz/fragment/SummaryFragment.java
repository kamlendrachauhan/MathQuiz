package edu.sjsu.mathquiz.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Created by I074841 on 10/9/2016.
 */


public class SummaryFragment extends DialogFragment {

    SummaryFragmentListener sListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Total Score : " + QuestionFragment.currentQuizState.getScore());
        builder.setTitle("Quiz Summary");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // sListener.onDialogPositiveClick(SummaryFragment.this);
                Log.d("QuestionFragment", "Quiz Over");
                getActivity().finish();
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            sListener = (SummaryFragmentListener) childFragment;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(childFragment.toString()
                    + " must implement NoticeDialogListener");
        }
    }


    public interface SummaryFragmentListener {
        public void onDialogPositiveClick(DialogFragment dialog);
    }

}