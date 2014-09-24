ui-test
=======

UI Test set for eXo platform product

Structure introduction
==================
This set is composed of 2 modules: ui-testsuite and ui-common

ui-common includes all common functions that are used in testcases. Common functions are divided in .java files according to products, features and modules of Platform. 

ui-testsuite includes all testcases which are executed to test our products. It is divided in packages according to products, features, and modules of Platform. Each package contains one or more .java files  which are testsuites. Each testsuite that includes one or more testcases is respective to one feature of Platform.

How to build the project
=======================
1. Prerequisite
* Make sure that mvn 3.0.4 or later version is installed. 
* Package Platform 4.1.x must be available. (You can get a package from https://storage.exoplatform.vn/, or https://acceptance.exoplatform.org/)
* Browsers must be available, at least Firefox
2. Launch test
Here are steps to build this project.
* Step 1: Start server to run Platform package.
* Step 2: clone this project from github by typing a command: git clone git@github.com:exoplatform/ui-test.git
* Step 3: Checkout the code of branch stable/4.1.x by running a command: git checkout stable/4.1.x
* Step 4: On terminal, go to ui-test folder.
* Step 5: type a command: mvn clean install [-P<type of test>] [-Dbrowser=<browser>] -DbaseUrl=<base url>
	  Where:
		<type of test> is id of profile in the ui-testsuite/pom.xml. This is a must.
		<browser> = firefox | iexplorer. If not, firefox is used by default.
		<baseUrl> = The url to Platform. If not, url http://localhost:8080/portal is used by default.
	  Example: Assume a profile with id "wiki-sniff" is defined in the ui-testsuite/pom.xml
		To run test cases of this profile on Firefox, with Url "http://192.168.1.44:8080/portal", run the following command:
		mvn clean install -Pwiki-sniff -Dbrowser=firefox -DbaseUrl=http://192.168.1.44:8080/portal


