alter table products
    add `description` TEXT NULL;

alter table products
    modify `description` TEXT not NULL;