import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        PasswordManager loadedPasswordManager = PasswordManager.loadFromFile("passwordManager.ser");
        if (loadedPasswordManager != null) {
            boolean stop = false;
            System.out.println("Enter your password manager's passcode");
            if (!scanner.nextLine().equals(loadedPasswordManager.getKey())) {
                stop = true;
            }

            while (!stop) {
                System.out.println(
                        "1. Store New Password\n2. Create and Store New Password\n3. Get Password\n4. Edit Password\n5. Delete Password\n6. Quit\n");
                String choice = scanner.nextLine();
                if (choice.equals("1")) {
                    System.out.println("Site Name (remember this)");
                    String site = scanner.nextLine();
                    System.out.println("Your Password");
                    String password = scanner.nextLine();

                    loadedPasswordManager.storePassword(site, password);
                    System.out.println("Stored. Try getting your password!");
                }
                if (choice.equals("2")) {
                    System.out.println("Site Name (remember this)");
                    String site = scanner.nextLine();

                    loadedPasswordManager.storePassword(site);
                    System.out.println("Stored. Try getting your password!");
                }
                if (choice.equals("3")) {
                    System.out.println("Site Name (remember this)");
                    String site = scanner.nextLine();
                    System.out.println("Your Password Is " + loadedPasswordManager.getPassword(site));
                    System.out.println("Hit enter to continue");
                    scanner.nextLine();
                }
                if (choice.equals("4")) {
                    System.out.println("Site Name (remember this)");
                    String site = scanner.nextLine();
                    System.out.println("Your Password");
                    String password = scanner.nextLine();

                    loadedPasswordManager.editPassword(site, password);
                    System.out.println("Edited. Try getting your password!");
                }
                if (choice.equals("5")) {
                    System.out.println("Site Name (remember this)");
                    String site = scanner.nextLine();

                    loadedPasswordManager.erasePassword(site);
                    System.out.println("Deleted.");
                }
                if (choice.equals("6")) {
                    stop = true;
                }
                loadedPasswordManager.saveToFile("passwordManager.ser");
            }

        } else {
            System.out.println("Create a passcode for your password manager:");
            String passcode = scanner.nextLine();
            PasswordManager myPasswords = new PasswordManager();
            myPasswords.setKey(passcode);
            System.out.println("A Password Manager has been created. Run Again.");
            myPasswords.saveToFile("passwordManager.ser");
        }

        scanner.close();
    }
}
