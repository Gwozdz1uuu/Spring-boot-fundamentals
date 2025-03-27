
CREATE TABLE IF NOT EXISTS store.users (
  id       BIGINT NOT NULL AUTO_INCREMENT,
  name     VARCHAR(255) NOT NULL,
  email    VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS store.addresses (
  id          BIGINT NOT NULL AUTO_INCREMENT,
  streetfood  VARCHAR(255) NOT NULL,
  city        VARCHAR(255) NOT NULL,
  zip         VARCHAR(255) NOT NULL,
  user_id     BIGINT NOT NULL,
  PRIMARY KEY (id),
  INDEX address_fk_idx (user_id ASC),
  CONSTRAINT address_fk
    FOREIGN KEY (user_id)
    REFERENCES store.users (id)

) ENGINE = InnoDB;
