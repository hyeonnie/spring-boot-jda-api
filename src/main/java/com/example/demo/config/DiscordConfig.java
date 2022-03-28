package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

@Configuration
@EnableConfigurationProperties(DiscordConfig.Properties.class)
public class DiscordConfig {

	private final Properties properties;
	
	public DiscordConfig(Properties properties) {
		this.properties = properties;
	}
	
	@Bean(destroyMethod="shutdown")
	public JDA jda() throws Exception {
		JDA jda = JDABuilder
				.createDefault(properties.getBot().getToken())
				.setRawEventsEnabled(true)
				.setStatus(OnlineStatus.ONLINE)
				.setActivity(Activity.playing(properties.getBot().getActivity()))
				.build();
		return jda;
	}
	
	@Getter @Setter
	@ConfigurationProperties(prefix = "discord")
	public static final class Properties {
		
		private Bot bot;
		
		@Getter @Setter
		public static class Bot {
			private String token;
			private String activity;
		}
		
	}
	
}
