package lm1.utils;

import java.util.ArrayList;
import java.util.List;

import lm1.items.Loc;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBUtils extends SQLiteOpenHelper{

	/*****************************************************************
	 * Class fields
	 *****************************************************************/
	 // DB name
	static String dbName = null;

	// Activity
	Activity activity;
	
	//
	Context context;

	/*********************************
	 * DB
	 *********************************/
	// Database
	SQLiteDatabase db = null;

	/*****************************************************************
	 * Constructor
	 *****************************************************************/
	public DBUtils(Context context, String dbName) {
		super(context, dbName, null, 1);
		
		// Initialize activity
		this.activity = (Activity) context;
		
		this.context = context;
		
		this.dbName = dbName;
		
	}//public DBUtils(Context context)


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	/******************************
	createTable()
	
	@param columns, types => use non-full version
	//@return true => Table created or exists
		@return -1	=> Table doesn't exist<br>
				-2	=> SQLException<br>
				1	=> Table created
	 ******************************/
	public static int 
//	public static boolean 
	createTable
	(Activity actv, 
			String tableName, 
			String[] columns, String[] types) {
		/*----------------------------
		 * Steps
		 * 1. Table exists?
		 * 2. Build sql
		 * 3. Exec sql
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		//
		//if (!tableExists(db, tableName)) {
		if (tableExists(actv, tableName)) {
			// Log
			Log.i("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists => " + tableName);
			
//			// debug
//			String msg_Toast = "Table exists => " + tableName;
//			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			
			
			return -1;
//			return true;
	//		return false;
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		/*----------------------------
		 * 2. Build sql
			----------------------------*/
		//
		StringBuilder sb = new StringBuilder();
		
		sb.append("CREATE TABLE " + tableName + " (");
		sb.append(android.provider.BaseColumns._ID +
							" INTEGER PRIMARY KEY AUTOINCREMENT, ");
		
		// created_at, modified_at
		sb.append("created_at TEXT, modified_at TEXT, ");
	//	sb.append("created_at INTEGER, modified_at INTEGER, ");
		
		int i = 0;
		for (i = 0; i < columns.length - 1; i++) {
			sb.append(columns[i] + " " + types[i] + ", ");
		}//for (int i = 0; i < columns.length - 1; i++)
		
		sb.append(columns[i] + " " + types[i]);
		
		sb.append(");");
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "sql => " + sb.toString());
		
		/*----------------------------
		 * 3. Exec sql
			----------------------------*/
		//
		try {
		//	db.execSQL(sql);
			wdb.execSQL(sb.toString());
			
			// Log
			Log.d(actv.getClass().getName() + 
					"["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table created => " + tableName);
			
			wdb.close();
			
			return 1;
//			return true;
			
		} catch (SQLException e) {
			
			// Log
			Log.e(actv.getClass().getName() + 
					"[" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]", 
					"Exception => " + e.toString());
			
			wdb.close();
			
			return -2;
//			return false;
			
		}//try
	
	}//public boolean createTable(SQLiteDatabase db, String tableName)

	public static boolean 
	tableExists
	(Activity actv, String tableName) {
		// The table exists?
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		Cursor cursor = rdb.rawQuery(
							"SELECT * FROM sqlite_master WHERE tbl_name = '" + 
							tableName + "'", null);
		
		actv.startManagingCursor(cursor);
//		actv.startManagingCursor(cursor);
		
		boolean res = (cursor.getCount() > 0) ? true : false;
		
//		// Judge
//		if (cursor.getCount() > 0) {
//			
//			rdb.close();
//			
//			return true;
//			
//		} else {//if (cursor.getCount() > 0)
//			
//			rdb.close();
//			return false;
//			
//		}//if (cursor.getCount() > 0)
		
		rdb.close();
		
		return res;
		
	}//tableExists

	/******************************
		dropTable
		@return -1	=> Table doesn't exist<br>
			-2		=> SQLException<br>
			1		=> Table dropped
	 ******************************/
	public static int
