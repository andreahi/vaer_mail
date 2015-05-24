package vaermail;

class MailInfo {
	
	private String mail;
	public MailInfo(String mail) {
		this.mail = mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMail() {
		return mail;
	}

	@Override
	public String toString(){
		return mail;
	}
}