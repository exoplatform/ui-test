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
* Browsers must be available, at least Firefox
2. Launch test
Here are steps to build this project.
* Step 1: Clone this project from github by typing a command: git clone git@github.com:exoplatform/ui-test.git
* Step 2: Checkout the code of branch stable/cloud by running a command: git checkout stable/cloud
* Step 3: On terminal, go to ui-test folder.
* Step 4: type a command: mvn clean install [-P<type of test>] [-Dbrowser=<browser>] -DbaseUrl=<base url>
	  Where:
		<type of test> is id of profile in the ui-testsuite/pom.xml
		<browser> = firefox (default) | iexplorer
		<baseUrl> = The url of tenant of cloud
	  Example: Assume a profile with id "wiki-sniff" is defined in the ui-testsuite/pom.xml
		To run test cases of this profile on Firefox, with Url "http://exoplatform.netstg.exoplatform.org/portal", run the 			following command:
		mvn clean install -Pwiki-sniff -Dbrowser=firefox -DbaseUrl=http://exoplatform.netstg.exoplatform.org/portal


