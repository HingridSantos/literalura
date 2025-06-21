package br.com.literalura.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.literalura.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByAutorContainingIgnoreCase(String autor);
    List<Livro> findByIdioma(String idioma);
}
