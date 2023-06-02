# 3scale Toolbox

## Introduction

The **3scale toolbox** is a commandline interface (CLI) tool that allows you to interact with a 3scale tenant via it's APIs.

## Build

To build the 3scale toolbox run

    quarkus build --no-tests

You can also create a native executable like this

	quarkus build --no-tests --native

## Configure
To configure the 3scale toolbox you need a 3scale access-token.
Afterwards create **config/application.properties** with the following content

	quarkus.profile=<profile-name>
	%<profile-name>.quarkus.rest-client.threescale.url=<Admin-URL>
	%<profile-name>.access_token=<access-token>

You can configure multiple tenants in the configuration file by adding additional profile sections, the currently active profile is defined by the **quarkus.profile** setting.

In the future this will be configureable by the 3scale toolbox itself using the **config** command (NYI).

## Run

The toolbox can be used to launch a single command or to run multiple commands in batch mode.

### Standard mode
To launch the 3scale toolbox execute

	java -jar target/quarkus-app/quarkus-run.jar

You should now see the following options:

	Usage:  [-hV] COMMAND
	  -h, --help      Show this help message and exit.
      -V, --version   Print version information and exit.
	Commands:
  	  config
  	  account
  	  account-plan
  	  user
  	  application-plans
	  application
	  service
	  backend
	  activedocs
	  authentication
	  invoice
	  policy
	  field-definitions
	  object
	  provider
	  service-plans
	  webhooks
### Batch mode
To run the toolbox in batch mode add run

 	java -jar target/quarkus-app/quarkus-run.jar -f <batch-file>

In the batch files you can also assign results to variables and apply filters, both may of course be combined.
#### Variables
In the batch file you can also assign the outcome of a command to a variable like this

	assign variable <VARIABLE_NAME>=<COMMAND>

To reference a variable in the batch file you specify

	${VARIABLE_NAME}

In case the variable is not assigned in the batch file itself, the toolbox will look for an environment variable with the same name.

#### Filters
You can also apply filters to a result of a command like this

	<COMMAND>|<FILTER>

Currently supported filters are **xpath** and **prettyprint**.

##### XPath
To apply an xpath filter specify the filter like this

	<COMMAND>|xpath <XPATH_EXPRESSION>

##### Prettyprint
For pretty printing the output run

	<COMMAND>|prettyprint

### Variables and Filters
For combining variables and filters use the following syntax

	assign variable <VARIABLE_NAME>=<COMMAND>|<FILTER>