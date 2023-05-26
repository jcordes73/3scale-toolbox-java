package com.redhat.threescale.toolbox.rest.client;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import picocli.CommandLine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.redhat.threescale.toolbox.Toolbox;
import com.redhat.threescale.toolbox.helpers.XPathExecution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountManagementServiceTest {

    @Inject
    CommandLine.IFactory factory;

    @Inject
    XPathExecution xPathExecution;

    private CommandLine cli;

    @BeforeAll
    public void setUp(){
        cli = new CommandLine(new Toolbox(), factory);
    }

    @Test
    @Order(value=Integer.MIN_VALUE)
    public void testCreateAccount(){
        int exitCode = cli.execute(getArgs("account create Toolbox toolbox toolbox@example.com toolbox"));

        assertEquals(0, exitCode);
    }

    @Test
    @Order(value=2)
    public void testGetAccounts(){
        int exitCode = cli.execute(getArgs("account list --state=approved --per-page=1 --page=1 --xpath=/accounts"));
        
        assertEquals(0, exitCode);
    }

    @Test
    @Order(value=3)
    public void testFindAccount(){
        int exitCode = cli.execute(getArgs("account find --email=toolbox@example.com"));
        
        assertEquals(0, exitCode);
    }

    @Test
    @Order(value=4)
    public void testGetAccount() throws Exception {
        String accountId = getTestAccountId();

        StringWriter sw = new StringWriter();
        cli.setOut(new PrintWriter(sw));
        int exitCode = cli.execute(getArgs("account get " + accountId + " --xpath=/account"));
        cli.setOut(new PrintWriter(System.out));

        String accountXml = sw.toString();

        String orgName = xPathExecution.execute(accountXml, "/account/org_name/text()");
        String email = xPathExecution.execute(accountXml, "/account/users/user/email/text()");
        String userName = xPathExecution.execute(accountXml, "/account/users/user/username/text()");
        
        assertEquals(0, exitCode);
        assertEquals("Toolbox",orgName);
        assertEquals("toolbox@example.com", email);
        assertEquals("toolbox", userName);
    }

    @Test
    @Order(value=5)
    public void testMakePendingAccount(){
        String accountId = getTestAccountId();

        int exitCode = cli.execute(getArgs("account pending " + accountId));

        StringWriter sw = new StringWriter();
        
        cli.setOut(new PrintWriter(sw));
        cli.execute(getArgs("account get " + accountId + " --xpath=/account/state/text() "));
        cli.setOut(new PrintWriter(System.out));

        String state = sw.toString().trim();
        
        assertEquals(0, exitCode);
        assertEquals("pending", state);
    }

    @Test
    @Order(value=6)
    public void testRejectAccount(){
        String accountId = getTestAccountId();

        int exitCode = cli.execute(getArgs("account reject " + accountId));

        StringWriter sw = new StringWriter();
        
        cli.setOut(new PrintWriter(sw));
        cli.execute(getArgs("account get " + accountId + " --xpath=/account/state/text() "));
        cli.setOut(new PrintWriter(System.out));

        String state = sw.toString().trim();
        
        assertEquals(0, exitCode);
        assertEquals("rejected", state);
    }

    @Test
    @Order(value=7)
    public void testApproveAccount(){
        String accountId = getTestAccountId();

        int exitCode = cli.execute(getArgs("account approve " + accountId));

        StringWriter sw = new StringWriter();
        
        cli.setOut(new PrintWriter(sw));
        cli.execute(getArgs("account get " + accountId + " --xpath=/account/state/text() "));
        cli.setOut(new PrintWriter(System.out));

        String state = sw.toString().trim();
        
        assertEquals(0, exitCode);
        assertEquals("approved", state);
    }

    @Test
    @Order(value=8)
    public void testAccountMessage(){
        String accountId = getTestAccountId();

        int exitCode = cli.execute(getArgs("account message " + accountId + " Test-Subject Test-Body"));

        assertEquals(0, exitCode);
    }

    @Test
    @Order(value=9)
    public void testUpdateAccount(){
        String accountId = getTestAccountId();

        StringWriter sw = new StringWriter();
        cli.setOut(new PrintWriter(sw));
        cli.execute(getArgs("account get " + accountId + " -x=/account/monthly_billing_enabled/text()"));
        cli.setOut(new PrintWriter(System.out));
        String billingEnabledBefore = sw.toString().trim();

        int exitCode = cli.execute(getArgs("account update " + accountId + " --no-monthly-billing"));

        sw = new StringWriter();
        cli.setOut(new PrintWriter(sw));
        cli.execute(getArgs("account get " + accountId + " -x=/account/monthly_billing_enabled/text()"));
        cli.setOut(new PrintWriter(System.out));
        String billingEnabledAfter = sw.toString().trim();

        cli.execute(getArgs("account update " + accountId + " --monthly-billing"));

        sw = new StringWriter();
        cli.setOut(new PrintWriter(sw));
        cli.execute(getArgs("account get " + accountId + " -x=/account/monthly_billing_enabled/text()"));
        cli.setOut(new PrintWriter(System.out));
        String billingEnabledAgain = sw.toString().trim();


        assertEquals(0, exitCode);
        assertEquals("true", billingEnabledBefore);
        assertEquals("false", billingEnabledAfter);
        assertEquals("true", billingEnabledAgain);
    }


    @Test
    @Order(value=10)
    public void testAccountSetCreditCard(){
        String accountId = getTestAccountId();

        int exitCode = cli.execute(getArgs("account credit-card set " + accountId + " test-token 12 2099 toolbox toolbox toolbox-city Germany"));
        
        StringWriter sw = new StringWriter();
        cli.setOut(new PrintWriter(sw));
        cli.execute(getArgs("account get " + accountId + " -x=/account/credit_card_stored/text()"));
        cli.setOut(new PrintWriter(System.out));
        
        String creditCardStored = sw.toString().trim();
        
        assertEquals(0, exitCode);
        assertEquals("true", creditCardStored);
    }

    @Test
    @Order(value=11)
    public void testAccountDeleteCreditCard(){
        String accountId = getTestAccountId();

        int exitCode = cli.execute(getArgs("account credit-card delete " + accountId));
        
        StringWriter sw = new StringWriter();
        cli.setOut(new PrintWriter(sw));
        cli.execute(getArgs("account get " + accountId + " -x=/account/credit_card_stored/text()"));
        cli.setOut(new PrintWriter(System.out));
        
        String creditCardStored = sw.toString().trim();
        
        assertEquals(0, exitCode);
        assertEquals("false", creditCardStored);
    }

    @Test
    @Order(value=12)
    public void testAccountServiceContractsList(){
        String accountId = getTestAccountId();

        int exitCode = cli.execute(getArgs("account service-contracts list " + accountId));

        assertEquals(0, exitCode);
    }

    @Test
    @Order(value=13)
    public void testAccountServiceContractsDelete() throws Exception {
        String accountId = getTestAccountId();

        StringWriter sw = new StringWriter();
        cli.setOut(new PrintWriter(sw));
        cli.execute(getArgs("account service-contracts list " + accountId));
        cli.setOut(new PrintWriter(System.out));

        String serviceContractsXml = sw.toString();

        String serviceContractId = xPathExecution.execute(serviceContractsXml, "/service_contracts/service-contract[1]/id/text()").trim();
        
        int exitCode = cli.execute(getArgs("account service-contracts delete " + accountId + " " + serviceContractId));

        assertEquals(0, exitCode);
    }

    @Test
    @Order(value=14)
    public void testAccountGetPlan(){
        String accountId = getTestAccountId();

        int exitCode = cli.execute(getArgs("account plan get " + accountId));

        assertEquals(0, exitCode);
    }

    @Test
    @Order(value=15)
    public void testAccountUsersList(){
        String accountId = getTestAccountId();

        int exitCode = cli.execute(getArgs("account user list " + accountId));

        assertEquals(0, exitCode);
    }

    @Test
    @Order(value=16)
    public void testAccountCreateUser(){
        String accountId = getTestAccountId();

        int exitCode = cli.execute(getArgs("account user create " + accountId + " toolbox-user toolbox-user@example.com toolbox"));

        assertEquals(0, exitCode);
    }

    @Test
    @Order(value=17)
    public void testAccountCommand(){
        int exitCode = cli.execute(getArgs("account"));

        assertNotEquals(0, exitCode);
    }

    @Test
    @Order(value=18)
    public void testAccountCreditCardCommand(){
        int exitCode = cli.execute(getArgs("account credit-card"));

        assertNotEquals(0, exitCode);
    }

    @Test
    @Order(value=19)
    public void testAccountUserCommand(){
        int exitCode = cli.execute(getArgs("account user"));

        assertNotEquals(0, exitCode);
    }

    @Test
    @Order(value=20)
    public void testAccountPlanCommand(){
        int exitCode = cli.execute(getArgs("account plan"));

        assertNotEquals(0, exitCode);
    }

    @Test
    @Order(value=21)
    public void testAccountServiceContractsCommand(){
        int exitCode = cli.execute(getArgs("account service-contracts"));

        assertNotEquals(0, exitCode);
    }

    @Test
    @Order(value=Integer.MAX_VALUE)
    public void testDeleteAccount(){
        String accountId = getTestAccountId();

        int exitCode = cli.execute(getArgs("account delete " + accountId));

        assertEquals(0, exitCode);
    }

    private String[] getArgs(String command){
        return command.split("\\s");
    }
    
    private String getTestAccountId() {
        StringWriter sw = new StringWriter();
        
        cli.setOut(new PrintWriter(sw));
        cli.execute(getArgs("account find --email=toolbox@example.com --xpath=/account/id/text()"));
        cli.setOut(new PrintWriter(System.out));

        String accountId = sw.toString().trim();


        return accountId;
    }
}