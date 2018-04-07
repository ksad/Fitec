//*** Create directories for replicatset data
mkdir data
mkdir data/rs01
mkdir data/rs02
mkdir data/rs03

//*** Run ReplicaSet on all hosts 
mongod --replSet rs0 --port 27018 --dbpath ~/MY-DATA/Fitec-Work/TP/MongoDB/data/rs01
mongod --replSet rs0 --port 27019 --dbpath ~/MY-DATA/Fitec-Work/TP/MongoDB/data/rs02
mongod --replSet rs0 --port 27020 --dbpath ~/MY-DATA/Fitec-Work/TP/MongoDB/data/rs03

//*** Connect to ReplicaSet Server
mongo --port 27018
> rs.initiate(); // Repl initialisation
> rs.conf(); // check configuration

//*** Add other members to replicaSet
rs0:PRIMARY > rs.add("localhost:27019")
rs0:PRIMARY > rs.add("localhost:27020")

//*** Check status
rs0:PRIMARY > rs.status()

//*** Add arbiter for quick election
mongod --replSet rs0 --port 30000 --dbpath ~/MY-DATA/Fitec-Work/TP/MongoDB/data/arbiter

//*** On primary server 
rs0:PRIMARY > rs.addArb("localhost:30000")

//*** Config file
//we can use a conf file instead of option in command line
/etc/mongod.conf // https://docs.mongodb.com/manual/reference/configuration-options/

// Replication options 
replication:
  oplogSizeMB: 512 // collection size to synchronize secondary nodes
  replSetName: rs0 
  localThresholdMS: <"primary"|"primaryPreferred"|"Secondary"| "Nearest"> // chose reading preference
  readConcern: <"majority"|"local"|"linearizable"> // access strategy to repilcasets

//*** Vote favorite
cfg = rs.conf();
cfg.members[0].priority = 0.5;
cfg.members[1].priority = 2;
cfg.members[2].priority = 3;

rs.reconfig(cfg);

