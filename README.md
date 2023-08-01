# 3scale Toolbox

## Introduction

The **3scale toolbox** is a commandline interface (CLI) tool that allows you to interact with a 3scale tenant via it's APIs.

## Build

To build the 3scale toolbox run

	quarkus build --no-tests

You can also create a native executable like this

	quarkus build --no-tests --native

## Configure
To configure the 3scale toolbox you need a 3scale **access-token** and the **Admin-URL** for the tenant. You can also use a **provider-key** instead.

The 3scale toolbox can use a property file for configuration or it can be configured via the toolbox itself.

To use configuration based on a file create **3scale-config.properties** in the current directory with the following content

	threescale.tenant=<tenant-name>
	threescale.tenant.<tenant-name>.url=<Admin-URL>
	threescale.tenant.<tenant-name>.access_token=<access-token>
	threescale.tenant.<tenant-name>.provider_key=<provider-key>
To get the access-token and the Admin-URL for the default admin instance use this command:

	ACCESS_TOKEN=`oc get secret system-seed -o json | jq -r '.data.ADMIN_ACCESS_TOKEN' | base64 -d`

	ADMIN_HOST=`oc get route | grep 3scale-admin | awk '{print $2}'`
You can configure multiple tenants in the configuration file by adding additional **threescale.tenant.<tenant-name>** sections, the currently active tenant is defined by the **threescale.tenant** setting.

For commandline based configuration use

	config tenant <tenant-name> <admin-url> --access-token=<access-token>
or

	config tenant <tenant-name> <admin-url> --provider-key=<provider-key>

Right now this configuration is not persisted in 3scale-config.properties. You can also use environment variables on the commandline.
## Run
The toolbox can be used to launch a single command, run multiple commands in batch mode or also in an interactive mode.

For convenience you can also set an alias like this

	alias toolbox="java -jar target/quarkus-app/quarkus-run.jar"

### Standard mode
To launch the 3scale toolbox execute

	java -jar target/quarkus-app/quarkus-run.jar

You should now see the following options:

	Usage:  [-hV] COMMAND
  	-h, --help      Show this help message and exit.
  	-V, --version   Print version information and exit.
	Commands:
  	  config
  	  services
  	  accounts
  	  provider
### Batch mode
To run the toolbox in batch mode add run

 	java -jar target/quarkus-app/quarkus-run.jar -f <batch-file>

In the batch files you can also assign results to variables and apply filters, both may of course be combined. You can also comment out lines by using **#** as the first character of the line.
### Interactive mode
To run toolbox in interactive/shell mode run

	java -jar target/quarkus-app/quarkus-run.jar -i

You should now see a prompt like this

	3scale>

Just hit **<enter>** for a list of available commands.

Use **CTRL-c** or type **exit** to exit the shell.

#### Variables
In the batch file or in the shell you can also assign the outcome of a command to a variable like this

	assign variable <VARIABLE_NAME>=<COMMAND>

To reference a variable in the batch file you specify

	${VARIABLE_NAME}

In case the variable is not assigned in the batch file itself, the toolbox will look for an environment variable with the same name.

To show the value of a variable you can use the **echo** command like this

	echo ${VARIABLE_NAME}

#### Filters
You can also apply filters to a result of a command like this

	<COMMAND>|<FILTER>

Currently supported filters are **xpath**, **jsonpath**, **prettyprint** and **json2xml**.

You can also specify more than one filter

	<COMMAND>|<FILTER1>|<FILTER2>

for example for converting json to xml and then applying xpath.
##### XPath
To apply an xpath filter specify the filter like this

	<COMMAND>|xpath <XPATH_EXPRESSION>
##### Jsonpath
To apply an jsonpath filter specify the filter like this

	<COMMAND>|jsonpath <JSONPATH_EXPRESSION>
##### Prettyprint
For pretty printing the output run

	<COMMAND>|prettyprint
### Variables and Filters
For combining variables and filters use the following syntax

	assign variable <VARIABLE_NAME>=<COMMAND>|<FILTER1>|<FILTER2>
#### Recording
You can record individual commands into a text file, for example for later batch processing

To start recording execute
```
recording start <textfile>
```
Afterwards on each command you can confirm wheter or not it should be added to the recording

To end the recording enter
```
recording stop
```