package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOMDB;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY) //padrão
                .setPrettyPrinting()
                .create();

        String apiKey = "c24d9e7e";
        String busca = "";
        List<Titulo> titulos = new ArrayList<>();

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Digite o nome do filme a buscar: ");
            busca = sc.nextLine();

            if (busca.equalsIgnoreCase("sair")) {
                break;
            }

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://www.omdbapi.com/?t=" + URLEncoder.encode(busca, StandardCharsets.UTF_8) + "&apikey=" + apiKey))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                TituloOMDB TituloOMDB = gson.fromJson(response.body(), TituloOMDB.class);
                System.out.println(TituloOMDB);

                Titulo filmeJson = new Titulo(TituloOMDB);
                System.out.println(filmeJson);

                titulos.add(filmeJson);

            } catch (NumberFormatException e) {
                System.out.println("Um erro ocorreu, não foi possível exibir as informações. Tente novamente mais tarde.");
                System.out.println("Erro: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Ocorreu um erro durante a busca, verifique o endereço e tente novamente.");
                System.out.println("Erro: " + e.getMessage());
            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Um erro desconhecido ocorreu");
            }
        }
        System.out.println(titulos);

        FileWriter escritor = new FileWriter("filmes.json");
        escritor.write(gson.toJson(titulos));
        escritor.close();

        System.out.println("O programa finalizou corretamente.!");
    }
}
