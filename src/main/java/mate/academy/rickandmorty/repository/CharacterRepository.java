package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    Optional<Character> findById(Long id);

    Page<Character> findCharactersByNameContains(String name, Pageable pageable);

    <S extends Character> List<S> saveAll(Iterable<S> characters);

    List<Character> findAll();
}
