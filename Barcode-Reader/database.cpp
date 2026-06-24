#include "database.h"

Database::Database() {}

Database& Database::getInstance() {
    static Database db;
    return db;
}

void Database::init() {
    const QString dbPath = QCoreApplication::applicationDirPath() + "/../databases/" + Database::DB_NAME;

    if(!QFile::exists(dbPath)) {
        qDebug() << "DB file doesn't exist";
        return;
    }

    this->db = QSqlDatabase::addDatabase("QSQLITE");
    this->db.setDatabaseName(dbPath);
    qDebug() << QCoreApplication::applicationDirPath();

    if(this->db.open()) {
        qDebug() << "DB is open";
    } else {
        qDebug() << "DB is not connected" << "\n" << "Error: " << this->db.lastError();
    }
}

bool Database::contains(const QString& barcode) {
    QSqlQuery query;
    const QString statement = QString("SELECT 1 FROM %1 WHERE barcode = :barcode").arg(this->TABLE_NAME);

    query.prepare(statement);
    query.bindValue(":barcode", barcode);

    if(!query.exec()) {
        qDebug() << "Query Failed: " << query.lastError().text();
        return false;
    }

    return query.next();
}

Product Database::getItem(const QString& barcode) {
    if(this->cache.contains(barcode)) {
        return this->cache.get(barcode);
    }

    Product product;

    QSqlQuery query;
    const QString statement = QString("SELECT * FROM %1 WHERE barcode = :barcode").arg(this->TABLE_NAME);

    query.prepare(statement);
    query.bindValue(":barcode", barcode);

    if(!query.exec()) {
        qDebug() << "Query Failed: " << query.lastError().text();
        return product;
    }

    query.next();

    product.barcode = query.value(static_cast<int>(ProductTableIndex::BARCODE)).toString();
    product.item_name = query.value(static_cast<int>(ProductTableIndex::ITEM_NAME)).toString();
    product.price = query.value(static_cast<int>(ProductTableIndex::PRICE)).toDouble();

    this->cache.add(std::make_pair(barcode, product));
    return product;
}

int Database::addProduct(const Product& product) {
    QSqlQuery query;
    const QString statement =
        QString("INSERT INTO %1(barcode, item_name, price) VALUES(:barcode, :item_name, :price)").arg(this->TABLE_NAME);

    query.prepare(statement);
    query.bindValue(":barcode", product.barcode);
    query.bindValue(":item_name", product.item_name);
    query.bindValue(":price", product.price);

    if(!query.exec()) {
        qDebug() << "Query Failed: " << query.lastError().text();
        return 0;
    }

    return 1;
}


Database::~Database() {
    this->db.close();
}
