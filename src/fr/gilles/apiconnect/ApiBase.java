package fr.gilles.apiconnect;

import com.google.gson.*;
import fr.gilles.dashboard.Dashboard;
import fr.gilles.main.Main;
import fr.gilles.route.Route;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ApiBase {
    private static final String API_BASE_URL = "http://localhost/api/";
    private static HttpURLConnection http;


    public static JsonObject registerUser(String registerUrl, String name, String email, String password){
        Map<String,String> arguments = new HashMap<>();
        arguments.put("email", email);
        arguments.put("name", name);
        arguments.put("password", password);
        String data = new Gson().toJson(arguments);
        JsonObject result ;
        try {
            result = Objects.requireNonNull(executeRequest(registerUrl, "POST", data)).getAsJsonObject();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonArray getTaskAbout(int token){
        JsonArray result ;
        try{
            result = Objects.requireNonNull(executeRequest("tasks/" + token + "/taskslist", "GET", null)).getAsJsonArray();
            if (result != null)
                return  result;
        }catch (IOException e){
            e.printStackTrace();
        }
        return  null;

    }

    public static void addNewTask(int id, String data){
        JsonObject result;
        try{
            result = Objects.requireNonNull(executeRequest("tasks/"+id+"/addTask","POST",data)).getAsJsonObject();
            JOptionPane.showMessageDialog(Main.getMainFrame(),"Votre tache a ete ajoutee");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void deleteTask(int taskId){
        String result;
        try{
            result = Objects.requireNonNull(executeRequest("tasks/"+taskId+"/delete","DELETE",null)).getAsString();
            JOptionPane.showMessageDialog(Main.getMainFrame(), result);
            Route.reloadDashbord();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateTask(int taskId, String data){

    }

    public static void markTaskDone(int taskId){
        String result;
        try{
            result = Objects.requireNonNull(executeRequest("tasks/"+taskId+"/done/1","POST",null)).getAsString();
            JOptionPane.showMessageDialog(Main.getMainFrame(), result);
            Route.reloadDashbord();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void markTaskUndone(int taskId){
        String result;
        try{
            result = Objects.requireNonNull(executeRequest("tasks/"+taskId+"/done/0","POST",null)).getAsString();
            JOptionPane.showMessageDialog(Main.getMainFrame(), result);
            Route.reloadDashbord();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  JsonObject loginUser(String loginUrl, String email, String password)  {
        Map<String,String> arguments = new HashMap<>();
        arguments.put("email", email);
        arguments.put("password", password);
        String data = new Gson().toJson(arguments);
        JsonObject result ;
        try {
            result = Objects.requireNonNull(executeRequest(loginUrl, "POST", data)).getAsJsonObject();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private static String url (String url){
        return API_BASE_URL+url;
    }

    private static JsonElement  executeRequest(String url,String method,String data) throws IOException {
        URL requestUrl = new URL(url(url));
        http = (HttpURLConnection) requestUrl.openConnection();
        http.setRequestMethod(method);
        if (data != null)
            http.setFixedLengthStreamingMode(data.getBytes(StandardCharsets.UTF_8).length);
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type","application/json");
        http.connect();
        if (data != null){
            try(OutputStream os = http.getOutputStream()) {
                os.write(data.getBytes(StandardCharsets.UTF_8));
            }
        }


        if (100 <= http.getResponseCode() && http.getResponseCode() <= 399) {
           // new BufferedReader(new InputStreamReader(http.getInputStream())).lines().forEach(s -> System.out.println(s));
           // System.out.println();
            return  readState(readResponse(new BufferedReader(new InputStreamReader(http.getInputStream()))));
        } else {
            System.out.println(readResponse(new BufferedReader(new InputStreamReader(http.getErrorStream()))));
            JOptionPane.showMessageDialog(null,"Erreur interne du serveur");
        }
        http.disconnect();
        return  null;
    }
    private static String readResponse(BufferedReader bufferedReader) throws IOException {
        String response = "",line ="";
        while((line = bufferedReader.readLine())!=null){
            response += line;
        }
        return response;

    }
    private static JsonElement readState(String result){
        JsonObject resultJSON = new JsonParser().parse(result).getAsJsonObject();
        if (resultJSON.get("response").getAsBoolean()){
            return resultJSON.get("result");
        }else{
            JOptionPane.showMessageDialog(null,resultJSON.get("message").getAsString());
        }
        return  null;
    }


}
