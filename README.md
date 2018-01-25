_Site communautaire autour de l'escalade_
--------

Cette application a pour objectif le partage d’informations entre grimpeur dans le thème de l’escalade, de ce fait le grimpeur peut:

* s’inscrire et s’authentifier pour certaines actions,

* proposer des topos faits sur des sites,

* réserver des topos disponibles,

* voir ses réservations,

* faire des commentaires sur les topos,

* ajouter des sites, secteurs et voies,

* voir le détail des sites et des topos,

* rechercher des sites

_Technologies utilisées :_
-----

* Application web réalisé en **Java/JEE (JDK 8)**

* Serveur **Apache Tomcat 8.0**

* Base de données **PostGreSQL 9.2**

* Packagée (WAR) avec **Maven**

_Outils_
----

* **Eclipse**
* **Papyrus**
* **PgAdmin**
* **SQL Power Architect**

_Intallation des outils_
----

**Pour PostgreSQL il faut:**

* installer **postgreSQL** : télécharger et langer l’installation de postgreSql suivre les instructions (choisr mot de passe , laisser le port par defaut…) et finir.

* lancer l’icône **PgAdminII** et créer une base de données: **escalade1**,

* exécuter le script du fichier : **escalade1.sql**,

* exécuter ensuite le script du fichier **jeu_de_donnée.sql** : pour le jeu de données

**Pour Java 8 (JDK 8) il faut:**

* télécharger et installer le kit de développement Java 8 (JDK 8) dans le site d’oracle : une fois votre jdk téléchargé et installé, il faut ajouter ce répertoire __C:\Program Files\Java\jdk1.8.0_101\bin__ dans votre variable d’environnement pour que toutes les commandes de java puisque être reconnu, enfin pour vérifier l’installation ouvrir votre invite de commande et taper **javac** ou  **java version** ou  **java**

**Pour Eclipse il faut:**

*  installer un environnement de développement (**IDE**) comme **Eclipse** ou **Intelli J**

**Pour Tomcat il faut:**

* installer **Tomcat** dans votre machine: télécharger et dézipper tomcat dans un répertoire de votre machine puis exécuter le fichier **.exe** suivre les instructions et pour tester l’installation aller à l’url **http://localhost:8080/**

* configurer **Tomcat** dans votre **IDE** : depuis l’interface de votre IDE faire **new** puis **other** et choisir **server**, **next** choisir la version installée dans votre machine **finish** et pour le démarrer **windows**, **show view** et choisir **server** et il apparaitra dans l’ide ou vous pouvez le démarrer l’arrêter, le supprimer…

**Pour Maven il faut:**

* installer **Maven** dans votre machine: télécharger et dézipper maven dans un répertoire de votre machine, ajouter la variable d’environnement utilisateur avec pour valeur le chemin du répertoire et lancer une nouvelle commande MS-DOS et exécuter ** mvn version** pour vérifier l’installation.
 
**Pour le déploiement du projet il faut**

* placer le dossier '**fichiers**' qui se trouve dans le dossier **documentation** dans la racine de votre disque **C**

**Par téléchargement**

* télécharger le projet depuis **Github** à l’url https://github.com/diarraSokhna/Projet3

* dézipper le projet qui contient deux dossiers : **escalade** et **documentation**

**Par clonage**

* se placer sur un dossier et faire git clone https://github.com/diarraSokhna/Projet3

**Avec un IDE**

* dans votre IDE , faire importer ‘un projet maven existant’ puis sélectionner le dossier **escalade** et suivre les instructions. 

* cliquer droit sur le projet **Run as** puis **maven package**

* lancer le module **escalade-presentation** sur le serveur puis entrer dans le navigateur l'url _http://localhost:8080/escalade-presentation/Accueil_ et enjoy!!!

**Sans IDE**

* se positionner dans le dossier escalade et exécuter la commande **mvn package** : un fichier war sera généré 

* copier le fichier **escalade-presantation.war** se trouvant dans le repertoire **escalade\escalade-presentation\target** du module escalade-presantation et le placer dans le repertoire **Tomcat/webapps** 

* demarrer **Tomcat** et lancer l'url _http://localhost:8080/escalade-presentation/Accueil_ dans votre navigateur
