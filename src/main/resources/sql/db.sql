CREATE TABLE crypto_transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    symbol VARCHAR(50),
    order_type VARCHAR(10),
    quantity DECIMAL(18, 8),
    price DECIMAL(18, 8),
    total DECIMAL(18, 8),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE crypto_price (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    symbol VARCHAR(50),
    bid_price DECIMAL(18, 8),
    ask_price DECIMAL(18, 8),
    latestUpdate BIGINT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE crypto_wallet (
    user_id BIGINT PRIMARY KEY,
    usdt_balance DECIMAL(18, 8),
    eth_balance DECIMAL(18, 8),
    btc_balance DECIMAL(18, 8),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE crypto_user (
    id BIGINT PRIMARY KEY,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);