import java.util.Random;
import java.io.*;

public class PasswordManager implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * A list of passwords stored by the manager.
     */
    Password[] passwords = new Password[10];

    /**
     * The number of passwords stored by the manager.
     */
    int count = 0;

    /**
     * The key to password manager to gain access.
     */
    String key = null;

    /**
     * Empty constructor.
     */
    public PasswordManager() {
    }

    /**
     * Resizes the password manager list to account for overflow.
     */
    public void resize() {
        Password[] newPasswords = new Password[passwords.length * 2];
        for (int i = 0; i < passwords.length; i++) {
            newPasswords[i] = passwords[i];
        }
        passwords = newPasswords;
    }

    /**
     * Getter for the key.
     * 
     * @return the key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key
     * 
     * @param key is the new key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the number of passwords stored in the list.
     * 
     * @return the count integer.
     */
    public int getCount() {
        return count;
    }

    /**
     * Saves this object to a file.
     * 
     * @param fileName is the name of the file.
     */
    public void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(this);
            System.out.println("Data saved to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the object from file name.
     * 
     * @param fileName is the name of the file to get from.
     * @return the PasswordManager class.
     */
    public static PasswordManager loadFromFile(String fileName) {
        PasswordManager passwordManager = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            passwordManager = (PasswordManager) inputStream.readObject();
            System.out.println("Data loaded from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return passwordManager;
    }

    /**
     * Edit the password for a site.
     * 
     * @param site     is the name / nickname of the site
     * @param password is the password for the site
     */
    public void editPassword(String site, String password) {
        for (int i = 0; i < passwords.length; i++) {
            if (passwords[i].getSite().equals(site)) {
                passwords[i].setPassword(password);

                // You may also edit the password to be completely random
                // Password myNewPassword = new Password(site, getRandPass());
                // passwords[i] = myNewPassword;
            }
        }
    }

    /**
     * Generates a random password for a site and stores it.
     * 
     * @param site is the name of the site.
     */
    public void storePassword(String site) {
        if (count >= passwords.length) {
            resize();
        }
        count++;
        for (int i = 0; i < passwords.length; i++) {
            if (passwords[i] == null) {
                Password myNewPassword = new Password(site, getRandPass());
                passwords[i] = myNewPassword;
            }
        }
    }

    /**
     * Creates a random password.
     * 
     * @return a random password string.
     */
    private String getRandPass() {
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        int PASSWORD_LENGTH = 15;

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

    /**
     * Stores a given password and site and stores it.
     * 
     * @param site     is the name of the site.
     * @param password is the password.
     */
    public void storePassword(String site, String password) {
        if (count >= passwords.length) {
            resize();
        }
        count++;
        for (int i = 0; i < passwords.length; i++) {
            if (passwords[i] == null) {
                Password myNewPassword = new Password(site, password);
                passwords[i] = myNewPassword;
            }
        }
    }

    /**
     * Gets the password from a given site.
     * 
     * @param site the given site.
     * @return password for the site.
     */
    public String getPassword(String site) {
        for (int i = 0; i < passwords.length; i++) {
            if (passwords[i].getSite().equals(site)) {
                return passwords[i].getPassword();
            }
        }
        return "Password Not Found";
    }

    /**
     * Delete the password from the list of passwords.
     * 
     * @param site is the site of password to delete.
     */
    public void erasePassword(String site) {
        for (int i = 0; i < passwords.length; i++) {
            if (passwords[i].getSite().equals(site)) {
                passwords[i] = null;
                count--;
                return;
            }
        }
    }
}
