USERNAME AND PASSWORD ;
hatout , 1234
boualam , 123

Droit administrateur : mdp --> 1234


pour metre en oeuvre l'application il faut :
**installer PostgreSQL ave ses extension.
**dans ce fichier il y a un fichier backup.sql il faut creer une base de donnees avec le nom appro1esmat
**METTRE A JOURS LES VARIABLES D'ENVIRONNEMENT 
** importer la db avec la commade : psql -U 'nom-role' -d appro1esmat -f backup.sql.
** rectifier le fichier config.properties : PATH--> \APPRO 1.0.2\target\classes\config.properties. dans le but de rectifier le path des resources selon 
votre chemain absolut
**utiliser jdk 21 et psql 42.6.2