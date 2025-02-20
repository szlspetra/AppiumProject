package org.example.utils;

import org.example.constants.TestData;
import org.testng.annotations.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FromStringTerm;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Demonstration class for accessing Gmail emails via IMAP protocol
 * Note: Requires Gmail account with IMAP access enabled and app password setup
 */
public class GmailTest {
    static Folder inbox;
    static Store store;

    @Test
    public static List<Map<String, String>> getFolder (String senderEmail) throws MessagingException {
   // public static void getFolder () throws MessagingException {
        // IMAP beállítások
        String host = "imap.gmail.com";
        String user = System.getenv("GMAIL_ADDRESS");
        String password = System.getenv("GMAIL_PWD");

        try {
            // IMAP szerver beállítása
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");
            properties.put("mail.imaps.host", host);
            properties.put("mail.imaps.port", "993");
            properties.put("mail.imaps.ssl.enable", "true");

            // Kapcsolódás a szerverhez
            Session session = Session.getDefaultInstance(properties, null);
            store = session.getStore("imaps");
            store.connect(host, user, password);

            // INBOX mappa megnyitása
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            List<Map<String, String>> msg = searchMessagesBySender(senderEmail);
            return msg;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // Mappa és kapcsolat lezárása
            closeResources();
        }
        return null;
    }

    /**
     * Searches messages from specific sender and extracts content
     * @param senderEmail Email address to search for
     * @return Array of messages with content maps
     */
    private static List<Map<String, String>> searchMessagesBySender(String senderEmail) throws MessagingException, IOException {

        SearchTerm searchTerm = new FromStringTerm(senderEmail);
        Message[] messages = inbox.search(searchTerm);
        String targetPattern = "confirmemail?email=";

        List<Map<String, String>> result = new ArrayList<>();

        for (Message message : messages) {
            Map<String, String> contentMap = new HashMap<>();

            contentMap.put("Subject", message.getSubject());
            contentMap.put("From", InternetAddress.toString(message.getFrom()));
            contentMap.put("Date", message.getSentDate().toString());

            contentMap.putAll(extractContent(message));

            //String emailBody = contentMap.getOrDefault("Body", "");
            List<String> links = extractLinks(contentMap.get("HTML"));

            for (String link : links) {
                if (link.contains(targetPattern)) {
                    contentMap.put("ConfirmationLink", link);
                    break;
                }
            }
            result.add(contentMap);
        }
        return result;
    }

    // Helper method to extract all links from content
    private static List<String> extractLinks(String content) {
        List<String> links = new ArrayList<>();
        String regex = "<a[^>]+href\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            links.add(matcher.group(1).replaceAll("&amp;", "&"));
        }

        return links;
    }


    /**
     * Extracts text/plain and text/html content from message
     */
    private static Map<String, String> extractContent(Part part) throws MessagingException, IOException {
        Map<String, String> content = new HashMap<>();

        if (part.isMimeType("text/plain")) {
            content.put("Text", (String) part.getContent());
        }
        else if (part.isMimeType("text/html")) {
            content.put("HTML", (String) part.getContent());
        }
        else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            for (int i = 0; i < multipart.getCount(); i++) {
                content.putAll(extractContent(multipart.getBodyPart(i)));
            }
        }
        return content;
    }

    /**
     * Safely closes email resources
     */
    private static void closeResources() {
        try {
            if (inbox != null && inbox.isOpen()) {
                inbox.close(false);
            }
            if (store != null && store.isConnected()) {
                store.close();
            }
        } catch (MessagingException e) {
            System.err.println("Error closing resources: " + e.getMessage());

        }
    }
}
