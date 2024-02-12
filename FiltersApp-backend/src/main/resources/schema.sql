CREATE TABLE filter (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL UNIQUE,
                        createdAt TIMESTAMP NOT NULL
);

/*CREATE TABLE criteria (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          type VARCHAR(50) NOT NULL,
                          condition VARCHAR(255) NOT NULL,
                          value VARCHAR(255),
                          filter_id BIGINT,
                          FOREIGN KEY (filter_id) REFERENCES filter (id) ON DELETE CASCADE
);
*/
