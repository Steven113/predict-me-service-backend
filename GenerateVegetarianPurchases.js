/**
 * A script to populate a MongoDB collection with the kind of shopping list
 * a vegetarian would have over a week
 */

// var allowedShoppingItems = [ "Milk", "Coca-Cola", "Fruit Juice", "Chocolate",
// "Chips", "Microwave Food", "Ready-made (Deli) Food", "Eggs", "Bread",
// "Biscuits", "Bakery Bread", "Bakery Desert", "Sweets", "Spices", "Sauces",
// "Meat", "Fruit", "Vegetables", "Vegetarian Meat Alternatives" ];
var allowedShoppingItems = [ "Milk", "Coca-Cola", "Fruit Juice", "Chocolate",
		"Chips", "Bread", "Biscuits", "Sweets", "Spices", "Sauces", "Fruit",
		"Vegetables", "Vegetarian Meat Alternatives" ];

var possibleDayOfWeek = [ "Sunday", "Monday", "Tuesday", "Wednesday",
		"Thursday", "Friday", "Saturday" ];

function getRandomInt(min, max) {
	return Math.floor(Math.random() * (max - min + 1)) + min;
}

function generateVegetarianShoppingListForWeek(){
	var shoppingList = [];
	
	possibleDayOfWeek.forEach(function(dayOfWeek){
		var randomDocument = {};
		allowedShoppingItems.forEach(function(item) {
			if (getRandomInt(0, 10) > 3) {
				randomDocument[item] = 1;
			}
		});
		randomDocument.DayOfWeek = dayOfWeek;
		
		shoppingList.push(randomDocument);
	});

	return shoppingList;
}
