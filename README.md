## Dropwizard Kotlin Example

[![Build Status](https://travis-ci.org/jecklgamis/dropwizard-kotlin-example.svg?branch=master)](https://travis-ci.org/jecklgamis/dropwizard-kotlin-example)

This is an example Dropwizard app using Kotlin.

Docker Hub: `docker run -p 8080:8080 -p 8443:8443 -p 8081:8081 jecklgamis/dropwizard-kotlin-example:latest` 
 
## Running The App
Ensure you have Java 8 installed.
```
./mvnw clean package
java -jar target/dropwizard-kotlin-example.jar
```

## Running The App Using Docker
Ensure you have a working Docker environment.
```
make dist image run
```

## Testing The Endpoints
Point your browser to `http://localhost:8080` or use `curl` in command line.

```
curl -v  http://localhost:8080/
curl -v  --cacert src/main/resources/server.crt --resolve "dropwizard-kotlin-example:8443:127.0.0.1"  https://dropwizard-kotlin-example:8443/
```

Operational menu endpoint:
* `http://localhost:8081`

## Running The App In Kubernetes

This is an example Kubernetes deployment. Ensure you have a working Kubernetes cluster and 
properly configured `kubectl` context (i.e `~/.kube/config`).

This deployment will:
* use the `jecklgamis/dropwizard-kotlin-example` image from Docker Hub
* create 2 pods and exposing both HTTP (8080) and HTTPS (8443) endpoints 
* create service with `LoadBalancer` type exposing ports HTTP (80) and HTTPS (443)

#### Create pods and service:
```
cd deployment/k8s 
kubectl apply -f deployment.yaml
```

#### Verify pods are running:
```
kubectl get pods -l app=dropwizard-kotlin-example -o wide
```
Example output:
```
NAME                                        READY   STATUS    RESTARTS   AGE   IP            NODE      NOMINATED NODE   READINESS GATES
dropwizard-kotlin-example-7d5bff84d-fvz6w   1/1     Running   0          17s   10.244.0.65   okinawa   <none>           <none>
dropwizard-kotlin-example-7d5bff84d-hkbqq   1/1     Running   0          17s   10.244.0.66   okinawa   <none>           <none>
```

#### Login to one of the containers:
```
kubectl exec -it dropwizard-kotlin-example-7d5bff84d-fvz6w -- bin/bash
```

#### Describe the service:
```
kubectl describe service dropwizard-kotlin-example
```
Example output:
```
kubectl describe service dropwizard-kotlin-example
Name:                     dropwizard-kotlin-example
Namespace:                default
Labels:                   app=dropwizard-kotlin-example
Annotations:              Selector:  app=dropwizard-kotlin-example
Type:                     NodePort
IP:                       10.104.78.125
Port:                     http  80/TCP
TargetPort:               8080/TCP
NodePort:                 http  31970/TCP
Endpoints:                10.244.0.65:8080,10.244.0.66:8080
Port:                     https  443/TCP
TargetPort:               8443/TCP
NodePort:                 https  31274/TCP
Endpoints:                10.244.0.65:8443,10.244.0.66:8443
Session Affinity:         None
External Traffic Policy:  Cluster
Events:                   <none>

```

#### Verify the service with `curl`

In one of the cluster nodes, you should be able to `curl http://<IP:Port>` or `curl --insecure https://<IP:Port>`. 

Example output:
```
{"name":"dropwizard-kotlin-example","java.version":"1.8.0_242","java.runtime.name":"OpenJDK Runtime Environment"}
```

Note that the Service's IP address is virtual. See [this](https://kubernetes.io/docs/concepts/services-networking/connect-applications-service/)
for details. 

#### Delete the deployment
```
kubectl delete deployment,service dropwizard-kotlin-example
```
