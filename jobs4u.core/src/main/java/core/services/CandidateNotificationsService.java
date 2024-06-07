package core.services;

import core.protocol.Jobs4UProtocol;
import core.protocol.ProtocolCodes;
import core.protocol.UnsignedInteger;

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

    /**
     * Requests notifications for the specified email.
     * This method communicates with a server using a custom protocol to fetch the notifications.
     * The notifications are then matched with the existing notifications in the system.
     * The matched notifications are returned.
     *
     * @param email The email for which to request notifications.
     * @return A list of notifications that match the requested notifications.
     */
    public List<String> requestNotifications(String email) {
        try {
            Socket socket = new Socket("127.0.0.1", 2005);
            DataInputStream inData = new DataInputStream(socket.getInputStream());

            Jobs4UProtocol protocol = new Jobs4UProtocol(socket);
            protocol.sendNotifications(email);

            inData.readByte();
            byte code = inData.readByte();

            if(code == ProtocolCodes.NOTIFICATIONS.code()){
                int dataLenL = new UnsignedInteger(inData.readByte()).positiveValue();
                int dataLenM = new UnsignedInteger(inData.readByte()).positiveValue();

                int lengthData = (256 * dataLenM) + dataLenL;

                byte[] notifications = inData.readNBytes(lengthData);

                String notification = new String(notifications, StandardCharsets.UTF_8);

                String notificationEntries = notification.replaceAll("[\\[\\]\"]", "").trim();

                String[] appEntries = notificationEntries.split(",");

                socket.close();

                return new ArrayList<>(List.of(appEntries));
            } else{
                System.out.println("No notifications found for the candidate.");
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}