# Predict-Me Service
A Spark/Java based service where users can enter a shopping list which will be loaded to a MongoDB collection. The user will then get recommendations mined from the database data.

## Building and Running (Maven)
The Maven pom.xml file is configured so that all the code will be exported into a executable (fat) jar for the server. Everything is in the jar so that you can just run the server.

You must run the command **mvn clean compile assembly:single** to rebuild the server.

To run the server you must navigate to the built JAR (by default it is in the target folder) and run the following command:

java -jar "predict-me-service-backend-0.0.1-SNAPSHOT-jar-with-dependencies.jar" [args]

Where the args are:  
-serverIP [IP you will run server on]  
-port [port server must run on]  
-pathToTemplateDirectory [location of folder with template html files for Freemarker]  
-pathToAllowedShoppingItems [Location of file defining possible items]  
-mongodbhost [IP of mongodb server to connect to]  
-mongodbport [port of mongodb server to connect to]  
-mongodbDatabaseName [mongodb database to use]  
-mongodbCollectionName [collection within mongodb database]  

*pathToTemplateDirectory* and *pathToAllowedShoppingItems* should be specified in terms of where the files are *when developing*.  

All of these arguments are required.

## Loading Testing Data

The MongoDB console lets you load external Javascript. I have created the code to auto-populate the database with a "generic" shopping list or a vegetarian shopping list. In your MongoDB console, enter the following:  
load([path to MongoDBDocumentPopulationUtils.js]);  
load([path to GenerateVegetarianPurchases.js]);  
load([path to GenerateGenericShoppingLists.js]);  

You can then generate a set of shopping lists (array of documents) with:

var vegetarianShoppingListForWeek = generateVegetarianShoppingListForWeek();  
OR  
var genericShoppingListForWeek = generateGenericShoppingListForWeek();  
  
And then you can invoke the following function:  
populateCollectionWithDocuments(collectionPointer, documents);  

You can then run:  
populateCollectionWithDocuments(db.ShoppingListDocument, vegetarianShoppingListForWeek);  

...to add the vegetarian shopping list. You can then run:  
populateCollectionWithDocuments(db.ShoppingListDocument, genericShoppingListForWeek);  

...to load the generic shopping list.  

Alternatively you can be more direct and just run:  
populateCollectionWithDocuments(db.ShoppingListDocument, generateVegetarianShoppingListForWeek());  
And/or:  
populateCollectionWithDocuments(db.ShoppingListDocument, generateGenericShoppingListForWeek());  

Note that these data-generation functions will create **seven** shopping lists, one per day of the week.

## Performance Issues
The Apriori algorithm code takes forever to run for about just 10 000 items. I need to either find a better Apriori algorithm implementation, write a parallelized version of the current algorithm, find a way to run a script right on the MongoDB server (which includes getting a JS Apriori implementation). Feel free to suggest other performance improvement options.

## DONE
1. Parsing command line options
2. Validating command line options
3. Using command line options to affect server startup
4. Addded Freemarker templating
5. UI for entering user items
6. Sending items to DB
7. Sending data to a MongoDB collection
8. Retrieving data from MongoDB collection
9. Generation of frequent item sets
10. Generation of association rules
11. When user enters their shopping list they are shown some items other people usually buy together, and some association
rules.
12. Maven build

## TODO

1. Tailoring how to limit DB size by culling old/unnecessary entries
2. Integrate testing into Maven build
3. Proper comments and documentation
4. Fix performance issues