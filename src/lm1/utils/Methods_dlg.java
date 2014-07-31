package lm1.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lm1.items.Loc;
import lm1.listeners.dialog.DB_OCL;
import lm1.listeners.dialog.DB_OTL;
import lm1.listeners.dialog.DOI_CL;
import lm1.main.R;
import lm1.utils.Tags.DialogTags;

import org.apache.commons.lang.StringUtils;

import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Methods_dlg {

	public static void dlg_Db_Activity(Activity actv) {
		/****************************
		 * 1. Dialog
		 * 2. Prep => List
		 * 3. Adapter
		 * 4. Set adapter
		 * 
		 * 5. Set listener to list
		 * 6. Show dialog
			****************************/
		Dialog dlg = Methods_dlg.dlg_Template_Cancel(
									actv, R.layout.dlg_tmpl_list_cancel, 
									R.string.dlg_db_admin_title, 
									R.id.dlg_tmpl_list_cancel_bt_cancel, 
//									R.id.dlg_db_admin_bt_cancel, 
									Tags.DialogTags.DLG_GENERIC_DISMISS);
		
		/****************************
		 * 2. Prep => List
			****************************/
		String[] choices = {
//				actv.getString(R.string.dlg_db_admin_item_exec_sql),
				
				actv.getString(R.string.dlg_db_admin_item_backup_db),
				actv.getString(
						R.string.dlg_db_admin_item_create_table_locations),
				actv.getString(
						R.string.dlg_db_admin_item_drop_table_locations),
				actv.getString(
						R.string.dlg_db_admin_item_restore_db),
//				actv.getString(R.string.dlg_db_admin_item_refresh_db),
				
		};
		
		List<String> list = new ArrayList<String>();
		
		for (String item : choices) {
			
			list.add(item);
			
		}
		
		/****************************
		 * 3. Adapter
			****************************/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				actv,
//				R.layout.dlg_db_admin,
//				android.R.layout.simple_list_item_1,
				R.layout.list_row_simple_1,
				list
				);

		/****************************
		 * 4. Set adapter
			****************************/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_tmpl_list_cancel_lv);
//		ListView lv = (ListView) dlg.findViewById(R.id.dlg_db_admin_lv);
		
		lv.setAdapter(adapter);
		
		/****************************
		 * 5. Set listener to list
			****************************/
		lv.setTag(Tags.DialogItemTags.DLG_DB_ADMIN_LV);
		
		lv.setOnItemClickListener(new DOI_CL(actv, dlg));
		
		/****************************
		 * 6. Show dialog
			****************************/
		dlg.show();
		
		
	}//public static void dlg_db_activity(Activity actv)

	public static
	Dialog dlg_Template_Cancel
	(Activity actv, int layoutId, int titleStringId,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		****************************/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(titleStringId);
		
		/****************************
		* 2. Add listeners => OnTouch
		****************************/
		//
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
		
		/****************************
		* 3. Add listeners => OnClick
		****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;
	
	}//public static Dialog dlg_template_okCancel()

	public static Dialog 
	dlg_Template_Cancel
	(Activity actv,
			int layoutId, String title, 
			int cancelButtonId, DialogTags cancelTag) {
		// TODO Auto-generated method stub
		
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(title);
		
		/****************************
		* 2. Add listeners => OnTouch
		****************************/
		//
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
		
		/****************************
		* 3. Add listeners => OnClick
		****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;		
	}//dlg_Template_Cancel


	public static void
	dlg_ShowMessage(Activity actv, String message) {
		
		Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.DLG_GENERIC_DISMISS);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		dlg.show();
		
	}
	
	public static void
	dlg_ShowMessage
	(Activity actv, String message, Tags.DialogTags tag) {
		
		Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				tag);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		dlg.show();
		
	}//dlg_ShowMessage
	
	public static void
	dlg_ShowMessage_Duration
	(Activity actv, String message, int duration) {
		
		final Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.DLG_GENERIC_DISMISS);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		dlg.show();
		
		//REF http://xjaphx.wordpress.com/2011/07/13/auto-close-dialog-after-a-specific-time/
		final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dlg.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, duration); // after 2 second (or 2000 miliseconds), the task will be active.
		
	}

	public static void 
	drop_Table_Confirm
	(Activity actv, String tableName, Dialog dlg1) {
		// TODO Auto-generated method stub

		Dialog dlg2 = 
				Methods_dlg.dlg_Template_okCancel_SecondDialog(
				actv, R.layout.dlg_tmpl_okcancel_simple, 
				R.string.generic_tv_confirm, 
				
				R.id.dlg_tmpl_okcancel_simple_btn_ok, 
				R.id.dlg_tmpl_okcancel_simple_btn_cancel,
				
				Tags.DialogTags.DLG_CONFIRM_DROP_TABLE_OK,
				Tags.DialogTags.DLG_GENERIC_DISMISS_SECOND_DIALOG,
				dlg1
				);
		
		TextView tv_Message = 
				(TextView) dlg2.findViewById(
							R.id.dlg_tmpl_okcancel_simple_tv_message);
		
		TextView tv_Text = 
				(TextView) dlg2.findViewById(
						R.id.dlg_tmpl_okcancel_simple_tv_text);
		
		tv_Message.setText(actv.getString(R.string.drop_table_message));
		
		tv_Text.setText(actv.getString(R.string.drop_table_locations));
		
		dlg2.show();
		
	}//drop_Table_Confirm

	public static Dialog 
	dlg_Template_okCancel_SecondDialog
	(Activity actv, 
		int layoutId, int titleStringId,
		int okButtonId, int cancelButtonId,
		
		Tags.DialogTags okTag, Tags.DialogTags cancelTag,
		Dialog dlg1) {
		/****************************
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		****************************/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		/****************************
		* 2. Add listeners => OnTouch
		****************************/
		//
		Button btn_ok = (Button) dlg2.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(new DB_OTL(actv, dlg2));
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg2));
		
		/****************************
		* 3. Add listeners => OnClick
		****************************/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
