package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;


public class JsonPlaceHolderStepdefinitions {


    String endpoint;
    Response response;
    JsonPath responseJP;
    JSONObject request;

    @Given("Kullanici {string} base URL'ini kullanir")
    public void kullanici_base_url_ini_kullanir(String string) {

        //Kullanici "jPHBaseUrl" base URL'ini kullanir
        endpoint = ConfigReader.getProperty("jPHBaseUrl"); //https://jsonplaceholder.typicode.com

    }
    @Then("Path parametreleri icin {string} kullanir")
    public void path_parametreleriicin_kullanir(String pathparams) {
        //Then  Path parametreleriicin "posts/44" kullanir
        endpoint=endpoint+"/"+pathparams;


    }
    @Then("jPH  server a GET request gonderir ve testleri yapmak icin response degerini kaydeder")
    public void j_ph_server_a_get_request_gonderir_ve_testleri_yapmak_icin_response_degerini_kaydeder() {
        //And  jPH  server a GET request gonderir ve testleri yapmak icin response degerini kaydeder

        response=given().when().get(endpoint);
        response.prettyPrint();

    }

    @Then("jPH respons'da status degerinin {int}")
    public void j_ph_respons_da_status_degerinin(Integer statusCode) {
        //Then jPH respons'da status degerinin 200

        Assert.assertEquals(statusCode,(Integer)response.statusCode());


    }
    @Then("jPH respons'da content type degerinin {string}")
    public void j_ph_respons_da_content_type_degerinin(String contentType) {
        //    And jPH respons'da content type degerinin "application/json; charset=utf-8"

        Assert.assertEquals(contentType,response.contentType());


    }
    @Then("jPH GET respons body'sinde {string} degerinin Integer {int}")
    public void j_ph_get_respons_body_sinde_degerinin_ınteger(String attiribute, Integer expectedValue) {
        //    Then jPH GET respons body'sinde "userId" degerinin Integer 5

        responseJP=response.jsonPath();
        Assert.assertEquals(expectedValue,(Integer)responseJP.getInt(attiribute));



    }
    @Then("jPH GET respons body'sinde {string} degerinin String {string}")
    public void j_ph_get_respons_body_sinde_degerinin_string(String attiribute, String expectedValue) {
        //    And jPH GET respons body'sinde "title" degerinin String "optio dolor molestias sit"

        responseJP=response.jsonPath();
        Assert.assertEquals(expectedValue,responseJP.getString(attiribute));

    }


    @Then("POST request icin {string},{string},{int} {int} bilgileri ile request body olusturur")
    public void post_request_icin_bilgileri_ile_request_body_olusturur(String title, String body, Integer userId, Integer id) {
        //And POST request icin "Ahmet","Merhaba",10 70 bilgileri ile request body olusturur

        request =new JSONObject();
        request.put("title","Ahmet");
        request.put("body","Merhaba");
        request.put("userId",10);
        request.put("id",70);

    }
    @Then("jPH server a POST request gonderir ve testleri yapmak icin response degerini kaydeder")
    public void j_ph_server_a_post_request_gonderir_ve_testleri_yapmak_icin_response_degerini_kaydeder() {
        //And jPH server a POST request gonderir ve testleri yapmak icin response degerini kaydeder
        response =given().contentType(ContentType.JSON)
                .when().body(request.toString())
                .post(endpoint);

        // code postla  fail oluyor put ile  yazınca response pass oluyor
        response.prettyPrint();
    }
    @Then("jPH respons daki {string} header degerinin {string}")
    public void j_ph_respons_daki_header_degerinin(String headerAttiribute, String attiributeValue) {

        //And jPH respons daki "Connection" header degerinin "keep-alive"

        Assert.assertEquals(attiributeValue,response.header(headerAttiribute));

    }
    @Then("response attributelerindeki degerlerinin {string},{string},{int} {int}")
    public void response_attributelerindeki_degerlerinin(String title, String body, Integer userId, Integer id) {

        //Then response attributelerindeki degerlerinin "Ahmet","Merhaba",10 70

        responseJP=response.jsonPath();

        Assert.assertEquals(title,responseJP.getString("title"));
        Assert.assertEquals(title,responseJP.getString("body"));
        Assert.assertEquals(title,(Integer)responseJP.getInt("userId"));
        Assert.assertEquals(title,(Integer)responseJP.getInt("id"));
    }

}