package Utilitaire;

import java.sql.Connection;     // pour une connexion active à la base de données
import java.sql.DriverManager; // Classe qui permet d'obtenir une connexion
import java.sql.SQLException;  // Pour lever une exception si une erreur survient pendan la connexion à la bd

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/Library";
    // jdbc:mysql -- > C'est le protocole pour mysql
    // localhost --> Le serveur tourne en local dans la machine
    // 3306        --> Port par défault dans mysql
    // Library     --> Nom de ma base de données
    private static final String USER = "root";  // Nom d'utilisateur pour se connecter à mysql
    private static final String PASSWORD = "17Enol@1998"; // possible de creer mon mdp ici


    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}

// Connection conn = DatabaseManager.getConnection();