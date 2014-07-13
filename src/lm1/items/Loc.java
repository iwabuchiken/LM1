package lm1.items;


public class Loc {

	public Loc(Builder builder) {
		super();
		this.id				= builder.id;
		this.created_at		= builder.created_at;
		this.modified_at	= builder.modified_at;
		this.longitude		= builder.longitude;
		this.latitude		= builder.latitude;
		this.memo			= builder.memo;
		this.uploaded_at	= builder.uploaded_at;
	}



	long id;					// 0
	String created_at;			// 1
	String modified_at;			// 2

	String longitude;			// 3
	String latitude;			// 4
	String memo;				// 5
	
	String uploaded_at;			// 6

	public long getId() {
		return id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getModified_at() {
		return modified_at;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getMemo() {
		return memo;
	}

	public String getUploaded_at() {
		return uploaded_at;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public void setModified_at(String modified_at) {
		this.modified_at = modified_at;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setUploaded_at(String uploaded_at) {
		this.uploaded_at = uploaded_at;
	}
	
	/*********************************
	 * Getter/Setter
	 *********************************/
	
	
	/*********************************
	 * Class: Builder
	 *********************************/
	public static class Builder {
		
		long id;					// 0
		String created_at;			// 1
		String modified_at;			// 2

		String longitude;			// 3
		String latitude;			// 4
		String memo;				// 5
		
		String uploaded_at;			// 6
		
		public Loc build() {
			
			return new Loc(this);
			
		}

		public Builder setId(long id) {
			this.id = id;	return this;
		}

		public Builder setCreated_at(String created_at) {
			this.created_at = created_at;	return this;
		}

		public Builder setModified_at(String modified_at) {
			this.modified_at = modified_at;	return this;
		}

		public Builder setLongitude(String longitude) {
			this.longitude = longitude;	return this;
		}

		public Builder setLatitude(String latitude) {
			this.latitude = latitude;	return this;
		}

		public Builder setMemo(String memo) {
			this.memo = memo;	return this;
		}

		public Builder setUploaded_at(String uploaded_at) {
			this.uploaded_at = uploaded_at;	return this;
		}

	}//public static class Builder

	
}
