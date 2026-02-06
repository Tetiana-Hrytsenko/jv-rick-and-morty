package mate.academy.rickandmorty.dto.external;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageInfoDto {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
