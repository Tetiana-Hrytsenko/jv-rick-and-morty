package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterApiDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper objectMapper;

    public List<CharacterApiDto> fetchCharactersFromApi() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL))
                .build();
        {
            try {
                HttpResponse<String> response = httpClient.send(
                        httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                CharacterResponseDto dataDto = objectMapper.readValue(
                        response.body(),
                        CharacterResponseDto.class);
                return dataDto.getResults();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
