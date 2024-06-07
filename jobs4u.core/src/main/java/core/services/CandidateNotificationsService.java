package core.services;

import core.domain.notification.Notification;
import core.protocol.Jobs4UProtocol;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * A service class for managing operations related to candidate notifications.
 * This class provides methods for requesting notifications for a specific email.
 * It communicates with a server using a custom protocol to fetch the notifications.
 * The notifications are then matched with the existing notifications in the system.
 * The matched notifications are returned to the caller.
 *
 * @author 1220812@isep.ipp.pt
 */

public class CandidateNotificationsService {
    NotificationService notificationService = new NotificationService();

    /**
     * Requests notifications for the specified email.
     * This method communicates with a server using a custom protocol to fetch the notifications.
     * The notifications are then matched with the existing notifications in the system.
     * The matched notifications are returned.
     *
     * @param email The email for which to request notifications.
     * @return A list of notifications that match the requested notifications.
     */
    public List<Notification> requestNotifications(String email) {
        List<Notification> matchedNotifications = new ArrayList<>();
        try {
            Socket socket = new Socket("127.0.0.1", 2005);
            DataInputStream inData = new DataInputStream(socket.getInputStream());

            Jobs4UProtocol protocol = new Jobs4UProtocol(socket);
            protocol.sendNotifications(email);

            byte list = inData.readByte();
            byte[] listNot = inData.readNBytes(list);

            String notification = new String(listNot, StandardCharsets.UTF_8);

            List<Notification> allNotifications = (List<Notification>) notificationService.allNotifications();

            String[] notificationEntries = notification.replaceAll("[\\[\\]]", "").split(",");

            for (String entry : notificationEntries) {
                String cleanedEntry = entry.replaceAll("[^0-9]", "");
                if (!cleanedEntry.isEmpty()) {
                    int id = Integer.parseInt(cleanedEntry);

                    for (Notification notification1 : allNotifications) {
                        if (notification1.identity().equals(id)) {
                            matchedNotifications.add(notification1);
                        }
                    }
                } else {
                    System.out.println("You don't have any notifications! ");
                }
            }
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return matchedNotifications;
    }
}