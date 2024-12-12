# Base image with Python 3.10.9
FROM python:3.10.9-slim

# Set environment variables for Go and Kotlin
ENV PATH="/usr/local/go/bin:$PATH"
ENV KOTLIN_HOME="/opt/kotlin"
ENV PATH="$KOTLIN_HOME/bin:$PATH"

# Install required system dependencies
RUN apt-get update && apt-get install -y \
    curl wget git unzip tar maven && \
    apt-get clean

# Install Go 1.23.3
RUN wget https://go.dev/dl/go1.23.3.linux-amd64.tar.gz && \
    tar -C /usr/local -xzf go1.23.3.linux-amd64.tar.gz && \
    rm go1.23.3.linux-amd64.tar.gz

# Install Amazon Corretto JDK 21.0.1.12.1
RUN wget -O amazon-corretto-21.tar.gz \
    https://corretto.aws/downloads/resources/21.0.1.12.1/amazon-corretto-21.0.1.12.1-linux-x64.tar.gz && \
    tar -C /usr/local -xzf amazon-corretto-21.tar.gz && \
    rm amazon-corretto-21.tar.gz && \
    ln -s /usr/local/amazon-corretto-21.0.1.12.1 /usr/local/java && \
    echo "export JAVA_HOME=/usr/local/java" >> ~/.bashrc && \
    echo "export PATH=$JAVA_HOME/bin:$PATH" >> ~/.bashrc


# Install Kotlin 2.0.20
RUN wget -O kotlin-compiler.zip https://github.com/JetBrains/kotlin/releases/download/v2.0.20/kotlin-compiler-2.0.20.zip && \
    unzip kotlin-compiler.zip -d /opt/kotlin && \
    rm kotlin-compiler.zip

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

# Initialize Go project in the specified folder
RUN cd /app/data/generated/docs_golang && \
    go mod tidy

# Set the entrypoint for the container
ENTRYPOINT ["python", "download_and_filter_dataset.py"]
