package lm1.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CONS {

	// Sort order
	public static enum SORT_ORDER {ASC, DEC};

	/*********************************
	 * Intent data labels
	 *********************************/
	public static String intent_label_file_ids = "file_ids";		// Methods.show_history()
	
	public static String intent_label_table_names = "table_names";	// Methods.show_history()

	public static String
	intent_label_searchedItems_table_names =
					"string_searchedItems_table_names";
	
	/*********************************
	 * Prefs
	 *********************************/
//	private static SharedPreferences prefs;
	public static SharedPreferences prefs_main;

//	public static String prefs_current_path = "current_path";
//	public static String prefs_current_path = "ifm9_master_current_path";
	public static String pname_current_path = "current_path";
	public static String pkey_current_path = "current_path";
	
	public static String pname_tnActv = "pref_tn_actv";
	
	public static String pname_current_image_position = 
							"pname_current_image_position";
	
	public static String pkey_current_image_position = 
							"pkey_current_image_position";
	
	// MainActv
	// history
	public static String pname_mainActv = "pref_main_actv";
	
	public static String pname_mainActv_history_mode = "history_mode";
	
	public static final int HISTORY_MODE_ON = 1;
	
	public static final int HISTORY_MODE_OFF = 0;

	/*********************************
	 * Paths and names
	 *********************************/
	public static String dpath_storage_sdcard = "/mnt/sdcard-ext";
	
	public static String dpath_storage_internal = "/mnt/sdcard";

	public static String  dname_base = "cm5";

//	public static String dirPath_base = dirName_ExternalStorage + File.separator + dirName_base;
	public static String dpath_base = 
					dpath_storage_sdcard
					+ File.separator
					+ dname_base;
	
	public static String fname_list = "list.txt";

//	public static String dirPath_current = null;
	public static String dpath_current = null;

	// tapeatalk
	public static String dname_tt_sdcard = "tapeatalk_records";
	
	public static String dname_tt_internal = "tapeatalk_records";


	
	/*********************************
	 * DB
	 *********************************/
	public static String dbName = "cm5.db";
	
	// Table names
	public static String tname_memo_patterns = "memo_patterns";

	// Table => main
	public static String tname_main = "cm5";
	
	public static String[] cols_item = 
		{"file_name", 	"file_path",	"title", "memo",
			"last_played_at",	"table_name",	"length"};
	
	public static String[] col_types_item =
		{"TEXT", 		"TEXT", 		"TEXT",	"TEXT",
			"INTEGER",			"TEXT", 		"INTEGER"};

	// Table => show_history
	public static String tname_show_history = "show_history";
	
	public static String[] cols_show_history = {
		"file_id", "table_name"
	};
	
	public static String[] col_types_show_history = {
		"INTEGER", "TEXT"
	};

	public static String tname_separator = "__";

	// Table => refresh_history
	public static String tname_refresh_history = "refresh_history";
	
	public static String[] cols_refresh_history = {
		"last_refreshed", "num_of_items_added"
	};
	
	public static String[] col_types_refresh_history = {
		"INTEGER", 			"INTEGER"
	};
	
	// Backup
	public static String dpath_db = "/data/data/cm5.main/databases";
	
	public static String fname_db = "cm5.db";

	public static String dpath_db_backup = 
						dpath_storage_sdcard + "/cm5_backup";
	
	public static String fname_db_backup_trunk = "cm5_backup";
	public static String fname_db_backup_ext = ".bk";

	/*********************************
	 * Others
	 *********************************/
	// Used => create_list_file()
//	public static String listFileName = "list.txt";

	public static boolean move_mode = false;

	/*********************************
	 * From: DBUtils.java
	 *********************************/

	static String[] cols_with_index = 
		{android.provider.BaseColumns._ID, 
			"file_id", 		"file_path", "file_name", "date_added",
			"date_modified", "memos", "tags"};

	static String[] col_types_with_index =
			{	"INTEGER", "TEXT", 	"TEXT",		"INTEGER",
				"INTEGER",		"TEXT",	"TEXT"};
	
	// Main data
	public static String[] cols = 
	{"file_id", "file_path", "file_name", 	"date_added",
	"date_modified",	"memos", "tags", 	"last_viewed_at"};
	//"date_modified", "memos", "tags"};
	
	public static String[] col_types =
	{"INTEGER", "TEXT", 	"TEXT",			"INTEGER",
	"INTEGER",			"TEXT",	"TEXT",		"INTEGER"};
	
	static String[] cols_for_insert_data = 
	{"file_id", 		"file_path", "file_name", "date_added", "date_modified"};
	
	// Proj
	static String[] proj = {
	MediaStore.Images.Media._ID, 
	MediaStore.Images.Media.DATA,
	MediaStore.Images.Media.DISPLAY_NAME,
	MediaStore.Images.Media.DATE_ADDED,
	MediaStore.Images.Media.DATE_MODIFIED,
	};
	
	static String[] proj_for_get_data = {
	MediaStore.Images.Media._ID, 
	MediaStore.Images.Media.DATA,
	MediaStore.Images.Media.DISPLAY_NAME,
	MediaStore.Images.Media.DATE_ADDED,
	MediaStore.Images.Media.DATE_MODIFIED,
	"memos",
	"tags"
	};
	
	static String[] cols_refresh_log = {
	"last_refreshed", "num_of_items_added"
	};
	
	static String[] col_types_refresh_log = {
	"INTEGER", 			"INTEGER"
	};
	
	static String[] cols_memo_patterns = {"word", "table_name"};
	static String[] col_types_memo_patterns = {"TEXT", "TEXT"};
	
	static String table_name_memo_patterns = "memo_patterns";

	public static enum MoveMode {
		// TIListAdapter.java
		ON, OFF,
		
	}//public static enum MoveMode

	public static class Intent {
		
		public static String bmactv_key_ai_id = "bmactv_key_ai_id";
		
		public static String bmactv_key_table_name = "bmactv_key_table_name";
		
		public static String bmactv_key_position = "bmactv_key_position";
		
		/***************************************
		 * Request codes
		 ***************************************/
		public final static int REQUEST_CODE_SEE_BOOKMARKS = 0;
		
		/***************************************
		 * Result code
		 ***************************************/
		public final static int RESULT_CODE_SEE_BOOKMARKS_OK = 1;
		
		public final static int RESULT_CODE_SEE_BOOKMARKS_CANCEL = 0;
		
	}//public static class Intent
	
	public static class DB {
		
		public static final String tname_BM = "bm";

		public static final String[] cols_bm = {
			"ai_id", "position", "title", "memo", "aiTableName"
		};
		
		public static final String[] cols_bm_full = {
			android.provider.BaseColumns._ID,
			"created_at", "modified_at",
			"ai_id", "position", "title", "memo", "aiTableName"
		};

		public static final String[] col_types_bm = {
			"INTEGER", "INTEGER", "TEXT", "TEXT", "TEXT"
		};
		
	}//public static class DB

	public static class Pref {
		
		public static final String pname_PlayActv = "pname_PlayActv";
		
		public static final String pkey_PlayActv_position = "prefKey_PlayActv_position";
		
	}

	public static class PlayActv {
	
		public static TextView tvCurrentPosition;
		
	}
	
	public static class Admin {
		
		public static final float DLG_WIDTH_RATIO = 0.8f;
		
	}
	
}//public class CONS
