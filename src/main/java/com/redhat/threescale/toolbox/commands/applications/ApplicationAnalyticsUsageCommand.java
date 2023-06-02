package com.redhat.threescale.toolbox.commands.applications;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AnalyticsService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name="usage", mixinStandardHelpOptions = true)
public class ApplicationAnalyticsUsageCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationAnalyticsUsageCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    @RestClient
    AnalyticsService analyticsService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Application ID", arity = "1")
    private int applicationId;

    @Parameters(index = "1", description = "Format. Valid values: ${COMPLETION-CANDIDATES}", arity = "1")
    private AnalyticsService.Format format;

    @Parameters(index = "2", description = "Metric name. Default: hits", defaultValue = "hits")
    private String metricName;

    @Parameters(index = "3", description = "Time range start. Format YYYY-MM-DD HH:MM:SS, '2012-02-22', '2012-02-22 23:49:00'")
    private String since;

    @Option(names = {"--period"}, description="Period combined with since time gives stats for the time range [since .. since + period]. It is required if until time is not passed. Valid values: ${COMPLETION-CANDIDATES}", defaultValue = Option.NULL_VALUE)
    private AnalyticsService.Period period;

    @Option(names = {"--until"}, description="Time range end. Format YYYY-MM-DD HH:MM:SS")
    private String until;

    @Option(names = {"--granularity"}, description="Granularity of results, each period has an associated granularity. Valid values: ${COMPLETION-CANDIDATES}", defaultValue = "month")
    private AnalyticsService.Granularity granularity;

    @Option(names = {"--timezone"}, description="Time zone for calculations.", defaultValue = "UTC")
    private AnalyticsService.Timezone timezone;

    @Option(names = {"--skip-change"}, description="Time zone for calculations.", defaultValue = Option.NULL_VALUE, negatable = true)
    private Boolean skipChange;


    @Override
    public void run() {
        try {
            String response = analyticsService.getApplicationTraffic(applicationId, format, accessToken, metricName, since, period, until, granularity, timezone, skipChange);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}