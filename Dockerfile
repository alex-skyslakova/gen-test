# Base image with Python 3.10.9
FROM python:3.10.9-slim

# Set environment variables for Go and Kotlin
ENV PATH="/usr/local/go/bin:$PATH"
ENV KOTLIN_HOME="/opt/kotlin"
ENV PATH="$KOTLIN_HOME/kotlinc/bin:$PATH"

# Install required system dependencies
RUN apt-get update && apt-get install -y \
    curl wget git unzip tar && \
    apt-get clean

# Install Maven 3.9.6
RUN wget https://downloads.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz && \
    tar -C /usr/local -xzf apache-maven-3.9.6-bin.tar.gz && \
    rm apache-maven-3.9.6-bin.tar.gz && \
    ln -s /usr/local/apache-maven-3.9.6 /usr/local/maven

# Set environment variables for Maven
ENV MAVEN_HOME=/usr/local/maven
ENV PATH="$MAVEN_HOME/bin:$PATH"

# Install Go 1.23.3
RUN wget https://go.dev/dl/go1.23.3.linux-amd64.tar.gz && \
    tar -C /usr/local -xzf go1.23.3.linux-amd64.tar.gz && \
    rm go1.23.3.linux-amd64.tar.gz

RUN curl -sSfL https://raw.githubusercontent.com/golangci/golangci-lint/master/install.sh | sh -s -- -b $(go env GOROOT)/bin v1.62.2

# Install Amazon Corretto JDK 21.0.1.12.1
# Install Amazon Corretto 21
RUN apt-get update && apt-get install -y wget gnupg && \
    wget -O - https://apt.corretto.aws/corretto.key | gpg --dearmor -o /usr/share/keyrings/corretto-keyring.gpg && \
    echo "deb [signed-by=/usr/share/keyrings/corretto-keyring.gpg] https://apt.corretto.aws stable main" > /etc/apt/sources.list.d/corretto.list && \
    apt-get update && apt-get install -y java-21-amazon-corretto-jdk && \
    apt-get clean && rm -rf /var/lib/apt/lists/*


# Set environment variables for Java
ENV JAVA_HOME=/usr/lib/jvm/java-21-amazon-corretto
ENV PATH="$JAVA_HOME/bin:$PATH"

# Install Kotlin 2.0.20
RUN wget -O kotlin-compiler.zip https://github.com/JetBrains/kotlin/releases/download/v2.0.20/kotlin-compiler-2.0.20.zip && \
    unzip kotlin-compiler.zip -d /opt/kotlin && \
    rm kotlin-compiler.zip

# Install bash (required for kotlinc)
RUN apt-get update && apt-get install -y bash

# Add Kotlin to PATH
ENV PATH="/opt/kotlin/kotlinc/bin:$PATH"

# Install ktlint 0.50.0
RUN wget -O /opt/ktlint.jar https://github.com/pinterest/ktlint/releases/download/0.50.0/ktlint && \
    chmod +x /opt/ktlint.jar && \
    echo "export PATH=/opt:$PATH" >> ~/.bashrc

# Copy the Python requirements
COPY requirements.txt /app/requirements.txt

# Install Python dependencies
RUN pip install --no-cache-dir -r /app/requirements.txt

# Set the working directory
WORKDIR /app

# Copy the entire project
COPY . /app
RUN bash -c "[ -d /app/llm-gen ] && rm -r /app/llm-gen || true && \
              [ -d /app/llm-gen-test ] && rm -r /app/llm-gen-test || true"


# Initialize Go project in the specified folder
RUN cd /app/data/generated/docs_golang && \
    go mod tidy

# Download dependecnies for Java and Kotlin projects for test execution
RUN mvn -f ./data/kotlinSetup/pom.xml clean validate dependency:go-offline && \
    mvn -f ./data/javaSetup/pom.xml clean validate dependency:go-offline

# Set the entrypoint for the container
ENTRYPOINT ["python", "main.py"]
