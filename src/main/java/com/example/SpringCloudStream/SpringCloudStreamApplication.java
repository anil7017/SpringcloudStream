package com.example.SpringCloudStream;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
@Configuration
@Slf4j
public class SpringCloudStreamApplication {

	private AtomicInteger count = new AtomicInteger(0);

	private Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamApplication.class, args);
    }

    @Bean
    public Supplier<Integer> send() {
        return () -> random.nextInt(100);
    }

    @Bean
	public Consumer<String> receive() {
		return payload -> {
			System.out.println(payload);
			//log.info(payload);
		};
	}

	@Bean
	public Function<Integer, String> accumulate() {
		return payload -> "Current value: " + payload + ", Total: " + this.count.addAndGet(payload);
	}
}
