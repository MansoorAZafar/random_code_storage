#ifndef DATABASE_H
#define DATABASE_H

#include <QCoreApplication>
#include <QSqlError>
#include <QSqlDatabase>
#include <QFile>
#include <QString>
#include <QSqlQuery>

#include "cache.h"
#include "product.h"

class Database
{
private:
    QSqlDatabase db;
    Cache<QString, Product> cache;

    static constexpr const char* DB_NAME = "inventory.db";
    static constexpr const char* TABLE_NAME = "products";

    Database();

public:
    static Database& getInstance();
    void init();
    bool contains(const QString& barcode);
    int addProduct(const Product& product);
    Product getItem(const QString& barcode);

    ~Database();

};

#endif // DATABASE_H
