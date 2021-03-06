package com.clients.api.mock;

import com.clients.api.TestUtils;
import com.clients.api.businessobject.ClientBO;
import com.clients.api.dto.ClientsKpi;
import com.clients.api.dto.country.Country;
import com.clients.api.dto.gho.GhoLifeExpectancy;
import com.clients.api.dto.name.NameGender;
import com.clients.api.dto.name.NameProcedence;
import com.clients.api.dto.name.PersonalNames;
import com.clients.api.dto.name.PersonalNamesGender;
import com.clients.api.model.Client;
import com.google.gson.Gson;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.testcontainers.shaded.com.google.common.reflect.TypeToken;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

public class Mocks {


    public static void expectedRequests(MockRestServiceServer restServiceServer, boolean shouldReturnException) {
        String countriesBody;
        HttpStatus httpStatus;

        restServiceServer.expect(ExpectedCount.once(),
                requestTo("https://v2.namsor.com/NamSorAPIv2/api2/json/countryBatch"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(org.springframework.http.HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"personalNames\":[{\"id\":\"123xyz456\",\"name\":\"Lionel\",\"score\":12.698700776562848,\"country\":\"FR\",\"countryAlt\":\"HT\",\"region\":\"Europe\",\"topRegion\":\"Europe\",\"subRegion\":\"Western Europe\",\"countriesTop\":[\"FR\",\"HT\",\"IL\",\"CH\",\"BE\",\"LC\",\"CM\",\"RE\",\"AR\",\"DE\"],\"probabilityCalibrated\":0.5087757386980774,\"probabilityAltCalibrated\":0.5596214750505436},{\"id\":\"456xyz789\",\"name\":\"DIEGO\",\"score\":13.897905917618063,\"country\":\"ES\",\"countryAlt\":\"MX\",\"region\":\"Europe\",\"topRegion\":\"Europe\",\"subRegion\":\"Southern Europe\",\"countriesTop\":[\"ES\",\"MX\",\"AR\",\"PH\",\"PE\",\"BF\",\"SN\",\"CL\",\"EC\",\"PT\"],\"probabilityCalibrated\":0.5431626225107287,\"probabilityAltCalibrated\":0.5515043361369276}]}")
                );

        restServiceServer.expect(ExpectedCount.once(),
                requestTo("https://v2.namsor.com/NamSorAPIv2/api2/json/genderFullBatch"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(org.springframework.http.HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"personalNames\":[{\"id\":\"123xyz456\",\"name\":\"Lionel\",\"likelyGender\":\"male\",\"genderScale\":-0.8523851639587186,\"score\":20.821128116903292,\"probabilityCalibrated\":0.9261925819793593},{\"id\":\"456xyz789\",\"name\":\"DIEGO\",\"likelyGender\":\"male\",\"genderScale\":-0.9932476758884425,\"score\":38.95598297634679,\"probabilityCalibrated\":0.9966238379442213}]}")
                );

        restServiceServer.expect(ExpectedCount.once(),
                requestTo("https://apps.who.int/gho/athena/api/GHO/WHOSIS_000001.json?profile=simple"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(org.springframework.http.HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"fact\":[{\"dim\":{\"YEAR\":\"2001\",\"SEX\":\"Male\",\"GHO\":\"Life expectancy at birth (years)\",\"REGION\":\"Europe\",\"COUNTRY\":\"France\",\"PUBLISHSTATE\":\"Published\"},\"Comments\":\"WHO life table method: Vital registration\",\"Value\":\"75.7\"},{\"dim\":{\"REGION\":\"Europe\",\"GHO\":\"Life expectancy at birth (years)\",\"YEAR\":\"2002\",\"SEX\":\"Male\",\"COUNTRY\":\"France\",\"PUBLISHSTATE\":\"Published\"},\"Comments\":\"WHO life table method: Vital registration\",\"Value\":\"76.0\"},{\"dim\":{\"PUBLISHSTATE\":\"Published\",\"YEAR\":\"2011\",\"GHO\":\"Life expectancy at birth (years)\",\"REGION\":\"Europe\",\"COUNTRY\":\"Spain\",\"SEX\":\"Male\"},\"Comments\":\"WHO life table method: Vital registration\",\"Value\":\"79.4\"},{\"dim\":{\"PUBLISHSTATE\":\"Published\",\"GHO\":\"Life expectancy at birth (years)\",\"COUNTRY\":\"Spain\",\"REGION\":\"Europe\",\"YEAR\":\"2012\",\"SEX\":\"Male\"},\"Comments\":\"WHO life table method: Vital registration\",\"Value\":\"79.5\"}]}")
                );

        if (shouldReturnException) {
            countriesBody = "{\"status\":400,\"message\":\"Bad Request\"}";
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            countriesBody = "[{\"name\":\"France\",\"topLevelDomain\":[\".fr\"],\"alpha2Code\":\"FR\",\"alpha3Code\":\"FRA\",\"callingCodes\":[\"33\"],\"capital\":\"Paris\",\"altSpellings\":[\"FR\",\"French Republic\",\"République française\"],\"region\":\"Europe\",\"subregion\":\"Western Europe\",\"population\":66710000,\"latlng\":[46,2],\"demonym\":\"French\",\"area\":640679,\"gini\":32.7,\"timezones\":[\"UTC-10:00\",\"UTC-09:30\",\"UTC-09:00\",\"UTC-08:00\",\"UTC-04:00\",\"UTC-03:00\",\"UTC+01:00\",\"UTC+03:00\",\"UTC+04:00\",\"UTC+05:00\",\"UTC+11:00\",\"UTC+12:00\"],\"borders\":[\"AND\",\"BEL\",\"DEU\",\"ITA\",\"LUX\",\"MCO\",\"ESP\",\"CHE\"],\"nativeName\":\"France\",\"numericCode\":\"250\",\"currencies\":[{\"code\":\"EUR\",\"name\":\"Euro\",\"symbol\":\"€\"}],\"languages\":[{\"iso639_1\":\"fr\",\"iso639_2\":\"fra\",\"name\":\"French\",\"nativeName\":\"français\"}],\"translations\":{\"de\":\"Frankreich\",\"es\":\"Francia\",\"fr\":\"France\",\"ja\":\"フランス\",\"it\":\"Francia\",\"br\":\"França\",\"pt\":\"França\",\"nl\":\"Frankrijk\",\"hr\":\"Francuska\",\"fa\":\"فرانسه\"},\"flag\":\"https://restcountries.eu/data/fra.svg\",\"regionalBlocs\":[{\"acronym\":\"EU\",\"name\":\"European Union\",\"otherAcronyms\":[],\"otherNames\":[]}],\"cioc\":\"FRA\"},{\"name\":\"Spain\",\"topLevelDomain\":[\".es\"],\"alpha2Code\":\"ES\",\"alpha3Code\":\"ESP\",\"callingCodes\":[\"34\"],\"capital\":\"Madrid\",\"altSpellings\":[\"ES\",\"Kingdom of Spain\",\"Reino de España\"],\"region\":\"Europe\",\"subregion\":\"Southern Europe\",\"population\":46438422,\"latlng\":[40,-4],\"demonym\":\"Spanish\",\"area\":505992,\"gini\":34.7,\"timezones\":[\"UTC\",\"UTC+01:00\"],\"borders\":[\"AND\",\"FRA\",\"GIB\",\"PRT\",\"MAR\"],\"nativeName\":\"España\",\"numericCode\":\"724\",\"currencies\":[{\"code\":\"EUR\",\"name\":\"Euro\",\"symbol\":\"€\"}],\"languages\":[{\"iso639_1\":\"es\",\"iso639_2\":\"spa\",\"name\":\"Spanish\",\"nativeName\":\"Español\"}],\"translations\":{\"de\":\"Spanien\",\"es\":\"España\",\"fr\":\"Espagne\",\"ja\":\"スペイン\",\"it\":\"Spagna\",\"br\":\"Espanha\",\"pt\":\"Espanha\",\"nl\":\"Spanje\",\"hr\":\"Španjolska\",\"fa\":\"اسپانیا\"},\"flag\":\"https://restcountries.eu/data/esp.svg\",\"regionalBlocs\":[{\"acronym\":\"EU\",\"name\":\"European Union\",\"otherAcronyms\":[],\"otherNames\":[]}],\"cioc\":\"ESP\"}]";
            httpStatus = HttpStatus.OK;
        }

        restServiceServer.expect(ExpectedCount.once(),
                requestTo("https://restcountries.eu/rest/v2/alpha?codes=FR;ES"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(httpStatus)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(countriesBody)
                );
    }

    public static List<Client> getTestClients(boolean forExceptionCase) throws ParseException {

        if (forExceptionCase) {
            Client newClient = new Client().setAge(39)
                    .setSurname("Gonzalez")
                    .setBirthDate(TestUtils.parseDate("1930-05-03"))
                    .setName(null);

            return Arrays.asList(newClient);
        }

        Client newClient1 = new Client().setAge(33)
                .setId("123xyz456")
                .setName("Lionel")
                .setSurname("Messi")
                .setBirthDate(TestUtils.parseDate("1987-06-24"));

        Client newClient2 = new Client().setAge(59)
                .setId("456xyz789")
                .setName("Diego")
                .setSurname("Maradona")
                .setBirthDate(TestUtils.parseDate("1960-10-30"));

        return Arrays.asList(newClient1, newClient2);
    }

    public static GhoLifeExpectancy getLifeExpectancyMock() {
        String ghoLifeExpectancyResponse = "{\"fact\":[{\"dim\":{\"YEAR\":\"2001\",\"SEX\":\"Male\",\"GHO\":\"Life expectancy at birth (years)\",\"REGION\":\"Europe\",\"COUNTRY\":\"France\",\"PUBLISHSTATE\":\"Published\"},\"Comments\":\"WHO life table method: Vital registration\",\"Value\":\"75.7\"},{\"dim\":{\"REGION\":\"Europe\",\"GHO\":\"Life expectancy at birth (years)\",\"YEAR\":\"2002\",\"SEX\":\"Male\",\"COUNTRY\":\"France\",\"PUBLISHSTATE\":\"Published\"},\"Comments\":\"WHO life table method: Vital registration\",\"Value\":\"76.0\"},{\"dim\":{\"PUBLISHSTATE\":\"Published\",\"YEAR\":\"2011\",\"GHO\":\"Life expectancy at birth (years)\",\"REGION\":\"Europe\",\"COUNTRY\":\"Spain\",\"SEX\":\"Male\"},\"Comments\":\"WHO life table method: Vital registration\",\"Value\":\"79.4\"},{\"dim\":{\"PUBLISHSTATE\":\"Published\",\"GHO\":\"Life expectancy at birth (years)\",\"COUNTRY\":\"Spain\",\"REGION\":\"Europe\",\"YEAR\":\"2012\",\"SEX\":\"Male\"},\"Comments\":\"WHO life table method: Vital registration\",\"Value\":\"79.5\"}]}";

        return new Gson().fromJson(ghoLifeExpectancyResponse, GhoLifeExpectancy.class);
    }

    public static ClientsKpi getClientsKpiMock() {
        String clientsKpi = "{\"averageAge\":68.5,\"standardDeviation\":4.2,\"status\":500}";

        return new Gson().fromJson(clientsKpi, ClientsKpi.class);
    }

    public static List<ClientBO> getClientsWithDeathDateUtilsMock() {
        String clientsBoJson = "[{\"id\":\"123xyz456\",\"name\":\"Lionel\",\"surname\":\"Messi\",\"age\":33,\"birthDate\":\"1987-06-24T03:00:00.000+00:00\",\"probablyDeathDate\":\"2063-06-24T03:00:00.000+00:00\"},{\"id\":\"456xyz789\",\"name\":\"Diego\",\"surname\":\"Maradona\",\"age\":59,\"birthDate\":\"1960-10-30T03:00:00.000+00:00\",\"probablyDeathDate\":\"2040-10-30T03:00:00.000+00:00\"}]";

        return new Gson().fromJson(clientsBoJson, new TypeToken<List<ClientBO>>() {
        }.getType());
    }

    public static NameProcedence getNameProcedenceObject() {
        return new NameProcedence().setId("123test456")
                .setName("Paolo")
                .setCountry("PT")
                .setCountryAlt("ES");
    }

    public static PersonalNames getPersonalNamesObject(NameProcedence nameProcedence) {
        return new PersonalNames()
                .setPersonalNames(Arrays.asList(nameProcedence));
    }

    public static NameGender getNameGenederObject() {
        return new NameGender().setId("123test456")
                .setName("Paolo")
                .setLikelyGender("Male");
    }

    public static Country getCountryObject() {
        return new Country().setName("Spain").setAlpha2Code("ES");
    }

    public static PersonalNamesGender getPersonalNamesGenderObject(NameGender nameGender) {
        return new PersonalNamesGender()
                .setPersonalNames(Arrays.asList(nameGender));
    }
}
