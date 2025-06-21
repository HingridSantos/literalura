package br.com.literalura.service;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.*;
import br.com.literalura.model.Livro;

@Service
public class GutendexService {
    private RestTemplate rest = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    public List<Livro> buscarPorTitulo(String titulo) throws Exception {
        String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "+");
        String json = rest.getForObject(url, String.class);
        JsonNode root = mapper.readTree(json).path("results");
        List<Livro> list = new ArrayList<>();

        for (JsonNode node : root) {
            Livro l = new Livro();
            l.setTitulo(node.path("title").asText());
            l.setDownloadCount(node.path("download_count").asInt());

            JsonNode authors = node.path("authors");
            String autor = (authors.isArray() && authors.size() > 0)
                    ? authors.get(0).path("name").asText()
                    : "Desconhecido";
            l.setAutor(autor);

            JsonNode languages = node.path("languages");
            String idioma = (languages.isArray() && languages.size() > 0)
                    ? languages.get(0).asText()
                    : "";
            l.setIdioma(idioma);

            list.add(l);
        }
        return list;
    }
}
