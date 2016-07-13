package de.vivistra.telegrambot.model;

import org.json.JSONObject;

/**
 * Represents for example a PDF send trough Telegram
 */
public class Contact {
	private String firstname;
    private String lastname;
    private String phone;
    private String mail;

	/**
	 * Creates a document object
	 * 
	 * @param fileId
	 * @param thumb
	 * @param fileName
	 * @param mimeType
	 * @param fileSize
	 */
	public Contact(String firstname,  String lastname, String phone, String mail) {
	    this.firstname= firstname;
        this.lastname= lastname;
        this.phone= phone;
        this.mail= mail;
	}

	/**
	 * Create a document object from a JSON String
	 * 
	 * @param documentObject
	 * @return Document
	 */
	public static Contact fromJSON(JSONObject documentObject) {

		String firstname = documentObject.getString("first_name");
        String lastname = "";//documentObject.getString("lastname");
        String phone = documentObject.getString("phone_number");
        String mail = "";//documentObject.getString("mail");

		return new Contact(firstname, lastname, phone, mail);
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
		result = prime * result + ((this.firstname == null) ? 0 : this.firstname.hashCode());
		result = prime * result + ((this.lastname == null) ? 0 : this.lastname.hashCode());
        result = prime * result + ((this.phone == null) ? 0 : this.phone.hashCode());
        result = prime * result + ((this.mail == null) ? 0 : this.mail.hashCode());
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
		if (!(obj instanceof Contact)) {
			return false;
		}
		Contact other = (Contact) obj;
		if (this.firstname == null) {
			if (other.firstname!= null) {
				return false;
			}
		} else if (!this.firstname.equals(other.firstname)) {
			return false;
		}
		if (this.lastname == null) {
			if (other.lastname != null) {
				return false;
			}
		} else if (!this.lastname.equals(other.lastname)) {
			return false;
		}
        if (this.phone == null) {
            if (other.phone != null) {
                return false;
            }
        } else if (!this.phone.equals(other.phone)) {
            return false;
        }
        if (this.mail == null) {
            if (other.mail != null) {
                return false;
            }
        } else if (!this.mail.equals(other.mail)) {
            return false;
        }
		return true;
	}

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String aFirstname)
    {
        firstname = aFirstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String aLastname)
    {
        lastname = aLastname;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String aPhone)
    {
        phone = aPhone;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String aMail)
    {
        mail = aMail;
    }
}
