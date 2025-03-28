ALTER TABLE store.users
DROP COLUMN state;

ALTER TABLE store.addresses
ADD COLUMN state varchar(255) NOT NULL;