//		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg2));
		
		//
		//dlg2.show();
		
		return dlg2;
	
	}//dlg_Template_okCancel_SecondDialog

	public static boolean
	dlg_EditLoc
	(Activity actv, Loc loc, AdapterView<?> parent, int position) {
		// TODO Auto-generated method stub
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_edit_locs);
		
		// Title
		dlg.setTitle(actv.getString(R.string.dlg_edit_locs_title));
		
		/*********************************
		 * Set: values
		 *********************************/
		/******************************
			date
		 ******************************/
		// Date
		TextView tv_Date = (TextView) dlg.findViewById(
								R.id.dlg_edit_locs_tv_date_val);
		
		//REF return http://stackoverflow.com/questions/16601585/add-new-line-character-in-string answered May 17 '13 at 4:53
		String date = StringUtils.join(loc.getCreated_at().split("_"), "\n");
		
		if (loc.getCreated_at() != null) {
			
			tv_Date.setText(date);
//			tv_Date.setText(loc.getCreated_at());
			
		}
		
		/******************************
			longitude
		 ******************************/
		TextView tv_Longi = (TextView) dlg.findViewById(
				R.id.dlg_edit_locs_tv_longi_val);
		
		String longi = loc.getLongitude();
		
		if (longi == null) {
			
			longi = "";
			
		}
//		} else if (longi.length() > CONS.Others.Default_LocStringLength) {
//			
//			longi = longi.substring(0, CONS.Others.Default_LocStringLength);
//			
//		}
		
		tv_Longi.setText(longi);
		
		/******************************
			latitude
		 ******************************/
		TextView tv_Lat = (TextView) dlg.findViewById(
				R.id.dlg_edit_locs_tv_lat_val);
		
		String lat = loc.getLatitude();
		
		if (lat == null) {
			
			lat = "";
			
		}
//		} else if (lat.length() > CONS.Others.Default_LocStringLength) {
//			
//			lat = lat.substring(0, CONS.Others.Default_LocStringLength);
//			
//		}
		
		tv_Lat.setText(lat);

		/******************************
			memo
		 ******************************/
		EditText et_Memo = 
				(EditText) dlg.findViewById(R.id.dlg_edit_locs_tv_memo_val);
		
		String original_Memo = loc.getMemo();
		
		et_Memo.setText(original_Memo);
		
		/*********************************
		 * Set: Listeners
		 *********************************/
		/******************************
			btn: Cancel
		 ******************************/
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_edit_locs_btn_cancel);
		
		//
		btn_cancel.setTag(Tags.DialogTags.DLG_GENERIC_DISMISS);
