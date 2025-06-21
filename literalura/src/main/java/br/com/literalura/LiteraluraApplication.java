package br.com.literalura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import br.com.literalura.ui.Menu;

@SpringBootApplication
public class LiteraluraApplication {
    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(Menu menu) {
        return args -> menu.exibir();
    }
}
