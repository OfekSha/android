package com.example.ex5x;

//import android.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FragA extends Fragment  {
	 FragAListener listener;
	 View saveView;
	 float num1=0,num2=0;


	@Override
	public void onAttach(@NonNull Context context) {
		try{
			this.listener = (FragAListener)context;
		}catch(ClassCastException e){
			throw new ClassCastException("the class " +
					context.getClass().getName() +
					" must implements the interface 'FragAListener'");
		}
		super.onAttach(context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.frag_a, container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		//view.findViewById(R.id.button1).setOnClickListener(this);
		saveView=view;

		// ------- button Listeners-----
		//plus
		view.findViewById(R.id.button4).setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
			plusClicked(arg0);
			}
		});
		//minus
		view.findViewById(R.id.button3).setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
			minusClicked(arg0);
			}
		});
		//multi
		view.findViewById(R.id.button2).setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
			multiClicked(arg0);
			}
		});
		//div
		view.findViewById(R.id.button).setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
			divClicked(arg0);
			}
		});
		// ------- button Listeners-----



		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}



	public interface FragAListener{
		public void OnClickEvent(float op1,float op2,String operation);
	}
	
	
	//-------------------Buttons

	public void plusClicked(View v) {
		hideSoftKeyboard();
		EditText et = (EditText) (saveView.findViewById(R.id.op1));
		try {
			 num1 = Float.valueOf(et.getText().toString());
			et = (EditText) (saveView.findViewById(R.id.op2));
			 num2 = Float.valueOf(et.getText().toString());
			listener.OnClickEvent(num1,num2,"+");

		} catch (Exception e) {
			toastMsg("missing number");
		}
	}


	// calculator methods:

	public void minusClicked(View v) {
		hideSoftKeyboard();
		EditText et = (EditText) (saveView.findViewById(R.id.op1));
		try {
			 num1 = Float.valueOf(et.getText().toString());
			et = (EditText) (saveView.findViewById(R.id.op2));
			 num2 = Float.valueOf(et.getText().toString());

			listener.OnClickEvent(num1,num2,"-");


		} catch (Exception e) {
			toastMsg("missing number");
		}
	}

	public void multiClicked(View v) {
		hideSoftKeyboard();
		EditText et = (EditText) (saveView.findViewById(R.id.op1));
		try {
			 num1 = Float.valueOf(et.getText().toString());
			et = (EditText) (saveView.findViewById(R.id.op2));
			 num2 = Float.valueOf(et.getText().toString());
			listener.OnClickEvent(num1,num2,"*");

		} catch (Exception e) {
			toastMsg("missing number");
		}
	}

	public void divClicked(View v) {
		hideSoftKeyboard();
		EditText et = (EditText) (saveView.findViewById(R.id.op1));
		try {
			 num1 = Float.valueOf(et.getText().toString());
			et = (EditText) (saveView.findViewById(R.id.op2));
			 num2 = Float.valueOf(et.getText().toString());
			Float res= num1 / num2;
			if (res.isInfinite() || res.isNaN()) {

				toastMsg("Divide exception: "+res.toString());
			}
			else {
				//send to frag
				listener.OnClickEvent(num1,num2,"/");
			}
		} catch (Exception e) {
			toastMsg("missing number");
		}



	}
	// for hide the keyboard (use after call onclick button methods)
	public void hideSoftKeyboard() {
		InputMethodManager inputMethodManager =
				(InputMethodManager) getActivity().getSystemService(
						Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(
				getActivity().findViewById(android.R.id.content).getWindowToken(), 0);
	}
	private void toastMsg(String msg){
		Toast.makeText( getActivity(), msg, Toast.LENGTH_SHORT).show();
	}

	//-------------------Butoons END


	
	
}
