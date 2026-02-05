package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterApiDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterDto toDto(Character character);

    Character toModel(CharacterApiDto characterApiDto);

    List<CharacterDto> toListDto(List<Character> characters);

    List<Character> toListModel(List<CharacterApiDto> characterApiDtos);
}
