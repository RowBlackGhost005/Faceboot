/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.notifications;

import com.masa.domain.Notification;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.Mail;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jjavi
 */
public class EmailProvider extends BaseNotificator {

    public final String API_KEY;

    public EmailProvider(INotificator notificator) {
        super(notificator);
        this.API_KEY = "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";
    }

    @Override
    public void sendNotification(Notification notification) {
        Email from = new Email(notification.getFrom().getEmail());
        Email to = new Email(notification.getTo().getEmail());
        String subject = "Faceboot";
        Content content = new Content(
                "text/plain",
                notification.getMessage()
        );
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(API_KEY);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            Logger.getLogger(EmailProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
