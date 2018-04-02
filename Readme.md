# Predict-Me Service
A Spark/Java based service for trying to predict a user's shopping list based on a few entered items, using the Apriori algorithm.

##Performance Issues
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

## TODO

1. Tailoring how to limit DB size by culling old/unnecessary entries