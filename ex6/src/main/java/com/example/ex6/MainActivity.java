package com.example.ex6;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex5x.R;

public class MainActivity extends AppCompatActivity implements FragA.FragAListener{
	Menu menu;
	FragB fragB;
	int numberOfZeros=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.menu=menu;
		getMenuInflater().inflate(R.menu.menu,menu);
		if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT)
		{
			menu.getItem(1).setVisible(true);
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId())
		{
			case R.id.exit:
				ExitDialogFrag.newInstance().show(getSupportFragmentManager(), "dialog");
				return true;
			case R.id.settings:
				seekBarDialogFrag.newInstance(fragB.numberOfZeros).show(getSupportFragmentManager(), "dialog");

				return true;
			default:
				return super.onOptionsItemSelected(item);
		}


	}

	@Override
	public void OnClickEvent(float f1, float f2 ,String s) {

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
	public Menu getMenu(){
		return menu;
	}
	public FragB getFragB(){
		return fragB;
	}
}
