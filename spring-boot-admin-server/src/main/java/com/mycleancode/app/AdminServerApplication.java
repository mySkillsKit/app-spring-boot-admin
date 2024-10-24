package com.mycleancode.app;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import de.codecentric.boot.admin.server.notify.Notifier;
import de.codecentric.boot.admin.server.web.client.InstanceExchangeFilterFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import static de.codecentric.boot.admin.server.web.client.InstanceExchangeFilterFunctions.ATTRIBUTE_ENDPOINT;

@EnableAdminServer
@SpringBootApplication
public class AdminServerApplication {

    private static final Logger log = LoggerFactory.getLogger(AdminServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }

    @Bean
    public Notifier customNotifier() {
        return event -> {
            log.info("Event from AdminServer {}", event.toString());
            return Mono.empty();
        };
    }

    @Bean
    public InstanceExchangeFilterFunction preventRootLog() {
        return ((instance, request, next) -> {
            if (HttpMethod.POST.equals(request.method())) {
                var isLogFile = request.attribute(ATTRIBUTE_ENDPOINT).map("loggers"::equals).orElse(false);
                var isRoot = request.url().getPath().endsWith("ROOT");
                if (isLogFile && isRoot) {
                    return Mono.just(ClientResponse.create(HttpStatus.FORBIDDEN).build());
                }
            }
            return next.exchange(request);
        });
    }
}
