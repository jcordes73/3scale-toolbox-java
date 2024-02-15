package com.redhat.threescale.toolbox.commands.provider.fielddefinitions;

import java.util.Arrays;
import java.util.List;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MultivaluedHashMap;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="create", mixinStandardHelpOptions = true)
public class FieldDefinitionCreateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Name", arity = "1")
    public String name;

    @Parameters(index = "1", description = "Target", arity = "1")
    public AccountManagementService.FieldTarget fieldTarget;

    @Parameters(index = "2", description = "Label", arity = "1")
    public String label;

    @Option(names = {"--required",}, description = "Required", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean required;

    @Option(names = {"--hidden",}, description = "Hidden", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean hidden;

    @Option(names = {"--read-only",}, description = "Read only", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean readOnly;

    @Option(names = {"--choices",}, description = "Choices")
    public String choices;


    @Override
    public void run() {

        try {

            MultivaluedHashMap<String,String> request = new MultivaluedHashMap<>();

            request.addFirst("name", name);
            request.addFirst("target", fieldTarget.toString());
            request.addFirst("label", label);

            if (required != null)
                request.addFirst("required", required.toString());

            if (hidden != null)
                request.addFirst("hidden", hidden.toString());

            if (readOnly != null)
                request.addFirst("read_only", readOnly.toString());

            if (choices != null)
                request.put("choices", Arrays.asList(choices.split(",")));

            accountManagementServiceFactory.getAccountManagementService().createFieldDefinition(request);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
        
    }
}