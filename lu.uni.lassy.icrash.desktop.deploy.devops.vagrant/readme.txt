#-------------------------------------------------------------------------------
# Copyright (c) 2014-2015 University of Luxembourg.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
# 
# Contributors:
#	  Thomas Mortimer - Initial document
#	  Alfredo Capozucca - general improvements
#-------------------------------------------------------------------------------

Using Vagrant to test iCrash Distributed Desktop version on a virtualized distributed environment


#*****************************************************************
 Installation
#*****************************************************************


1. Open up a terminal or command prompt

2. Navigate to the folder where this readme.txt file is located. This location, from now on, is referred to as ~

3. Go to the folder ~/vagrantFiles
   The following will be present in your folder:
	3.a. A folder called iCrash containing:
		3.a.i. crash_db_create.sql
		3.a.ii. iCrashClient.jar
		3.a.iii. iCrashServer.jar
		3.a.iv. iCrashState.properties
		3.a.v. iCrashTesting_1.0_Linux.xml
	3.b. scriptAll.sh
	3.c. scriptClient.sh
	3.d. scriptClientAndServer.sh
	3.e. scriptServer.sh
	3.f. scriptSQL.sh
	3.g. Vagrantfile

4. Type in "vagrant up", press enter and wait the process ends.
   After hitting enter, 3 virtual boxes are started up, called server, db, and client. 
   Important remarks: 
   4.1. If you do not have the boxes already downloaded, it will download the boxes "ubuntu/trusty64" and "draken/clientwithjubula".
        Depending on your internet connection speed, the downloading might take very long (about 3 hs.)
   4.2. After the boxes have been downloaded, they are setup. The set up process of the boxes can take up to 15 minutes to finish.


5. Once, the process has finished, select the client virtual machine (the only one with graphical interface) and do the following:
   5.a. Log in with user vagrant
   5.b. Open a terminal, and change the directory to /vagrant/iCrash/ 
   5.c. Type in “java -jar iCrashClient.jar”. 
   5.d  You will now have the iCrash window on the client machine and can interact with it.
    



#*****************************************************************
 Important information:
#*****************************************************************

1 - Credentials
a. The username and password for all the machines are vagrant
b. The username and password for the iCrash administrator are:
	username: icrashadmin
	password: 7WXC1359


2 - Vagrant commands
a. To initialise the virtual machines: “vagrant up”
b. To shutdown the virtual machines: “vagrant halt”
b. To suspend the virtual machines: “vagrant suspend”
c. To resume the virtual machines: “vagrant resume” (earlier stopped with command “vagrant suspend”)

