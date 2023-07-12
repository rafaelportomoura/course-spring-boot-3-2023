#!/bin/bash
curl -sL https://deb.nodesource.com/setup_20.x | bash -

sudo apt update -y \
    && sudo apt install -y --no-install-recommends nodejs 


VERSION=3.9.3
if [ ! -d "/usr/share/maven/apache-maven-$VERSION" ]; then
    echo "Installing Maven..."
    cd /tmp
    curl -sO https://archive.apache.org/dist/maven/maven-3/$VERSION/binaries/apache-maven-$VERSION-bin.tar.gz
    sudo tar -zxvf apache-maven-$VERSION-bin.tar.gz
    sudo mv apache-maven-$VERSION /usr/share/maven
    rm apache-maven-$VERSION-bin.tar.gz
    echo "export PATH=$PATH:/usr/share/maven">>~/.zshrc
    echo "export PATH=$PATH:/usr/share/maven/bin">>~/.zshrc
    source ~/.zshrc 1>/dev/null 2>/dev/null
fi
