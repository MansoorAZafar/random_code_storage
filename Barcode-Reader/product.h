#ifndef PRODUCT_H
#define PRODUCT_H

#include <QString>

/*
 * Barcode      TEXT PRIMARY KEY,
 * item_name    TEXT,
 * price        REAL,
 */

struct Product {
    QString barcode;
    QString item_name;
    double price;
};

enum class ProductTableIndex {
    BARCODE = 0,
    ITEM_NAME = 1,
    PRICE = 2,
};

#endif // PRODUCT_H
