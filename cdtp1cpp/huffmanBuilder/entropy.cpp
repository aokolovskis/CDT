#include "entropy.h"

Entropy::Entropy(char * data)
{
    long i = strlen(data);
    this->data = new char[i];
    strcpy(this->data,data);
    this->calculateProbability();
}

double Entropy::getTotalProbability(){
    double totalProb = 0;

    for(int i=0;i<this->leavesCount;i++){
        totalProb+=this->leaves[i].nodeWeight;
    }
    return totalProb;
}

void Entropy::calculateProbability(){

    long i = strlen(this->data);
    map<char, long> ccmap;
    for(long j=0;j<i;j++){
        if(ccmap.find(this->data[j]) != ccmap.end()){
            ccmap[this->data[j]]+=1;
        } else {
            ccmap.insert(make_pair(this->data[j],1));
        }
    }

    this->leaves = new huffmanTreeNode[ccmap.size()];

    long j = 0;
    double weight = 0;
    for( map<char,long>::const_iterator it = ccmap.begin(); it != ccmap.end(); ++it )
       {
        weight = ((long)it->second)/(double)i;
        this->entropy += weight * (-1.d * (log2(weight)));
        this->leaves[j].left = 0;
        this->leaves[j].right = 0;
        this->leaves[j].nodeValue = it->first;
        this->leaves[j].nodeWeight = weight;
        j++;
       }
    this->leavesCount = ccmap.size();

}

double Entropy::getEntropy(){
    return this->entropy;
}

long Entropy::getLeavesCount(){
    return this->leavesCount;
}

huffmanTreeNode * Entropy::getTreeLeaves(){
    struct huffmanTreeNode * leaves = new huffmanTreeNode[this->leavesCount];
    for(long j = 0;j<this->leavesCount;j++){
        leaves[j].left = this->leaves[j].left;
        leaves[j].right = this->leaves[j].right;
        leaves[j].nodeValue = this->leaves[j].nodeValue;
        leaves[j].nodeWeight = this->leaves[j].nodeWeight;
    }
    return leaves;
}

char * Entropy::getData(){
    long i = strlen(data);
    char * data = new char[i];
    strcpy(data, this->data);
    return data;
}

Entropy::~Entropy(){
    delete[] this->data;
    delete[] this->leaves;
}
