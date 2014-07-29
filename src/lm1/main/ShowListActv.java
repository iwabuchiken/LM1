package lm1.main;

import lm1.adapters.Adp_Loc;
import lm1.listeners.STL;
import lm1.listeners.buttons.BO_CL;
import lm1.listeners.list.List_ILCL;
import lm1.utils.CONS;
import lm1.utils.DBUtils;
import lm1.utils.Methods;
import lm1.utils.Methods_dlg;
import lm1.utils.Tags;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

		_Setup_Set_LocationList();

		////////////////////////////////

		// listener

		////////////////////////////////
		_Setup_Listeners();
		
		
		super.onStart();
		

	}//protected void onStart()

	private void _Setup_Listeners() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// listview

		////////////////////////////////
		ListView lv = this.getListView();
		
//		lv.setTag(Tags.ListTags.actv_main_lv);
		lv.setTag(Tags.ListTags.ACTV_MAIN_LV);
		
		lv.setOnItemLongClickListener(new List_ILCL(this));
		
		////////////////////////////////

		// swipe

		////////////////////////////////
		LinearLayout ll = 
				(LinearLayout) this.findViewById(R.id.actv_showlist_LL_buttons);
		
		ll.setTag(Tags.SwipeTags.SWIPE_ACTV_SHOWLIST);
		
		ll.setOnTouchListener(new STL(this));

		LinearLayout ll_swipe = 
				(LinearLayout) this.findViewById(R.id.actv_showlist_LL_swipe_area);
		
		ll_swipe.setTag(Tags.SwipeTags.SWIPE_ACTV_SHOWLIST);
		
		ll_swipe.setOnTouchListener(new STL(this));
		
		////////////////////////////////

		// button: back

		////////////////////////////////
		ImageButton ib_Back = 
				(ImageButton) this.findViewById(R.id.actv_showlist_ib_back);
		
		ib_Back.setTag(Tags.ButtonTags.ACTV_SHOWLIST_IB_BACK);
		
		ib_Back.setOnClickListener(new BO_CL(this));;
		

	}

	private void 
	_Setup_Set_LocationList() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// loc list

		////////////////////////////////
		
		// Log
		String msg_Log = "_Setup_Set_LocationList()";
		Log.d("ShowListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		CONS.ShowList.loc_List = DBUtils.get_LocList(this);

		if (CONS.ShowList.loc_List != null) {
			
			// Log
			msg_Log = "size() => " + CONS.ShowList.loc_List.size();
			Log.d("ShowListActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			msg_Log = "CONS.ShowList.loc_List => null";
			Log.d("ShowListActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

		}
		
//		//debug
//		if (CONS.Main.loc_List == null) {
//		
//			CONS.Main.loc_List = DBUtils.get_LocList(this);
//			
////			// Log
////			String msg_Log = "loc_List => null";
////			Log.d("MainActv.java" + "["
////					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////					+ "]", msg_Log);
////			
////			return;
//			
//		}

		////////////////////////////////

		// adp

		////////////////////////////////
		CONS.ShowList.adp_Loc = new Adp_Loc(
//				Adp_Loc adp_LocList = new Adp_Loc(
						this,
						R.layout.list_row_loc_list,
						CONS.ShowList.loc_List
//						CONS.Main.loc_List
//						loc_List
		);

		////////////////////////////////

		// set

		////////////////////////////////
		CONS.ShowList.lv_ShowList = this.getListView();
//		ListView lv = this.getListView();
		
		CONS.ShowList.lv_ShowList.setAdapter(CONS.ShowList.adp_Loc);
//		CONS.ShowList.lv_ShowList.setAdapter(CONS.Main.adp_Loc);


	}//_Setup_Set_LocationList()

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
