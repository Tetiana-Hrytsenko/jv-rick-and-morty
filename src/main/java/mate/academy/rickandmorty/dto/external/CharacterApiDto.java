package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterApiDto {
    @JsonProperty("id")
    private Long externalId;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Object origin;
    private Object location;
    private String image;
    private Object episode;
    private String url;
    private LocalDateTime created;
}
