#ifndef HUFFMANENCODER_H
#define HUFFMANENCODER_H

#include <map>
#include <string.h>
#include <sstream>
using std::stringstream;
using std::string;
using std::map;

class HuffmanEncoder
{
public:
    HuffmanEncoder(map<char,string> * mapping);
    string encodeText(char * data);
    string encodeText(string data);

private:
    map<char,string> * encMap= 0;
};

#endif // HUFFMANENCODER_H
