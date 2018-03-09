from couchbase.cluster import Cluster
from couchbase.cluster import PasswordAuthenticator
import couchbase.exceptions as E

cluster = Cluster('couchbase://localhost:8091')
authenticator = PasswordAuthenticator('Administrator', 'enirys')
cluster.authenticate(authenticator)

try:
    bucket = cluster.open_bucket('travel-sample')
except E.AuthError:
    print("Authentication failed, You provided an invalid username/password combination.")
except E.TimeoutError:
	print "The operation timed out"
except E.ConnectError:
	print"Failed to connect to the requested server"
	
	
'''

	


try:
    bucket.get("foo")
except E.NotFoundError:
    print("Item does not exist")
except E.CouchbaseTransientError:
    print("Transient error received. Handling and backing off")'''
