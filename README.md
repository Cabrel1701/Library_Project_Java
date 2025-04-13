
# 📚 Library Management Console App (Java)

Cette application Java permet de gérer une bibliothèque via une interface console.  
Elle propose la gestion des livres, des membres, des emprunts et des retours.

---

## ✨ Fonctionnalités

- Ajouter, modifier et supprimer des **livres**
- Ajouter, modifier et supprimer des **membres**
- Enregistrer des **emprunts** et des **retours**
- Lister les livres disponibles
- Lister tous les emprunts (actifs, terminés et par membre)
- Affichage propre et aligné en console

---

## 🛠️ Technologies utilisées

- **Java SE**
- **JDBC** (avec MySQL )
- **MySQL Connector**
- Structure en **DAO Data Acces Object()**
- Développement dans **IntelliJ IDEA**

---

## 📁 Structure du projet

```
Projet_UA3/
├── src/
│   ├── DAO/                  # DAO : LivreDao, MembreDAO, EmpruntDAO
│   ├── MODEL/                # Modèles : Livre, Membre, Emprunt
│   ├── MAIN/                 # Classe Main (exécutable)
│   └── Utilitaire/           # Classe DatabaseManager.java
├── .gitignore
├── README.md
```

---

## 🚀 Lancer le projet

1. Cloner ce dépôt :
   ```bash
   git clone https://github.com/ton-utilisateur/Library_Project_Java.git
   ```

2. Ouvrir le projet dans **IntelliJ IDEA**

3. Vérifier votre base de données MySQL et la configuration dans `DatabaseManager.java`

4. Exécuter la classe `Main`

---

## 🧑‍💻 Auteur

Développé par **Cabrel CHETSONG**  
📧 [cabrechetsong@gmail.com](mailto:cabrelchetsong@gmail.com)

---

## 📝 Licence

Ce projet est librement réutilisable à des fins personnelles.
