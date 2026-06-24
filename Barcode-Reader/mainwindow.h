#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QMessageBox>
#include <QValidator>

#include "database.h"
#include "ScannerIndex.h"
#include "product.h"
#include "utilities.h"

QT_BEGIN_NAMESPACE
namespace Ui {
class MainWindow;
}
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

private slots:
    void handleBarcodeScan();
    void handleTabSwitch(int index);
    void handleAddItemClicked();

private:
    Ui::MainWindow *ui;
    Database* db;

    void setBarcodeFocus();
    void showBarcodeDetails(const QString& barcode) const;
    void connectSignals();
    bool validForm() const;
    void clearForm();

    void formatQLabel(QLabel*& label, const QString& style);

    int displayPopup(
        const char* title,
        const char* message,
        QFlags<QMessageBox::StandardButton> btns = (QMessageBox::Save | QMessageBox::Discard | QMessageBox::Cancel)
    );
};
#endif // MAINWINDOW_H
