/**
 * 
 */

function populateCollectionWithDocuments(collectionPointer, documents){
	documents.forEach(function(document){
		collectionPointer.insert(document);
	});
}