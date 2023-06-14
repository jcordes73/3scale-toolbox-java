package com.redhat.threescale.toolbox.commands.accounts.creditcards;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="set", mixinStandardHelpOptions = true)
public class AccountSetCreditCardCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountSetCreditCardCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Parameters(index = "1", description = "Credit Card Token", arity = "1")
    public String creditCardToken;

    @Parameters(index = "2", description = "Credit Card Expiration Month", arity = "1")
    public String creditCardExpirationMonth;

    @Parameters(index = "3", description = "Credit Card Expiration Year", arity = "1")
    public String creditCardExpirationYear;

    @Parameters(index = "4", description = "Billing Address Name", arity = "1")
    public String billingAddressName;

    @Parameters(index = "5", description = "Billing Address Address", arity = "1")
    public String billingAddressAddress;
    
    @Parameters(index = "6", description = "Billing Address City", arity = "1")
    public String billingAddressCity;

    @Parameters(index = "7", description = "Billing Address Country", arity = "1")
    public String billingAddressCountry;

    @Option(names = {"--phone",}, description = "Billing Address Phone")
    public String billingAddressPhone;

    @Option(names = {"--zip",}, description = "Billing Address ZIP Code")
    public String billingAddressZip;

    @Option(names = {"--state",}, description = "Billing Address State")
    public String billingAddressState;

    @Option(names = {"--partial-number",}, description = "Billing Address State")
    public String creditCardPartialNumber;

    @Option(names = {"--authorize-token",}, description = "Authorize Token")
    public String creditCardAuthorizeNetPaymentProfileToken;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().accountSetCreditCard(accountId, creditCardToken, creditCardAuthorizeNetPaymentProfileToken, creditCardExpirationYear, creditCardExpirationMonth, billingAddressName, billingAddressAddress, billingAddressCity, billingAddressCountry, billingAddressState, billingAddressPhone, billingAddressZip, creditCardPartialNumber);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
