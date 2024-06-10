**OpenTelemetry Collector Configuration and Deployment for Kubernetes:**

This repository provides configuration and deployment files for the OpenTelemetry Collector in a Kubernetes environment. The collector ingests metrics, traces, and logs from various sources and exports them to backend systems for analysis and monitoring.

**Configuration (configmap_collector.yml)**

The configmap_collector.yml file defines the configuration for the OpenTelemetry Collector using a ConfigMap. It contains the following sections:

**Receivers:**

**otlp:** Defines an OpenTelemetry Protocol (OTLP) receiver for ingesting data from client applications.<br>
**kubeletstats:** Scrapes metrics from kubelets using the kubelet API. Configurable options include collection interval, authentication type, endpoint, insecure mode, and metric groups to collect.<br>
**k8s_cluster:** Scrapes metrics from the Kubernetes API server. Configurable options include collection interval and authentication type.

**Processors:**

**batch:** Batches data before export to improve efficiency. Configurable options include maximum batch size, minimum batch size, and timeout.<br>
**memory_limiter:** Limits the memory usage of the collector to prevent resource exhaustion. Configurable options include check interval, memory limit, and spike limit (temporary increase in allowed memory).<br>
**k8sattributes:** Extracted Kubernetes metadata from received data for enrichment. Configurable options include authentication type, passthrough behavior, and list of metadata attributes to extract.

**Exporters:**

**debug:** Exports data to a debug output stream for troubleshooting. Configurable verbosity level.<br>
**prometheusremotewrite:** Exports metrics to a Prometheus remote write endpoint. Configurable endpoint URL.<br>
**prometheus:** Exposes metrics scraped by the collector at a Prometheus endpoint. Configurable endpoint URL and open metrics behavior.<br>
**otlp/tempo:** Exports traces to Tempo, a distributed tracing backend. Configurable endpoint URL and insecure mode setting.<br>
**loki:** Exports logs to Loki, a horizontally scalable log aggregation system. Configurable endpoint URL.

**Service Pipelines:**

Three pipelines are defined: metrics, traces, and logs. Each pipeline specifies the receivers, processors, and exporters to be used for that type of data.

**Deployment (deployment-collector.yml)**
The deployment-collector.yml file defines a DaemonSet to deploy the OpenTelemetry Collector on every Kubernetes node.

**Key points:**

The DaemonSet ensures a single collector instance runs on each node.
The collector container image uses otel/opentelemetry-collector-contrib:0.91.0 (or adjust the version as needed).
The container mounts the configuration from the otel-collector-config ConfigMap as /etc/otelcol-contrib/otel-collector.yml.
The collector container reads the Kubernetes node name from the environment variable K8S_NODE_NAME for the kubeletstats receiver configuration.
The var/log/pods and /var/lib/docker/containers directories are mounted read-only for potential use by receivers (modify if different paths are used).
The collector Service exposes ports for OTLP gRPC (4317) and Prometheus (8889).

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
