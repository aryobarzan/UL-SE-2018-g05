#!/bin/bash
echo "***************************************************"
echo "**************************************************"
echo "			Install Java 8"
echo "***************************************************"
echo "***************************************************"
apt-get -y -q install software-properties-common htop
apt-get -y -q install python-software-properties
add-apt-repository ppa:webupd8team/java
apt-get -y -q update
apt-get -y -q autoremove
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
apt-get -y -q install oracle-java8-installer
update-java-alternatives -s java-8-oracle
echo "***************************************************"
echo "		Installing port analyser (nmap)"
echo "***************************************************"
echo "***************************************************"
apt-get -y -q install nmap
echo "***************************************************"
echo "***************************************************"
echo "			Opening port"
echo "***************************************************"
echo "***************************************************"
ufw allow from 192.168.1.2
ufw allow from 192.168.1.3
ufw allow from 192.168.1.4
ufw allow 22
ufw --force enable
echo "***************************************************"
echo "***************************************************"
echo "			Ending script"
echo "***************************************************"
echo "***************************************************"
exit 0