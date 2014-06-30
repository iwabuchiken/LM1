package lm1.listeners.dialog;


import lm1.main.R;
import lm1.utils.CONS;
import lm1.utils.Methods;
import lm1.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class DOI_CL implements OnItemClickListener {

	//
	Activity actv;
	Dialog dlg1;
	Dialog dlg2;
	
	//
	Vibrator vib;
	
	int aiList_Position;
	
	//
//	Methods.DialogTags dlgTag = null;

	public DOI_CL(Activity actv, Dialog dlg, Dialog dlg2) {
		// 
		this.actv = actv;
		this.dlg1 = dlg;
		this.dlg2 = dlg2;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)


	public DOI_CL(Activity actv, Dialog dlg1) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.dlg1	= dlg1;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	//	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 1. Get tag
		 * 2. Vibrate
		 * 3. Switching
			----------------------------*/
		
		Tags.DialogItemTags tag = (Tags.DialogItemTags) parent.getTag();
//		
		vib.vibrate(CONS.Admin.vibLength_click);
		
		String item = (String) parent.getItemAtPosition(position);
		
		/*----------------------------
		 * 3. Switching
			----------------------------*/
		switch (tag) {
		
		case DLG_DB_ADMIN_LV:
			
			case_DLG_DB_ADMIN_LV(item);
			
			break;
//		case dlg_db_admin_lv://----------------------------------------------
//		case DLG_ALACTV_LIST_LONGCLICK://----------------------------------------------
//			
//			item = (String) parent.getItemAtPosition(position);
//			
//			case_Dlg_ALActv_LongClick(item);
//			
//			break;// case dlg_bmactv_list_long_click
			
			
		default:
			break;
		}//switch (tag)
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)


	private void 
	case_DLG_DB_ADMIN_LV(String item) {
		// TODO Auto-generated method stub
		if (item.equals(actv.getString(
				R.string.dlg_db_admin_item_exec_sql))) {
			
//			Methods.exec_Sql(actv);
			
		} else if (item.equals(actv.getString(
				R.string.dlg_db_admin_item_backup_db))) {
			
//			Methods.db_Backup(actv);
			
		} else if (item.equals(actv.getString(
				R.string.dlg_db_admin_item_create_table_locations))) {
			
			Methods.create_Table(actv, CONS.DB.tname_Locations);
			
		} else if (item.equals(actv.getString(
				R.string.dlg_db_admin_item_drop_table_locations))) {
			
			Methods.drop_Table(actv, CONS.DB.tname_Locations);
			

		}
	
		
		dlg1.dismiss();
		
		
	}//case_DLG_DB_ADMIN_LV

}//public class DialogOnItemClickListener implements OnItemClickListener
