package com.wallpaper.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages="com.wallpaper.api")
@ComponentScan("com.wallpaper.api")
public class WallpaperApiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WallpaperApiAppApplication.class, args);
	}

}
