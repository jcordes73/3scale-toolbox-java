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