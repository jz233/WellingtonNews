package demo.zjj.wellingtonnews.domain;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * representation of  the JSON string structure
 */
public class NewsInfo implements Parcelable{
	public String id;
	public String name;
	public String url;
	public String date;
	public String imageUrl;
	public String description;

	public LatLong latLng;

	protected NewsInfo(Parcel in) {
		id = in.readString();
		name = in.readString();
		url = in.readString();
		date = in.readString();
		imageUrl = in.readString();
		description = in.readString();
	}

	public static final Creator<NewsInfo> CREATOR = new Creator<NewsInfo>() {
		@Override
		public NewsInfo createFromParcel(Parcel in) {
			return new NewsInfo(in);
		}

		@Override
		public NewsInfo[] newArray(int size) {
			return new NewsInfo[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(id);
		out.writeString(name);
		out.writeString(url);
		out.writeString(date);
		out.writeString(imageUrl);
		out.writeString(description);
	}

	public class LatLong{
		public String latitude;
		public String longitude;
	}

}
