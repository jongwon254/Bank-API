![bank](https://user-images.githubusercontent.com/36485235/185007346-f0b76a9a-5045-479c-baf0-203cfd8340d1.png)

# Decentralized Bank API
Own REST API to enable a decentralized [Banking Dashboard](https://github.com/jongwon254/Bank-Dashboard) for inspecting available banks.

## Technologies
- Languages: Kotlin, TypeScript, JavaScript, HTML, and CSS
- Backend: 
  - REST API built with Spring Boot and PostgreSQL
  - Test Driven Development with JUnit, Spring Boot Test, MockMVC, and Jackson
  - Deployed with Docker
- Frontend: 
  - Built with Angular and Bootstrap
  
## Functionality
- The Bank API provides two endpoints that can be utilized to retrieve, search, create, update, and delete banks
- Data is stored in a PostgreSQL database
- API Connection:
1. GET, POST, PATCH: BASE_URL/API/BANKS
    - PARAMETERS (POST, PATCH): ID, IP_ADDRESS, ACCOUNT_NUMBER, PORT, NODE_IDENTIFIER, VERSION, PROTOCOL, TRANSACTION_FEE, TRUST
    - RESPONSE: ID, IP_ADDRESS, ACCOUNT_NUMBER, PORT, NODE_IDENTIFIER, VERSION, PROTOCOL, TRANSACTION_FEE, TRUST
2. GET, DELETE: BASE_URL/API/BANKS/ID
    - PARAMETERS: ID
    - RESPONSE: ID, IP_ADDRESS, ACCOUNT_NUMBER, PORT, NODE_IDENTIFIER, VERSION, PROTOCOL, TRANSACTION_FEE, TRUST
    
## Screenshots
<img width="700" alt="docker" src="https://user-images.githubusercontent.com/36485235/185008138-ebe7ae52-286f-44aa-af9f-b4bd005a5644.png">


## More Info
[Visit Website](https://jongwonlee.dev/banks)
