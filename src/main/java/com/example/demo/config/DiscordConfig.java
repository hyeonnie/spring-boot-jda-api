package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.listener.PingPongListener;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Configuration
@EnableConfigurationProperties(DiscordConfig.Properties.class)
public class DiscordConfig extends ListenerAdapter {

	private final Properties properties;
	
	public DiscordConfig(Properties properties) {
		this.properties = properties;
	}
	
	@Bean(destroyMethod="shutdown")
	public JDA jda() throws Exception {
		JDA jda = JDABuilder
				.createDefault(properties.getBot().getToken())
				.build();
		jda.addEventListener(pingPongEventListener());
		return jda;
	}
	
	public EventListener pingPongEventListener() {
		return new PingPongListener();
	}
	
	@Getter @Setter
	@ConfigurationProperties(prefix = "discord")
	public static final class Properties {
		
		private Bot bot;
		
		@Getter @Setter
		public static class Bot {
			private String token;
		}
		
	}
	
}
