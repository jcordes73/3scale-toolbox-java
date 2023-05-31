package com.redhat.threescale.toolbox.rest.client.service;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/admin/api")
@RegisterRestClient(configKey = "threescale")
public interface AccountManagementService {

    enum Role {
        admin,
        member
    }

    enum State {
        pending,
        approved,
        rejected
    }

    enum StateEvent {
        publish,
        hide
    }

    enum LimitPeriod {
        eternity,
        year,
        month,
        week,
        day,
        hour,
        minute
    }

    enum FieldTarget {
        Account,
        User,
        Cinstance
    }

    enum ObjectType {
        service,
        account,
        proxy,
        backend_api
    }

    enum DeploymentOption {
        hosted,
        self_managed,
        service_mesh_istio
    }

    enum FeatureScope {
        ApplicationPlan,
        ServicePlan
    }

    enum Environment {
        sandbox,
        production
    }

    enum CredentialsLocation {
        headers,
        query,
        authorization
    }

    enum ClientIdType {
        plain,
        liquid
    }

    @POST
    @Path("/signup.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createAccount(
        @FormParam("access_token") String accessToken,
        @FormParam("org_name") String orgName,
        @FormParam("username") String userName,
        @FormParam("email") String email,
        @FormParam("password") String password,
        @FormParam("account_plan_id") Integer accountPlanId,
        @FormParam("service_plan_id") Integer servicePlanId,
        @FormParam("application_plan_id") Integer applicationPlanId,
        @FormParam("additionalPropertes") String additionalProperties);

    @GET
    @Path("/accounts.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getAccounts(
        @QueryParam("access_token") String accessToken,
        @QueryParam("state") State state,
        @QueryParam("page") int page,
        @QueryParam("per_page") int perPage
    );

