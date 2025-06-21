package br.com.literalura.ui;

import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import br.com.literalura.model.Livro;
import br.com.literalura.service.GutendexService;
import br.com.literalura.repository.LivroRepository;

@Component
public class Menu {
    private final Scanner sc = new Scanner(System.in);
    private final GutendexService service;
    private final LivroRepository repo;

    public Menu(GutendexService service, LivroRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    public void exibir() {
        while (true) {
            System.out.println("\n1) Buscar por título");
            System.out.println("2) Ver histórico de buscas");
            System.out.println("3) Limpar histórico");
            System.out.println("4) Sair");

            String opcao = sc.next();
            switch (opcao) {
                case "1": buscar(); break;
                case "2": listarHistorico(); break;
                case "3": limparHistorico(); break;
                case "4": System.exit(0);
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private void buscar() {
        System.out.print("Título: ");
        sc.nextLine();
        String t = sc.nextLine();
        try {
            List<Livro> encontrados = service.buscarPorTitulo(t);
            encontrados.forEach(l -> {
                System.out.println(l);
                repo.save(l);
            });
        } catch (Exception e) {
            System.out.println("Erro ao buscar livros: " + e.getMessage());
        }
    }

    private void listarHistorico() {
        List<Livro> todos = repo.findAll();
        todos.forEach(System.out::println);
    }

    private void limparHistorico() {
        repo.deleteAll();
        System.out.println("Histórico limpo!");
    }
}
