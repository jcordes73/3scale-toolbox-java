package com.redhat.threescale.toolbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.function.ToLongBiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    CommandLine.IFactory factory;

    @Inject
    XPathExecution xPathExecution;
    
    private HashMap<String,String> variables = new HashMap<String,String>();
    
    @Override
    public void run() {
        CommandLine commandLine = new CommandLine(this, factory);
        commandLine.usage(System.out);
    }

    @Override
    public int run(String... args) throws Exception {
        CommandLine commandLine = new CommandLine(this, factory);

        int exitCode = -1;

        if (args.length > 0 && "-f".equals(args[0])) {
            exitCode = runBatch(commandLine, args);
        } else {
            exitCode = commandLine.execute(args);
        }

        return exitCode;
    }

    private int runBatch(CommandLine commandLine, String... args) throws Exception {
        int exitCode = -1;

        BufferedReader reader = new BufferedReader(new FileReader(args[1]));

        String line = reader.readLine();

        while (line != null) {
            String toolboxCommand = replaceVariables(line);

            String variableName = null;
            String filter = null;

            if (toolboxCommand.startsWith("assign variable")){
                int commandPos = toolboxCommand.indexOf("=");

                variableName = toolboxCommand.substring(16, commandPos);
                toolboxCommand = toolboxCommand.substring(commandPos+1);

            }

            int filterIndex = toolboxCommand.indexOf("|");

            if (filterIndex > 0) {
                filter = toolboxCommand.substring(filterIndex+1);

                toolboxCommand = toolboxCommand.substring(0, filterIndex);
            }

            StringWriter sw = new StringWriter();                
            commandLine.setOut(new PrintWriter(sw));
            exitCode = commandLine.execute(toolboxCommand.split("\\s"));
            commandLine.setOut(new PrintWriter(System.out));

            String result = sw.toString();

            if (filter != null){
                if (filter.startsWith("xpath")){
                    String xpathQuery = filter.substring(6);

                    result = xPathExecution.execute(result, xpathQuery);
                } else if (filter.startsWith("prettyprint")){
                    result = xPathExecution.prettyPrint(result);
                }
            }

            if (variableName != null){
                variables.put(variableName, result);
            } else {
                System.out.print(result);
            }

            line = reader.readLine();
        }

        reader.close();

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