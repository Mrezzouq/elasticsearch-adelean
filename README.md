# Elasticsearch Indexation Project

## Description
Ce projet est une application Java utilisant Gradle, conçue pour dézipper un fichier XML, le parser, et indexer les données dans un index Elasticsearch.
J'ai utilise un conteneur Docker pour exécuter la version requise d'Elasticsearch.
## Project Structure
- `src/main/java/org/adelean/elasticsearch/Main.java`: Point d'entrée principal de l'application.
- `src/main/java/org/adelean/elasticsearch/service/IndexationService.java`: Service responsable de l'indexation des produits dans Elasticsearch.
- `src/main/java/org/adelean/elasticsearch/service/ElasticsearchService.java`: Service pour interagir avec Elasticsearch.
- `src/main/java/org/adelean/elasticsearch/service/ElasticsearchPort.java`: Interface pour le service Elasticsearch.
- `src/main/java/org/adelean/elasticsearch/parser/XmlParser.java`: Parseur pour les fichiers XML.
- `src/main/java/org/adelean/elasticsearch/utils/UnzipUtils.java`: Utilitaire pour dézipper les fichiers.

## Prérequis
- Java 17 or higher
- Elasticsearch
- Gradle

## Configuration
- L'application attend un fichier XML compressé (zip) situé dans `src/main/resources/fixtures/xml.zip`.
- Le répertoire de sortie pour les fichiers dézippés est `resources/fixtures`.
- Le nom de l'index Elasticsearch utilisé est `products`.
- le resultat de l'indexation est affiché dans le JSON situé dans `result/elastisearch-product.json`.

## Usage
- L'application dézippe le fichier XML, le parse, puis indexe les produits dans l'index Elasticsearch spécifié