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

## Updating an existing container 
* Build and push image to registery
* Use imperative kubernets command to update container: `kubectl set image deployment/server-deployment [deploymentName]=[image]:[tag]` *Full example*: `kubectl set image deployment/server-deployment server=pachecopaulo/multi-server:v1`
