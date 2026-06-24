## Requirement To Run
- You need a sqlite3 database file called inventory.db and the table be called products
  - The database has to be in this folder ../databases/inventory.db
    - back 1 folder, inside a new folder called databases

- To make a sqlite3 database do this:
```bash
cd ../databases/
sqlite3 inventory.db
create table products(barcode text primary key, item_name text, price real);
```

### Database Properties
| Name          | Type  |
| ------------- | ----- |
| Barcode       | text  |
| item_name     | text  |
| price         | real  |
