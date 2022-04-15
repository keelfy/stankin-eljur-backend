package org.keelfy.eljur.api;

import org.keelfy.eljur.api.configuration.property.CredentialsProperties;
import org.keelfy.eljur.api.configuration.property.InvitationProperties;
import org.keelfy.eljur.api.configuration.property.MailProperties;
import org.keelfy.eljur.api.configuration.property.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableConfigurationProperties({
		CredentialsProperties.class,
		SecurityProperties.class,
		MailProperties.class,
		InvitationProperties.class,
})
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.keelfy.eljur.data", "org.keelfy.eljur.api"}, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)
})
@EntityScan("org.keelfy.eljur.data.entity")
@EnableJpaRepositories("org.keelfy.eljur.data.repository")
public class DigitalJournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalJournalApplication.class, args);
	}

}
