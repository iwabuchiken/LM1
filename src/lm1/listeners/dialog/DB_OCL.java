package lm1.listeners.dialog;

import lm1.items.Loc;
import lm1.main.R;
import lm1.utils.CONS;
import lm1.utils.DBUtils;
import lm1.utils.Methods;
import lm1.utils.Methods_dlg;
import lm1.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DB_OCL implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog dlg1;
	Dialog dlg2;		//=> Used in dlg_input_empty_btn_XXX
	Dialog dlg3;

	// edit loc
	AdapterView<?> parent;
	int position;
	String original_Memo;

	Loc loc;
	
	//
	Vibrator vib;
	
	// Used in => Methods.dlg_addMemo(Activity actv, long file_id, String tableName)
	long file_id;
	String tableName;
	
	int alList_Position;
	
	public DB_OCL(Activity actv, Dialog dlg1) {
		//
		this.actv = actv;
		this.dlg1 = dlg1;
		
		//
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
	}

	public DB_OCL(Activity actv, Dialog dlg1,
			Dialog dlg2) {
		//
		this.actv = actv;
		this.dlg1 = dlg1;
		this.dlg2 = dlg2;
		
		//
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
	}

	public DB_OCL(Activity actv, Dialog dlg1,
			Dialog dlg2, Dialog dlg3) {
		//
		this.actv = actv;
		this.dlg1 = dlg1;
		this.dlg2 = dlg2;
		this.dlg3 = dlg3;
		
		//
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
	}

	public DB_OCL(Activity actv, Dialog dlg1, long file_id, String tableName) {
		// 
		this.actv = actv;
		this.dlg1 = dlg1;
		
		this.tableName = tableName;
		
		this.file_id = file_id;
		
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogButtonOnClickListener(Activity actv, Dialog dlg1, long file_id, String tableName)


	public DB_OCL
	(Activity actv, Dialog dlg1,
		Loc loc,
		AdapterView<?> parent, int position, String original_Memo) {
		// TODO Auto-generated constructor stub
		this.actv	= actv;
		this.dlg1	= dlg1;
		
		this.parent			= parent;
		this.position		= position;
		this.original_Memo	= original_Memo;
		
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
		// Log
		String log_msg = "Initialized => "
						+ "postion=" + String.valueOf(position)
						+ "/"
						+ "loc.time=" + loc.getCreated_at();

		Log.d("[" + "DB_OCL.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
		
	}

	public DB_OCL(Activity actv, Dialog dlg1, Dialog dlg2, Loc loc,
			AdapterView<?> parent, int position, String original_Memo) {
		// TODO Auto-generated constructor stub
	}

	public DB_OCL
	(Activity actv, Loc loc, AdapterView<?> parent,
			int position_InListView, String original_Memo, 
			Dialog dlg1, Dialog dlg2) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.loc	= loc;
		this.parent			= parent;
		
		this.position		= position_InListView;
		this.original_Memo	= original_Memo;
		
		this.dlg1	= dlg1;
		this.dlg2	= dlg2;
		
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);

	}

	public void onClick(View v) {
		//
		Tags.DialogTags tag_name = (Tags.DialogTags) v.getTag();

		// Log
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "tag_name.name()=" + tag_name.name());
		
//		CONS.Admin.vib = 
//					(Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
		CONS.Admin.vib.vibrate(CONS.Admin.vibLength_click);
		
		//
		switch (tag_name) {
		
		case DLG_GENERIC_DISMISS://------------------------------------------------
			
			dlg1.dismiss();
			
			break;

		case DLG_GENERIC_DISMISS_SECOND_DIALOG: // ----------------------------------------------------
			
			dlg2.dismiss();
			
			break;// case dlg_generic_dismiss_second_dialog

		case dlg_generic_dismiss_third_dialog://------------------------------------------------
			
			dlg3.dismiss();
			
			break;

		case DLG_CONFIRM_DROP_TABLE_OK://------------------------------------------------
			
			case_DLG_CONFIRM_DROP_TABLE_OK();
			
			break;
			
		case DLG_EDIT_LOCS_BTN_OK://------------------------------------------------
			
			case_DLG_EDIT_LOCS_BTN_OK();
			
			break;
			
		default: // ----------------------------------------------------
			break;
		}//switch (tag_name)
	}//public void onClick(View v)

