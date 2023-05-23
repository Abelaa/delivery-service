
[Start Kafka Local]

/usr/local/bin/zookeeper-server-start /usr/local/etc/zookeeper/zoo.cfg
/usr/local/bin/kafka-server-start /usr/local/etc/kafka/server.properties

[istio]



[helm for mongodb]

helm install mongodb-deploy oci://registry-1.docker.io/bitnamicharts/mongodb

[helm for kafka]

helm install kafka-deploy oci://registry-1.docker.io/bitnamicharts/kafka








** Please be patient while the chart is being deployed **

MongoDB&reg; can be accessed on the following DNS name(s) and ports from within your cluster:

    mongodb-deploy.default.svc.cluster.local

To get the root password run:

    export MONGODB_ROOT_PASSWORD=$(kubectl get secret --namespace default mongodb-deploy -o jsonpath="{.data.mongodb-root-password}" | base64 -d)

To connect to your database, create a MongoDB&reg; client container:

    kubectl run --namespace default mongodb-deploy-client --rm --tty -i --restart='Never' --env="MONGODB_ROOT_PASSWORD=$MONGODB_ROOT_PASSWORD" --image docker.io/bitnami/mongodb:6.0.6-debian-11-r0 --command -- bash

Then, run the following command:
    mongosh admin --host "mongodb-deploy" --authenticationDatabase admin -u root -p $MONGODB_ROOT_PASSWORD

To connect to your database from outside the cluster execute the following commands:

    kubectl port-forward --namespace default svc/mongodb-deploy 27017:27017 &
    mongosh --host 127.0.0.1 --authenticationDatabase admin -p $MONGODB_ROOT_PASSWORD
