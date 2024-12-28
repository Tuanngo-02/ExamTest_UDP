package Config;

public enum Common {
	IPCONFIG("localhost"),
	DATABASECONFIG("jdbc:mysql://localhost:3306/test_online",
					"root",
					"root");
	private String ipAddress;
	private String dbUrl;
	private String dbUser;
	private String dbPass;

	private Common(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	private Common(String dbUrl, String dbUser, String dbPass) {
		this.dbUrl = dbUrl;
		this.dbUser = dbUser;
		this.dbPass = dbPass;
	}

	
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public String getDbUser() {
		return dbUser;
	}
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
	public String getDbPass() {
		return dbPass;
	}
	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
