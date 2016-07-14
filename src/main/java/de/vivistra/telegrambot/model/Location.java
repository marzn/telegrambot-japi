package de.vivistra.telegrambot.model;

import org.json.JSONObject;

/**
 * Represents for example a PDF send trough Telegram
 */
public class Location {
	private Double longitude;
	private Double latitude;

	/**
	 * Creates a document object
	 * 
	 * @param fileId
	 * @param thumb
	 * @param fileName
	 * @param mimeType
	 * @param fileSize
	 */
	public Location(Double longitude, Double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	/**
	 * Create a document object from a JSON String
	 * 
	 * @param documentObject
	 * @return Document
	 */
	public static Location fromJSON(JSONObject documentObject) {

		Double longitude = documentObject.getDouble("longitude");

		// Optional
		JSONObject thumbJson = documentObject.optJSONObject("thumb");
		PhotoSize thumb = (thumbJson != null) ? PhotoSize.fromJSON(thumbJson) : null;

		Double latitude = documentObject.getDouble("latitude");
		return new Location(longitude, latitude);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.longitude == null) ? 0 : this.longitude.hashCode());
		result = prime * result + ((this.latitude == null) ? 0 : this.latitude.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Location)) {
			return false;
		}
		Location other = (Location) obj;
		if (this.longitude == null) {
			if (other.longitude!= null) {
				return false;
			}
		} else if (!this.longitude.equals(other.longitude)) {
			return false;
		}
		if (this.latitude == null) {
			if (other.latitude != null) {
				return false;
			}
		} else if (!this.latitude.equals(other.latitude)) {
			return false;
		}
		return true;
	}

    public Double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Double aLongitude)
    {
        longitude = aLongitude;
    }

    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Double aLatitude)
    {
        latitude = aLatitude;
    }
}
