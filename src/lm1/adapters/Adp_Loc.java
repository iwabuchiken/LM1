package lm1.adapters;

import java.util.List;

import lm1.items.Loc;
import lm1.main.R;
import lm1.utils.CONS;
import lm1.utils.Methods;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Adp_Loc extends ArrayAdapter<Loc> {

	//
	private int resourceId;
	
	Context con;
	
	public Adp_Loc
	(Context context, int textViewResourceId, List<Loc> list) {
		super(context, textViewResourceId, list);
		// TODO �ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ黷ｽ�ｿｽR�ｿｽ�ｿｽ�ｿｽX�ｿｽg�ｿｽ�ｿｽ�ｿｽN�ｿｽ^�ｿｽ[�ｿｽE�ｿｽX�ｿｽ^�ｿｽu
		this.con		= context;
		
		this.resourceId = textViewResourceId;
		
		
	}//public Adp_Loc

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		/*----------------------------
		 * Steps
		 * 1. Inflate
		 * 2. Get views
		 * 3. Get item
		 * 4. Set values
		 * 
		 * 5. Set background
			----------------------------*/
		
		// TODO �ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ�ｿｽ黷ｽ�ｿｽ�ｿｽ�ｿｽ\�ｿｽb�ｿｽh�ｿｽE�ｿｽX�ｿｽ^�ｿｽu
		
        if (v == null) {
        	
            LayoutInflater inflater = 
            		(LayoutInflater) getContext()
    					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            
            v = inflater.inflate(resourceId, null);
        }

        Loc loc = (Loc) getItem(position);

        _getView_SetTexts(v, loc, position);

        ////////////////////////////////

		// background

		////////////////////////////////
        _getView_Background(v, loc, position);
        
		return v;
//		return super.getView(position, convertView, parent);
	}//public View getView(int position, View convertView, ViewGroup parent)

	private void 
	_getView_Background
	(View v, Loc loc, int position) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// vies

		////////////////////////////////
//		TextView tv_Longi = (TextView) v.findViewById(R.id.listrow_loc_list_tv_longi);
//		TextView tv_Lat = (TextView) v.findViewById(R.id.listrow_loc_list_tv_lat);
		
		TextView tv_Memo = 
				(TextView) v.findViewById(R.id.listrow_loc_list_tv_memo);
		

		////////////////////////////////

		// pref position

		////////////////////////////////
		long current_Base = Methods.get_Pref_Long(
								(Activity) con, 
								CONS.Pref.pname_MainActv, 
								CONS.Pref.pkey_MainActv_CurrentBase, 
								CONS.Pref.dflt_LongExtra_value);
		
		long current_Ref = Methods.get_Pref_Long(
				(Activity) con, 
				CONS.Pref.pname_MainActv, 
				CONS.Pref.pkey_MainActv_CurrentRef, 
				CONS.Pref.dflt_LongExtra_value);
		
		////////////////////////////////

		// set: bg

		////////////////////////////////
		if (current_Base != CONS.Pref.dflt_LongExtra_value
				&& current_Base == loc.getId()) {
			
			tv_Memo.setTextColor(
					((Activity)con).getResources().getColor(R.color.white));
			
			tv_Memo.setBackgroundColor(
					((Activity)con).getResources().getColor(R.color.blue1));
			
//			tv_Longi.setTextColor(
//					((Activity)con).getResources().getColor(R.color.yello));
//			tv_Lat.setTextColor(
//					((Activity)con).getResources().getColor(R.color.yello));
			
		} else if (current_Ref != CONS.Pref.dflt_LongExtra_value
				&& current_Ref == loc.getId()) {
			
			tv_Memo.setTextColor(
					((Activity)con).getResources().getColor(R.color.black));
			
			tv_Memo.setBackgroundColor(
					((Activity)con).getResources().getColor(R.color.yello));
			
//			tv_Longi.setTextColor(
//					((Activity)con).getResources().getColor(R.color.yello));
//			tv_Lat.setTextColor(
//					((Activity)con).getResources().getColor(R.color.yello));
		} else {
			
			tv_Memo.setTextColor(
					((Activity)con).getResources().getColor(R.color.black));
			
			tv_Memo.setBackgroundColor(
					((Activity)con).getResources().getColor(R.color.white));
			
//			tv_Longi.setTextColor(
//					((Activity)con).getResources().getColor(R.color.white));
//			tv_Lat.setTextColor(
//					((Activity)con).getResources().getColor(R.color.white));

		}
		
		
	}//_getView_Background

	private void
	_getView_SetTexts(View v, Loc loc, int position) {
		/*********************************
		 * Day
		 *********************************/
		// Get views
		TextView tv_Date = (TextView) v.findViewById(R.id.listrow_loc_list_tv_date);
		
		// Get data
		String date = loc.getCreated_at();
		
		String day = date.split("_")[0];
		
		tv_Date.setText(day);
		
		/*********************************
		 * Time
		 *********************************/
		// Get views
		TextView tv_Time = (TextView) v.findViewById(R.id.listrow_loc_list_tv_time);
		
		// Get data
		String time = date.split("_")[1];
		
		tv_Time.setText(time);
		
		/*********************************
		 * Longitude
		 *********************************/
		// Get views
		TextView tv_Longi = (TextView) v.findViewById(R.id.listrow_loc_list_tv_longi);
		
		// Get data
		String longi = loc.getLongitude();
		
		if (longi.length() > 12) {
			
			longi = longi.substring(0, 12);
			
		}
		
		tv_Longi.setText(longi);
		
		/*********************************
		 * Latitude
		 *********************************/
		// Get views
		TextView tv_Lat = (TextView) v.findViewById(R.id.listrow_loc_list_tv_lat);
		
		// Get data
		String lat = loc.getLatitude();
		
		if (lat.length() > 12) {
					
			lat = lat.substring(0, 12);
			
		}
		
		tv_Lat.setText(lat);

		/*********************************
		 * Memo
		 *********************************/
		String memo = loc.getMemo();
		
		TextView tv_Memo = (TextView) v.findViewById(R.id.listrow_loc_list_tv_memo);
		
		if (memo != null) {
			
			tv_Memo.setText(memo);
			
		} else {
			
			tv_Memo.setText("");
			
		}
		
		/*********************************
		 * Background
		 *********************************/
//		// TODO Auto-generated method stub
//		SharedPreferences prefs = ((Activity)con).getSharedPreferences(
//				CONS.Prefs.pName_LM,
//				Context.MODE_PRIVATE);
//		
//		int savedPosition = prefs.getInt(
//				CONS.Prefs.pKey_CurrentItemPosition,
//				-1);
//
//		if (savedPosition == position) {
//			
//			tv_Longi.setBackgroundResource(R.color.gold2);
//			tv_Longi.setTextColor(Color.BLACK);
//			
//			tv_Lat.setBackgroundResource(R.color.gold2);
//			tv_Lat.setTextColor(Color.BLACK);
//			
//		} else if (savedPosition == -1) {//if (savedPosition == position)
//			
//		} else {//if (savedPosition == position)
//			
//			tv_Longi.setBackgroundColor(Color.BLACK);
//			tv_Longi.setTextColor(Color.WHITE);
//			
//			tv_Lat.setBackgroundColor(Color.BLACK);
//			tv_Lat.setTextColor(Color.WHITE);
//			
//		}//if (savedPosition == position)
		
	}//_getView_SetTexts(View convertView, Loc loc)

}//public class ItemListAdapter extends ArrayAdapter<ShoppingItem>
