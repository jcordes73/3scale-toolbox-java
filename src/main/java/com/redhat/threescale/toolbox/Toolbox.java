package com.redhat.threescale.toolbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigSource;

import com.redhat.threescale.toolbox.commands.account.AccountCommand;
import com.redhat.threescale.toolbox.commands.accountplan.AccountPlanCommand;
import com.redhat.threescale.toolbox.commands.activedocs.ActiveDocsCommand;
import com.redhat.threescale.toolbox.commands.applicationplans.ApplicationPlanCommand;
import com.redhat.threescale.toolbox.commands.applications.ApplicationCommand;
import com.redhat.threescale.toolbox.commands.authentication.AuthenticationCommand;
import com.redhat.threescale.toolbox.commands.backend.BackendCommand;
import com.redhat.threescale.toolbox.commands.config.ConfigCommand;
import com.redhat.threescale.toolbox.commands.fielddefinitions.FieldDefinitionsCommand;
import com.redhat.threescale.toolbox.commands.invoice.InvoiceCommand;
import com.redhat.threescale.toolbox.commands.objects.ObjectsCommand;
import com.redhat.threescale.toolbox.commands.policy.PolicyCommand;
import com.redhat.threescale.toolbox.commands.provider.ProviderCommand;
import com.redhat.threescale.toolbox.commands.serviceplans.ServicePlansCommand;
import com.redhat.threescale.toolbox.commands.services.ServiceCommand;
import com.redhat.threescale.toolbox.commands.user.UserCommand;
import com.redhat.threescale.toolbox.commands.webhooks.WebhooksCommand;
import com.redhat.threescale.toolbox.config.ThreescaleConfigSource;
import com.redhat.threescale.toolbox.helpers.JsonToXmlConverter;
import com.redhat.threescale.toolbox.helpers.XPathExecution;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@QuarkusMain
@TopCommand
@Command(name="",mixinStandardHelpOptions = true,
         subcommands = {ConfigCommand.class,
                        AccountCommand.class,
                        AccountPlanCommand.class,
                        UserCommand.class,
                        ApplicationPlanCommand.class,
                        ApplicationCommand.class,
                        ServiceCommand.class,
                        BackendCommand.class,
                        ActiveDocsCommand.class,
                        AuthenticationCommand.class,
                        InvoiceCommand.class,
                        PolicyCommand.class,
                        FieldDefinitionsCommand.class,
                        ObjectsCommand.class,
                        ProviderCommand.class,
                        ServicePlansCommand.class,
                        WebhooksCommand.class
                        },
                        synopsisSubcommandLabel = "COMMAND")
public class Toolbox implements Runnable, QuarkusApplication {

    @Inject
    Config config;
    
    @Inject
    CommandLine.IFactory factory;

    @Inject
    XPathExecution xPathExecution;

    @Inject
    JsonToXmlConverter jsonToXmlConverter;

    private HashMap<String,String> variables = new HashMap<String,String>();
    
    @Override
    public void run() {
        CommandLine commandLine = new CommandLine(this, factory);
        commandLine.usage(System.out);
    }

    @Override
    public int run(String... args) throws Exception {
        readConfiguration();

        CommandLine commandLine = new CommandLine(this, factory);

        int exitCode = -1;

        if (args.length > 0 && "-f".equals(args[0])) {
            commandLine.setTrimQuotes(false);
            exitCode = runBatch(commandLine, args);
        } else if (args.length > 0 && "-i".equals(args[0])) {
            runInteractive(commandLine);
        } else {
            exitCode = executeLine(commandLine, String.join(" ", args));
        }

        return exitCode;
    }

    private void readConfiguration() throws Exception {
        Properties prop = new Properties();
        File file = new File("3scale-config.properties");

        if (file.exists()){
            prop.load(new FileReader(file));

            HashMap<String,String> configs = prop.entrySet().stream().collect(
                Collectors.toMap(
                e -> String.valueOf(e.getKey()),
                e -> String.valueOf(e.getValue()),
                (prev, next) -> next, HashMap::new
            ));

            for (Iterator<ConfigSource> it = config.getConfigSources().iterator(); it.hasNext();){
                ConfigSource configSource = it.next();

                if (configSource.getName() == ThreescaleConfigSource.NAME){
                    configSource.getProperties().putAll(configs);
                }
            }
        }
    }

