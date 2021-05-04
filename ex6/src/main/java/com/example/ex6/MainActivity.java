package com.example.ex6;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex5x.R;

public class MainActivity extends AppCompatActivity implements FragA.FragAListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

	}
	@Override
	public void onSaveInstanceState(Bundle outState) {

		//super.onSaveInstanceState(outState);
	}


	@Override
	public void OnClickEvent(float f1, float f2 ,String s) {
		FragB fragB;
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
		{
			getSupportFragmentManager().beginTransaction()
					.setReorderingAllowed(true)
					.replace(R.id.fragContainer, FragB.class, null,"FRAGB")
					.addToBackStack(null)
					.commit();
			getSupportFragmentManager().executePendingTransactions();
			//.add(R.id.fragContainer, FragB.class, null,"FRAGB")
		}
		fragB = (FragB) getSupportFragmentManager().findFragmentByTag("FRAGB");
		fragB.updateStuff(f1,f2,s);
	}

}
