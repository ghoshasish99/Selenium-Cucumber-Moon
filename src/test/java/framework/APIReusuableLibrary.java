package framework;


import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import java.io.*;
import java.util.Map;

public class APIReusuableLibrary {


    public enum SERVICEMETHOD {
        GET, POST, PUT, DELETE;
    }

    public enum SERVICEFORMAT {
        JSON, XML, TEXT, FILE;
    }

    /**
     * Function to read input file from given Path
     *
     * @param inputFilePath The Path of the given File
     * @return The String of the input File
     */
    public String readInput(String inputFilePath) {
        String intputFileContent = "";
        BufferedReader bufferReader = null;
        try {
            String line;
            FileReader fileReader = new FileReader("src/test/resources/" + inputFilePath);
            bufferReader = new BufferedReader(fileReader);
            while ((line = bufferReader.readLine()) != null) {
                intputFileContent += line.trim();
            }
        } catch (FileNotFoundException x) {
            System.out.println(x.getMessage() + "first");
        } catch (IOException ex) {

            System.out.println(ex.getMessage() + "second");
        } finally {
            try {
                if (bufferReader != null) {
                    bufferReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return intputFileContent;

    }

    /**
     * Function to get the response of an API
     *
     * @param url        The URL of the Application
     * @param methodType The Service Method GET,POST, PUT ,DELETE {@link SERVICEMETHOD}
     * @param headersMap The headers passed as Map object
     * @param statusCode The Expected Status Code
     * @return The Response {@link ValidatableResponse}
     */
    public ValidatableResponse sendNReceive(String url, SERVICEMETHOD methodType, Map<String, String> headersMap,
                                            int statusCode) throws Exception {
        ValidatableResponse response = null;
        try {
            switch (methodType) {
                case GET:

                    if (headersMap != null) {
                        response = RestAssured.given().relaxedHTTPSValidation().headers(headersMap).get(url).then()
                                .assertThat().statusCode((Integer) statusCode);
                        reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                    } else {
                        response = RestAssured.given().relaxedHTTPSValidation().get(url).then().assertThat()
                                .statusCode((Integer) statusCode);
                        reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                    }

                    break;

                case DELETE:
                    if (headersMap != null) {
                        response = RestAssured.given().relaxedHTTPSValidation().headers(headersMap).delete(url).then()
                                .assertThat().statusCode((Integer) statusCode);
                        reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                    } else {
                        response = RestAssured.given().relaxedHTTPSValidation().headers(headersMap).delete(url).then()
                                .assertThat().statusCode((Integer) statusCode);
                        reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                    }
                    break;

                default:
                    break;
            }
        } catch (AssertionError x) {
            reportItFAIL(url, Integer.toString(statusCode), x.getMessage(), "FAIL");
            Assert.fail(x.getMessage());
        } catch (Exception ex) {
            reportItFAIL(url, Integer.toString(statusCode), ex.getMessage(), "FAIL");
            Assert.fail(ex.getMessage());
        }
        return response;
    }

    /**
     * Function to get the response of an API
     *
     * @param url             The URL of the Application
     * @param methodType      The Service Method GET,POST, PUT ,DELETE {@link SERVICEMETHOD}
     * @param postBodyType    The Format of Post Body {@link SERVICEFORMAT}
     * @param postBodyContent The Post Body Content
     * @param headersMap      The headers passed as Map object
     * @param statusCode      The Expected Status Code
     * @return The Response {@link ValidatableResponse}
     */
    public ValidatableResponse sendNReceive(String url, SERVICEMETHOD methodType, SERVICEFORMAT postBodyType,
                                            String postBodyContent, Map<String, String> headersMap, int statusCode) throws Exception {
        ValidatableResponse response = null;
        try {
            Object postBody = getPostBodyContent(postBodyContent, postBodyType);
            ContentType contentType = getPostContentType(postBodyType);
            switch (methodType) {
                case POST:

                    if (postBody instanceof File) {/* File */
                        if (headersMap != null) {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation()
                                    .body((File) postBody).headers(headersMap).post(url).then().assertThat()
                                    .statusCode((Integer) statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        } else {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation()
                                    .body((File) postBody).post(url).then().assertThat().statusCode((Integer) statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        }
                    } else if (postBody instanceof String) {/* String */
                        if (headersMap != null) {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation().body(postBody)
                                    .headers(headersMap).post(url).then().assertThat().statusCode((Integer) statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        } else {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation().body(postBody)
                                    .post(url).then().assertThat().statusCode((Integer) statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        }
                    }
                    break;

                case PUT:

                    if (postBody instanceof File) {/* File */
                        if (headersMap != null) {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation()
                                    .body((File) postBody).headers(headersMap).put(url).then().assertThat()
                                    .statusCode((Integer) statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        } else {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation()
                                    .body((File) postBody).put(url).then().assertThat().statusCode((Integer) statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        }
                    } else if (postBody instanceof String) {/* String */
                        if (headersMap != null) {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation().body(postBody)
                                    .headers(headersMap).put(url).then().assertThat().statusCode((Integer) statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        } else {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation().body(postBody)
                                    .put(url).then().assertThat().statusCode((Integer) statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        }
                    }
                    break;

                default:
                    break;
            }
        } catch (AssertionError x) {
            reportItFAIL(url, Integer.toString(statusCode), x.getMessage(), "FAIL");
            Assert.fail(x.getMessage());
        } catch (Exception ex) {
            reportItFAIL(url, Integer.toString(statusCode), ex.getMessage(), "FAIL");
            Assert.fail(ex.getMessage());
        }
        return response;
    }

    /**
     * Function to get body content
     *
     * @param postBodyContent The Post Body Content
     * @param postBodyType    The Format of Post Body {@link SERVICEFORMAT}
     * @return The Post Body Content
     */

    private Object getPostBodyContent(String postBodyContent, SERVICEFORMAT postBodyType) {

        if (postBodyType.equals(SERVICEFORMAT.FILE)) {
            File file = new File(postBodyContent);
            return file;
        } else {
            return postBodyContent;
        }
    }

    /**
     * Function to get body content
     *
     * @param contentTypes The Format of Post Body {@link SERVICEFORMAT}
     * @return Content Types Content Types Binary,Json,xml,Text
     */

    private ContentType getPostContentType(SERVICEFORMAT contentTypes) {
        ContentType contentType = null;
        switch (contentTypes) {
            case FILE:
                contentType = ContentType.BINARY;
                break;
            case JSON:
                contentType = ContentType.JSON;
                break;
            case XML:
                contentType = ContentType.XML;
                break;
            case TEXT:
                contentType = ContentType.TEXT;
                break;
            default:
                break;
        }
        return contentType;
    }

    /**
     * Function is used to update the report
     *
     * @param uri           Endpoint uri
     * @param expectedValue The expected value
     * @param actualValue   The actual value
     * @param status        The Status (PASS)
     */

    private void reportItPASS(String uri, Object expectedValue, Object actualValue, String status) {
        expectedValue = ((Object) expectedValue).toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        actualValue = ((Object) actualValue).toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
       /* ExtentCucumberAdapter.addTestStepLog(
                "<style>table {border-collapse !important  }td {border: 1px solid black !important;  white-space: normal; !important;}</style>"
                        + "<table><tr><td><b>ENDPOINT</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>"
                        + uri + "</td></tr>" + "<tr><td><b>EXPECTED RESULT</b></td><td>" + expectedValue + "</td></tr>"
                        + "<tr><td><b>ACTUAL RESULT</b>&nbsp;&nbsp;&nbsp;&nbsp;</td><td>" + actualValue + "</td></tr>"
                        + "<tr><td><b>STATUS</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><font color=green>"
                        + status + "</font></td></tr></table>");*/
    }


    /**
     * Function is used to update the report
     *
     * @param uri           Endpoint uri
     * @param expectedValue The expected value
     * @param actualValue   The actual value
     * @param status        The Status (Fail)
     */

    private void reportItFAIL(String uri, Object expectedValue, Object actualValue, String status) {
        expectedValue = ((Object) expectedValue).toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        actualValue = ((Object) actualValue).toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        /*  ExtentCucumberAdapter.addTestStepLog(
                "<style>table {border-collapse !important  }td {border: 1px solid black !important;  white-space: normal; !important;}</style>"
                        + "<table><tr><td><b>ENDPOINT</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>"
                        + uri + "</td></tr>" + "<tr><td><b>EXPECTED RESULT</b></td><td>" + expectedValue + "</td></tr>"
                        + "<tr><td><b>ACTUAL RESULT</b>&nbsp;&nbsp;&nbsp;&nbsp;</td><td>" + actualValue + "</td></tr>"
                        + "<tr><td><b>STATUS</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><font color=red>"
                        + status + "</font></td></tr></table>"); */
    }

    /**
     * This Function is used to create json for registration
     *
     * @param strFirstName       First Name of the user
     * @param strLastName        Last Name of the user
     * @param strEmail           Email of the user
     * @param strPassword        Password of the user
     * @return Registration payload (Json)
     */

    public String registrationPayload(String strFirstName, String strLastName, String strEmail, String strPassword) {
        int random_int = (int) (Math.random() * (1000000 - 10 + 1) + 10);
        String[] userEmail = strEmail.split("@");
        String strRegEmail = userEmail[0] + random_int + userEmail[1];
        JsonObject objJSON = new JsonObject();
        objJSON.addProperty("firstName", strFirstName);
        objJSON.addProperty("lastName", strLastName);
        objJSON.addProperty("email", strRegEmail);
        objJSON.addProperty("password", strPassword);
        return objJSON.toString();
    }

}