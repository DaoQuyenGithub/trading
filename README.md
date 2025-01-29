# Crypto Trading System

## Description
This project is a cryptocurrency trading system built using the Spring Boot framework and an in-memory H2 database. It allows users to buy and sell supported crypto trading pairs, view trading transactions, and check wallet balances.

## Functional Scope
1. Users can buy/sell supported crypto trading pairs.
2. Users can view their trading transaction history.
3. Users can check their cryptocurrency wallet balance.

## Assumptions
1. Users are already authenticated and authorized to access the APIs.
2. Users have an initial wallet balance of **50,000 USDT** stored in the database.
3. The system only supports **Ethereum (ETHUSDT)** and **Bitcoin (BTCUSDT)** trading pairs.

## Tasks
### 1. Price Aggregation
- Retrieve pricing from the following sources at **10-second intervals**:
    - **Binance**: [https://api.binance.com/api/v3/ticker/bookTicker](https://api.binance.com/api/v3/ticker/bookTicker)
    - **Huobi**: [https://api.huobi.pro/market/tickers](https://api.huobi.pro/market/tickers)
- Store the best aggregated price in the database.
- **Bid Price** is used for **SELL** orders, and **Ask Price** is used for **BUY** orders.

### 2. API Endpoints
#### Retrieve Latest Best Aggregated Price
- API to get the latest best price based on aggregated data.

#### Trading API
- API to allow users to trade based on the latest best aggregated price.

#### Wallet Balance API
- API to retrieve the user's cryptocurrency wallet balance.

#### Trading History API
- API to retrieve the user's trading history.

## Technologies Used
- **Java 21**
- **Spring Boot**
- **H2 Database**
- **Spring Scheduler**
- **Spring Data JPA** 
- **RESTful APIs** 

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/DaoQuyenGithub/trading.git
   cd trading
   ```
2. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
3. Access H2 database console:
    - URL: `http://localhost:9999/api/v1/h2-console`
    - JDBC URL: `jdbc:h2:mem:develop`
    - Username: `sa`
    - Password: (leave blank)

