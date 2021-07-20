package fr.gilles.route;

import com.google.gson.JsonObject;
import fr.gilles.apiconnect.ApiBase;
import fr.gilles.authentification.Login;
import fr.gilles.authentification.Register;
import fr.gilles.dashboard.Dashboard;
import fr.gilles.main.Main;
import fr.gilles.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Route {

    public static void loginUser(String email, String password){
        JsonObject result = ApiBase.loginUser("users/login",email,password);
        if (result!= null){
            Dashboard dashboard = new Dashboard(new User(result));
            gotToDashBoard(dashboard);
        }
    }
    public static void registerUser(String name, String email, String password){
        JsonObject result = ApiBase.registerUser("users/register",name,email,password);
        if (result!= null){
            Dashboard dashboard = new Dashboard(new User(result));
            gotToDashBoard(dashboard);
        }
    }

    public static  void gotoRegister(){
        Main.gotoRoute(new Register(),"Inscription",new Dimension(400,400),false);
    }
    public static  void gotoLogin(){
        Main.gotoRoute(new Login(),"Connexion",new Dimension(400,400),false);
    }

    private static  void gotToDashBoard(Dashboard dashboard){
        Main.gotoRoute(dashboard,"Mes Taches", new Dimension(850,600),false);
    }

    public static void reloadDashbord(){
        ((Dashboard)Main.getMainFrame().getContentPane()).reload();
    }
}
