package br.com.alura.screenmatch.modelos;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;

public class Titulo implements Comparable<Titulo>{
    //@SerializedName("Title")
    private String nome;
    //@SerializedName("Year")
    private int anoDeLancamento;
    //@SerializedName("Runtime")
    private int duracaoEmMinutos;
    private boolean incluidoNoPlano;
    private int somaAvaliacoes;
    //@SerializedName("imdbVotes")
    private int totalAvaliacoes;

    public Titulo(String nome, int anoDeLancamento) {
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
    }

    public Titulo(TituloOMDB tituloOMDB) {
        this.nome = tituloOMDB.Title();
        if (tituloOMDB.Year().length() > 4) {
            throw new ErroDeConversaoDeAnoException("Ano não pôde ser convertido. Possui mais que 4 caracteres.");
        }
        this.anoDeLancamento = Integer.valueOf(tituloOMDB.Year());
        this.duracaoEmMinutos = Integer.valueOf(tituloOMDB.Runtime().substring(0, tituloOMDB.Runtime().indexOf(" ")));
        this.totalAvaliacoes = Integer.valueOf(tituloOMDB.imdbVotes().replace(",", ""));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public boolean isIncluidoNoPlano() {
        return incluidoNoPlano;
    }

    public void setIncluidoNoPlano(boolean incluidoNoPlano) {
        this.incluidoNoPlano = incluidoNoPlano;
    }

    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public void exibeFichaTecnica() {
        System.out.println("O titulo do ficha: " + nome);
        System.out.println("Ano de lançamento: " + anoDeLancamento);
    } // Esse method tira a necessidade de um getTitulo ou getAnoDeLancamento.

    public void avalia(double nota) {
        somaAvaliacoes += nota;
        totalAvaliacoes++;
    }

    public int pegaMedia() {
        return somaAvaliacoes / totalAvaliacoes;
    }

    @Override
    public int compareTo(Titulo outroTitulo) {
        return this.getNome().compareTo(outroTitulo.getNome());
    }

    @Override
    public String toString() {
        return "(nome=" + nome +
                ", anoDeLancamento = " + anoDeLancamento + ", totalAvaliacoes = " + totalAvaliacoes + ", duracaoEmMinutos = " + duracaoEmMinutos + ")";
    }
}
