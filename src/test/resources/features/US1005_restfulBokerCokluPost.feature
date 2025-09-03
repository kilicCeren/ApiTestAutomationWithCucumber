Feature: US1005 Kullanici farkli verilerle coklu POST request gonderir


  Scenario Outline: TC01 Kullanici farkli verilerle POST request sonrasi response dogrular
    Given Kullanici "restfulBookerBaseUrl" base URL'ini kullanir.
    Then  Path parametre icin "booking" kullanir
    And POST request icin "<firstname>", "<lastname>", <totalprice>, <depositpaid>, "<checkin>", "<checkout>", "<additionalneeds>" bilgileriyle request body olusturur
    And restfulBooker server'a POST request gonderir ve response degerini kaydeder
    Then response status code degeri 200 olmali
    And response body "firstname" "<firstname>" olmali
    And response body "lastname" "<lastname>" olmali
    And response body "totalprice" <totalprice> olmali
    And response body "depositpaid" <depositpaid> olmali
    And response body "checkin" "<checkin>" olmali
    And response body "checkout" "<checkout>" olmali
    And response body "additionalneeds" "<additionalneeds>" olmali

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Ceren     | Kilic    | 150        | true        | 2026-10-01 | 2026-10-05 | Kahvalti        |
      | Amelie    | Poulain  | 200        | false       | 2026-11-10 | 2026-11-15 | Aksam yemegi    |
