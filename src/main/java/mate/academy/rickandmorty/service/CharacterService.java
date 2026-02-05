package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterApiDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    List<CharacterDto> saveAll(List<CharacterApiDto> characterApiDtos);

    CharacterDto findById(Long id);

    CharacterDto getRandomWikiCharacter();

    Page<CharacterDto> getCharactersByNameContains(String name, Pageable pageable);
}
