# 🌐 ApiTestAutomationWithCucumber

Bu repository, **Java** dili kullanılarak geliştirilmiş, **REST API test otomasyonu** çalışmalarını içeren bir projedir.  
Proje, **Behavior Driven Development (BDD)** yaklaşımı doğrultusunda **Cucumber** framework’ü ile yapılandırılmıştır ve API testleri **Rest Assured** kütüphanesi kullanılarak gerçekleştirilmiştir.

Amaç; API endpoint’lerinin davranışlarını **okunabilir, sürdürülebilir ve otomatik** test senaryoları ile doğrulamaktır.

---

## 🎯 Projenin Amacı

- RESTful API’leri otomatik test etmek  
- İş birimi ve teknik ekipler için **okunabilir Gherkin senaryoları** oluşturmak  
- Cucumber ile **BDD tabanlı test yaklaşımı** uygulamak  
- Maven ile merkezi ve düzenli bir test altyapısı kurmak  
- API yanıtlarını status code, body ve header seviyesinde doğrulamak  

---

## 🛠️ Kullanılan Teknolojiler ve Araçlar

| Teknoloji | Açıklama |
|----------|---------|
| **Java** | Test geliştirme dili |
| **Cucumber (BDD)** | Gherkin tabanlı senaryo yazımı |
| **Rest Assured** | API istekleri ve response doğrulamaları |
| **Maven** | Proje ve bağımlılık yönetimi |
| **JUnit** | Test çalıştırma altyapısı |

---

## 📁 Proje Yapısı

```ApiTestAutomationWithCucumber/
├── .gitignore
├── pom.xml # Maven bağımlılık ve yapılandırma dosyası
├── configuration.properties # Ortam ve API konfigürasyonları
└── src
└── test
├── java
│ ├── runners # Cucumber test runner sınıfları
│ ├── stepdefinitions # Gherkin adımlarının Java karşılıkları
│ └── utilities # Ortak yardımcı sınıflar
└── resources
└── features # Gherkin (.feature) dosyaları
```
---

## :test_tube: Test Yaklaşımı (BDD)
Test senaryoları **Gherkin** dili kullanılarak yazılmıştır.
Bu sayede senaryolar hem teknik hem de teknik olmayan kişiler tarafından kolayca okunabilir.

## Örnek Feature Dosyası
```gherkin
Feature: User API Test
  Scenario: Get user by id
    Given API base url is defined
    When user sends GET request to "/users/1"
    Then response status code should be 200
    And response body should contain user data
```
---

## :mag: Kapsanan Test Senaryoları
-GET istekleri ile veri doğrulama
-Status code kontrolleri (200, 400, 404 vb.)
-Response body içeriği doğrulamaları
-Header kontrolleri
-Dinamik endpoint ve parametre kullanımı
-Ortak yapıların utility sınıfları ile yönetilmesi

## :gear: configuration.properties
Bu dosya, test ortamına ait yapılandırma bilgilerini içerir.
Örnek kullanım:
```
base.url=https://api.example.com
users.endpoint=/users

```
Bu yapı sayesinde ortam değişiklikleri koddan bağımsız yönetilebilir.

