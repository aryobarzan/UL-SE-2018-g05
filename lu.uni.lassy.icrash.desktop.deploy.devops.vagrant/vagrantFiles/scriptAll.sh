#!/bin/bash
echo "***************************************************"
echo "***************************************************"
echo "		Starting script"
echo "***************************************************"
echo "***************************************************"
echo ""
echo "***************************************************"
echo "***************************************************"
echo "			Updating and cleanup"
echo "***************************************************"
echo "***************************************************"
apt-get -y -q update
apt-get -y -q dist-upgrade
apt-get -y -q autoremove
echo "***************************************************"
echo "***************************************************"
echo "			Ending script"
echo "***************************************************"
echo "***************************************************"
exit 0