package core.services;

import core.protocol.Jobs4UProtocol;
import core.protocol.ProtocolCodes;
import core.protocol.UnsignedInteger;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CandidateApplicationsService {

    public List<String> requestApplications(String email) {
        try {
            Socket socket = new Socket("127.0.0.1", 2005);
            DataInputStream inData = new DataInputStream(socket.getInputStream());

            Jobs4UProtocol protocol = new Jobs4UProtocol(socket);
            protocol.sendApplications(email);

            inData.readByte();
            byte code = inData.readByte();

            if (code == ProtocolCodes.APPLICATIONS.code()) {

                int dataLenL = new UnsignedInteger(inData.readByte()).positiveValue();
                int dataLenM = new UnsignedInteger(inData.readByte()).positiveValue();

                int lenghtData = (256 * dataLenM + dataLenL);

                byte[] listApp = inData.readNBytes(lenghtData);

                String application = new String(listApp, StandardCharsets.UTF_8);

                String applicationsArray = application.replaceAll("[\\[\\]\"]", "").trim();
                String[] appEntries = applicationsArray.split(",");

                return new ArrayList<>(Arrays.asList(appEntries));
            } else {
                System.out.println("You do not have any applications! ");
                return null;
            }
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
