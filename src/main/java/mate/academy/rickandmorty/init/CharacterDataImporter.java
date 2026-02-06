package mate.academy.rickandmorty.init;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterBasicDto;
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
    private static final long CHARACTERS_FETCH_LIMIT = 500;
    private final RickAndMortyClient client;
    private final CharacterService characterService;

    @Override
    public void run(String... args) throws Exception {
        List<CharacterBasicDto> charactersBasicDtoList = new ArrayList<>();
        CharacterResponseDto response = null;
        String nextUrl = BASE_URL;
        while (nextUrl != null && charactersBasicDtoList.size() <= CHARACTERS_FETCH_LIMIT) {
            response = client.fetchCharactersFromApi(nextUrl);
            if (response.getResults().isEmpty()) {
                throw new DataLoadException("Failed to fetch characters from public API.");
            }
            charactersBasicDtoList.addAll(response.getResults());
            nextUrl = response.getInfo().getNext();
        }
        characterService.saveAll(charactersBasicDtoList);
    }
}