//		btn_cancel.setTag(Tags.DialogTags.dlg_generic_dismiss);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		/******************************
			btn: Cancel
		******************************/
		Button btn_Ok = (Button) dlg.findViewById(R.id.dlg_edit_locs_btn_ok);
		
		//
		btn_Ok.setTag(Tags.DialogTags.DLG_EDIT_LOCS_BTN_OK);
		
		//
		btn_Ok.setOnTouchListener(new DB_OTL(actv, dlg));
		
		// Get the current memo value
		
		// Pass the value to OnClickListener
		btn_Ok.setOnClickListener(new DB_OCL(
				actv, dlg, loc, parent, position, original_Memo));
		
		/*********************************
		 * Show
		 *********************************/
		dlg.show();
		
		return false;
		
	}//dlg_EditLoc
	
	public static boolean
	dlg_EditLoc
	(Activity actv, 
		Loc loc, AdapterView<?> parent, int position_InListView,
		Dialog dlg1) {
		// TODO Auto-generated method stub
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(R.layout.dlg_edit_locs);
		
		// Title
		dlg2.setTitle(actv.getString(R.string.dlg_edit_locs_title));
		
		/*********************************
		 * Set: values
		 *********************************/
		/******************************
			date
		 ******************************/
		// Date
		TextView tv_Date = (TextView) dlg2.findViewById(
				R.id.dlg_edit_locs_tv_date_val);
		
		//REF return http://stackoverflow.com/questions/16601585/add-new-line-character-in-string answered May 17 '13 at 4:53
		String date = StringUtils.join(loc.getCreated_at().split("_"), "\n");
		
		if (loc.getCreated_at() != null) {
			
			tv_Date.setText(date);
//			tv_Date.setText(loc.getCreated_at());
			
		}
		
		/******************************
			longitude
		 ******************************/
		TextView tv_Longi = (TextView) dlg2.findViewById(
				R.id.dlg_edit_locs_tv_longi_val);
		
		String longi = loc.getLongitude();
		
		if (longi == null) {
			
			longi = "";
			
		}
//		} else if (longi.length() > CONS.Others.Default_LocStringLength) {
//			
//			longi = longi.substring(0, CONS.Others.Default_LocStringLength);
//			
//		}
		
		tv_Longi.setText(longi);
		
		/******************************
			latitude
		 ******************************/
		TextView tv_Lat = (TextView) dlg2.findViewById(
				R.id.dlg_edit_locs_tv_lat_val);
		
		String lat = loc.getLatitude();
		
		if (lat == null) {
			
			lat = "";
			
		}
//		} else if (lat.length() > CONS.Others.Default_LocStringLength) {
//			
//			lat = lat.substring(0, CONS.Others.Default_LocStringLength);
//			
//		}
		
		tv_Lat.setText(lat);
		
		/******************************
			memo
		 ******************************/
		EditText et_Memo = 
				(EditText) dlg2.findViewById(R.id.dlg_edit_locs_tv_memo_val);
		
		String original_Memo = loc.getMemo();
		
		et_Memo.setText(original_Memo);
		
		/*********************************
		 * Set: Listeners
		 *********************************/
		/******************************
			btn: Cancel
		 ******************************/
		Button btn_cancel = (Button) dlg2.findViewById(R.id.dlg_edit_locs_btn_cancel);
		
		//
		btn_cancel.setTag(Tags.DialogTags.DLG_GENERIC_DISMISS_SECOND_DIALOG);
//		btn_cancel.setTag(Tags.DialogTags.DLG_GENERIC_DISMISS);
//		btn_cancel.setTag(Tags.DialogTags.dlg_generic_dismiss);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2));
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		
		/******************************
			btn: Ok
		 ******************************/
		Button btn_Ok = (Button) dlg2.findViewById(R.id.dlg_edit_locs_btn_ok);
		
		//
		btn_Ok.setTag(Tags.DialogTags.DLG_EDIT_LOCS_BTN_OK);
		
		//
		btn_Ok.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2));
		
		// Get the current memo value
		
		// Pass the value to OnClickListener
		btn_Ok.setOnClickListener(new DB_OCL(
				actv, loc, parent, 
				position_InListView, original_Memo, 
				dlg1, dlg2));
		
		/*********************************
		 * Show
		 *********************************/
		dlg2.show();
		
		return false;
		
	}//dlg_EditLoc
	
	public static boolean
	dlg_MainActv_List
	(Activity actv, 
		Loc loc, AdapterView<?> parent, int position_InListView) {
		// TODO Auto-generated method stub
		Dialog dlg1 = new Dialog(actv);
		
		//
		dlg1.setContentView(R.layout.dlg_tmpl_list_cancel_with_message);
//		dlg1.setContentView(R.layout.dlg_tmpl_list_cancel);

		////////////////////////////////

		// message

		////////////////////////////////
		StringBuilder sb = new StringBuilder();
//		sb.append(actv.getString(R.string.dlg_mainactv_list_title));
//		sb.append(actv.getString(R.string.dlg_));
//		sb.append("(");
		sb.append(loc.getLongitude());
		sb.append("/");
		sb.append(loc.getLatitude());
//		sb.append(")");

		TextView tv = (TextView) dlg1.findViewById(
							R.id.dlg_tmpl_list_cancel_with_message_tv);
		
		tv.setText(sb.toString());
		
		////////////////////////////////

		// Title

		////////////////////////////////
//		dlg1.setTitle(sb.toString());
		dlg1.setTitle(actv.getString(R.string.dlg_mainactv_list_title));
		
		////////////////////////////////

		// choices

		////////////////////////////////
		String[] choices = {
				
				actv.getString(R.string.dlg_edit_locs_title),
				actv.getString(R.string.dlg_mainactv_list_opt_set_base),
				actv.getString(R.string.dlg_mainactv_list_opt_set_ref),
				actv.getString(R.string.dlg_mainactv_list_opt_show_distance),
				actv.getString(R.string.dlg_mainactv_list_opt_delete),
				
		};
		
		List<String> list = new ArrayList<String>();
		
		for (String item : choices) {
			
			list.add(item);
			
		}
		
		/*----------------------------
		 * 3. Adapter
			----------------------------*/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				actv,
//				R.layout.dlg_db_admin,
				R.layout.list_row_simple_1,
				list
				);

		/*----------------------------
		 * 4. Set adapter
			----------------------------*/
