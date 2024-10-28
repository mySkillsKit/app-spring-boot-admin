# spring-boot-admin

## go to http://localhost:8060

## see https://docs.spring-boot-admin.com/current/getting-started.html for documentation

## Create images docker

/app-spring-boot-admin/first-microservice

```
docker build . -t myskillskit/first-microservice:latest -t myskillskit/first-microservice:1.0.0
```

/app-spring-boot-admin/second-microservice

```
docker build . -t myskillskit/second-microservice:latest -t myskillskit/second-microservice:1.0.0
```

/app-spring-boot-admin/third-microservice

```
docker build . -t myskillskit/third-microservice:latest -t myskillskit/third-microservice:1.0.0
```

/app-spring-boot-admin/spring-boot-admin-server

```
docker build . -t myskillskit/admin-server:latest -t myskillskit/admin-server:1.0.0
```

docker run --rm -p8060:8060 -it myskillskit/admin-server:1.0.0

### Auth docker hub and upload image to https://hub.docker.com

```
docker login
```

```
docker push myskillskit/first-microservice --all-tags
```

```
docker push myskillskit/second-microservice --all-tags
```

```
docker push myskillskit/third-microservice --all-tags
```

```
docker push myskillskit/admin-server --all-tags
```

## Create deploy minikube example for app admin-server

deploy=deployment |
svc=service |
you can create alias:  alias k=kubectl

```angular2html
minikube start
minikube version

kubectl create deploy admin-server-deploy --image=myskillskit/admin-server:1.0.0
kubectl get deploy

kubectl get pods
kubectl describe deploy admin-server-deploy
kubectl describe pod admin-server-deploy-df7498f48-98s7w

kubectl get pods -o wide
minikube ssh
curl 10.244.0.23:8060; echo

kubectl get svc

kubectl get deploy

//open tunnel
minikube tunnel
kubectl expose deploy admin-server-deploy --type=LoadBalancer --port=9060 --target-port=8060
kubectl get svc

http://localhost:9060


//replica
kubectl scale deploy admin-server-deploy --replicas=3
kubectl get pods -o wide


//update app admin-server
kubectl rollout status deploy admin-server-deploy

minikube ssh
docker ps | grep admin-server-deploy
exit

kubectl set image deploy admin-server-deploy admin-server=myskillskit/admin-server:latest
kubectl rollout status deploy admin-server-deploy
kubectl describe deploy admin-server-deploy

//rollback update
kubectl set image deploy admin-server-deploy admin-server=myskillskit/admin-server:1.0.0

//delete pods
kubectl delete svc admin-server-deploy
kubectl delete deploy admin-server-deploy
kubectl get pods -o wide
```

## Create deployment.yaml

/Users/avas/IdeaProjects/app-spring-boot-admin/deployment.yaml

```angular2html
minikube start
kubectl apply -f deployment.yaml
kubectl get pods -o wide
minikube ssh
curl 10.244.0.7:8060; echo
kubectl logs admin-server-deploy-66c597f59d-hpjcd


//update deployment.yaml  example add replicas
kubectl apply -f deployment.yaml
kubectl logs admin-server-deploy-66c597f59d-hpjcd
```

## Create service.yaml

/Users/avas/IdeaProjects/app-spring-boot-admin/service.yaml

```angular2html
//open tunnel
minikube tunnel
kubectl apply -f service.yaml
kubectl get svc
kubectl describe svc admin-server-deploy

http://localhost:9060

```

### minikube dashboard

```angular2html
minikube dashboard

```

```angular2html
kubectl delete -f deployment.yaml -f service.yaml
```

### Check logging

```angular2html
kubectl logs pod-name
kubectl logs pod-name --all-conainers=true
kubectl logs pod-name --since=1h

kubectl events
kubectl get events --all-namespaces
kubectl events --for pod/pod-name

kubectl logs deployment deployment-name
```

### 2 app deployment

```angular2html
//open tunnel
minikube start
minikube tunnel
cd /Users/avas/IdeaProjects/app-spring-boot-admin/.kube
kubectl apply -f admin-and-micro-service.yaml -f first-micro-service.yaml

kubectl get pods -o wide

kubectl get svc

kubectl logs pod-name

kubectl describe svc admin-server-deploy

http://localhost:9060
http://localhost:9081

kubectl delete -f admin-and-micro-service.yaml -f first-micro-service.yaml

```