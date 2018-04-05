/**
 * Populate a given collection with the given array of documents.
 * calls insert() on the given collection for each document
 */

function populateCollectionWithDocuments(collectionPointer, documents){
	documents.forEach(function(document){
		collectionPointer.insert(document);
	});
}