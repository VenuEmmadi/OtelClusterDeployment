
**Deploying the Application to Kubernetes Cluster:**

This section outlines the steps to deploy the application to an Azure Kubernetes Service (AKS) cluster. You'll need your resource group name and cluster name for these commands.

**Prerequisites:**

* Azure CLI with AKS extension installed (https://learn.microsoft.com/en-us/cli/azure/install-azure-cli-windows)
* kubectl configured to access your AKS cluster

**Steps:**

1. **Get AKS (if you are using AKS) Cluster Credentials:**

   ```bash
   az aks get-credentials --resource-group <YOUR_RESOURCEGROUP_NAME> --name <YOUR_CLUSTER_NAME> --overwrite
2. **Deploy Configuration Files:**
* kubectl apply -f configmap-collector.yml
* kubectl apply -f configmap-tempo.yml
* kubectl apply -f configmap-prometheus.yml
* kubectl apply -f configmap-grafana.yml


3. **Deploy Application Components:**
* kubectl apply -f deployment-employee.yml
* kubectl apply -f deployment-collector.yml
* kubectl apply -f deployment-tempo.yml
* kubectl apply -f deployment-loki.yml
* kubectl apply -f deployment-prometheus.yml
* kubectl apply -f deployment-grafana.yml
* kubectl apply -f deployment-postgres.yml
