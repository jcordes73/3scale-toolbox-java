package com.redhat.threescale.toolbox.rest.client.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/stats")
@RegisterRestClient(configKey = "threescale")
public interface AnalyticsService {

    
    enum Format {
        json,
        xml
    }

    enum Period {
        year,
        month,
        week,
        day
    }

    enum Granularity {
        month,
        day,
        year
    }

    enum Timezone {
        INTERNATIONAL_DATE_LINE_WEST("International Date Line West"),
        MIDWAY_ISLAND("Midway Island"),
        AMERICAN_SAMOA("American Samoa"),
        HAWAII("Hawaii"),
        ALASKA("Alaska"),
        PACIFIC_TIME_US_CANADA("Pacific Time (US & Canada)"),
        TIJUANA("Tijuana"),
        MOUNTAIN_TIME_US_CANADA("Mountain Time (US & Canada)"),
        ARIZONA("Arizona"),
        CHIHUAHUA("Chihuahua"),
        MAZATLAN("Mazatlan"),
        CENTRAL_TIME_US_CANADA("Central Time (US & Canada)"),
        SASKATCHEWAN("Saskatchewan"),
        GUADALAJARA("Guadalajara"),
        MEXICO_CITY("Mexico City"),
        MONTERREY("Monterrey"),
        CENTRAL_AMERICA("Central America"),
        EASTERN_TIME_US_CANADA("Eastern Time (US & Canada)"),
        INDIANA_EAST("Indiana (East)"),
        BOGOTA("Bogota"),
        LIMA("Lima"),
        QUITO("Quito"),
        ATLANTIC_TIME_CANADA("Atlantic Time (Canada)"),
        CARACAS("Caracas"),
        LA_PAZ("La Paz"),
        SANTIAGO("Santiago"),
        NEWFOUNDLAND("Newfoundland"),
        BRASILIA("Brasilia"),
        BUENOS_AIRES("Buenos Aires"),
        GEORGETOWN("Georgetown"),
        GREENLAND("Greenland"),
        MID_ATLANTIC("Mid-Atlantic"),
        AZORES("Azores"),
        CAPE_VERDE_IS("Cape Verde Is."),
        DUBLIN("Dublin"),
        EDINBURGH("Edinburgh"),
        LISBON("Lisbon"),
        LONDON("London"),
        CASABLANCA("Casablanca"),
        MONROVIA("Monrovia"),
        UTC("UTC"),
        BELGRADE("Belgrade"),
        BRATISLAVA("Bratislava"),
        BUDAPEST("Budapest"),
        LJUBLJANA("Ljubljana"),
        PRAGUE("Prague"),
        SARAJEVO("Sarajevo"),
        SKOPJE("Skopje"),
        WARSAW("Warsaw"),
        ZAGREB("Zagreb"),
        BRUSSELS("Brussels"),
        COPENHAGEN("Copenhagen"),
        MADRID("Madrid"),
        PARIS("Paris"),
        AMSTERDAM("Amsterdam"),
        BERLIN("Berlin"),
        BERN("Bern"),
        ROME("Rome"),
        STOCKHOLM("Stockholm"),
        VIENNA("Vienna"),
        WEST_CENTRAL_AFRICA("West Central Africa"),
        BUCHAREST("Bucharest"),
        CAIRO("Cairo"),
        HELSINKI("Helsinki"),
        KYIV("Kyiv"),
        RIGA("Riga"),
        SOFIA("Sofia"),
        TALLINN("Tallinn"),
        VILNIUS("Vilnius"),
        ATHENS("Athens"),
        ISTANBUL("Istanbul"),
        MINSK("Minsk"),
        JERUSALEM("Jerusalem"),
        HARARE("Harare"),
        PRETORIA("Pretoria"),
        MOSCOW("Moscow"),
        ST_PETERSBURG("St. Petersburg"),
        VOLGOGRAD("Volgograd"),
        KUWAIT("Kuwait"),
        RIYADH("Riyadh"),
        NAIROBI("Nairobi"),
        BAGHDAD("Baghdad"),
        TEHRAN("Tehran"),
        ABU_DHABI("Abu Dhabi"),
        MUSCAT("Muscat"),
        BAKU("Baku"),
        TBILISI("Tbilisi"),
        YEREVAN("Yerevan"),
        KABUL("Kabul"),
        EKATERINBURG("Ekaterinburg"),
        ISLAMABAD("Islamabad"),
        KARACHI("Karachi"),
        TASHKENT("Tashkent"),
        CHENNAI("Chennai"),
        KOLKATA("Kolkata"),
        MUMBAI("Mumbai"),
        NEW_DELHI("New Delhi"),
        KATHMANDU("Kathmandu"),
        ASTANA("Astana"),
        DHAKA("Dhaka"),
        SRI_JAYAWARDENEPURA("Sri Jayawardenepura"),
        ALMATY("Almaty"),
        NOVOSIBIRSK("Novosibirsk"),
        RANGOON("Rangoon"),
        BANGKOK("Bangkok"),
        HANOI("Hanoi"),
        JAKARTA("Jakarta"),
        KRASNOYARSK("Krasnoyarsk"),
        BEIJING("Beijing"),
        CHONGQING("Chongqing"),
        HONG_KONG("Hong Kong"),
        URUMQI("Urumqi"),
        KUALA_LUMPUR("Kuala Lumpur"),
        SINGAPORE("Singapore"),
        TAIPEI("Taipei"),
        PERTH("Perth"),
        IRKUTSK("Irkutsk"),
        ULAAN_BATAAR("Ulaan Bataar"),
        SEOUL("Seoul"),
        OSAKA("Osaka"),
        SAPPORO("Sapporo"),
        TOKYO("Tokyo"),
        YAKUTSK("Yakutsk"),
        DARWIN("Darwin"),
        ADELAIDE("Adelaide"),
        CANBERRA("Canberra"),
        MELBOURNE("Melbourne"),
        SYDNEY("Sydney"),
        BRISBANE("Brisbane"),
        HOBART("Hobart"),
        VLADIVOSTOK("Vladivostok"),
        GUAM("Guam"),
        PORT_MORESBY("Port Moresby"),
        MAGADAN("Magadan"),
        SOLOMON_IS("Solomon Is."),
        NEW_CALEDONIA("New Caledonia"),
        FIJI("Fiji"),
        KAMCHATKA("Kamchatka"),
        MARSHALL_IS("Marshall Is."),
        AUCKLAND("Auckland"),
        WELLINGTON("Wellington"),
        NUKU_ALOFA("Nuku'alofa"),
        TOKELAU_IS("Tokelau Is."),
        SAMOA("Samoa");
        
        private String name;

        Timezone(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String toString() {
            return name;
        }
    }

    @ClientExceptionMapper
    static RuntimeException toException(Response response) {
        if (response.getStatus() == 401) {
            return new RuntimeException("Unauthorized");
        } else if (response.getStatus() == 403) {
            return new RuntimeException("Access denied");
        } else if (response.getStatus() == 404) {
            return new RuntimeException("Entry not found");
        } else if (response.getStatus() == 500) {
            return new RuntimeException("The remote service responded with HTTP 500");
        }
        
        return null;
    }

    @GET
    @Path("/applications/{applicationId}/usage.{format}")
    @Produces(MediaType.APPLICATION_XML)
    public String getApplicationTraffic(
        @PathParam("applicationId") int applicationId,
        @PathParam("format") Format format,
        @QueryParam("metric_name") String metricName,
        @QueryParam("since") String since,
        @QueryParam("period") Period period,
        @QueryParam("until") String until,
        @QueryParam("granularity") Granularity granularity,
        @QueryParam("timezone") Timezone timezone,
        @QueryParam("skipe_change") Boolean skipChange
    );
}