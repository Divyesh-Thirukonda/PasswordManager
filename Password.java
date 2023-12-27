public class Password {

    /**
     * Site for the password.
     */
    private String site;

    /**
     * Password for site.
     */
    private String password;

    /**
     * Constructs the Password class.
     * 
     * @param site     is the name of the site. Ex: Twitter, Instagram...
     * @param password is the password for that site.
     */
    public Password(String site, String password) {
        this.site = site;
        this.password = password;
    }

    /**
     * Getter for the site.
     * 
     * @return the name of the site.
     */
    public String getSite() {
        return site;
    }

    /**
     * Getter for the password.
     * 
     * @return the name of the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password.
     * 
     * @param password is the password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the details for the password.
     * 
     * @return the password for [site] is [password]
     */
    @Override
    public String toString() {
        return "The password for " + site + " is " + password;
    }
}
