import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList chatHistory = new ArrayList();

        StringSimilarity compare = new StringSimilarity();

        String url1 = "http://words.bighugelabs.com/api/2/181ce7943f66625afafd885d0e44cf22/";
        String url2 = "http://words.bighugelabs.com/api/2/181ce7943f66625afafd885d0e44cf22/hi/json";

//        Welcome message
        System.out.println("Chat: Hello dear user. Welcome to this great chat. I would ask you couple of questions.");
        System.out.println("Chat: If you would like to stop the conversation please type 'stop'. Thank you. ");

        boolean chatRun = true;

        Post po = new Post();

//        String resultAPI = po.executePost(url2, "");

//        System.out.println(resultAPI);

        JSONParser parser = new JSONParser();

        JSONArray synArray = null;

        try {

            Object obj = parser.parse(po.executePost(url2, ""));

            JSONObject jsonObject = (JSONObject) obj;

            System.out.println(jsonObject);

            JSONObject msg = (JSONObject) jsonObject.get("noun");

            System.out.println(msg);

            synArray = (JSONArray) msg.get("syn");


            } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }


        while (chatRun) {

//      Read a input from a user
            System.out.print("You: ");
            String answer = scanner.nextLine();

//        String answer = rawAnswer.replaceAll("[^A-Za-z]+", "").toLowerCase();

            if (answer.equals("stop")) {

                chatRun = false;
                System.out.println(answer);
                break;

            } else {

                chatHistory.add(answer);

                Iterator<String> iterator = synArray.iterator();
                while (iterator.hasNext()) {
                    String elements = iterator.next();

                    double bestResult = compare.similarity(elements, answer);
                    compare.printSimilarity(elements, answer);

                    if(bestResult > 0.5){
                        System.out.println("Chat: Hi, what is your name?");
                        System.out.println(bestResult);
                    }

//                    System.out.println(iterator.next());
                }



            }


        }


    }
}