//		ListView lv = (ListView) dlg1.findViewById(R.id.dlg_tmpl_list_cancel_lv);
		ListView lv = 
				(ListView) dlg1.findViewById(
						R.id.dlg_tmpl_list_cancel_with_message_lv);
		
		lv.setAdapter(adapter);
		
		/*----------------------------
		 * 5. Set listener to list
			----------------------------*/
		lv.setTag(Tags.DialogItemTags.DLG_MAINACTV_LIST);
		
		lv.setOnItemClickListener(new DOI_CL(
					actv, loc, parent, position_InListView, dlg1));
		
		////////////////////////////////

		// btn: cancel

		////////////////////////////////
		Button bt_Cancel = 
				(Button) dlg1.findViewById(
//						R.id.dlg_tmpl_list_cancel_bt_cancel);
						R.id.dlg_tmpl_list_cancel_with_message_bt_cancel);
		
		bt_Cancel.setTag(Tags.DialogTags.DLG_GENERIC_DISMISS);
		
		bt_Cancel.setOnTouchListener(new DB_OTL(actv));
		bt_Cancel.setOnClickListener(new DB_OCL(actv, dlg1));
		
		/*********************************
		 * Show
		 *********************************/
		dlg1.show();
		
		return false;
		
	}//dlg_MainActv_List

	public static void 
	show_Distance
	(Activity actv, 
		Loc loc, AdapterView<?> parent, 
		int position_InListView, Dialog dlg1) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// get: base location

		////////////////////////////////
		long base_LocId = Methods.get_Pref_Long(
							actv, 
							CONS.Pref.pname_MainActv, 
							CONS.Pref.pkey_MainActv_CurrentBase, 
							CONS.Pref.dflt_LongExtra_value);

		if (base_LocId == CONS.Pref.dflt_LongExtra_value) {
			
			String message = "No base loc data";
			Methods_dlg.dlg_ShowMessage(actv, message);
			
			return;
			
		}

		////////////////////////////////

		// get: loc

		////////////////////////////////
		Loc loc_Base = DBUtils.get_Loc_FromId(actv, base_LocId);
		
		////////////////////////////////

		// dialog

		////////////////////////////////
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(R.layout.dlg_tmpl_toast_ok);
		
		// Title
		dlg2.setTitle(loc.getLongitude());
		
		////////////////////////////////

		// view

		////////////////////////////////
		TextView tv_Message = (TextView) dlg2.findViewById(
								R.id.dlg_tmpl_toast_ok_tv_message);

		////////////////////////////////

		// data

		////////////////////////////////
