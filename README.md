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
* GET istekleri ile veri doğrulama
* Status code kontrolleri (200, 400, 404 vb.)
* Response body içeriği doğrulamaları
* Header kontrolleri
* Dinamik endpoint ve parametre kullanımı
* Ortak yapıların utility sınıfları ile yönetilmesi

## :gear: configuration.properties
Bu dosya, test ortamına ait yapılandırma bilgilerini içerir.
Örnek kullanım:
```
base.url=https://api.example.com
users.endpoint=/users

```
Bu yapı sayesinde ortam değişiklikleri koddan bağımsız yönetilebilir.

 ## :rocket: Projeyi Çalıştırma
 ### Ön Gereksinimler
* Java JDK 8 veya üzeri
* Maven
* Git
* IntelliJ IDEA veya Eclipse
 
 ### Kurulum ve Çalıştırma
 Repository'i klonlayın:
 ```
 git clone https://github.com/kilicCeren/ApiTestAutomationWithCucumber.git
 cd ApiTestAutomationWithCucumber
 ```
 Testleri çalıştırın:
 ```
 mvn clean test
 ```
 Belirli tag'e sahip testleri çalıştırmak için:
 ```
 mvn clean test -Dcucumber.filter.tags="@smoke"
 ```
 ---
 
 ### :bar_chart: Test Sonuçları ve Raporlama
 
 Testler Maven üzerinden çalıştırıldığında, Cucumber'ın varsayılan raporları target/ klasörü altında üretilir.
 İleri seviye raporlama için Extent veya Allure entegrasyonu yapılabilir.
 * Projenin Sağladıkları
 * Okunabilir ve sürdürülebilir BDD senaryoları
 * API testleri için merkezi yapı
 * Kolay çalıştırılabilir Maven altyapısı
 * Konfigürasyon dosyası ile esnek ortam yönetimi
 * Geliştirilebilir ve genişletilebilir framework yapısı
   
 ## :pushpin: Notlar
 Bu proje yalnızca API test otomasyonu içermektedir.
 UI testleri kapsam dışındadır.
 Geliştirilmeye ve yeni senaryolar eklenmeye uygundur.
 
 ## :memo: Lisans
 Bu proje kişisel eğitim ve portföy amaçlıdır

 ---
 
 # 🌐 ApiTestAutomationWithCucumber

This repository is a project developed using the **Java** programming language and contains **REST API test automation** implementations.  
The project is structured according to the **Behavior Driven Development (BDD)** approach using the **Cucumber** framework, and API tests are performed with the **Rest Assured** library.

The goal is to validate API endpoint behaviors through **readable, maintainable, and automated** test scenarios.

---

## 🎯 Project Purpose

- To automate RESTful API testing  
- To create **readable Gherkin scenarios** for both business and technical teams  
- To apply a **BDD-based testing approach** with Cucumber  
- To build a centralized and organized test infrastructure using Maven  
- To validate API responses at the status code, body, and header levels  

---

## 🛠️ Technologies and Tools Used

| Technology | Description |
|----------|-------------|
| **Java** | Test development language |
| **Cucumber (BDD)** | Gherkin-based scenario writing |
| **Rest Assured** | API requests and response validations |
| **Maven** | Project and dependency management |
| **JUnit** | Test execution infrastructure |

---

## 📁 Project Structure

```ApiTestAutomationWithCucumber/
├── .gitignore
├── pom.xml # Maven dependency and configuration file
├── configuration.properties # Environment and API configurations
└── src
└── test
├── java
│ ├── runners # Cucumber test runner classes
│ ├── stepdefinitions # Java implementations of Gherkin steps
│ └── utilities # Common utility classes
└── resources
└── features # Gherkin (.feature) files
```