//	private void case_DLG_EDIT_LOCS_BTN_OK() {
//		// TODO Auto-generated method stub
//		
//	}

	private void 
	case_DLG_CONFIRM_DROP_TABLE_OK() {
		// TODO Auto-generated method stub
	
		Methods.drop_Table(actv, CONS.DB.tname_Locations, dlg1, dlg2);
		
//		// debug
//		String msg_Toast = "Confirmed";
//		Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
		
		
	}

	private void case_DLG_EDIT_LOCS_BTN_OK() {
//		private void case_Dlg_EditLocs_Btn_Ok() {
		/*********************************
		 * Update: Location list
		 *********************************/
		// Compare the current and the new memo value
		EditText et_Memo = (EditText) dlg2.findViewById(
//				EditText et_Memo = (EditText) dlg1.findViewById(
								R.id.dlg_edit_locs_tv_memo_val);
		
		String new_Memo = et_Memo.getText().toString();
		

		// If any change, then replace the current value with the new
		boolean res = false;	// flag for DB update
		
		if (original_Memo != null &&
				new_Memo != null &&
				original_Memo.equals(new_Memo)) {
			
			// Log
			String log_msg = "orig memo=" + original_Memo
					+ "/"
					+ "new memo=" + new_Memo;
			
			Log.d("[" + "DB_OCL.java : "
					+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", log_msg);
			
		} else if(new_Memo == null){//if (original_Memo.equals(new_Memo))
			
			// Log
			String log_msg = "new memo => null";
			
			Log.d("[" + "DB_OCL.java : "
					+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", log_msg);
			
		} else {//if (original_Memo.equals(new_Memo))
			
			//debug
			Loc loc = CONS.Main.loc_List.get(position);
			
			// Log
			String log_msg = "position="
							+ String.valueOf(position)
							+ "/"
							+ "loc.time=" + loc.getCreated_at();

			Log.d("[" + "DB_OCL.java : "
					+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", log_msg);

			
			res = _case_Dlg_EditLocs_Btn_Ok_UpdateMemo(new_Memo);
			
		}//if (original_Memo.equals(new_Memo))
		
		////////////////////////////////

		// report

		////////////////////////////////
		// Close dialog
		if (res == true) {
			
			String message = "Update => done";
			Methods_dlg.dlg_ShowMessage(actv, message);
			
			dlg2.dismiss();
			dlg1.dismiss();
			
		} else {
			
			String message = "Update => failed";
			Methods_dlg.dlg_ShowMessage(actv, message);

		}
		
	}//private void case_Dlg_EditLocs_Btn_Ok()

	private boolean
	_case_Dlg_EditLocs_Btn_Ok_UpdateMemo(String new_Memo) {
		// TODO Auto-generated method stub
		/*********************************
		 * Update: Location data
		 *********************************/
		Loc loc2 = CONS.Main.loc_List.get(position);
		
		// Log
		String log_msg = "loc2::position="
						+ String.valueOf(position)
						+ "/"
						+ "loc2.time=" + loc2.getCreated_at();
		
		Loc loc = (Loc)CONS.Main.lv_Main.getItemAtPosition(position);
		
		log_msg = "loc::"
				+ "position="
				+ String.valueOf(position)
				+ "/"
				+ "loc.time=" + loc.getCreated_at();

		Log.d("[" + "DB_OCL.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
		
		loc.setMemo(new_Memo);
		loc.setModified_at(
				Methods.get_TimeLabel(Methods.getMillSeconds_now()));
//				loc.setModified_at(
//						Methods.get_TimeLabel(Methods.getMillSeconds_now());
//				Methods.getTimeLabel(
//						Methods.getMillSeconds_now(),
//						CONS.Others.TimeLabelTypes.Serial));
		
		// Notify the adapter
		CONS.Main.adp_Loc.notifyDataSetChanged();
		
		// Log
		log_msg = "CONS.Adapters.adp_LocList => notified";
//		String log_msg = "CONS.Adapters.adp_LocList => notified";

		Log.d("["
				+ "DB_OCL.java : "
				+ +Thread.currentThread().getStackTrace()[2]
						.getLineNumber() + " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
		
		/*********************************
		 * Update: db
		 *********************************/
		boolean res = DBUtils.update_Loc_Memo(actv, loc);
				
		// Log
		log_msg = "res => " + res;

		Log.d("["
				+ "DB_OCL.java : "
				+ +Thread.currentThread().getStackTrace()[2]
						.getLineNumber() + " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
		
		if (res == false) {
			
			// Log
			log_msg = "DB upate => Failed";

			Log.d("["
					+ "DB_OCL.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ " : "
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]", log_msg);
			
			// debug
			String toa_msg = "DB upate => Failed";
			Toast.makeText(actv, toa_msg, Toast.LENGTH_SHORT).show();
			
//			return;
			
		}//if (res == false)

		////////////////////////////////

		// return

		////////////////////////////////
		return res;
		
//		/*********************************
//		 * Post to remote for update
//		 *********************************/
//		Task_PostLoc task_ = new Task_PostLoc(actv, loc);
//		
//		// debug
//		Toast.makeText(actv, "Starting a task...", Toast.LENGTH_LONG)
//				.show();
//		
//		task_.execute(CONS.TaskData.TaskItems.PostLoc_Update.toString());
		
	}//_case_Dlg_EditLocs_Btn_Ok_UpdateMemo(String new_Memo)

}//DialogButtonOnClickListener
