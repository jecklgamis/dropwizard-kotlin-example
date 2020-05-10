default:
	cat ./Makefile
apply:
	kubectl apply -f deployment.yaml
delete:
	kubectl delete deployment,service dropwizard-kotlin-example
get-pods:
	kubectl get pods -l app=dropwizard-kotlin-example -o wide
describe-service:
	kubectl describe service dropwizard-kotlin-example
