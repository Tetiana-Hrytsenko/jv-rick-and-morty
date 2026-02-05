package mate.academy.rickandmorty.init;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterApiDto;
import mate.academy.rickandmorty.exception.DataLoadException;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterDataImporter implements CommandLineRunner {
    private final RickAndMortyClient client;
    private final CharacterService characterService;

    @Override
    public void run(String... args) throws Exception {
        List<CharacterApiDto> charactersFromApi = client.fetchCharactersFromApi();
        if (charactersFromApi.isEmpty()) {
            throw new DataLoadException("Failed to fetch characters from public API.");
        }
        characterService.saveAll(charactersFromApi);
    }
}
