package com.niit.collaboration.restcontroller;



import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import oracle.net.ns.Message;

@Controller
public class ChatController {
	
	private static final Logger Logger = LoggerFactory.getLogger(ChatController.class);
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public <OutputMessage> OutputMessage sendMessage(Message message){
		
		Logger.debug("Callong the method sendMessage");
		Logger.debug(" Message : ",message.getMessage());
		Logger.debug("Message ID :",message.getId());
		
		return new OutputMessage(message, new Date());
	}

}
