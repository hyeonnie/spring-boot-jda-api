package com.example.demo.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Component
public class SlashCommandListener extends ListenerAdapter {

	@Autowired
	public SlashCommandListener(JDA jda) {
		jda.addEventListener(this);
		jda.updateCommands().queue();
		jda.upsertCommand("test1", "test1").queue();
//		jda.upsertCommand("test2", "test2")
//			.addOptions(new OptionData(OptionType.STRING, "subCommand1", "subCommand")
//					.setRequired(true))
//			.queue();
	}
	
	@Override
	public void onSlashCommandInteraction (SlashCommandInteractionEvent event) {
		switch (event.getName()) {
		case "test" :
			event.reply(event.getOption("content").getAsString()).queue();
			break;
		default :
			break;
		}
	}
	
}
