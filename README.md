<h1>OpenTelemetry Collector Configuration and Deployment for Kubernetes</h1>

This repository provides configuration and deployment files for the OpenTelemetry Collector in a Kubernetes environment. The collector ingests metrics, traces, and logs from various sources and exports them to backend systems for analysis and monitoring.

<h2>Configuration (configmap_collector.yml)</h2>

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

<h2>Deployment (deployment-collector.yml)</h2>
The deployment-collector.yml file defines a DaemonSet to deploy the OpenTelemetry Collector on every Kubernetes node.


**Key points:**

- The DaemonSet ensures a single collector instance runs on each node.<br>
- The collector container image uses *otel/opentelemetry-collector-contrib:0.91.0* (or adjust the version as needed).
- The container mounts the configuration from the otel-collector-config ConfigMap as */etc/otelcol-contrib/otel-collector.yml*.
- The collector container reads the Kubernetes node name from the environment variable *K8S_NODE_NAME* for the kubeletstats receiver configuration.<br>
- The *var/log/pods* and */var/lib/docker/containers* directories are mounted read-only for potential use by receivers (modify if different paths are used).

**Note**
* In configmap-collector.yml file, ensure to modify the prometheusremotewrite, Prometheus, Loki and otlp/tempo endpoint with proper port number. <br>
* In deployment-collector.yml file , ensure to modify the,
   - "Kind" value with required kind type(daemonset or deployment)
   - "serviceAccountName" Replace with your required service account name
   - "image" Replace with the proper image name
   - Under "VolumeMounts" section ensure to change the mount paths of otel-collector.yml  with your own directory names.
  
