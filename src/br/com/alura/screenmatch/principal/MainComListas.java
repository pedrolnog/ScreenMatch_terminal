package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;
import br.com.alura.screenmatch.modelos.Titulo; // alt + enter

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainComListas {
    public static void main(String[] args) {
        Filme meuFilme = new Filme("Interestelar", 2014);
        meuFilme.avalia(10);
        Filme outroFilme = new Filme("Avatar", 2022);
        outroFilme.avalia(9);
        var filmeDoJoao = new Filme("Sonic", 2020);
        filmeDoJoao.avalia(7);
        Serie lost = new Serie("Lost", 2000);
        lost.avalia(8);

        Filme f1 = filmeDoJoao; // variável de referência, referenciando filmeDoPaulo

        ArrayList<Titulo> lista = new ArrayList<>(); // parametrizar lista
        lista.add(meuFilme);
        lista.add(outroFilme);
        lista.add(filmeDoJoao);
        lista.add(lost);

        for (Titulo item : lista) {
            System.out.println(item.getNome());
            if (item instanceof Filme filme && filme.getClassificacao() > 2) {
                System.out.println("Classificação: " + filme.getClassificacao());
            }
            }

        /*
        Implementado no Java 8, esse é o forEach, que reescreve parte do código acima de uma forma mais concisa.
        O forEach abaixo irá imprimir o nome de cada item.

        - lista.forEach(item -> System.out.println(item.getNome()));

        Reduzindo mais ainda, temos:
        - lista.forEach(System.out::println);
        "::" É chamado de Method Reference.
         */


        ArrayList<String> buscaPorArtista = new ArrayList<>();
        buscaPorArtista.add("Christopher Nolan");
        buscaPorArtista.add("Adam Sandler");
        buscaPorArtista.add("Vin Diesel");
        System.out.println(buscaPorArtista);

        Collections.sort(buscaPorArtista); // ordenando lista
        System.out.println("Ordenado: " + buscaPorArtista);

        Collections.sort(lista);
        System.out.println("Ordenado: " + lista);
        lista.sort(Comparator.comparing(Titulo::getAnoDeLancamento));
        System.out.println("Ordenado por ano de lançamento: " + lista);
    }
}