    @GET
    @Path("/accounts/{accountId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getAccount(
        @PathParam("accountId") int accountId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/accounts/find.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String findAccount(
        @QueryParam("access_token") String accessToken,
        @QueryParam("username") String userName,
        @QueryParam("email") String email,
        @QueryParam("user_id") String userId
    );

    @PUT
    @Path("/accounts/{accountId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateAccount(
        @PathParam("accountId") int accountId,
        @FormParam("access_token") String accessToken,
        @FormParam("org_name") String orgName,
        @FormParam("monthly_billing_enabled") Boolean monthlyBillingEnabled,
        @FormParam("monthly_charging_enabled") Boolean monthlyChargingEnabled,
        @FormParam("additionalPropertes") String additionalProperties
    );

    @DELETE
    @Path("/accounts/{accountId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void deleteAccount(
        @PathParam("accountId") int accountId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/accounts/{accountId}/applications.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getApplications(
        @PathParam("accountId") int accountId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/accounts/{accountId}/applications/{applicationId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getApplication(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/accounts/{accountId}/applications.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createApplication(
        @PathParam("accountId") int accountId,
        @FormParam("access_token") String accessToken,
        @FormParam("plan_id") int planId,
        @FormParam("name") String name,
        @FormParam("description") String description,
        @FormParam("user_key") String userKey,
        @FormParam("application_id") String applicationId,
        @FormParam("application_key") String applicationKey,
        @FormParam("redirect_url") String redirectUrl,
        @FormParam("first_traffic_at") String firstTrafficAt,
        @FormParam("first_daily_traffic_at") String firstDailyTrafficAt
    );


    @PUT
    @Path("/accounts/{accountId}/applications/{applicationId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateApplication(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("description") String description,
        @FormParam("redirect_url") String redirectUrl,
        @FormParam("first_traffic_at") String firstTrafficAt,
        @FormParam("first_daily_traffic_at") String firstDailyTrafficAt
    );

    @PUT
    @Path("/accounts/{accountId}/applications/{applicationId}/accept.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void acceptApplication(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @FormParam("access_token") String accessToken
    );

    @PUT
    @Path("/accounts/{accountId}/applications/{applicationId}/change_plan.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void changeApplicationPlan(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @FormParam("access_token") String accessToken,
        @FormParam("plan_id") int planId
    );

    @PUT
    @Path("/accounts/{accountId}/applications/{applicationId}/customize_plan.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void customizeApplicationPlan(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @FormParam("access_token") String accessToken
    );

    @PUT
    @Path("/accounts/{accountId}/applications/{applicationId}/decustomize_plan.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void decustomizeApplicationPlan(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @FormParam("access_token") String accessToken
    );

    @PUT
    @Path("/accounts/{accountId}/applications/{applicationId}/resume.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void resumeApplication(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @FormParam("access_token") String accessToken
    );

    @PUT
    @Path("/accounts/{accountId}/applications/{applicationId}/suspend.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void suspendApplication(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @FormParam("access_token") String accessToken
    );

    @DELETE
    @Path("/accounts/{accountId}/applications/{applicationId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void deleteApplication(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/accounts/{accountId}/applications/{applicationId}/keys.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getApplicationKeys(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/accounts/{accountId}/applications/{applicationId}/keys.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createApplicationKey(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @FormParam("access_token") String accessToken,
        @FormParam("key") String key
    );

    @DELETE
    @Path("/accounts/{accountId}/applications/{applicationId}/keys/{key}.xml")
    public void deleteApplicationKey(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @PathParam("key") String key,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/accounts/{accountId}/applications/{applicationId}/referrer_filters.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getApplicationReferrerFilters(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/accounts/{accountId}/applications/{applicationId}/referrer_filters.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createApplicationReferrerFilter(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @FormParam("access_token") String accessToken,
        @FormParam("referrer_filter") String referrerFilter
    );

    @DELETE
    @Path("/accounts/{accountId}/applications/{applicationId}/referrer_filters/{referrer_filter}.xml")
    public void deleteApplicationReferrerFilter(
        @PathParam("accountId") int accountId,
        @PathParam("applicationId") int applicationId,
        @PathParam("referrer_filter") String referrerFilter,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/accounts/{accountId}/messages.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createAccountMessage(
        @PathParam("accountId") int accountId,
        @FormParam("access_token") String accessToken,
        @FormParam("subject") String subject,
        @FormParam("body") String body);

    @GET
    @Path("/accounts/{accountId}/plan.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getAccountPlanByAccount(
        @PathParam("accountId") int accountId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/accounts/{accountId}/service_contracts.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getAccountServiceContracts(
        @PathParam("accountId") int accountId,
        @QueryParam("access_token") String accessToken
    );

    @DELETE
    @Path("/accounts/{accountId}/service_contracts/{serviceContractId}.xml")
    public void deleteAccountServiceContract(
        @PathParam("accountId") int accountId,
        @PathParam("serviceContractId") int serviceContractId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/accounts/{accountId}/approve.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void approveAccount(
        @PathParam("accountId") int accountId,
        @FormParam("access_token") String accessToken
    );
    
    @PUT
    @Path("/accounts/{accountId}/change_plan.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void accountChangePlan(
        @PathParam("accountId") int accountId,
        @FormParam("access_token") String accessToken,
        @FormParam("plan_id") int planId
    );

    @PUT
    @Path("/accounts/{accountId}/credit_card.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void accountSetCreditCard(
        @PathParam("accountId") int accountId,
        @FormParam("access_token") String accessToken,
        @FormParam("credit_card_token") String creditCardToken,
        @FormParam("credit_card_authorize_net_payment_profile_token") String creditCardAuthorizeNetPaymentProfileToken,
        @FormParam("credit_card_expiration_year") String creditCardExpirationYear,
        @FormParam("credit_card_expiration_month") String creditCardExpirationMonth,
        @FormParam("billing_address_name") String billingAddressName,
        @FormParam("billing_address_address") String billingAddressAddress,
        @FormParam("billing_address_city") String billingAddressCity,
        @FormParam("billing_address_country") String billingAddressCountry,
        @FormParam("billing_address_state") String billingAddressState,
        @FormParam("billing_address_phone") String billingAddressPhone,
        @FormParam("billing_address_zip") String billingAddressZip,
        @FormParam("credit_card_partial_number") String creditCardPartialNumber);

    @DELETE
    @Path("/accounts/{accountId}/credit_card.xml")
    public void accountDeleteCreditCard(
        @PathParam("accountId") int accountId,
        @FormParam("access_token") String accessToken);

    @PUT
    @Path("/accounts/{accountId}/make_pending.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void accountMakePending(
        @PathParam("accountId") int accountId,
        @FormParam("access_token") String accessToken
    );

    @PUT
    @Path("/accounts/{accountId}/reject.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void accountReject(
        @PathParam("accountId") int accountId,
        @FormParam("access_token") String accessToken
    );

    @GET
    @Path("/accounts/{accountId}/users.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getAccountUsers(
        @PathParam("accountId") int accountId,
        @QueryParam("access_token") String accessToken,
        @QueryParam("state") State state,
        @QueryParam("role") Role role
    );

    @POST
    @Path("/accounts/{accountId}/users.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createUser(
        @PathParam("accountId") int accountId,
        @FormParam("access_token") String accessToken,
        @FormParam("username") String userName,
        @FormParam("email") String email,
        @FormParam("password") String password);

    @GET
    @Path("/users/{userId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getUser(
        @PathParam("userId") int userId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/users/{userId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateUser(
        @PathParam("userId") String userId,
        @FormParam("access_token") String accessToken,
        @FormParam("username") String userName,
        @FormParam("email") String email,
        @FormParam("password") String password
    );

    @DELETE
    @Path("/users/{userId}.xml")
    public void deleteUser(
        @PathParam("userId") int userId,
        @FormParam("access_token") String accessToken);

    @PUT
    @Path("/users/{userId}/activate.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void activateUser(
        @PathParam("userId") int userId,
        @FormParam("access_token") String accessToken);

    @PUT
    @Path("/users/{userId}/admin.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void changeUserRoleToAdmin(
        @PathParam("userId") int userId,
        @FormParam("access_token") String accessToken);

    @PUT
    @Path("/users/{userId}/member.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void changeUserRoleToMember(
        @PathParam("userId") int userId,
        @FormParam("access_token") String accessToken);

    @GET
    @Path("/users/{userId}/permissions.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getUserPermissions(
        @PathParam("userId") int userId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/users/{userId}/permissions.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateUserPermissions(
        @PathParam("userId") int userId,
        @FormParam("access_token") String accessToken,
        @FormParam("allowed_service_ids") List<String> allowedServiceIds,
        @FormParam("allowed_sections") List<String> allowedSections
        );

    @PUT
    @Path("/users/{userId}/suspend.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void suspendUser(
        @PathParam("userId") int userId,
        @FormParam("access_token") String accessToken);
    
    @PUT
    @Path("/users/{userId}/unsuspend.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void unsuspendUser(
        @PathParam("userId") int userId,
        @FormParam("access_token") String accessToken);

    @POST
    @Path("/users/{userId}/access_tokens.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String createAccessToken(
        @PathParam("userId") String userId,
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("permission") String permission,
        @FormParam("scopes") List<String> scopes);

    @GET
    @Path("/account_plans.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getAccountPlans(
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/account_plans.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createAccountPlan(
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("approval_required") boolean permission,
        @FormParam("system_name") String systemName,
        @FormParam("state_event") StateEvent stateEvent
    );

    @GET
    @Path("/account_plans/{accountPlanId}/features.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getAccountPlanFeatures(
        @PathParam("accountPlanId") int accountPlanId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/account_plans/{accountPlanId}/features/{accountPlanFeatureId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createAccountPlanFeature(
        @PathParam("accountPlanId") int accountPlanId,
        @PathParam("accountPlanFeatureId") int accountPlanFeatureId,
        @FormParam("access_token") String accessToken
    );

    @DELETE
    @Path("/account_plans/{accountPlanId}/features/{accountPlanFeatureId}.xml")
    public void deleteAccountPlanFeature(
        @PathParam("accountPlanId") int accountPlanId,
        @PathParam("accountPlanFeatureId") int accountPlanFeatureId,
        @QueryParam("access_token") String accessToken);

    @GET
    @Path("/account_plans/{accountPlanId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getAccountPlan(
        @PathParam("accountPlanId") int accountPlanId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/account_plans/{accountPlanId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateAccountPlan(
        @PathParam("accountPlanId") int accountPlanId,
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("approval_requited") boolean permission,
        @FormParam("state_event") String stateEvent
    );

    @DELETE
    @Path("/account_plans/{accountPlanId}.xml")
    public void deleteAccountPlan(
        @PathParam("accountPlanId") int accountPlanId,
        @QueryParam("access_token") String accessToken);

    @PUT
    @Path("/account_plans/{accountPlanId}/default.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void accountPlanDefault(
        @PathParam("accountPlanId") int accountPlanId,
        @FormParam("access_token") String accessToken);
    
    @GET
    @Path("/active_docs.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getActiveDocs(
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/active_docs.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createActiveDocs(
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("system_name") String systemName,
        @FormParam("service_id") Integer serviceId,
        @FormParam("body") String body,
        @FormParam("description") String description,
        @FormParam("published") Boolean published,
        @FormParam("skip_swagger_validations") Boolean skipSwaggerValidations
    );

    @PUT
    @Path("/active_docs.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateActiveDocs(
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("service_id") Integer serviceId,
        @FormParam("body") String body,
        @FormParam("description") String description,
        @FormParam("published") Boolean published,
        @FormParam("skip_swagger_validations") Boolean skipSwaggerValidations
    );

    @GET
    @Path("/active_docs/{activeDocId}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getActiveDoc(
        @PathParam("activeDocId") int activeDocId,
        @QueryParam("access_token") String accessToken
    );

    @DELETE
    @Path("/active_docs/{activeDocId}.json")
    public void deleteActiveDoc(
        @PathParam("activeDocId") int activeDocId,
        @QueryParam("access_token") String accessToken);

    @GET
    @Path("/application_plans.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getApplicationPlans(
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/application_plans/{applicationPlanId}/features.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getApplicationPlanFeatures(
        @PathParam("applicationPlanId") int applicationPlanId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/application_plans/{applicationPlanId}/features.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createApplicationPlanFeature(
        @PathParam("applicationPlanId") int applicationPlanId,
        @FormParam("access_token") String accessToken,
        @FormParam("feature_id") int featureId
    );

    @DELETE
    @Path("/application_plans/{applicationPlanId}/features/{featureId}.xml")
    public void deleteApplicationPlanFeature(
        @PathParam("applicationPlanId") int applicationPlanId,
        @PathParam("featureId") int featureId,
        @QueryParam("access_token") String accessToken);

    @GET
    @Path("/application_plans/{applicationPlanId}/limits.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getApplicationPlanLimits(
        @PathParam("applicationPlanId") int applicationPlanId,
        @QueryParam("access_token") String accessToken,
        @QueryParam("page") int page,
        @QueryParam("per_page") int perPage
    );
    
    @GET
    @Path("/application_plans/{applicationPlanId}/metrics/{metricId}/limits.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getApplicationPlanMetricLimits(
        @PathParam("applicationPlanId") int applicationPlanId,
        @PathParam("metricId") int metricId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/application_plans/{applicationPlanId}/metrics/{metricId}/limits.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createApplicationPlanMetricLimit(
        @PathParam("applicationPlanId") int applicationPlanId,
        @PathParam("metricId") int metricId,
        @FormParam("access_token") String accessToken,
        @FormParam("value") int value,
        @FormParam("period") LimitPeriod period
    );

    @GET
    @Path("/application_plans/{applicationPlanId}/metrics/{metricId}/limits/{limitId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getApplicationPlanMetricLimit(
        @PathParam("applicationPlanId") int applicationPlanId,
        @PathParam("metricId") int metricId,
        @PathParam("limitId") int limitId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/application_plans/{applicationPlanId}/metrics/{metricId}/limits/{limitId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateApplicationPlanMetricLimit(
        @PathParam("applicationPlanId") int applicationPlanId,
        @PathParam("metricId") int metricId,
        @PathParam("limitId") int limitId,
        @FormParam("access_token") String accessToken,
        @FormParam("value") int value,
        @FormParam("period") LimitPeriod period
    );

    @DELETE
    @Path("/application_plans/{applicationPlanId}/metrics/{metricId}/limits/{limitId}.xml")
    public void deleteApplicationPlanMetricLimit(
        @PathParam("applicationPlanId") int applicationPlanId,
        @PathParam("metricId") int metricId,
        @PathParam("limitId") int limitId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/application_plans/{applicationPlanId}/metrics/{metricId}/pricing_rules.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getApplicationPlanMetricPricingRules(
        @PathParam("applicationPlanId") int applicationPlanId,
        @PathParam("metricId") int metricId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/application_plans/{applicationPlanId}/metrics/{metricId}/pricing_rules.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createApplicationPlanMetricPricingRule(
        @PathParam("applicationPlanId") int applicationPlanId,
        @PathParam("metricId") int metricId,
        @FormParam("access_token") String accessToken,
        @FormParam("min") int min,
        @FormParam("max") int max,
        @FormParam("cost_per_unit") float costPerUnit
    );

    @DELETE
    @Path("/application_plans/{applicationPlanId}/metrics/{metricId}/pricing_rules/{pricingRuleId}.json")
    public void deleteApplicationPlanMetricPricingRule(
        @PathParam("applicationPlanId") int applicationPlanId,
        @PathParam("metricId") int metricId,
        @PathParam("pricingRuleId") int pricingRuleId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/application_plans/{applicationPlanId}/pricing_rules.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getApplicationPlanPricingRules(
        @PathParam("applicationPlanId") int applicationPlanId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/services/{serviceId}/application_plans.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServiceApplicationPlans(
        @PathParam("serviceId") int serviceId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/services/{serviceId}/application_plans.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createServiceApplicationPlan(
        @PathParam("serviceId") int serviceId,
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("approval_required") boolean approvalRequired,
        @FormParam("cost_per_month") Float costPerMonth,
        @FormParam("setup_fee") Float setupFee,
        @FormParam("trial_period_days") Integer trialPeriodDays,
        @FormParam("state_event") StateEvent stateEvent
    );

    @GET
    @Path("/services/{serviceId}/application_plans/{applicationPlanId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServiceApplicationPlan(
        @PathParam("serviceId") int serviceId,
        @PathParam("applicationPlanId") int applicationPlanId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/services/{serviceId}/application_plans/{applicationPlanId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateServiceApplicationPlan(
        @PathParam("serviceId") int serviceId,
        @PathParam("applicationPlanId") int applicationPlanId,
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("approval_required") Boolean approvalRequired,
        @FormParam("cost_per_month") Float costPerMonth,
        @FormParam("setup_fee") Float setupFee,
        @FormParam("trial_period_days") int trialPeriodDays,
        @FormParam("state_event") StateEvent stateEvent
    );

    @DELETE
    @Path("/services/{serviceId}/application_plans/{applicationPlanId}.xml")
    public void deleteServiceApplicationPlan(
        @PathParam("serviceId") int serviceId,
        @PathParam("applicationPlanId") int applicationPlanId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/services/{serviceId}/application_plans/{applicationPlanId}/default.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void defaultApplicationPlan(
        @PathParam("serviceId") int serviceId,
        @PathParam("applicationPlanId") int applicationPlanId,
        @FormParam("access_token") String accessToken
    );

    @GET
    @Path("/applications.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServicesApplications(
        @QueryParam("access_token") String accessToken,
        @QueryParam("page") int page,
        @QueryParam("per_page") int perPage,
        @QueryParam("active_since") String activeSince,
        @QueryParam("inactice_since") String inActiveSince,
        @QueryParam("service_id") String serviceId,
        @QueryParam("plan_id") String planId,
        @QueryParam("plan_type") String planType
    );

    @GET
    @Path("/applications/find.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String findServicesApplications(
        @QueryParam("access_token") String accessToken,
        @QueryParam("application_id") String applicationId,
        @QueryParam("user_key") String userKey,
        @QueryParam("app_id") String appId,
        @QueryParam("service_id") String serviceId
    );

    @GET
    @Path("/account/authentication_providers.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getAdminPortalAuthenticationProviders(
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/account/authentication_providers.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createAdminPortalAuthenticationProvider(
        @FormParam("access_token") String accessToken,
        @FormParam("kind") String kind,
        @FormParam("name") String name,
        @FormParam("system_name") String systemName,
        @FormParam("client_id") String clientId,
        @FormParam("client_secret") String clientSecret,
        @FormParam("site") String site,
        @FormParam("skip_ssl_certificate_validation") boolean skipSslCertificateValidation,
        @FormParam("published") boolean published
    );

    @GET
    @Path("/account/authentication_providers/{authenticationProviderId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getAdminPortalAuthenticationProvider(
        @PathParam("authenticationProviderId") int authenticationProviderId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/account/authentication_providers/{authenticationProviderId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateAdminPortalAuthenticationProvider(
        @PathParam("authenticationProviderId") int authenticationProviderId,
        @FormParam("access_token") String accessToken,
        @FormParam("client_id") String clientId,
        @FormParam("client_secret") String clientSecret,
        @FormParam("site") String site,
        @FormParam("skip_ssl_certificate_validation") Boolean skipSslCertificateValidation,
        @FormParam("published") Boolean published
    );

    @GET
    @Path("/authentication_providers.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getDeveloperPortalAuthenticationProviders(
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/authentication_providers.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createDeveloperPortalAuthenticationProvider(
        @FormParam("access_token") String accessToken,
        @FormParam("kind") String kind,
        @FormParam("name") String name,
        @FormParam("system_name") String systemName,
        @FormParam("client_id") String clientId,
        @FormParam("client_secret") String clientSecret,
        @FormParam("site") String site,
        @FormParam("token_url") String tokenUrl,
        @FormParam("user_info_url") String userInfoUrl,
        @FormParam("authorize_url") String authorizeUrl,
        @FormParam("identifier_key") String identifierKey,
        @FormParam("username_key") String userNameKey,
        @FormParam("trust_email") boolean trustEmail,
        @FormParam("published") boolean published,
        @FormParam("branding_state_event") String brandingStateEvent,
        @FormParam("skip_ssl_certificate_validation") boolean skipSslCertificateValidation,
        @FormParam("automatically_approve_accounts") boolean automaticallyApproveAccounts
    );

    @GET
    @Path("/authentication_providers/{authenticationProviderId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getDeveloperPortalAuthenticationProvider(
        @PathParam("authenticationProviderId") String authenticationProviderId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/authentication_providers/{authenticationProviderId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateDeveloperPortalAuthenticationProvider(
        @PathParam("authenticationProviderId") int authenticationProviderId,
        @FormParam("access_token") String accessToken,
        @FormParam("client_id") String clientId,
        @FormParam("client_secret") String clientSecret,
        @FormParam("site") String site,
        @FormParam("published") Boolean published,
        @FormParam("skip_ssl_certificate_validation") Boolean skipSslCertificateValidation,
        @FormParam("automatically_approve_accounts") Boolean automaticallyApproveAccounts
    );

    @GET
    @Path("/backend_apis.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBackends(
        @QueryParam("access_token") String accessToken,
        @QueryParam("page") int page,
        @QueryParam("per_page") int perPage
    );

    @POST
    @Path("/backend_apis.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createBackend(
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("system_name") String systemName,
        @FormParam("description") String description,
        @FormParam("private_endpoint") String privateEndpoint
    );

    @GET
    @Path("/backend_apis/{backendId}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBackend(
        @PathParam("backendId") int backendId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/backend_apis/{backendId}.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateBackend(
        @PathParam("backendId") int backendId,
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("system_name") String systemName,
        @FormParam("description") String description,
        @FormParam("private_endpoint") String privateEndpoint
    );
    
    @DELETE
    @Path("/backend_apis/{backendId}.json")
    public void deleteBackend(
        @PathParam("backendId") int backendId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/backend_apis/{backendId}/mapping_rules.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBackendMappingRules(
        @PathParam("backendId") int backendId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/backend_apis/{backendId}/mapping_rules.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createBackendMappingRule(
        @PathParam("backendId") int backendId,
        @FormParam("access_token") String accessToken,
        @FormParam("http_method") String httpMethod,
        @FormParam("pattern") String pattern,
        @FormParam("delta") int delta,
        @FormParam("metric_id") int metricId,
        @FormParam("position") Integer position,
        @FormParam("last") Boolean last
    );

    @GET
    @Path("/backend_apis/{backendId}/mapping_rules/{mappingRuleId}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBackendMappingRule(
        @PathParam("backendId") int backendId,
        @PathParam("mappingRuleId") int mappingRuleId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/backend_apis/{backendId}/mapping_rules/{mappingRuleId}.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateBackendMappingRule(
        @PathParam("backendId") int backendId,
        @PathParam("mappingRuleId") int mappingRuleId,
        @FormParam("access_token") String accessToken,
        @FormParam("http_method") String httpMethod,
        @FormParam("pattern") String pattern,
        @FormParam("delta") Integer delta,
        @FormParam("metric_id") Integer metricId,
        @FormParam("position") Integer position,
        @FormParam("last") Boolean last
    );

    @DELETE
    @Path("/backend_apis/{backendId}/mapping_rules/{mappingRuleId}.json")
    public void deleteBackendMappingRule(
        @PathParam("backendId") int backendId,
        @PathParam("mappingRuleId") int mappingRuleId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/backend_apis/{backendId}/metrics.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBackendMetrics(
        @PathParam("backendId") int backendId,
        @QueryParam("access_token") String accessToken,
        @QueryParam("page") int page,
        @QueryParam("per_page") int perPage       
    );

    @POST
    @Path("/backend_apis/{backendId}/metrics.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createBackendMetric(
        @PathParam("backendId") String backendId,
        @FormParam("access_token") String accessToken,
        @FormParam("friendly_name") String friendlyName,
        @FormParam("system_name") String systemName,
        @FormParam("unit") String unit,
        @FormParam("description") String description
    );

    @GET
    @Path("/backend_apis/{backendId}/metrics/{metricId}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBackendMetric(
        @PathParam("backendId") int backendId,
        @PathParam("metricId") int metricId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/backend_apis/{backendId}/metrics.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateBackendMetric(
        @PathParam("backendId") int backendId,
        @FormParam("access_token") String accessToken,
        @FormParam("friendly_name") String friendlyName,
        @FormParam("unit") String unit,
        @FormParam("description") String description
    );

    @DELETE
    @Path("/backend_apis/{backendId}/metrics/{metricId}.json")
    public void deleteBackendMetric(
        @PathParam("backendId") int backendId,
        @PathParam("metricId") int metricId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/backend_apis/{backendId}/metrics/{metricId}/methods.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBackendMetricMethods(
        @PathParam("backendId") int backendId,
        @PathParam("metricId") int metricId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/backend_apis/{backendId}/metrics/{metricId}/methods.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createBackendMetricMethod(
        @PathParam("backendId") int backendId,
        @PathParam("metricId") int metricId,
        @FormParam("access_token") String accessToken,
        @FormParam("friendly_name") String friendlyName,
        @FormParam("system_name") String systemName,
        @FormParam("unit") String unit,
        @FormParam("description") String description
    );

    @GET
    @Path("/backend_apis/{backendId}/metrics/{metricId}/methods/{methodId}.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getBackendMetricMethod(
        @PathParam("backendId") int backendId,
        @PathParam("metricId") int metricId,
        @PathParam("methodId") int methodId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/backend_apis/{backendId}/metrics/{metricId}/methods/{methodId}.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateBackendMetricMethod(
        @PathParam("backendId") int backendId,
        @PathParam("metricId") int metricId,
        @PathParam("methodId") int methodId,
        @FormParam("access_token") String accessToken,
        @FormParam("friendly_name") String friendlyName,
        @FormParam("unit") String unit,
        @FormParam("description") String description
    );

    @DELETE
    @Path("/backend_apis/{backendId}/metrics/{metricId}/methods/{methodId}.json")
    public void deleteBackendMetricMethod(
        @PathParam("backendId") int backendId,
        @PathParam("metricId") int metricId,
        @PathParam("methodId") int methodId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/admin/api/services/{serviceId}/backend_usages.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBackendUsages(
        @PathParam("serviceId") int serviceId,
        @QueryParam("access_token") String accessToken
    );
    
    @POST
    @Path("/admin/api/services/{serviceId}/backend_usages.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createBackendUsage(
        @PathParam("serviceId") int serviceId,
        @FormParam("access_token") String accessToken,
        @FormParam("backendId") int backendId,
        @FormParam("path") String path
    );

    @GET
    @Path("/admin/api/services/{serviceId}/backend_usages/{backendUsageId}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBackendUsage(
        @PathParam("serviceId") int serviceId,
        @PathParam("backendUsageId") int backendUsageId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/admin/api/services/{serviceId}/backend_usages/{backendUsageId}.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateBackendUsage(
        @PathParam("serviceId") int serviceId,
        @PathParam("backendUsageId") int backendUsageId,
        @FormParam("access_token") String accessToken,
        @FormParam("path") String path
    );

    @DELETE
    @Path("/admin/api/services/{serviceId}/backend_usages/{backendUsageId}.json")
    public void deleteBackendUsage(
        @PathParam("serviceId") int serviceId,
        @PathParam("backendUsageId") int backendUsageId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/features.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getAccountFeatures(
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/features.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createAccountFeature(
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("system_name") String systemName
    );

    @GET
    @Path("/features.xml/{featureId}")
    @Produces(MediaType.APPLICATION_XML)
    public String getAccountFeature(
        @PathParam("featureId") int featureId,
        @QueryParam("access_token") String accessToken
    );

    @DELETE
    @Path("/features.xml/{featureId}")
    public void deleteAccountFeature(
        @PathParam("featureId") int featureId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/fields_definitions.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getFieldDefinitions(
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/fields_definitions.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createFieldDefinition(
        @FormParam("access_token") String accessToken,
        @FormParam("target") FieldTarget target,
        @FormParam("name") String name,
        @FormParam("label") String label,
        @FormParam("required") Boolean required,
        @FormParam("hidden") Boolean hidden,
        @FormParam("read_only") Boolean readOnly,
        @FormParam("choices") List<String> choices
    );

    @GET
    @Path("/fields_definitions/{fieldDefinitionId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getFieldDefinition(
        @PathParam("fieldDefinitionId") String fieldDefinitionId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/fields_definitions/{fieldDefinitionId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateFieldDefinition(
        @PathParam("fieldDefinitionId") String fieldDefinitionId,
        @FormParam("access_token") String accessToken,
        @FormParam("target") FieldTarget target,
        @FormParam("label") String label,
        @FormParam("required") Boolean required,
        @FormParam("position") Integer position,
        @FormParam("hidden") Boolean hidden,
        @FormParam("read_only") Boolean readOnly,
        @FormParam("choices") List<String> choices
    );

    @GET
    @Path("/objects/status.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getObjectStatus(
        @QueryParam("access_token") String accessToken,
        @QueryParam("object_type") ObjectType objectType,
        @QueryParam("object_id") String objectId
    );

    @GET
    @Path("/provider.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getProvider(
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/provider.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateProvider(
        @FormParam("access_token") String accessToken,
        @FormParam("from_email") String fromEmail,
        @FormParam("support_email") String supportEmail,
        @FormParam("finance_support_email") String financeSupportEmail,
        @FormParam("site_access_code") String siteAccessCode
    );

    @GET
    @Path("/service_plans.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServicePlans(
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/service_plans/{servicePlanId}/features.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServicePlanFeatures(
        @PathParam("servicePlanId") int servicePlanId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/service_plans/{servicePlanId}/features.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createServicePlanFeature(
        @PathParam("servicePlanId") int servicePlanId,
        @FormParam("access_token") String accessToken,
        @FormParam("feature_id") int featureId
    );

    @DELETE
    @Path("/service_plans/{servicePlanId}/features/{featureId}.xml")
    public void deleteServicePlanFeature(
        @PathParam("servicePlanId") int servicePlanId,
        @PathParam("featureId") int featureId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/services.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServices(
        @QueryParam("access_token") String accessToken,
        @QueryParam("page") int page,
        @QueryParam("per_page") int perPage       
    );

    @POST
    @Path("/services.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createService(
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("description") String description,
        @FormParam("deployment_option") DeploymentOption deploymentOption,
        @FormParam("backend_version") String authenticationMode,
        @FormParam("system_name") String system_name
    );

    @GET
    @Path("/services/{serviceId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getService(
        @PathParam("serviceId") int serviceId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/services/{serviceId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateService(
        @PathParam("serviceId") int serviceId,
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("description") String description,
        @FormParam("deployment_option") DeploymentOption deploymentOption,
        @FormParam("backend_version") String authenticationMode,
        @FormParam("system_name") String system_name
    );

    @DELETE
    @Path("/services/{serviceId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public void deleteService(
        @PathParam("serviceId") int serviceId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/services/{serviceId}/service_plans.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServicePlans(
        @PathParam("serviceId") int serviceId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/services/{serviceId}/service_plans.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createServicePlan(
        @PathParam("serviceId") int serviceId,
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("approval_required") Boolean approvalRequired,
        @FormParam("system_name") String systenName,
        @FormParam("state_event") StateEvent stateEvent
    );

    @GET
    @Path("/services/{serviceId}/service_plans/{servicePlanId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServicePlan(
        @PathParam("serviceId") int serviceId,
        @PathParam("servicePlanId") int servicePlanId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/services/{serviceId}/service_plans/{servicePlanId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateServicePlan(
        @PathParam("serviceId") int serviceId,
        @PathParam("servicePlanId") int servicePlanId,
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("approval_required") Boolean approvalRequired,
        @FormParam("state_event") StateEvent stateEvent
    );

    @GET
    @Path("/services/{serviceId}/service_plans/{servicePlanId}.xml")
    public void deleteServicePlan(
        @PathParam("serviceId") int serviceId,
        @PathParam("servicePlanId") int servicePlanId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/services/{serviceId}/service_plans/{servicePlanId}/default.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void defaultServicePlan(
        @PathParam("serviceId") int serviceId,
        @PathParam("servicePlanId") int servicePlanId,
        @FormParam("access_token") String accessToken
    );

    @GET
    @Path("/services/{service_id}/features.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServiceFeatures(
        @PathParam("serviceId") int serviceId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/services/{service_id}/features.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createServiceFeature(
        @PathParam("serviceId") int serviceId,
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("system_name") String systemName,
        @FormParam("description") String description,
        @FormParam("scope") FeatureScope featureScope
    );

    @GET
    @Path("/services/{service_id}/features/{serviceFeatureId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServiceFeature(
        @PathParam("serviceId") int serviceId,
        @PathParam("serviceFeatureId") int serviceFeatureId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/services/{service_id}/features/{serviceFeatureId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateServiceFeature(
        @PathParam("serviceId") int serviceId,
        @PathParam("serviceFeatureId") int serviceFeatureId,
        @QueryParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("description") String description
    );
    
    @DELETE
    @Path("/services/{service_id}/features/{serviceFeatureId}.xml")
    public void deleteServiceFeature(
        @PathParam("serviceId") int serviceId,
        @PathParam("serviceFeatureId") int serviceFeatureId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/services/{serviceId}/metrics.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServiceMetrics(
        @PathParam("serviceId") int serviceId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/services/{serviceId}/metrics.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createServiceMetric(
        @PathParam("serviceId") int serviceId,
        @FormParam("access_token") String accessToken,
        @FormParam("system_name") String systemName,
        @FormParam("friendly_name") String friendlyName,
        @FormParam("unit") String unit,
        @FormParam("description") String description       
    );

    @GET
    @Path("/services/{serviceId}/metrics/{metricId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServiceMetric(
        @PathParam("serviceId") int serviceId,
        @PathParam("metricId") int metricId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/services/{serviceId}/metrics/{metricId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateServiceMetric(
        @PathParam("serviceId") int serviceId,
        @PathParam("metricId") int metricId,
        @FormParam("access_token") String accessToken,
        @FormParam("friendly_name") String friendlyName,
        @FormParam("unit") String unit,
        @FormParam("description") String description       
    );

    @DELETE
    @Path("/services/{serviceId}/metrics/{metricId}.xml")
    public void deleteServiceMetric(
        @PathParam("serviceId") int serviceId,
        @PathParam("metricId") int metricId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/services/{serviceId}/metrics/{metricId}/methods.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServiceMetricMethods(
        @PathParam("serviceId") int serviceId,
        @PathParam("metricId") int metricId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/services/{serviceId}/metrics/{metricId}/methods.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createServiceMetricMethod(
        @PathParam("serviceId") int serviceId,
        @PathParam("metricId") int metricId,
        @FormParam("access_token") String accessToken,
        @FormParam("friendly_name") String friendlyName,
        @FormParam("system_name") String systemName,
        @FormParam("unit") String unit,
        @FormParam("description") String description       
    );

    @GET
    @Path("/services/{serviceId}/metrics/{metricId}/methods/{methodId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServiceMetricMethod(
        @PathParam("serviceId") int serviceId,
        @PathParam("metricId") int metricId,
        @PathParam("methodId") int methodId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/services/{serviceId}/metrics/{metricId}/methods/{methodId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateServiceMetricMethod(
        @PathParam("serviceId") int serviceId,
        @PathParam("metricId") int metricId,
        @PathParam("methodId") int methodId,
        @FormParam("access_token") String accessToken,
        @FormParam("friendly_name") String friendlyName,
        @FormParam("unit") String unit,
        @FormParam("description") String description       
    );

    @DELETE
    @Path("/services/{serviceId}/metrics/{metricId}/methods/{methodId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public void deleteServiceMetricMethod(
        @PathParam("serviceId") int serviceId,
        @PathParam("metricId") int metricId,
        @PathParam("methodId") int methodId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/account/proxy_configs/{environment}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProxyConfig(
        @PathParam("environment") Environment environment,
        @QueryParam("access_token") String accessToken,
        @QueryParam("host") String host,
        @QueryParam("version") String version
    );

    @GET
    @Path("/services/{serviceId}/proxy.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServiceProxy(
        @PathParam("serviceId") int serviceId,
        @QueryParam("access_token") String accessToken
    );

    @PATCH
    @Path("/services/{serviceId}/proxy.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateServiceProxy(
        @PathParam("serviceId") int serviceId,
        @FormParam("access_token") String accessToken,
        @FormParam("endpoint") String endpoint,
        @FormParam("api_backend") String apiBackend,
        @FormParam("credentials_location") CredentialsLocation credentialsLocation,
        @FormParam("auth_app_key") String authAppKey,
        @FormParam("auth_app_id") String authAppId,
        @FormParam("auth_user_key") String authUserKey,
        @FormParam("error_auth_failed") String errorAuthFailed,
        @FormParam("error_status_auth_failed") Integer errorStatusAuthFailed,
        @FormParam("error_headers_auth_failed") String errorHeadersAuthFailed,
        @FormParam("error_auth_missing") String errorAuthMissing,
        @FormParam("error_status_auth_missing") Integer errorStatusAuthMissing,
        @FormParam("error_headers_auth_missing") String errorHeadersAuthMissing,
        @FormParam("error_no_match") String errorNoMatch,
        @FormParam("error_status_no_match") String errorStatusNoMatch,
        @FormParam("error_status_limits_exceeded") Integer errorStatusLimitsExceeded,
        @FormParam("error_headers_limits_exceeded") String errorHeadersLimitsExceeded,
        @FormParam("error_limits_exceeded") String errorLimitsExceeded,
        @FormParam("oidc_issuer_endpoint") String oidcIssuerEndpoint,
        @FormParam("oidc_issuer_type") String oidcIssuerType,
        @FormParam("sandbox_endpoint") String sandboxEndpoint,
        @FormParam("jwt_claim_with_client_id") String jwtClaimWithCliendId,
        @FormParam("jwt_claim_with_client_id_type") ClientIdType jwtClaimWithClientIdType
    );

    @POST
    @Path("/services/{serviceId}/proxy/deploy.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void deployServiceProxy(
        @PathParam("serviceId") int serviceId,
        @FormParam("access_token") String accessToken
    );

    @GET
    @Path("/services/{serviceId}/proxy/mapping_rules.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServiceProxyMappingRules(
        @PathParam("serviceId") int serviceId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/services/{serviceId}/proxy/mapping_rules.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createServiceProxyMappingRule(
        @PathParam("serviceId") int serviceId,
        @FormParam("access_token") String accessToken,
        @FormParam("http_method") String httpMethod,
        @FormParam("pattern") String pattern,
        @FormParam("delta") int delta,
        @FormParam("metric_id") int metricId,
        @FormParam("position") Integer position,
        @FormParam("last") Boolean last
    );

    @GET
    @Path("/services/{serviceId}/proxy/mapping_rules/{mappingRuleId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServiceProxyMappingRule(
        @PathParam("serviceId") int serviceId,
        @PathParam("mappingRuleId") int mappingRuleId,
        @QueryParam("access_token") String accessToken
    );

    @DELETE
    @Path("/services/{serviceId}/proxy/mapping_rules/{mappingRuleId}.xml")
    public void deleteServiceProxyMappingRule(
        @PathParam("serviceId") int serviceId,
        @PathParam("mappingRuleId") int mappingRuleId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/services/{serviceId}/proxy/mapping_rules/{mappingRuleId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateServiceProxyMappingRule(
        @PathParam("serviceId") int serviceId,
        @PathParam("mappingRuleId") int mappingRuleId,
        @FormParam("access_token") String accessToken,
        @FormParam("http_method") String httpMethod,
        @FormParam("pattern") String pattern,
        @FormParam("delta") int delta,
        @FormParam("metric_id") int metricId,
        @FormParam("position") Integer position,
        @FormParam("last") Boolean last
    );

    @GET
    @Path("/services/{serviceId}/proxy/oidc_configuration.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getServiceProxyOidc(
        @PathParam("serviceId") int serviceId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/services/{serviceId}/proxy/oidc_configuration.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateServiceProxyOidc(
        @PathParam("serviceId") int serviceId,
        @FormParam("access_token") String accessToken,
        @FormParam("standard_flow_enabled") Boolean standardFlowEnabled,
        @FormParam("implicit_flow_enabled") Boolean implicitFlowEnabled,
        @FormParam("service_accounts_enabed") Boolean serviceAccountEnabled,
        @FormParam("direct_access_grants_enabled") Boolean directAccessGrantsEnabled
    );

    @GET
    @Path("/services/{serviceId}/proxy/configs/{environment}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getServiceProxyConfigs(
        @PathParam("serviceId") int serviceId,
        @PathParam("environment") Environment environment,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/services/{serviceId}/proxy/configs/{environment}/latest.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getServiceProxyConfigLatest(
        @PathParam("serviceId") int serviceId,
        @PathParam("environment") Environment environment,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/services/{serviceId}/proxy/configs/{environment}/{version}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getServiceProxyConfigVersion(
        @PathParam("serviceId") int serviceId,
        @PathParam("environment") Environment environment,
        @PathParam("version") String version,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/services/{serviceId}/proxy/configs/{environment}/{version}/promote.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void promoteServiceProxyConfigVersion(
        @PathParam("serviceId") int serviceId,
        @PathParam("environment") Environment environment,
        @PathParam("version") String version,
        @FormParam("access_token") String accessToken,
        @FormParam("to") Environment toEnvironment
    );

    @GET
    @Path("/services/{serviceId}/proxy/policies.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getServiceProxyPolicies(
        @PathParam("serviceId") int serviceId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/services/{serviceId}/proxy/policies.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateServiceProxyPolicies(
        @PathParam("serviceId") int serviceId,
        @FormParam("access_token") String accessToken,
        @FormParam("policies_config") String policiesConfig
    );

    @GET
    @Path("/settings.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSettings(
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/settings.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateSettings(
        @FormParam("access_token") String accessToken,
        @FormParam("useraccountarea_enabled") Boolean userAccountAreaEnabled,
        @FormParam("hide_service") Boolean hideService,
        @FormParam("signups_enabled") Boolean signupEnabled,
        @FormParam("account_approval_required") Boolean accountApprovalRequired,
        @FormParam("strong_passwords_enabled") Boolean strongPasswordsEnabled,
        @FormParam("public_search") Boolean publicSearch,
        @FormParam("account_plans_ui_visible") Boolean accountPlansUiVisible,
        @FormParam("change_account_plan_permission") Boolean changeAccountPlanPermission,
        @FormParam("change_service_plan_permission") String changeServicePlanPermission
    );

    @PUT
    @Path("/webhooks.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateWebhooks(
        @FormParam("access_token") String accessToken,
        @FormParam("url") String url,
        @FormParam("active") Boolean active,
        @FormParam("provider_actions") Boolean providerActions,
        @FormParam("account_created_on") Boolean accountCreatedOn,
        @FormParam("account_updated_on") Boolean accountUpdatedOn,
        @FormParam("account_deleted_on") Boolean accountDeletedOn,
        @FormParam("user_created_on") Boolean userCreatedOn,
        @FormParam("user_updated_on") Boolean userUpdatedOn,
        @FormParam("user_deleted_on") Boolean userDeletedOn,
        @FormParam("application_created_on") Boolean applicationCreatedOn,
        @FormParam("application_updated_on") Boolean applicationUpdatedOn,
        @FormParam("application_deleted_on") Boolean applicationDeletedOn,
        @FormParam("account_plan_changed_on") Boolean accountPlanChangedOn,
        @FormParam("application_plan_changed_on") Boolean applicationPlanChangedOn,
        @FormParam("application_user_key_updated_on") Boolean applicationUserKeyUpdatedOn,
        @FormParam("application_key_created_on") Boolean applicationKeyCreatedOn,
        @FormParam("application_key_deleted_on") Boolean applicationKeyDeletedOn,
        @FormParam("application_suspended_on") Boolean applicationSuspendedOn,
        @FormParam("application_key_updated_on") Boolean applicationKeyUpdatedOn
    );

    @GET
    @Path("/webhooks/failures.xml")
    @Produces(MediaType.APPLICATION_XML) 
    public String getWebhooksFailures(
        @QueryParam("access_token") String accessToken
    );

    @DELETE
    @Path("/webhooks/failures.xml")
    public void deleteWebhooksFailures(
        @QueryParam("access_token") String accessToken
    );
}