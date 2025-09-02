package stepdefinitions;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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
}