//	public static boolean
	dropTable
	(Activity actv, String tableName) {
		/***************************************
		 * Setup: DB
		 ***************************************/
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		/*------------------------------
		 * The table exists?
		 *------------------------------*/
		// The table exists?
		boolean tempBool = tableExists(actv, tableName);
		
		if (tempBool == true) {
		
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists: " + tableName);

		} else {//if (tempBool == true)
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist: " + tableName);
			
//			// debug
//			String msg_Toast = "Table doesn't exist: " + tableName;
////			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
//			Methods_dlg.dlg_ShowMessage(actv, msg_Toast);

			return -1;
//			return false;
		}//if (tempBool == true)

		/*------------------------------
		 * Drop the table
		 *------------------------------*/
		// Define the sql
        String sql 
             = "DROP TABLE " + tableName;
        
        // Execute
        try {
			wdb.execSQL(sql);
			
			// Vacuum
			wdb.execSQL("VACUUM");
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "The table dropped => " + tableName);
			
//			// debug
//			String msg_Toast = "The table dropped => " + tableName;
//			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			
			
			wdb.close();
			
			// Return
			return 1;
//			return true;
			
		} catch (SQLException e) {
			// TODO ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ黷ｽ catch ?�ｿｽ�ｿｽu?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽb?�ｿｽ�ｿｽN
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "DROP TABLE => failed (table=" + tableName + "): " + e.toString());
			
//			// debug
//			Toast.makeText(actv, 
//						"DROP TABLE => failed(table=" + tableName, 
//						Toast.LENGTH_LONG).show();
			
			wdb.close();
			
			// Return
			return -2;
