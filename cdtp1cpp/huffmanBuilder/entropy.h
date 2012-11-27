#ifndef ENTROPY_H
#define ENTROPY_H

#include <string.h>
#include <map>
#include <cmath>
#include "huffmanTreeNode.h"
#include <cstdlib>

using std::map;
using std::make_pair;

class Entropy
{
public:
    Entropy(char * data);
    double getEntropy(void);
    char * getData(void);
    huffmanTreeNode * getTreeLeaves(void);
    double getTotalProbability(void);
    long getLeavesCount(void);
    ~Entropy();
private:
    void calculateProbability(void);
    char * data = 0;
    struct huffmanTreeNode * leaves = 0;
    long leavesCount = 0;
    double entropy = 0;
};

#endif // ENTROPY_H
