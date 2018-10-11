import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TheGuarantorPostAPITest {

    private static String URL = "https://research.theguarantors.com/qa-test/";
    private static String INVALID_URL = "https://research.theguarantors.com/";
    private static  String VALID_INPUT;

    private static final String MIXDATATYPE_VALID_INPUT;
    private static final String EXTRA_KEY_VALUE;
    private static final String CONTENT_TYPE = "application/json";
    private TheGuarantorRestClient theGuarantorRestClient = new TheGuarantorRestClient();
    CloseableHttpResponse closeableHttpResponse;
    private static int STATUS_CODE;
    private static String responseString;



    static {
        JSONObject listObject = new JSONObject();
        List<String> listNumbers = new ArrayList<String>();
        listNumbers.add("2");
        listNumbers.add("3");
        listNumbers.add("1");
        listObject.put("numbers", listNumbers);
        VALID_INPUT = listObject.toJSONString();
        List mixData = new ArrayList(listNumbers);
        mixData.add("Parth");
        JSONObject mixObject = new JSONObject();
        mixObject.put("numbers", mixData);
        MIXDATATYPE_VALID_INPUT = mixObject.toJSONString();
        mixObject.put("list", listNumbers);
        EXTRA_KEY_VALUE = mixObject.toJSONString();
    }

    @Test
    public void validate_SortedService_SortedAsendingResponse() throws ParseException, IOException {
        System.out.println("validate SortedService returns sorted in asending order : \n");

        String inputList = null;
        JSONObject jsonObject = new JSONObject();
        List<String> numbers = new ArrayList<String>();
        numbers.add("2");
        numbers.add("3");
        numbers.add("1");
        jsonObject.put("numbers", numbers);
        inputList = jsonObject.toJSONString();

        closeableHttpResponse = theGuarantorRestClient.post(URL, inputList,CONTENT_TYPE);

        printResponseFromServicePostCall(numbers);
        Integer expectedCode = 200;
        Integer actual = printStatusCodeFromServicePostCall();
        Assert.assertEquals(expectedCode, actual, "actual vs expected status code : ");
        System.out.println("Test Successfully Passed");
    }

    @Test
    public void validate_SortedService_WithoutHeader() throws IOException, ParseException {
        System.out.println("validate SortedService WithoutHeader: \n");
        closeableHttpResponse = theGuarantorRestClient.post(URL, VALID_INPUT, null);
        Integer expectedCode = 500;
        Integer actual = printStatusCodeFromServicePostCall();
        Assert.assertEquals(expectedCode, actual, "actual vs expected status code : ");
        System.out.println("Test Successfully Passed");
    }

    @Test
    public void validate_SortedService_WithoutPayLoads() throws IOException, ParseException {
        System.out.println("validate SortedService WithoutPayLoads: \n");
        closeableHttpResponse = theGuarantorRestClient.post(URL, "", CONTENT_TYPE);
        Integer expectedCode = 500;
        Integer actual = printStatusCodeFromServicePostCall();
        Assert.assertEquals(expectedCode, actual, "actual vs expected status code : ");
        System.out.println("Test Successfully Passed");;
    }

    @Test
    public void validate_SortedService_INVALID_INPUT() throws IOException, ParseException {
        System.out.println("validate SortedService INVALID INPUT: \n");
        closeableHttpResponse = theGuarantorRestClient.post(URL, "{\"number\":[1,3,2.6L,-178,-5]}", CONTENT_TYPE);
        Integer expectedCode = 400;
        Integer actual = printStatusCodeFromServicePostCall();
        Assert.assertEquals(expectedCode, actual, "actual vs expected status code : ");
        System.out.println("Test Successfully Passed");
    }

    @Test
    public void validate_SortedService_MIXDATATYPE_VALID_INPUT() throws IOException, ParseException {
        System.out.println("validate SortedService MIXDATATYPE VALID INPUT: \n");
        closeableHttpResponse = theGuarantorRestClient.post(URL, MIXDATATYPE_VALID_INPUT, CONTENT_TYPE);
        printResponseFromServicePostCall();
        Integer expectedCode = 200;
        Integer actual = printStatusCodeFromServicePostCall();
        Assert.assertEquals(expectedCode, actual, "actual vs expected status code : ");
        System.out.println("Test Successfully Passed");
    }

    @Test
    public void validate_SortedService_WITH_UNWANTED_KEY_VALUE() throws IOException, ParseException {
        System.out.println("validate SortedService MIXDATATYPE VALID INPUT WITH UNWANTED KEY VALUE: \n");
        closeableHttpResponse = theGuarantorRestClient.post(URL, EXTRA_KEY_VALUE, CONTENT_TYPE);
        printResponseFromServicePostCall();
        Integer expectedCode = 200;
        Integer actual = printStatusCodeFromServicePostCall();
        Assert.assertEquals(expectedCode, actual, "actual vs expected status code : ");
        System.out.println("Test Successfully Passed");
    }

    @Test
    public void validate_SortedService_Large_IntegerValue() throws IOException, ParseException {
        System.out.println("validate SortedService Large IntegerValue: \n");
        String request = "{ \"numbers\": [10000000001111100002000020000060000, 3000000000000000000000000000000000, 1000000000000000000000000, -100000000000000000000000000000000, 2.30000000000000000000000000000000000000000000000000000, -10000000001111100002000020000060000, 3000000000000000000000000000000000, 10000000002]}";
        closeableHttpResponse = theGuarantorRestClient.post(URL, request, CONTENT_TYPE);
        printResponseFromServicePostCall();
        Integer expectedCode = 200;
        Integer actual = printStatusCodeFromServicePostCall();
        Assert.assertEquals(expectedCode, actual, "actual vs expected status code : ");
        System.out.println("Test Successfully Passed");
    }

    @Test
    public void validate_SortedService_WithEmptyArrray() throws IOException, ParseException {
        System.out.println("validate SortedService WithEmptyArrray: \n");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("numbers", new ArrayList<Integer>());
        closeableHttpResponse = theGuarantorRestClient.post(URL, jsonObject.toJSONString(), CONTENT_TYPE);
        printResponseFromServicePostCall();
        Integer expectedCode = 200;
        Integer actual = printStatusCodeFromServicePostCall();
        Assert.assertEquals(expectedCode, actual, "actual vs expected status code : ");
        System.out.println("Test Successfully Passed");

    }

    @Test
    public void validate_SortedService_NotConfiguedProperly() throws IOException, ParseException {
        System.out.println("validate SortedService NotConfiguedProperly: \n" );
        closeableHttpResponse = theGuarantorRestClient.post(INVALID_URL, VALID_INPUT, CONTENT_TYPE);
        Integer expectedCode = 405;
        Integer actual = printStatusCodeFromServicePostCall();
        Assert.assertEquals(expectedCode, actual, "actual vs expected status code : ");
        System.out.println("Test Successfully Passed");
    }




    //function for printing response from service post call
    private void printResponseFromServicePostCall() throws IOException, ParseException {
        responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONParser parser = new JSONParser();
        List<String> responseNumbers = (List) parser.parse(responseString);
        System.out.println("The Response from Service is:"+ responseNumbers);
    }



    //overload function for validating response
    public void printResponseFromServicePostCall(List<String> expectedPayload) throws IOException, ParseException {
        responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONParser parser = new JSONParser();
        List<String> responseNumbers = (List) parser.parse(responseString);
        System.out.println("The Response from Service is:"+ responseNumbers);
        validate(expectedPayload, responseNumbers);

    }




    //function for printing status code from service post call
    private int printStatusCodeFromServicePostCall() throws IOException, ParseException {
        STATUS_CODE = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("The status code from Service is:"+ STATUS_CODE);
        return STATUS_CODE;
    }




    //function for Validating expected payloads vs actual payloads
    private  void validate(List<String> expectedPayloads, List<String> actualPayloads){
        Collections.sort(expectedPayloads);
        boolean equals = actualPayloads.equals(expectedPayloads);
        Assert.assertTrue(equals, "Validating expected payloads vs actual payloads");

    }
}