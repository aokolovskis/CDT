#ifndef HUFFMANTREEBUILDER_H
#define HUFFMANTREEBUILDER_H

#include "entropy.h"
#include <list>
#include <map>
#include <string.h>
#include <string>

using std::string;
using std::map;
using std::make_pair;

class HuffmanTreeBuilder
{
public:
    HuffmanTreeBuilder(Entropy * entropy);
    map<char,string> getEncodeMap(void);
    ~HuffmanTreeBuilder();

private:
    void buildTree(void);
    long leavesCount = 0;
    void sortNodes();
    struct huffmanTreeNode * leaves = 0;
    struct huffmanTreeNode * theTree = 0;
    map<char,string> * encodeMap;
    void buildEncodeMap(huffmanTreeNode* tree, string value);
};

#endif // HUFFMANTREEBUILDER_H
