## Dropwizard Kotlin Example

[![Build](https://github.com/jecklgamis/dropwizard-kotlin-example/actions/workflows/build.yml/badge.svg)](https://github.com/jecklgamis/dropwizard-kotlin-example/actions/workflows/build.yml)

This is an example Dropwizard app using Kotlin.

Docker: `docker run -p 8080:8080 -p 8081:8081 jecklgamis/dropwizard-kotlin-example:main`

## Running The App
Ensure you have Java 11 installed.
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
```

Operational menu endpoint:
* `http://localhost:8081`

## Running The App In Kubernetes
This is an example Kubernetes deployment. This assumes you have a basic knowledge on [Kubernetes](https://kubernetes.io).

### Requirements
* Ensure you can deploy to a Kubernetes cluster using `kubectl`
* Ensure you have [Helm ](https://helm.sh/) installed

## Create and deploy Helm chart
```
cd deployment/k8s/helm 
make all
make install
```

#### Verify pods are running
```
kubectl get pods -l app=dropwizard-kotlin-example -o wide
```
Example output:
```
NAME                                         READY   STATUS    RESTARTS   AGE     IP            NODE                   NOMINATED NODE   READINESS GATES
dropwizard-kotlin-example-6d84898db6-6sl4p   1/1     Running   0          6m32s   10.244.0.13   pool-74ylys6z6-377s5   <none>           <none>
dropwizard-kotlin-example-6d84898db6-dlwbf   1/1     Running   0          6m32s   10.244.0.40   pool-74ylys6z6-377s5   <none>           <none>
```

#### Verify pod end point using `kubectl port-forward`
```
export POD_NAME=$(kubectl get pods --namespace default -l "app=dropwizard-kotlin-example" -o jsonpath="{.items[0].metadata.name}")
kubectl --namespace default port-forward $POD_NAME 8080
curl http://localhost:8080
```

#### Log in to one of the containers
```
kubectl exec -it $POD_NAME -- bin/bash
```

#### Describe the service:
```
kubectl describe service dropwizard-kotlin-example
```
Example output:
```
Name:                     dropwizard-kotlin-example
Namespace:                default
Labels:                   app=dropwizard-kotlin-example
Annotations:              Selector:  app=dropwizard-kotlin-example
Type:                     NodePort
IP:                       10.245.41.171
Port:                     http  80/TCP
TargetPort:               8080/TCP
NodePort:                 http  30081/TCP
Endpoints:                10.244.0.13:8080,10.244.0.40:8080
Port:                     https  443/TCP
TargetPort:               8443/TCP
NodePort:                 https  30964/TCP
Endpoints:                10.244.0.13:8443,10.244.0.40:8443
Session Affinity:         None
External Traffic Policy:  Cluster
Events:                   <none>
```

#### Verify service endpoint
```
curl http://<node-ip-address:NodePort>
curl --insecure https://<node-ip-adress:NodePort>
```
Note that the `node-ip-address` here is the ip address of the Kubernetes node and is different from the `IP` above.
This might or might not be publicly accessible depending on your Kubernetes setup. Changing the type to `LoadBalancer` 
will automatically create the cloud provider specific load balancer and a publicly accessible ip address.

See [Service](https://kubernetes.io/docs/concepts/services-networking/service/) for more details.

#### Delete the deployment
```
cd deployment/k8s/helm
make uninstall 
```
