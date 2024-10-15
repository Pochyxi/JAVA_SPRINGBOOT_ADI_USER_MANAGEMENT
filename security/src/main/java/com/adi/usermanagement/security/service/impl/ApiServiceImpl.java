package com.adi.usermanagement.security.service.impl;

import com.adi.usermanagement.security.dto.*;
import com.adi.usermanagement.security.exception.appException;
import com.adi.usermanagement.security.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {

    private static final Logger logger = LoggerFactory.getLogger( ApiServiceImpl.class);

    private final WebClient webClient;

    @Override
    public Mono<UserDTO> getById( Long id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), this::handleError)
                .bodyToMono(UserDTO.class);
    }

    @Override
    public Mono<UserDTOInternal> getUserData( String usernameOrEmail) {
        return webClient.get()
                .uri("/username_email/{usernameOrEmail}", usernameOrEmail)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), this::handleError)
                .bodyToMono(UserDTOInternal.class);
    }

    @Override
    public Mono<Boolean> existsByUsernameOrEmail(String usernameOrEmail) {
        return webClient.get()
                .uri("/username_email/exist/{usernameOrEmail}", usernameOrEmail)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), this::handleError)
                .bodyToMono( Boolean.class);
    }

    public Mono<Set<ProfilePermissionDTO>> getProfilePermissions( Long profileId) {
        return webClient.get()
                .uri("/profile_permissions/{profileId}", profileId)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), this::handleError)
                .bodyToFlux( ProfilePermissionDTO.class)
                .collect( Collectors.toSet());
    }

    public Mono<Void> signup( SignupDTO signupDTO ) {
        return webClient.post()
                .uri( "/signup" )
                .bodyValue( signupDTO )
                .retrieve()
                .onStatus( status -> status.is4xxClientError() || status.is5xxServerError(), this::handleError )
                .bodyToMono( Void.class );
    }

    @Override
    public Mono<ProfileDTO> getProfile( Long userId ) {
        return webClient.get()
                .uri( "/profile/{userId}", userId )
                .retrieve()
                .onStatus( status -> status.is4xxClientError() || status.is5xxServerError(), this::handleError )
                .bodyToMono( ProfileDTO.class );
    }

    private Mono<? extends Throwable> handleError( ClientResponse clientResponse) {
        return clientResponse.bodyToMono( ErrorDetailsDTO.class)
                .flatMap(errorDetails -> {
                    HttpStatus status = ( HttpStatus ) clientResponse.statusCode();
                    String timestamp = errorDetails.getTimestamp().toString();
                    String message = errorDetails.getMessage();
                    String details = errorDetails.getDetails();
                    String errorMessage = String.format("Errore ricevuto: Timestamp: %s, Messaggio: %s, Dettagli: %s", timestamp, message, details);

                    // Logga i dettagli dell'errore
                    logger.error(errorMessage);

                    return Mono.error(new appException(
                            status, errorMessage));
                });
    }
}
