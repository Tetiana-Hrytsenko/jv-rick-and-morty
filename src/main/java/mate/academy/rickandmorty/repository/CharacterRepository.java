package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    Page<Character> findCharactersByNameContains(String name, Pageable pageable);

}
