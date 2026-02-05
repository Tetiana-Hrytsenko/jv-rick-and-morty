package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Rick and Morty characters",
        description = "Endpoints for working with Rick and Morty characters")
@RestController
@RequestMapping("characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/wiki")
    @Operation(
            summary = "Get random character wiki",
            description = "Returns randomly generated a wiki about one character in the universe "
                    + "the animated series Rick & Morty."
    )
    public CharacterDto getWikiCharacter() {
        return characterService.getRandomWikiCharacter();
    }

    @GetMapping("/by-name")
    @Operation(
            summary = "Search characters by name",
            description = "Returns a list of Rick and Morty characters whose name contains the "
                    + "search part of character name."
    )
    public Page<CharacterDto> searchCharactersByPartOfName(@RequestParam String name,
                                                           Pageable pageable) {
        return characterService.getCharactersByNameContains(name, pageable);
    }
}
