Feature: US1006 Kullanici booking kaydi olusturur, siler ve silindigini dogrular

  Scenario: TC01 Kullanici yeni booking oluşturur, DELETE ile siler ve GET ile dogrular
    Given Kullanici "restfulBookerBaseUrl" base URL'ini kullanir.
    Then  Path parametre icin "booking" kullanir
    And POST request için "RestFullData.jSONDataOlustur()" metodu ile request body olusturur
    And restfulBooker server'a POST request gonderir ve response degerini kaydeder
    Then response status code degeri 200 olmali
    And response body'den bookingId alinir
    When Kullanici aynı bookingId için "DELETE" request gonderir
    Then response status code degeri 201 olmali
    When Kullanici aynı bookingId için "GET" request gonderir
    Then response status code degeri 404 olmali