package stepdefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import testData.RestFullData;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestfulBookerStepdefinitions {
    String endpoint;
    Response response;
    JSONObject reqBody;
    int bookingId;
    String exceptionMesaj;

    // ---------------- COMMON STEPS ----------------
    @Given("Kullanici {string} base URL'ini kullanir.")
    public void kullanici_base_url_ini_kullanir(String string) {
        endpoint = ConfigReader.getProperty("restfulBookerBaseUrl");
    }

    @Then("Path parametre icin {string} kullanir")
    public void path_parametreleri_icin_kullanir(String pathparams) {
        endpoint = endpoint + "/" + pathparams;
    }

    // ---------------- POST REQUEST ----------------
    // Sabit test datasi (US1004 icin)
    @And("POST request için {string} metodu ile request body olusturur")
    public void postRequestIcinMetoduIleRequestBodyOlusturur(String methodName) {
        reqBody = RestFullData.jSONDataOlustur();
    }

    // Request gönderme
    @And("restfulBooker server'a POST request gonderir ve response degerini kaydeder")
    public void restfulbookerServerAPOSTRequestGonderirVeResponseDegeriniKaydeder() {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .post(endpoint);
        response.prettyPrint();
    }

    // ---------------- RESPONSE CHECKS ----------------
    @Then("response status code degeri {int} olmali")
    public void responseStatusCodeDegeriOlmali(Integer statusCode) {
        Assert.assertEquals(statusCode, (Integer) response.statusCode());
    }

    // US1004 → Request Body ile ayni mi?
    @And("response body booking bilgileri request body ile ayni olmali")
    public void responseBodyBookingBilgileriRequestBodyIleAyniOlmali() {
        response.then()
                .body("booking.firstname", equalTo(reqBody.getString("firstname")))
                .body("booking.lastname", equalTo(reqBody.getString("lastname")))
                .body("booking.totalprice", equalTo(reqBody.getInt("totalprice")))
                .body("booking.depositpaid", equalTo(reqBody.getBoolean("depositpaid")))
                .body("booking.bookingdates.checkin", equalTo(reqBody.getJSONObject("bookingdates").getString("checkin")))
                .body("booking.bookingdates.checkout", equalTo(reqBody.getJSONObject("bookingdates").getString("checkout")))
                .body("booking.additionalneeds", equalTo(reqBody.getString("additionalneeds")));
    }
    // Dinamik test datası (US1005 için)
    @And("POST request icin {string}, {string}, {int}, {word}, {string}, {string}, {string} bilgileriyle request body olusturur")
    public void postRequestIcinBilgilerleRequestBodyOlusturur(String firstname, String lastname,
                                                              Integer totalprice, String depositpaid,
                                                              String checkin, String checkout,
                                                              String additionalneeds) {
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", checkin);
        bookingdates.put("checkout", checkout);
        reqBody = new JSONObject();
        reqBody.put("firstname", firstname);
        reqBody.put("lastname", lastname);
        reqBody.put("totalprice", totalprice);
        reqBody.put("depositpaid", Boolean.parseBoolean(depositpaid));
        reqBody.put("bookingdates", bookingdates);
        reqBody.put("additionalneeds", additionalneeds);
    }

    // US1005 → Parametreye göre tek tek assert
    // Sadece true|false kabul eden özel tip
    @ParameterType("true|false")
    public boolean bool(String s) {
        return Boolean.parseBoolean(s);
    }

    @And("response body {string} {string} olmali")
    public void responseBodyStringOlmali(String key, String value) {
        if (key.equals("checkin") || key.equals("checkout")) {
            response.then().body("booking.bookingdates." + key, equalTo(value));
        } else {
            response.then().body("booking." + key, equalTo(value));
        }
    }

    @And("response body {string} {int} olmali")
    public void responseBodyIntOlmali(String key, int value) {
        response.then().body("booking." + key, equalTo(value));
    }

    @And("response body {string} {bool} olmali")
    public void responseBodyBooleanOlmali(String key, boolean value) {
        response.then().body("booking." + key, equalTo(value));
    }

    // ---------------- US1006 NEW STEPS ----------------
    @And("response body'den bookingId alinir")
    public void responseBodydenBookingIdAlinir() {
        bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("Alinan bookingId: " + bookingId);
    }

    @When("Kullanici aynı bookingId için {string} request gonderir")
    public void kullaniciAyniBookingIdIcinRequestGonderir(String method) {
        String targetEndpoint = endpoint + "/" + bookingId;
        exceptionMesaj = null;
        try {
            switch (method.toUpperCase()) {
                case "DELETE":
                    response = given()
                            .contentType(ContentType.JSON)
                            .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                            .header("Cookie", "token=abc123")
                            .when()
                            .delete(targetEndpoint);
                    break;
                case "GET":
                    try {
                        response = given().when().get(targetEndpoint);
                    } catch (Exception e) {
                        exceptionMesaj = e.getMessage();
                    }
                    System.out.println("exceptionMesaj : " + exceptionMesaj);
                    Assert.assertEquals(ConfigReader.getProperty("notFoundExceptionMessage"), exceptionMesaj);
                    break;
                default:
                    throw new IllegalArgumentException("Desteklenmeyen method: " + method);
            }
            if (response != null) {
                System.out.println("Response for " + method + " " + targetEndpoint + ":");
                response.prettyPrint();
            }
        } catch (Exception e) {
            System.err.println("Hata: " + e.getMessage());
            throw e;
        }
    }
}