#ifndef UTILITIES_H
#define UTILITIES_H

#include <QLabel>
#include <vector>

class Utilities
{
private:

public:

    struct Format {
        QString text;
        int fontSize;
        int fontWeight;
    };

    class FormatStyler {
    private:
        QString start;
        QString end;
    public:
        FormatStyler* span(const int& fontSize, const int& fontWeight);
        FormatStyler* endSpan(const int& fontSize, const int& fontWeight, const QString& text);
        FormatStyler* pTag();
        FormatStyler* text(const QString& text);
        void Combine(FormatStyler& other);
        QString get();
    };

    static void FormatQLabel(QLabel*& label, const QString& style);
    static QString BasicFormat(const QString& item, const int& fontSize = 16, const int& fontWeight = -1);
    static QString MultiTextFormat(const std::vector<Format> items);

};

#endif // UTILITIES_H
