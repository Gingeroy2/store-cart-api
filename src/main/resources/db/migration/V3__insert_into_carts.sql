insert into carts (id, date_created)
values (uuid_to_bin(uuid()), curdate());
