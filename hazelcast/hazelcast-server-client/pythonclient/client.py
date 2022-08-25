import hazelcast

# Start the Hazelcast Client and connect to an already running Hazelcast Cluster on 127.0.0.1
client = hazelcast.HazelcastClient()
# Get the Distributed Map from Cluster.
my_map = client.get_map("data").blocking()
# Standard Put and Get
print(my_map.get(992533683))

# Shutdown this Hazelcast Client
client.shutdown()