    private int runBatch(CommandLine commandLine, String... args) throws Exception {
        int exitCode = -1;

        BufferedReader reader = new BufferedReader(new FileReader(args[1]));

        String line = reader.readLine();

        while (line != null) {
            if (!line.startsWith("#") && !line.isBlank()){
                if (line.startsWith("echo")){
                    System.out.println(replaceVariables(line.substring(5)));
                } else {
                    exitCode = executeLine(commandLine, line);
                }
            }
            line = reader.readLine();
        }

        reader.close();

        return exitCode;
    }

    private int runInteractive(CommandLine commandLine) throws Exception {
        int exitCode = -1;

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("3scale>");
        String line = scanner.nextLine();

        while (line != null && !"exit".equals(line)) {
            if (line.startsWith("echo")){
                System.out.println(replaceVariables(line.substring(5)));
            } else {
                exitCode = executeLine(commandLine, line);
            }
            
            System.out.print("3scale>");
            line = scanner.nextLine();
        }

        scanner.close();

        return exitCode;
    }

    private int executeLine(CommandLine commandLine, String line) throws Exception {
        String toolboxCommand = replaceVariables(line);

        String variableName = null;
        String filterCommand = null;

        if (toolboxCommand.startsWith("assign variable")){
            int commandPos = toolboxCommand.indexOf("=");

            variableName = toolboxCommand.substring(16, commandPos);
            toolboxCommand = toolboxCommand.substring(commandPos+1);
        }

        int filterIndex = toolboxCommand.indexOf("|");

        if (filterIndex > 0) {
            filterCommand = toolboxCommand.substring(filterIndex+1);

            toolboxCommand = toolboxCommand.substring(0, filterIndex);
        }


        ArrayList<String> parameters = new ArrayList<String>();
        
        String regex = "\"([^\"]*)\"|(\\S+)";

        Matcher m = Pattern.compile(regex).matcher(toolboxCommand);
        while (m.find()) {
            if (m.group(1) != null) {
                parameters.add(m.group(1));
            } else {
                parameters.add(m.group(2));
            }
        }

        StringWriter sw = new StringWriter();                
        commandLine.setOut(new PrintWriter(sw));
        int exitCode = commandLine.execute(parameters.toArray(new String[parameters.size()]));
        commandLine.setOut(new PrintWriter(System.out));

        String result = sw.toString();

        if (filterCommand != null){

            String[] filters = null;
            
            if (filterCommand.contains("|"))
                filters = filterCommand.split("\\|");
            else
                filters = new String[]{filterCommand};

            for (int i=0; i < filters.length; i++){
                String filter = filters[i];

                if (filter.startsWith("xpath")){
                    String xpathQuery = filter.substring(6);

                    result = xPathExecution.execute(result, xpathQuery);
                } else if (filter.startsWith("prettyprint")){
                    result = xPathExecution.prettyPrint(result);
                } else if (filter.startsWith("json2xml")){
                    result = jsonToXmlConverter.convert(result);
                }
          }
        }

        if (variableName != null){
            variables.put(variableName, result);
        } else {
            System.out.print(result);
        }

        return exitCode;
    }

    private String replaceVariables(String text){
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
        Matcher matcher = pattern.matcher(text);
        
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (matcher.find()) {
            String variableName = matcher.group(1);
            String replacement = getVariable(variableName);

            builder.append(text.substring(i, matcher.start()));
            if (replacement == null)
                builder.append(matcher.group(0));
            else
                builder.append(replacement);
            i = matcher.end();
        }
        builder.append(text.substring(i, text.length()));
        return builder.toString();
    }

    private String getVariable(String variableName){
        String variableValue = null;

        if (variables.containsKey(variableName)){
            variableValue = variables.get(variableName);
        } else {
            variableValue = System.getenv(variableName);
        }

        return variableValue;
    }
}