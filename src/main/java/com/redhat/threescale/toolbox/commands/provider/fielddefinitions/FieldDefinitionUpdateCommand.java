package com.redhat.threescale.toolbox.commands.provider.fielddefinitions;

import java.util.Arrays;
import java.util.List;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name="update", mixinStandardHelpOptions = true)
public class FieldDefinitionUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(FieldDefinitionUpdateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Field Definition ID", arity = "1")
    public String fieldDefinitionId;

    @Parameters(index = "1", description = "Target", arity = "1")
    public AccountManagementService.FieldTarget fieldTarget;

    @Option(names = {"--label"}, description = "Label", arity = "1")
    public String label;

    @Option(names = {"--required",}, description = "Required", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean required;

    @Option(names = {"--position",}, description = "Required", defaultValue = Option.NULL_VALUE)
    public Integer position;

    @Option(names = {"--hidden",}, description = "Hidden", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean hidden;

    @Option(names = {"--read-only",}, description = "Read only", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean readOnly;

    @Option(names = {"--choices",}, description = "Choices")
    public String choices;


    @Override
    public void run() {

        try {

            List<String> choicesList = null;
            if (choices != null)
                choicesList = Arrays.asList(choices.split(","));

            accountManagementServiceFactory.getAccountManagementService().updateFieldDefinition(fieldDefinitionId, fieldTarget, label, required, position, hidden, readOnly, choicesList);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        
    }
}