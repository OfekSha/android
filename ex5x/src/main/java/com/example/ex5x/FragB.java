package com.example.ex5x;

//import android.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class FragB extends Fragment implements  SeekBar.OnSeekBarChangeListener {
	TextView tvValue;
	int	numberOfZeros=0;
	static int myInt=0;
	float masterResult;
	float n1,n2;
	String operation;





	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.frag_b, container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		((SeekBar)getActivity().findViewById(R.id.sb)).setOnSeekBarChangeListener(this);
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		TextView tv = (TextView) (getActivity().findViewById(R.id.sol));
		tv.setText(""+myInt);
		super.onActivityCreated(savedInstanceState);
	}

	//the activity informs fragB about new click in fragA
	public void updateStuff(float n1, float n2 , String operation){
		this.n1=n1;
		this.n2=n2;
		this.operation=operation;
		switch (operation)
		{
			case ("+"):
				masterResult=n1+n2;
				break;
			case ("-"):
				masterResult=n1-n2;
				break;
			case ("/"):
				masterResult=n1/n2;
				break;
			case ("*"):
				masterResult=n1*n2;
				break;
			default:
				return;
		}
		solutionFormatter(masterResult);

	}

	private  void solutionFormatter(float f)
	{
		TextView tv = (TextView) (getActivity().findViewById(R.id.sol));
		tv.setText(String.format("%."+numberOfZeros+"f %s %."+numberOfZeros+"f = %."+numberOfZeros+"f",n1,operation,n2,f));
	}



	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if(fromUser)
		{
			numberOfZeros= progress/20;
			TextView  tv = (TextView) (getActivity().findViewById(R.id.sol));

			if(!tv.getText().toString().equals(""))
				solutionFormatter(masterResult);

			TextView  Etv = (TextView) (getActivity().findViewById(R.id.eTv));
			Etv.setText(String.format("Example %."+numberOfZeros+"f",123.0));

		}
	}


	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}




}
