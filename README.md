az aks get-credentials --resource-group <YOUR_RESOURCEGROUP_NAME> --name <YOUR_CLUSTER_NAME> --overwrite

kubectl apply -f configmap-collector.yml
kubectl apply -f configmap-tempo.yml
kubectl apply -f configmap-prometheus.yml
kubectl apply -f configmap-grafana.yml



kubectl apply -f deployment-employee.yml
kubectl apply -f deployment-collector.yml
kubectl apply -f deployment-tempo.yml
kubectl apply -f deployment-loki.yml
kubectl apply -f deployment-prometheus.yml
kubectl apply -f deployment-grafana.yml
kubectl apply -f deployment-postgres.yml
