FROM ubuntu:focal

ARG ENV

ARG DEBIAN_FRONTEND=noninteractive

# Install initial dependencies
RUN apt-get -y update && \
        apt-get install -y -q add-apt-key curl software-properties-common

ARG APT_KEY_DONT_WARN_ON_DANGEROUS_USAGE=DontWarn

# Add the apt.gbif.es repo and gpg key
RUN echo "deb [arch=amd64] https://apt.gbif.es/ bionic main" > /etc/apt/sources.list.d/ala.list && \
        apt-key adv --keyserver keyserver.ubuntu.com --recv-keys F697D8D2ADB9E24A

# yq
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys CC86BB64 && \
        add-apt-repository ppa:rmescandon/yq && \
        apt-get -y update && \
        apt-get install -y -q yq

# docopts
RUN curl -o /usr/local/bin/docopts -LJO https://github.com/docopt/docopts/releases/download/v0.6.3-rc2/docopts_linux_amd64
RUN chmod +x /usr/local/bin/docopts

RUN mkdir -p /data/pipelines-data
RUN mkdir -p /data/pipelines-shp

# la-pipeline main dependencies
RUN apt-get install -y -q openjdk-8-jdk

# Update the next commented date to force a new docker hub build with an updated la-pipelines version
RUN apt-get -y update && \
        apt-get install -y -q la-pipelines  # 20200811

# Enable bash autocompletion by default
RUN sed -i '/^#.*\. \/etc\/bash_completion/s/^#//' /etc/bash.bashrc

VOLUME  ["/data"]

CMD ["/bin/bash"]