//		double diff = Double.parseDouble(loc.getLongitude())
//					- Double.parseDouble(loc_Base.getLongitude());
		
//		double diff = Methods.distance(
//					Double.parseDouble(loc.getLatitude()), 
//					Double.parseDouble(loc.getLongitude()), 
//					
//					Double.parseDouble(loc_Base.getLatitude()), 
//					Double.parseDouble(loc_Base.getLongitude()), 
//					
//					'K'
//					);
		
		double diff = Methods.distance_2(
					Double.parseDouble(loc.getLatitude()), 
					Double.parseDouble(loc.getLongitude()), 
					
					Double.parseDouble(loc_Base.getLatitude()), 
					Double.parseDouble(loc_Base.getLongitude()) 
					);
		
		////////////////////////////////

		// set: data to textview

		////////////////////////////////
		String message = String.format("diff => %f", diff);
		
		tv_Message.setText(message);
		
		/******************************
			btn: Ok
		 ******************************/
		Button btn_Ok = 
				(Button) dlg2.findViewById(R.id.dlg_tmpl_toast_ok_bt_cancel);
		
		//
		btn_Ok.setTag(Tags.DialogTags.DLG_GENERIC_DISMISS_SECOND_DIALOG);
		
		//
		btn_Ok.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2));
		
		// Get the current memo value
		
		// Pass the value to OnClickListener
		btn_Ok.setOnClickListener(new DB_OCL(
						actv, dlg1, dlg2));
		
		/*********************************
		 * Show
		 *********************************/
		dlg2.show();
		
	}

	public static void
	conf_DeleteLoc
	(Activity actv, Loc loc, Dialog dlg1,
		AdapterView<?> parent, int position_InListView) {
		// TODO Auto-generated method stub
//		Dialog dlg = Methods_dlg.dlg_Template_Cancel(
//				actv, R.layout.dlg_tmpl_list_cancel, 
//				R.string.dlg_db_admin_title, 
//				R.id.dlg_tmpl_list_cancel_bt_cancel, 
////				R.id.dlg_db_admin_bt_cancel, 
//				Tags.DialogTags.dlg_generic_dismiss);
		
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(R.layout.dlg_tmpl_confirm_simple);
//		dlg2.setContentView(R.layout.dlg_confirm_remove_folder);
		
		// Title
		dlg2.setTitle(R.string.generic_tv_confirm);

		////////////////////////////////

		// Set: Message

		////////////////////////////////
		TextView tv_Message = (TextView) dlg2.findViewById(
							R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Message.setText(
				actv.getString(R.string.dlg_conf_del_loc_message));

		////////////////////////////////

		// Set: BM position

		////////////////////////////////
		TextView tv_Loc = (TextView) dlg2.findViewById(
							R.id.dlg_tmpl_confirm_simple_tv_item_name);

		tv_Loc.setText(loc.getLongitude());

		////////////////////////////////

		// Add listeners => OnTouch

		////////////////////////////////
		Button btn_ok = (Button) dlg2.findViewById(
								R.id.dlg_tmpl_confirm_simple_btn_ok);
		
		Button btn_cancel = (Button) dlg2.findViewById(
								R.id.dlg_tmpl_confirm_simple_btn_cancel);
		
		//
		btn_ok.setTag(Tags.DialogTags.DLG_DELETE_LOC_OK);
		btn_cancel.setTag(Tags.DialogTags.DLG_GENERIC_DISMISS_SECOND_DIALOG);
		
		//
		btn_ok.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2));
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2));
		
		/****************************
		 * 4. Add listeners => OnClick
			****************************/
		//
		btn_ok.setOnClickListener(
				new DB_OCL(
						actv, dlg1, dlg2, loc, 
						parent, position_InListView));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		
		/****************************
		 * 5. Show dialog
			****************************/
		dlg2.show();
		
		
		
	}//conf_DeleteBM(Activity actv, Dialog dlg1, BM bm)

}//public class Methods_dialog
