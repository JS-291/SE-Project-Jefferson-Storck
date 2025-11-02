# SE-Project-Jefferson-Storck

L'application s'exécute entièrement dans le terminal et prend **trois arguments** :

1. **Nom du fichier** à lire.

2. **Type de compression** :
   - `-o` → *overlap*  
   - `-no` → *no overlap*  
   - `-fl` → *overflow*

3. **Type d'action** :
   - `-c` → Compression  
   - `-d` → Décompression  
     > Le programme demandera ensuite la longueur standard utilisée pendant la compression.
   - `-g` → Get  
     > Le programme demandera ensuite la longueur standard utilisée pendant la compression et l'indice du nombre à renvoyer.

Le résultat des compressions et décrompression est conservé dans un fichier Out.txt créé par le programme

## Format:

Seule la première ligne du fichier est considérée.
Les nombres doivent être séparés par un seule espace.
Se reférer aux fichiers **Tests** comme exemples.

## Exemple:

java -jar SE-Project.jar Fichier.txt -o -c
java -jar SE-Project.jar Fichier.txt -no -d
