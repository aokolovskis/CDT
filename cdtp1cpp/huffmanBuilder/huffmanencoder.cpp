#include "huffmanencoder.h"

HuffmanEncoder::HuffmanEncoder(map<char, string> *mapping)
{
    this->encMap = mapping;
}

string HuffmanEncoder::encodeText(char * data){
    stringstream str;
    for(long i = 0;i<strlen(data);i++){
        str << (*this->encMap)[data[i]];
    }
    return str.str();
}

string HuffmanEncoder::encodeText(string data){
    char * sdata = const_cast<char*> (data.c_str());
    string result = this->encodeText(sdata);
    return result;
}
