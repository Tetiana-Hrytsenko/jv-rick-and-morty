package mate.academy.rickandmorty.init;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.exception.DataLoadException;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterDataImporter implements CommandLineRunner {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final RickAndMortyClient client;
    private final CharacterService characterService;

    @Override
    public void run(String... args) throws Exception {
        CharacterResponseDto response = client.fetchCharactersFromApi(BASE_URL);
        if (response.getResults().isEmpty()) {
            throw new DataLoadException("Failed to fetch characters from public API.");
        }
        String nextUrl = BASE_URL;
        while (nextUrl != null) {
            try {
                response = client.fetchCharactersFromApi(nextUrl);
                characterService.saveAll(response.getResults());
                nextUrl = response.getInfo().getNext();
            } catch (Exception e) {
                CharacterResponseDto debugResponse = response;
            }
        }
    }
}
