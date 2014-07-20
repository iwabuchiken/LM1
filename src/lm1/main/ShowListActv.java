package lm1.main;

import lm1.utils.CONS;
import lm1.utils.Methods;
import lm1.utils.Methods_dlg;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ShowListActv extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actv_showlist);
//		setContentView(R.layout.actv_main_orig);
		
		this.setTitle(this.getClass().getName());
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
//		CONS..mLocationManager.removeUpdates(this);
		
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
//		Methods.confirm_quit(this, keyCode);
		
		this.onBackPressed();
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		/****************************
		 * memo
			****************************/
		this.finish();
		
		overridePendingTransition(0, 0);
		
	}//public void onBackPressed()

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		
		case R.id.menu_actvmain_db://---------------
			
			Methods_dlg.dlg_Db_Activity(this);
//			Methods.db_backup(this);
			
			break;// case R.id.main_opt_menu_backup_db

		case R.id.menu_settings://---------------
			
//			this._test();	// ClassCastException
			
			Methods.start_Activity_Pref(this);
//			Methods.db_backup(this);
			
			break;// case R.id.main_opt_menu_backup_db
			
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		

	}//protected void onStart()

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actv_main, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		
		// debug
		String msg_Toast = "list item";
		Toast.makeText(this, msg_Toast, Toast.LENGTH_SHORT).show();
		
		
		super.onListItemClick(l, v, position, id);
	}

}
