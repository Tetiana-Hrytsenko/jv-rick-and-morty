package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    private final ObjectMapper objectMapper;

    public CharacterResponseDto fetchCharactersFromApi(String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        {
            try {
                HttpResponse<String> response = HTTP_CLIENT.send(
                        httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                return objectMapper.readValue(
                        response.body(),
                        CharacterResponseDto.class);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
