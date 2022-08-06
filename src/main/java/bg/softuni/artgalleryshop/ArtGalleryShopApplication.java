package bg.softuni.artgalleryshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ArtGalleryShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtGalleryShopApplication.class, args);
	}
}
