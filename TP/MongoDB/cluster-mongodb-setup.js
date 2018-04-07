// Creating data directories
mkdir cluster-mongodb-data
mkdir cluster-mongodb-data/config1
mkdir cluster-mongodb-data/config2

// Launch config servers
mongod --configsvr --replSet configReplSet --port 27019 --dbpath ~/MY-DATA/Fitec-Work/TP/MongoDB/cluster-mongodb-data/config1
mongod --configsvr --replSet configReplSet --port 27020 --dbpath ~/MY-DATA/Fitec-Work/TP/MongoDB/cluster-mongodb-data/config2

// Connect to config server 
mongo --port 27019
rs.initiate();
rs.add("localhost:27020");

// Set up shards
mkdir cluster-mongodb-data/sh1
mkdir cluster-mongodb-data/sh2
mongod --shardsvr --replSet sh1 --port 27031 --dbpath ~/MY-DATA/Fitec-Work/TP/MongoDB/cluster-mongodb-data/sh1
mongod --shardsvr --replSet sh2 --port 27032 --dbpath ~/MY-DATA/Fitec-Work/TP/MongoDB/cluster-mongodb-data/sh2
mongo --port 27031 --eval "rs.initiate()"
mongo --port 27032 --eval "rs.initiate()"

// Set up router 
mongos --configdb configReplSet/localhost:27019 --port 27017

// Connect to router
mongo --port 27017
sh.addShard( "sh1/localhost:27031"); // add shard1
sh.addShard( "sh2/localhost:27032"); // add shard2

// Database distribution
use testDB;
sh.enableSharding("testDB");
db.createCollection("test");
db.test.createIndex({"_id":1});
sh.shardCollection("testDB.test",{"_id":1});

//Import data
mongoimport --db testDB --collection test --port 27017 Tuto_OpenClassRoom/restaurants.json

// check sharding status
mongo --port 27017 --eval "sh.status()"