db.restaurants.findOne() // show first element
db.restaurants.find( { "borough" : "Brooklyn" } ) // find an element other syntax : db.getCollection('restaurants').find({"borough" : "Brooklyn"})
db.restaurants.find( { "borough" : "Brooklyn" } ).count() // count elemnets
// show all restaurants with italian speciality in 5 avenue street and name contains pizza (regex : i sensitive key)
db.restaurants.find( { 
    "borough" : "Brooklyn",
    "cuisine" : "Italian",
    "address.street" : "5 Avenue",
    "name" : /pizza/i
}) 

// show only one element in result
db.getCollection('restaurants').find(
    {"borough":"Brooklyn",
     "cuisine":"Italian",
     "name":/pizza/i,
     "address.street" : "5 Avenue"},
    {"name":1, "grades.score" : 1} // add "_id":0 to hide id in result
)

//search with condition
db.getCollection('restaurants').find(
    {"borough":"Manhattan",
     "grades.score":{
         $lt:10,
         $not:{$gte:10}
     }
    },
    {"name":1,"grades.score":1, "_id":0})


// search on each element
db.restaurants.find({
    "grades" : {
           $elemMatch : {
               "grade" : "C",
               "score" : {$lt :40}
           }
    }
  },
  {"grades.grade" : 1,"grades.score" : 1}
)

// Last "C" graded restaurent 
db.restaurants.find({
    "grades.0.grade":"C"
  },
  {"name":1, "borough":1, "_id":0}
);

// sow all possible value of borough
db.restaurants.distinct("borough")

// Saqme as find but with aggeregate and params
db.restaurants.aggregate( [
    { $match : {
        "grades.0.grade":"C"
    }},
    { $project : {
        "name":1, "borough":1, "_id":0
    }}
] )

// Other syntax with variables
varMatch = { $match : { "grades.0.grade":"C"} };
varProject = { $project : {"name":1, "borough":1, "_id":0}};
db.restaurants.aggregate( [ varMatch, varProject ] );

// Result group
varMatch = { $match : { "grades.0.grade":"C"} };
varGroup = { $group : {"_id" : null, "total" : {$sum : 1} } };
db.restaurants.aggregate( [ varMatch, varGroup ] );
// Same result as this :
db.restaurants.count({"grades.0.grade":"C"})
db.restaurants.find({"grades.0.grade":"C"}).count()

// Group by key 
varMatch = { $match : { "grades.0.grade":"C"} };
varGroup2 = { $group : {"_id" : "borough", "total" : {$sum : 1} } };
db.restaurants.aggregate( [ varMatch, varGroup2 ] );

// Group by value of key
varMatch = { $match : { "grades.0.grade":"C"} };
varGroup3 = { $group : {"_id" : "$borough", "total" : {$sum : 1} } };
db.restaurants.aggregate( [ varMatch, varGroup3 ] );

// Score avergae of restaurant by borough
varUnwind = {$unwind : "$grades"}
varGroup4 = { $group : {"_id" : "$borough", "moyenne" : {$avg : "$grades.score"} } };
varSort2 = { $sort : { "moyenne" : -1 } }
db.restaurants.aggregate( [ varUnwind, varGroup4, varSort2 ] );

//Update or add if not exist
db.restaurants.update (
   {"_id" : ObjectId("5abb8d9879c1dafe66699f5b")},
   {$set : {"comment" : "new comment"}}
);

//Delete a key
db.restaurants.update (
   {"_id" : ObjectId("5abb8d9879c1dafe66699f5b")},
   {$unset : {"comment" : 1}}
);

// update with condition
db.restaurants.update (
   {"grades.grade" : {$not : {$eq : "C"}}},
   {$set : {"comment" : "acceptable"}}
   {"multi" : true} // to update all elemenst
);

//function to create note according to grade
db.restaurants.find( {"grades.grade" : {$not : {$eq : "C"}}} ).forEach( 
    function(restaurant){
        total = 0;
        for(i=0 ; i<restaurant.grades.length ; i++){
            if(restaurant.grades[i].grade == "A")         total += 3;
            else if(restaurant.grades[i].grade == "B")    total += 1;
            else                                          total -= 1;
        }
        restaurant.note = total;
        db.restaurants.save(restaurant);
    }
);

// delete elements where condition
db.restaurants.remove(
   {"note":0},
   {"multi" : true}
);

// insert new line
db.restaurants.save({"_id" : 1, "test" : 1});