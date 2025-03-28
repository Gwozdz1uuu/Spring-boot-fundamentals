CREATE TABLE IF NOT EXISTS store.tags
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS store.user_tags
(
    user_id BIGINT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (user_id,tag_id),
    CONSTRAINT user_id_fk
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT tag_id_fk
    FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
);