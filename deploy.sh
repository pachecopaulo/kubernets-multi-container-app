docker build pachecopaulo/multi-client:latest
docker build pachecopaulo/multi-server:latest

docker push pachecopaulo/multi-client:latest
docker push pachecopaulo/multi-server:latest

#docker build -t pachecopaulo/multi-client:latest -t pachecopaulo/multi-client:$SHA ./client
#docker build -t pachecopaulo/multi-server:latest -t pachecopaulo/multi-server:$SHA .
#
#docker push pachecopaulo/multi-client:latest
#docker push pachecopaulo/multi-server:latest
#
#docker push pachecopaulo/multi-client:$SHA
#docker push pachecopaulo/multi-server:$SHA
#
#kubectl apply -f k8s
#kubectl set image deployments/server-deployment server=pachecopaulo/multi-server:$SHA
#kubectl set image deployments/client-deployment client=pachecopaulo/multi-client:$SHA