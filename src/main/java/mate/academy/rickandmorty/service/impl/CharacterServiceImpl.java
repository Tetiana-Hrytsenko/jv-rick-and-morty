package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterBasicDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public List<CharacterDto> saveAll(List<CharacterBasicDto> characterBasicDtos) {
        List<Character> characters = characterMapper.toListModel(characterBasicDtos);
        return characterMapper.toListDto(characterRepository.saveAll(characters));
    }

    @Override
    public CharacterDto findById(Long id) {
        Character character = characterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find character by id: " + id));
        return characterMapper.toDto(character);
    }

    @Override
    public Page<CharacterDto> getCharactersByNameContains(String name, Pageable pageable) {
        return characterRepository.findCharactersByNameContains(name, pageable)
                .map(characterMapper::toDto);
    }

    public CharacterDto getRandomWikiCharacter() {
        Long randomId = new Random().nextLong(1, characterRepository.count());
        return findById(randomId);
    }
}
