package lm1.listeners.list;

import lm1.items.Loc;
import lm1.utils.Methods_dlg;
import lm1.utils.Tags;
import android.app.Activity;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class List_ILCL
implements OnItemLongClickListener {

	Activity actv;
	static Vibrator vib;

	
	public List_ILCL(Activity actv) {
		
		this.actv = actv;
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);

	}

	@Override
	public boolean onItemLongClick
	(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		
		Tags.ListTags tag = (Tags.ListTags) parent.getTag();
		
		vib.vibrate(40);

		switch (tag) {
		
		case ACTV_MAIN_LV://-----------------------------

			case_ACTV_MAIN_LV(parent, position);
			
			break;// case actv_main_lv
			
//		case actv_main_lv_locs://-----------------------------
//			
//			case_Actv_main_lv_locs(CONS.Views.lv_Locations, position);
////			case_Actv_main_lv_locs(parent, position);
//			
//			break;// case actv_main_lv_locs
			
		default:
			break;
		}//switch (tag) {
		
		return true;
//		return false;
	}//public boolean onItemLongClick

	private void
	case_Actv_main_lv_locs(ListView parent, int position) {
//		case_Actv_main_lv_locs(AdapterView<?> parent, int position) {
		// TODO Auto-generated method stub
		Loc loc = (Loc) parent.getItemAtPosition(position);
		
		// Log
		String log_msg = "loc.id="
						+ String.valueOf(loc.getId())
						+ "/"
						+ "time=" + loc.getCreated_at()
						+ "/"
						+ "position=" + String.valueOf(position);

		Log.d("[" + "List_ILCL.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
		
		// Show dialog
		boolean res = Methods_dlg.dlg_EditLoc(actv, loc, parent, position);
		
		// If updated, update the data in the loc list, as well
		//	=> execute in the dialog process
		
	}//case_Actv_main_lv_locs(AdapterView<?> parent, int position)

	private void case_ACTV_MAIN_LV
	(AdapterView<?> parent, int position) {
		
		Loc loc = (Loc) parent.getItemAtPosition(position);
		
		Methods_dlg.dlg_MainActv_List(actv, loc, parent, position);
//		Methods_dlg.dlg_EditLoc(actv, loc, parent, position);
//		boolean res = Methods_dlg.dlg_EditLoc(actv, loc, parent, position);
		
//		// debug
//		Toast.makeText(actv, itemName, Toast.LENGTH_LONG).show();
		
	}//private void case_actv_main_lv

}//public class Custom_ILCL
