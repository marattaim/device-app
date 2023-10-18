CREATE TABLE IF NOT EXISTS device_tbl (
    id BIGINT AUTO_INCREMENT NOT NULL,
    device_name VARCHAR(50),
    status VARCHAR(255),
    version INT,
    CONSTRAINT pk_device PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS order_tbl (
	id BIGINT AUTO_INCREMENT NOT NULL,
    device_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    order_time TIMESTAMP NOT NULL,
    CONSTRAINT pk_order PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_tbl (
    id BIGINT AUTO_INCREMENT NOT NULL,
    user_name VARCHAR(30),
    CONSTRAINT pk_user PRIMARY KEY (id)
);
