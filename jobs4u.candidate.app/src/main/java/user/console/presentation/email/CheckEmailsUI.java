package user.console.presentation.email;

import core.domain.email.Email;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class CheckEmailsUI extends AbstractUI {

    @Override
    protected boolean doShow() {
        System.out.println("Checking the most recent emails...\n");

        try {
            Socket sock = new Socket("127.0.0.1", 1010);

            ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
            List<Email> messages = (List<Email>) ois.readObject();
            handleEmails(messages);

            sock.close();

            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    private void handleEmails(List<Email> messages) {
        System.out.println("\nReceived emails: ");
        for (Email message : messages) {
            System.out.println(message);
            System.out.println("-------------------");
        }
    }


    @Override
    public String headline() {
        return "Check Emails";
    }
}
