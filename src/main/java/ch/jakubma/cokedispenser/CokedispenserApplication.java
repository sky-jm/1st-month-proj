package ch.jakubma.cokedispenser;

import ch.jakubma.cokedispenser.Coin.CoinCurrency;
import ch.jakubma.cokedispenser.Coin.CoinReturner;
import ch.jakubma.cokedispenser.DispenserDb.DatabaseLoader;
import ch.jakubma.cokedispenser.DispenserDb.model.Drink;
import ch.jakubma.cokedispenser.DispenserDb.repository.DrinkRepository;
import ch.jakubma.cokedispenser.DispenserLogger.CustomLogger;
import ch.jakubma.cokedispenser.DispenserMain.DispenserMachine;
import ch.jakubma.cokedispenser.Payment.PayByCash;
import ch.jakubma.cokedispenser.Payment.PaymentStrategy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.Scanner;
@Configuration
@EntityScan("ch.jakubma.cokedispenser.DispenserDb.model")
@EnableJpaRepositories(basePackages = { "ch.jakubma.cokedispenser.DispenserDb.repository" })
@SpringBootApplication
public class CokedispenserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CokedispenserApplication.class, args);
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }
}