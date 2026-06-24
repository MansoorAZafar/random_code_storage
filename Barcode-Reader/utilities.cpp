#include "utilities.h"

void Utilities::FormatQLabel(QLabel*& label, const QString& style) {
    label->setTextFormat(Qt::RichText);
    label->setText(style);
}

QString Utilities::BasicFormat(const QString& item, const int& fontSize, const int& fontWeight) {
    FormatStyler fs;
    QString res = fs.pTag()
        ->span(fontSize, fontWeight)
        ->text(item)
        ->get();

    return res;
}

Utilities::FormatStyler* Utilities::FormatStyler::pTag() {
    this->start += "<p align=\"center\">";
    this->end += "</p>";

    return this;
}

Utilities::FormatStyler* Utilities::FormatStyler::span(const int& fontSize, const int& fontWeight) {
    this->start += QString("<span style=\"font-size: %1pt;").arg(fontSize);
    if(fontWeight != -1) {
        this->start += QString("font-weight: %2pt;\">").arg(fontWeight);
    }

    this->end = "</span>" + end;
    return this;
}


Utilities::FormatStyler* Utilities::FormatStyler::endSpan(const int& fontSize, const int& fontWeight, const QString& text) {
    this->start += QString("<span style=\"font-size: %1pt;").arg(fontSize);
    if(fontWeight != -1) {
        this->start += QString("font-weight: %2pt;\">").arg(fontWeight);
    }
    this->start += text + "</span>";

    return this;
}


Utilities::FormatStyler* Utilities::FormatStyler::text(const QString& text) {
    this->start += text;
    return this;
}

QString Utilities::MultiTextFormat(const std::vector<Format> items) {
    FormatStyler fs;
    FormatStyler tmp;

    for(const Format& item : items) {
        tmp.endSpan(item.fontSize, item.fontWeight, item.text);
    }

    fs.pTag();
    fs.Combine(tmp);
    return fs.get();
}


void Utilities::FormatStyler::Combine(FormatStyler& other) {
    this->start = this->start + other.start;
    this->end = other.end + this->end;
}


QString Utilities::FormatStyler::get() {
    return this->start + this-> end;
}
