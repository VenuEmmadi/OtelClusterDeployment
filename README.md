## Employee Otel AutoInstrumentation

This project features an Employee Microservice that is configured with OpenTelemetry Automatic Instrumentation. It leverages OpenTelemetry to provide comprehensive observability, allowing you to monitor and trace the application's performance seamlessly

### Getting Started

This section guides you through setting up the environment and running the application.

**Prerequisites:**
* Docker Desktop (https://www.docker.com/) with a Docker Hub account

**Steps:**

1. **Clone the Code:**
    - Clone the repository using the following command:

      ```bash
      git clone https://github.com/VenuEmmadi/OtelAutoInstrumentation.git
      ```

    - Navigate to the project directory:

      ```bash
      cd OtelAutoInstrumentation
      ```

2. **Download and Install Docker Desktop:**
    - Download and install Docker Desktop from the official website if you haven't already.
    - Sign in to Docker Desktop using your Docker Hub credentials.

3. **Building and Pushing the Image (Optional):**
    - This section explains how to build and push the Docker image to your Azure Container Registry (ACR). You can skip this step if you only want to run the application locally.
    - **Configure Jib in build.gradle:**
        - Edit the `build.gradle` file and add the following Jib configuration snippet. Replace placeholders with your details:
    
        ```groovy
        jib {
            from {
                image = 'mcr.microsoft.com/openjdk/jdk:17-ubuntu'
            }
            to {
                image = 'YOUR-ACR.azurecr.io/employee:0.0.1-SNAPSHOT'
                auth {
                    username = "YOUR-ACR"
                    password = "YOUR-ACR-PASSWORD"
                }
            }
        }
    - **Build and Push the Image:**
        - Run the following command to build and push the image to your ACR:

          ```bash
          gradle jib
          ```

4. **Building the Image Locally:**
    - To build the image locally without pushing it to a registry, use this command:

      ```bash
      gradle jibDockerBuild
      ```

5. **Running the Application:**
    - **Create and Start Containers:**
        - Use Docker Compose to create and start the containers:

          ```bash
          docker compose up -d
          ```

    - **Verify the Setup:**
        - Open http://localhost:8080/getEmployee/Venu in your web browser. If you see a response with employee details, the setup is successful. Otherwise, troubleshoot your configuration.

6. **Accessing Monitoring Tools (Optional):**
    - This section provides links to access optional monitoring tools if included in the project:
        - Prometheus: http://localhost:9090/
        - Grafana: http://localhost:3000/
**Note:**

- Replace placeholders like `<username>`, `<ACR>`, and `<ACR-PASSWORD>` with your actual values throughout the process.
