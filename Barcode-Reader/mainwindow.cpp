#include "mainwindow.h"
#include "ScannerIndex.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    this->resize(640, 480);

    this->ui->barcodeInput->setFocusPolicy(Qt::StrongFocus);
    this->setBarcodeFocus();
    this->connectSignals();

    this->db = &Database::getInstance();
    this->db->init();
}


void MainWindow::setBarcodeFocus() { this->ui->barcodeInput->setFocus(); }


void MainWindow::connectSignals() {
    connect(
        this->ui->barcodeInput,
        &QLineEdit::returnPressed,
        this,
        &MainWindow::handleBarcodeScan
    );

    connect(
        this->ui->tabWidget,
        &QTabWidget::currentChanged,
        this,
        &MainWindow::handleTabSwitch
    );

    connect(
        this->ui->add_item_btn,
        &QPushButton::clicked,
        this,
        &MainWindow::handleAddItemClicked
    );

    this->ui->add_price_input->setValidator(new QDoubleValidator(0, 200, 2, this));
}


void MainWindow::handleBarcodeScan() {
    QString barcode = this->ui->barcodeInput->text();
    this->ui->barcodeInput->clear();

    const bool containsBarcode = this->db->contains(barcode);
    const int index = containsBarcode ? static_cast<int>(ScannerIndex::DETAILS) : static_cast<int>(ScannerIndex::ADD);
    this->ui->tabWidget->setCurrentIndex(index);

    if(containsBarcode) {
        this->showBarcodeDetails(barcode);
    } else {
        this->displayPopup("Item Not In Database", "Feel free to add the item now", (QMessageBox::Ok));
        this->ui->add_barcode_input->setText(barcode);
        this->ui->add_barcode_input->setFocus(Qt::FocusReason::NoFocusReason);
        this->setBarcodeFocus();
    }
}


void MainWindow::showBarcodeDetails(const QString& barcode) const {
    Product relatedProduct = this->db->getItem(barcode);

    QString formattedBarcode = Utilities::BasicFormat(relatedProduct.barcode, 16, 400);
    Utilities::FormatQLabel(this->ui->item_barcode, formattedBarcode);

    QString formattedItemName = Utilities::BasicFormat(relatedProduct.item_name, 20, 400);
    Utilities::FormatQLabel(this->ui->item_name, formattedItemName);


    const QString price = QString::number(relatedProduct.price, 'f', 2);
    const QString formattedPrice = Utilities::MultiTextFormat({
        {price, 100, 700},
        {"each", 11, 700}
    });
    Utilities::FormatQLabel(this->ui->item_price, formattedPrice);
}


void MainWindow::handleTabSwitch(int index) {
    this->ui->barcodeInput->setFocusPolicy(Qt::StrongFocus); this->setBarcodeFocus();
}


bool MainWindow::validForm() const {
    bool ok;
    this->ui->add_price_input->text().toDouble(&ok);

    return !this->ui->add_barcode_input->text().isEmpty()
           && ok
           && !this->ui->add_item_name_input->text().isEmpty();
}


void MainWindow::clearForm() {
    this->ui->add_barcode_input->clear(),
    this->ui->add_item_name_input->clear(),
    this->ui->add_price_input->clear();
}


void MainWindow::handleAddItemClicked() {
    if(!this->validForm()) {

        this->displayPopup(
            "Invalid Input",
            "Either the barcode, name are empty or the price is invalid formatting",
            (QMessageBox::Ok)
        );

        this->setBarcodeFocus();
        return;
    }

    int ret = this->displayPopup("are you sure you want to ", "Add Item");
    if(ret == QMessageBox::Save) {
        const Product product{
            this->ui->add_barcode_input->text(),
            this->ui->add_item_name_input->text(),
            this->ui->add_price_input->text().toDouble()
        };

        int ok = this->db->addProduct(product);
        if(ok) {
            this->displayPopup("Successfully Added Item!", "", QMessageBox::Ok);
            this->clearForm();
        } else {
            this->displayPopup("Failed to Add Item!", "Item may already exist...", QMessageBox::Close);
        }

    } else if (ret != QMessageBox::Cancel) {
        const int index = static_cast<int>(ScannerIndex::SCAN);
        this->ui->tabWidget->setCurrentIndex(index);
    }

    this->setBarcodeFocus();
}


int MainWindow::displayPopup(const char* title, const char* message, QFlags<QMessageBox::StandardButton> btns) {
    QMessageBox msgBox;

    msgBox.setText(message);
    msgBox.setInformativeText(title);
    msgBox.setStandardButtons(btns);
    int ret = msgBox.exec();

    return ret;
}


MainWindow::~MainWindow()
{
    delete ui;
}
