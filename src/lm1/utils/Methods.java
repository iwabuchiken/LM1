package lm1.utils;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Toast;

public class Methods {
	
	public static void 
	create_Table
	(Activity actv, String tname) {
		// TODO Auto-generated method stub
		
//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
//		boolean res;
		
		////////////////////////////////

		// Dispatch

		////////////////////////////////
		if (tname.equals(CONS.DB.tname_Locations)) {
			
			_create_Table__Locations(actv);
//			res = dbu.createTable(actv, 
//					CONS.DB.tname_CM7, CONS.DB.col_names_CM7, 
//					CONS.DB.col_types_CM7);
//			
//			if (res == true) {
//				
//				// debug
//				String msg_Toast = "Table => created: " + CONS.DB.tname_CM7;
//				Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
//				
//				
//			} else {
//
//				// debug
//				String msg_Toast = "Table => can't create: " + CONS.DB.tname_CM7;
//				Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
//				
//			}
			
		} else {
			

		}//if (tname.equals(CONS.DB.tname_CM7))
		
	}//create_Table

//	private static void
	private static void
	_create_Table__Locations(Activity actv) {
		// TODO Auto-generated method stub
		int res = DBUtils.createTable(actv, 
//				boolean res = DBUtils.createTable(actv, 
//				boolean res = dbu.createTable(actv, 
							CONS.DB.tname_Locations, 
							CONS.DB.col_names_Locations, 
							CONS.DB.col_types_Locations);
		
		if (res == 1) {
//			if (res == true) {
			
			// debug
			String msg_Toast = "Table => created: " + CONS.DB.tname_Locations;
//			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			Methods_dlg.dlg_ShowMessage(actv, msg_Toast);
			
			
		} else if (res == -1) {
			
			// debug
			String msg_Toast = "Table => exists: " + CONS.DB.tname_Locations;
//			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			Methods_dlg.dlg_ShowMessage(actv, msg_Toast);
			
		} else {

			// debug
			String msg_Toast = "Table => can't create: " + CONS.DB.tname_Locations;
//			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			Methods_dlg.dlg_ShowMessage(actv, msg_Toast);
			
		}
		
	}//_create_Table__Locations(Activity actv)

	public static void 
	drop_Table
	(Activity actv, String tableName, 
			Dialog dlg1, Dialog dlg2) {
		// TODO Auto-generated method stub
		
		int res = DBUtils.dropTable(actv, tableName);
//		boolean res = DBUtils.dropTable(actv, tableName);
				
		if (res == 1) {
//			if (res == true) {
			
			// debug
			String msg_Toast = "Table => dropped: " + tableName;
//			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			
			Methods_dlg.dlg_ShowMessage(actv, msg_Toast);
			
			
		} else if (res == -1) {
			
			// debug
			String msg_Toast = "Table => Doesn't exist: " + tableName;
//						Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			
			Methods_dlg.dlg_ShowMessage(actv, msg_Toast);
			
		} else {

			// debug
			String msg_Toast = "Table => can't be dropped: " + tableName;
//			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			
			Methods_dlg.dlg_ShowMessage(actv, msg_Toast);

		}
		
	}//drop_Table

}//public class Methods

