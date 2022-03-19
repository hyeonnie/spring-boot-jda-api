package com.example.demo.listener;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PingPongListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		final Message message = event.getMessage();
		if (message.getContentRaw().equals("!ping")) {
			MessageChannel channel = event.getChannel();
			long time = System.currentTimeMillis();
			channel.sendMessage("Pong!")
				.queue(response -> {
					response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
				});
		}
	}
	
}
