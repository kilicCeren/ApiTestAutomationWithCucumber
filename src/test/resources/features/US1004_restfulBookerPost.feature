Feature: US1004 Kullanici Post request sonucu donen response'u test eder

  Scenario: TC01 Kullanici Post request sonrası response degerlerini dogrular
    Given Kullanici "restfulBookerBaseUrl" base URL'ini kullanir.
    Then  Path parametre icin "booking" kullanir
    And POST request için "RestFullData.jSONDataOlustur()" metodu ile request body olusturur
    And restfulBooker server'a POST request gonderir ve response degerini kaydeder
    Then response status code degeri 200 olmali
    And response body booking bilgileri request body ile ayni olmali
