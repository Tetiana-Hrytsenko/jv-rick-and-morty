package mate.academy.rickandmorty.dto.internal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Wiki information about a Rick and Morty character.")
public class CharacterDto {
    @Schema(description = "Internal database ID.")
    private Long id;
    @Schema(description = "ID of the character from public API.")
    private Long externalId;
    @Schema(description = "The name of the character.", example = "Toxic Rick")
    private String name;
    @Schema(description = "The status of the character.", example = "'Alive', 'Dead' or 'unknown'")
    private String status;
    @Schema(description = "The gender of the character.",
            example = "'Female', 'Male', 'Genderless' or 'unknown'.")
    private String gender;
}
