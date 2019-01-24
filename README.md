# kubernets-multi-container-app

PoC project intended to work with multi container deployment within Kubernets

### Stack
   * Kotlin
   * Spring Boot
   * Redis DB
   * Postgres
   * Docker
   * Kubernets
   * NGINX Ingress Controller

## Deploying Docker containers to Kubernets 

* Build the docker image and set a tag: `docker build -t pachecopaulo/[image]:[version]`
* Push the image to the registery: docker push `docker build -t pachecopaulo/[image]:[version]`
* Apply configuration for Kubernets: `kubectl apply -f k8s/`

## Running with Minikube
* Install Minikube
* Apply configuration to create the cluster `kubectl apply -f k8s/`
* Get the IP address to access the application `minikube ip`

The NGINX will take care of routing the / to the frontend application which is running on port 3000

## Updating an existing container 
* Build and push image to registery
* Use imperative kubernets command to update container: `kubectl set image deployment/[yaml deployment file] [deploymentName]=[image]:[tag]` 

  *Full example*: `kubectl set image deployment/server-deployment server=pachecopaulo/multi-server:v1`
