// Configure hosts file on each server 
172.31.20.126   ip-172-31-20-126.eu-west-1.compute.internal #Aziz
172.31.20.240   ip-172-31-20-240.eu-west-1.compute.internal #Ruben
172.31.29.164   ip-172-31-29-164.eu-west-1.compute.internal #Ali
172.31.18.9     ip-172-31-18-9.eu-west-1.compute.internal   #Karim
172.31.31.238   ip-172-31-31-238.eu-west-1.compute.internal #Felix

// Install mongodb server on all hosts
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2930ADAE8CAF5059EE73BB4B58712A2291FA4AD5
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/3.6 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-3.6.list
sudo apt-get update
sudo apt-get install -y mongodb-org

// ################## Set up configServer #########################
sudo vim /etc/mongod.conf
# network interfaces
net:
  port: 27019
  bindIp: 172.31.29.164/172.31.20.240
replication:
  replSetName: configReplSet
sharding:
  clusterRole: "configsvr"

// Restart mongod service
sudo systemctl restart mongod / sudo service mongod restart

// Initiate configServers
mongo 172.31.29.164:27019
>rs.initiate();
>rs.add("ip-172-31-20-240.eu-west-1.compute.internal:27019");
>rs.status()


// ################## Set up mongos #########################
sudo vim /etc/mongos.conf
# where to write logging data.
systemLog:
  destination: file
  logAppend: true
  path: /var/log/mongodb/mongos.log
# network interfaces
net:
  port: 27017
  bindIp: 172.31.20.126
sharding:
  configDB: configReplSet/ip-172-31-29-164.eu-west-1.compute.internal:27019,ip-172-31-20-240.eu-west-1.compute.internal:27019

sudo vim /lib/systemd/system/mongos.service
[Unit]
Description=Mongo Cluster Router
After=network.target

[Service]
User=mongodb
Group=mongodb
ExecStart=/usr/bin/mongos --config /etc/mongos.conf
# file size
LimitFSIZE=infinity
# cpu time
LimitCPU=infinity
# virtual memory size
LimitAS=infinity
# open files
LimitNOFILE=64000
# processes/threads
LimitNPROC=64000
# total threads (user+kernel)
TasksMax=infinity
TasksAccounting=false

[Install]
WantedBy=multi-user.target

//##################### Configure mongo to start on system start
sudo systemctl stop mongod // stop mongod service
sudo systemctl enable mongos.service/mongod.service // start mongos/mongod on stratup
sudo systemctl start mongos //

// ################## Set up shards #########################
sudo vim /etc/mongod.conf
# network interfaces
net:
  port: 27018
  bindIp: 172.31.31.238/172.31.18.9
/*replication: // ne pas tenir compte
  replSetName: sh1/sh2*/
sharding:
  clusterRole: "shardsvr"

// Restart mongod service
sudo systemctl restart mongod

// Shards initialisation
mongo 172.31.31.238:27018 --eval "rs.initiate()"
mongo 172.31.18.9:27018 --eval "rs.initiate()"

// Add shards to cluster
sh.addShard("ip-172-31-31-238.eu-west-1.compute.internal:27018")
sh.addShard("ip-172-31-18-9.eu-west-1.compute.internal:27018")

// Configure database
use testDB;
sh.enableSharding("testDB");
db.createCollection("test");
db.test.createIndex({"_id":1});
sh.shardCollection("testDB.test",{"_id":1});

// Import Data
mongoimport --db testDB --collection test --host ip-172-31-20-126.eu-west-1.compute.internal --port 27017 restaurants.json
//Check status
mongo --port 27017 --eval "sh.status()"