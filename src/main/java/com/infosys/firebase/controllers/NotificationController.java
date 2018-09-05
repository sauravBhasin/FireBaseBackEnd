package com.infosys.firebase.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

@RestController
public class NotificationController {
	
	static {
		FileInputStream serviceAccount;
		try {
			serviceAccount = new FileInputStream("F:/serviceKey.json");

		FirebaseOptions options;
			options = new FirebaseOptions.Builder()
			    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			    .setDatabaseUrl("https://new-project1-73257.firebaseio.com/")
			    .build();
			FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@GetMapping("/v1/notify")
	public String sendNotification() throws IOException, FirebaseMessagingException {
		
		
		String registrationToken = "eLHACumxxJ0:APA91bG2rbTNeZCaRvybW6CHlYbg_nAYmCs2qXdgNJktuJLwI7km0WV2XJJw771BgMMKjkAO3Q_6beMA2ao3tLqJF0xp2mdqe-REZh8KD0Xc5Shkw3mGMAGjtLbsaLm1gOB162UKjZHi";

		// See documentation on defining a message payload.
		Message message = Message.builder()
		    .putData("score", "850")
		    .putData("time", "2:45")
		    .setToken(registrationToken)
		    .build();

		// Send a message to the device corresponding to the provided
		// registration token.
		String response = FirebaseMessaging.getInstance().send(message);
		// Response is a message ID string.
		System.out.println("Successfully sent message: " + response);
		
		return "Hello";
	}
}
