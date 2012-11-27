#ifndef HUFFMANDECODER_H
#define HUFFMANDECODER_H
#include "huffmanTreeNode.h"
#include <string.h>
#include <sstream>
#include <map>
using std::map;
using std::stringstream;
using std::string;

class HuffmanDecoder
{
public:
    HuffmanDecoder(map<char,string> * encodeMap);
    string decodeText(char * data);
    string decodeText(string data);
    ~HuffmanDecoder();
private:
    map<string,char> * decodeMap = 0;
    int shortestWord = 0;
    int longestWord = 0;
};

#endif // HUFFMANDECODER_H
