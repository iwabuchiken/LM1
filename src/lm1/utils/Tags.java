package lm1.utils;

public class Tags {

	public static enum DialogTags {
		// Generics
		DLG_GENERIC_DISMISS, DLG_GENERIC_DISMISS_SECOND_DIALOG, 
		dlg_generic_dismiss_third_dialog,
		
		// dlg_search.xml
		dlg_search, dlg_search_ok,
		
		// drop table
		DLG_CONFIRM_DROP_TABLE_OK,
		
		// edit loc
		DLG_EDIT_LOCS_BTN_OK,


	}//public static enum DialogTags
	
	public static enum DialogItemTags {
		// dlg_moveFiles(Activity actv)
		dlg_move_files,
		
		// dlg_add_memos.xml
		dlg_add_memos_gv,

		// dlg_db_admin.xml
		DLG_DB_ADMIN_LV,

		// dlg_admin_patterns.xml
		dlg_admin_patterns_lv,

		// dlg_delete_patterns.xml
		dlg_delete_patterns_gv,

		// dlg_moveFiles_search(Activity actv)
		dlg_move_files_search,

		// main_opt_menu_admin
		main_opt_menu_admin,
		
		// dlg_bmactv_list_long_click
		dlg_bmactv_list_long_click,
		
		
	}//public static enum DialogItemTags
	
	
	public static enum ButtonTags {

		// actv_main.xml
		actv_main_bt_go,
		
		ACTVMAIN_BT_GETDATA, ACTVMAIN_BT_SAVEDATA,
		ACTVMAIN_BT_SHOW_MAP,
		
	}//public static enum ButtonTags
	
	public static enum ItemTags {
		
		// MainActivity.java
		dir_list,
		
		// ThumbnailActivity.java
		dir_list_thumb_actv,
		
		// Methods.java
		dir_list_move_files,
		
		// TIListAdapter.java
		tilist_checkbox,
		
		// SearchActv.java
		dir_list_actv_search,
		
	}//public static enum ItemTags

//	public static enum MoveMode {
//		// TIListAdapter.java
//		ON, OFF,
//		
//	}//public static enum MoveMode

	public static enum PrefenceLabels {
		
		CURRENT_PATH,
		
		thumb_actv,
		
		chosen_list_item,
		
	}//public static enum PrefenceLabels

	public static enum ListTags {
		// MainActivity.java
		ACTV_MAIN_LV,
		
		// BMActv.java
		actv_bm_lv,
		
	}//public static enum ListTags


}//public class Tags
