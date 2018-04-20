echo "******* Creating instances folders ********"
# replicaSet/shards
mkdir -p mongo_cluster/rs01_inst01
mkdir -p mongo_cluster/rs01_inst02
mkdir -p mongo_cluster/rs01_inst03
mkdir -p mongo_cluster/rs01_arbiter
# configServers
mkdir -p mongo_cluster/data_conf/configdb_01
mkdir -p mongo_cluster/data_conf/configdb_02
mkdir -p mongo_cluster/data_conf/configdb_03
# mongos
mkdir -p mongo_cluster/data_conf/mongos/
sudo chown -R mongodb:mongodb mongo_cluster/

# start shards instances
echo "\n******* Starting mongo service ********"
sudo systemctl start mongod.service
sudo systemctl status mongod.service
echo "\n******* Creating shards ********"
sudo -u mongodb mongod --replSet rs01 --port 27101 --shardsvr --dbpath mongo_cluster/rs01_arbiter/ --logpath mongo_cluster/rs01_arbiter/mongod_27101_arbiter.log --fork
sudo -u mongodb mongod --replSet rs01 --port 27102 --shardsvr --dbpath mongo_cluster/rs01_inst01/ --logpath mongo_cluster/rs01_inst01/mongod_27102.log --fork
sudo -u mongodb mongod --replSet rs01 --port 27103 --shardsvr --dbpath mongo_cluster/rs01_inst02/ --logpath mongo_cluster/rs01_inst02/mongod_27103.log --fork
sudo -u mongodb mongod --replSet rs01 --port 27104 --shardsvr --dbpath mongo_cluster/rs01_inst03/ --logpath mongo_cluster/rs01_inst03/mongod_27103.log --fork

# start configServers instances
echo "\n******* Creating configServers ********"
sudo -u mongodb mongod --configsvr --replSet rs01 --port 27201 --dbpath mongo_cluster/data_conf/configdb_01/ --logpath mongo_cluster/data_conf/configdb_01/configsvr1.log --fork
sudo -u mongodb mongod --configsvr --replSet rs01 --port 27202 --dbpath mongo_cluster/data_conf/configdb_02/ --logpath mongo_cluster/data_conf/configdb_02/configsvr2.log --fork
sudo -u mongodb mongod --configsvr --replSet rs01 --port 27203 --dbpath mongo_cluster/data_conf/configdb_03/ --logpath mongo_cluster/data_conf/configdb_03/configsvr3.log --fork

echo "******* Initializing shards ********"
sudo -u mongodb mongo --port 27102 admin --eval 'cfg = {_id:"rs01", members:[{_id:0, host:"127.0.0.1:27102"}, {_id:1, host:"127.0.0.1:27103"}, {_id:3, host:"127.0.0.1:27104"}, {_id:2, host:"127.0.0.1:27101", arbiterOnly:true}]}; rs.initiate(cfg);'
echo "\n******* Initializing configServers ********"
sudo -u mongodb mongo --port 27201 admin --eval 'rs.initiate(); rs.add("127.0.0.1:27202"); rs.add("127.0.0.1:27203");'


# start mongos
echo "\n******* Creating mongos ********"
sudo -u mongodb mongos --port 27301 --configdb rs01/127.0.0.1:27201,127.0.0.1:27202,127.0.0.1:27203 --logpath mongo_cluster/data_conf/mongos/mongo_27301.log --fork
echo "\n******* Initializing mongos ********"
sudo -u mongodb mongo --port 27301 admin --eval 'sh.addShard("127.0.0.1:27101"); sh.addShard("127.0.0.1:27102"); sh.addShard("127.0.0.1:27103");' # add shards to mongos

# test config
#mongo --port 27301 testDB --eval "db.createCollection('clients');"