//			return false;
		}//try

	}//public boolean dropTable(String tableName) 

	/*********************************
	 * @param columnNames Timestamps => auto-inserted<br>
	 * 			'col_names' doesn't have to have the column
	 * @return false => 1. table doesn't exist<br>
	 * 					2. DB transaction exception
	 *********************************/
	public static boolean
	save_LocationData
	(Activity actv, String tableName, 
		String[] col_names, String[] values) {

		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		/*------------------------------
		 * The table exists?
		 *------------------------------*/
		// The table exists?
		boolean tmp_b = DBUtils.tableExists(actv, tableName);
		
		if (tmp_b == true) {
		
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists: " + tableName);

		} else {//if (tempBool == true)
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist: " + tableName);
			
			wdb.close();
			
			return false;
			
		}//if (tempBool == true)
		
		/*----------------------------
		* 1. Insert data
		----------------------------*/
		try {
			// Start transaction
			wdb.beginTransaction();
			
			// ContentValues
			ContentValues val = new ContentValues();
			
			// Put values
			for (int i = 0; i < col_names.length; i++) {
				val.put(col_names[i], values[i]);
			}//for (int i = 0; i < col_names.length; i++)
			
			// timestamps
			val.put(
				CONS.DB.col_names_Locations_full[1],
				Methods.get_TimeLabel(Methods.getMillSeconds_now()));
			
			val.put(
					CONS.DB.col_names_Locations_full[2],
					Methods.get_TimeLabel(Methods.getMillSeconds_now()));
			
			// Insert data
			long res_i = wdb.insert(tableName, null, val);
			
			if(res_i != -1) {
				
				// Set as successful
				wdb.setTransactionSuccessful();
				
				// Log
				String msg_Log = "insertion => successful";
				Log.d("DBUtils.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			} else {
				
				// Log
				String msg_Log = "insertion => failed";
				Log.d("DBUtils.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			}
			
			// End transaction
			wdb.endTransaction();
			
			wdb.close();
			
			return true;
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Exception! => " + e.toString());
			
			return false;
			
		}//try
		
	}//public insertData(String tableName, String[] col_names, String[] values)


	public static List<Loc>
	get_LocList(Activity actv) {
		// TODO Auto-generated method stub
		/******************************
			validate: table exists?
		 ******************************/
		boolean res = DBUtils.tableExists(actv, CONS.DB.tname_Locations);
		
		if (res == false) {
			
			String msg = "No such table => " + CONS.DB.tname_Locations;
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return null;
			
		}
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();

		////////////////////////////////

		// query

		////////////////////////////////
		Cursor cursor = rdb.query(
					CONS.DB.tname_Locations,
			//		CONS.columns,
					CONS.DB.col_names_Locations_full,
			//		android.provider.BaseColumns._ID + "=",	// where
					null, null,
					null, null, null
		);

		/******************************
			validate
		 ******************************/
		if(cursor == null || cursor.getCount() < 1) {
			
			// Log
			String msg_Log = "cursor == null || cursor.getCount() < 1";
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return null;
			
		}
		
		////////////////////////////////

		// build: Loc list

		////////////////////////////////
		
		cursor.moveToFirst();
		
		List<Loc> loc_List = new ArrayList<Loc>();
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",		// 1, 2
//		"longitude", "latitude",			// 3, 4
//		"memo",						// 5
//		"uploaded_at",						// 6

		for (int i = 0; i < cursor.getCount(); i++) {
			
			Loc loc = new Loc.Builder()
					.setId(cursor.getLong(0))
					.setCreated_at(cursor.getString(1))
					.setModified_at(cursor.getString(2))
					.setLongitude(cursor.getString(3))
					.setLatitude(cursor.getString(4))
					.setMemo(cursor.getString(5))
					.setUploaded_at(cursor.getString(6))
					
					.build();
			
			loc_List.add(loc);
			
			cursor.moveToNext();
			
		}
		
		rdb.close();
		
		return loc_List;
		
	}//get_LocList(Activity actv)

	/*********************************
	 * updateData_SI_all_V2(ShoppingItem si)
	 * @param actv 
	 * 
	 * @return true => update successful<br>
	 * 	false =>
	 * 	<pre>1. Transaction unsuccessful
	 * 2. Exception</pre>
	 *********************************/
	public static boolean 
	update_Loc_Memo
	(Activity actv, Loc loc) {
		// TODO Auto-generated method stub
		/***************************************
		 * Build value set
		 ***************************************/
		ContentValues cv = _update_Loc_Memo__BuildValues(loc);
		
		/***************************************
		 * Setup db
		 ***************************************/
		DBUtils dbm = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbm.getWritableDatabase();
		
		try {
			//
			wdb.beginTransaction();
			
			long res = wdb.update(
					CONS.DB.tname_Locations,
					cv,
					android.provider.BaseColumns._ID + " = ?",
					new String[]{String.valueOf(loc.getId())});
			
			if (res < 1) {
				
				// Log
				Log.d("DBUtils.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
						"Update => Returned less than 1");
				
				wdb.close();
				
				return false;
				
			}	
			
			// Set as successful
			wdb.setTransactionSuccessful();
			
			// End transaction
			wdb.endTransaction();
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Update => Successful");
			
			wdb.close();
			
			return true;
			
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			wdb.close();
			
			return false;
			
		}//try
		
	}//public boolean updateData_SI_all_V2(ShoppingItem si)

	private static ContentValues
	_update_Loc_Memo__BuildValues(Loc loc) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		
		/*
			android.provider.BaseColumns._ID,	// 0
			"created_at", "modified_at",		// 1, 2
			"longitude", "latitude",			// 3, 4
			"memo",						// 5
			"uploaded_at",						// 6
		*/
		
		cv.put(CONS.DB.col_names_Locations_full[5], loc.getMemo());
		cv.put(CONS.DB.col_names_Locations_full[2], 
				Methods.get_TimeLabel(Methods.getMillSeconds_now()));
		
		return cv;
		
	}//_update_Loc_Memo__BuildValues(Loc loc)


	/******************************
		@return null => Can't build loc
	 ******************************/
	public static Loc 
	get_Loc_FromId
	(Activity actv, long base_LocId) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();

		String where = android.provider.BaseColumns._ID + " = ?";
		
		String[] args = new String[]{
				
				String.valueOf(base_LocId)
				
		};
		
		Cursor cursor = rdb.query(
				CONS.DB.tname_Locations,
		//		CONS.columns,
				CONS.DB.col_names_Locations_full,
		//		android.provider.BaseColumns._ID + "=",	// where
				where, args,
				null, null, null
				);
				
		/******************************
			validate: any result?
		 ******************************/
		if (cursor == null || cursor.getCount() < 1) {
			
			// Log
			String msg_Log = "result => "
					+ "(cursor == null || cursor.getCount() < 1)";
			
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			rdb.close();
			
			return null;
			
		}

		////////////////////////////////

		// build: loc

		////////////////////////////////
		cursor.moveToFirst();
		
		Loc loc = new Loc.Builder()
					.setId(cursor.getLong(0))
					.setCreated_at(cursor.getString(1))
					.setModified_at(cursor.getString(2))
					.setLongitude(cursor.getString(3))
					.setLatitude(cursor.getString(4))
					.setMemo(cursor.getString(5))
					.setUploaded_at(cursor.getString(6))
					
					.build();

		rdb.close();
		
		return loc;
		
	}//get_Loc_FromId

}//public class DBUtils

