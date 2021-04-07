/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package code.review;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) throws IOException {
//        MalformedUrlException and FileNotFoundException must inherit from IOException

        String urlString = "https://pokeapi.co/api/v2/pokemon?limit=100&offset=200";
        URL pokeUrl = new URL(urlString);
//        Java is synchronous, we don't need promises or .thens
        HttpURLConnection pokemonConnection = (HttpURLConnection) pokeUrl.openConnection();
//        Go get the data from the api
        pokemonConnection.setRequestMethod("GET");
        System.out.println("about to send request");
        InputStreamReader inStreamReader = new InputStreamReader(pokemonConnection.getInputStream());
        BufferedReader buffy = new BufferedReader(inStreamReader);

        String allMyJsonInOneLine = buffy.readLine();
//        while(line != null){
//            System.out.println(line);
//            line = buffy.readLine();
//        }
        Gson gson = new Gson();
        PokemonApiData pokeResults = gson.fromJson(allMyJsonInOneLine, PokemonApiData.class);
//        line === {"count":1118,"next":"https://pokeapi.co/api/v2/pokemon?offset=300&limit=100","previous":"https://pokeapi.co/api/v2/pokemon?offset=100&limit=100","results":[{"name":"unown","url":"https://pokeapi.co/api/v2/pokemon/201/"},{"name":"wobbuffet","url":"https://pokeapi.co/api/v2/pokemon/202/"}]
        Pokemon[] pokemonArray = pokeResults.results;
        System.out.println(Arrays.toString(pokemonArray));
    }

//    Need Gson and a class

public static void getFromJSON() throws FileNotFoundException {
    Gson gson = new Gson();

    Pokemon[] poke = gson.fromJson(
            new FileReader(new File("src/main/resources/pokes.json")),
            Pokemon[].class
    );
//        System.out.println(poke);
    int randomIndex = (int)(Math.random() * poke.length);
    System.out.println(poke[randomIndex]);
}
}

class Pokemon{
    public String name;
    public String url;

    public String toString(){
        return String.format("%s, %s, you can find me at %s", name, name, url);
    }
}

class PokemonApiData {
    Pokemon[] results;
}