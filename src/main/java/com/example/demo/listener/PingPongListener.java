package com.example.demo.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Component
public class PingPongListener extends ListenerAdapter {

	@Autowired
	public PingPongListener(JDA jda) {
		jda.addEventListener(this);
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		final Message message = event.getMessage();
		if (event.getAuthor().isBot()) {
			return;
		}
		if (message.getContentRaw().equals("!ping")) {
			MessageChannel channel = event.getChannel();
			channel.sendMessage("Pong!").queue();
		}
	}
	
}
