package com.example.blooddrop;

import android.util.Log;
import java.util.concurrent.Executors;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    private final String smtpHost = "smtp.gmail.com";
    private final String smtpPort = "587";
    private final String senderEmail = "techfixcomputershop@gmail.com"; // Replace with your email
    private final String senderPassword = "hhma oidh lbct hggi"; // Replace with your app password

    public void sendEmail(String recipientEmail, String subject, String messageBody) {
        Executors.newSingleThreadExecutor().execute(() -> {
            Properties props = new Properties();
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", smtpPort);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(senderEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                message.setSubject(subject);
                message.setText(messageBody);

                Transport.send(message);
                Log.d("EmailSender", "Email sent successfully to " + recipientEmail);
            } catch (Exception e) {
                Log.e("EmailSender", "Error sending email: " + e.getMessage(), e);
            }
        });
    }
}

