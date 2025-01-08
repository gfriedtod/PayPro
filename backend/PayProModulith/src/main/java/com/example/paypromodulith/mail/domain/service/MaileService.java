package com.example.paypromodulith.mail.domain.service;

import com.example.paypromodulith.mail.domain.dto.EmailRequest;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MaileService {

  public void send(EmailRequest email) throws MailjetException {
    MailjetClient client;
    MailjetRequest request;
    MailjetResponse response;
    client =
        new MailjetClient(
            ClientOptions.builder()
                .apiKey("afa8a4a91cdd4720022b00ba9fe28547")
                .apiSecretKey("c83ae2631b89e4edef3013b6a4340cc1")
                .build());

    request =
        new MailjetRequest(Emailv31.resource)
            .property(
                Emailv31.MESSAGES,
                new JSONArray()
                    .put(
                        new JSONObject()
                            .put(
                                Emailv31.Message.FROM,
                                new JSONObject()
                                    .put("Email", "gemini.gfried@gmail.com")
                                    )
                            .put(
                                Emailv31.Message.TO,
                                new JSONArray()
                                    .put(
                                        new JSONObject()
                                            .put("Email", email.getTo())
                                            ))
                            .put(Emailv31.Message.SUBJECT, email.getSubject())
                            .put(Emailv31.Message.HTMLPART, email.getMessage())));
    response = client.post(request);
    System.out.println(response.getStatus());
    System.out.println(response.getData());
  }
}
