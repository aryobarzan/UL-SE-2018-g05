#!/bin/bash
echo "***************************************************"
echo "***************************************************"
echo "			Installing MySQL"
echo "***************************************************"
echo "***************************************************"
sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password password root'
sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password_again password root'
apt-get -y -q update
apt-get -y -q install mysql-server
apt-get -y -q install mysql-client
apt-get -y -q install mysql-workbench
echo "***************************************************"
echo "***************************************************"
echo "			Creating database"
echo "***************************************************"
echo "***************************************************"
mysql -u root -proot < /vagrant/iCrash/icrash_db_create.sql
echo "***************************************************"
echo "***************************************************"
echo "			Updating"
echo "***************************************************"
echo "***************************************************"
apt-get -y -q update
apt-get -y -q upgrade
echo "***************************************************"
echo "***************************************************"
echo "	Opening MySQL to outside users"
echo "***************************************************"
echo "***************************************************"
replace "bind-address" "#bind-address" -- /etc/mysql/my.cnf
/etc/init.d/mysql restart
echo "***************************************************"
echo "***************************************************"
echo "			Ending script"
echo "***************************************************"
echo "***************************************************"
exit 0