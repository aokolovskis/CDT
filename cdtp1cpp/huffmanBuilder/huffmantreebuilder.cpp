#include "huffmantreebuilder.h"

HuffmanTreeBuilder::HuffmanTreeBuilder(Entropy * entropy)
{
    //get the leaves
    this->leaves = entropy->getTreeLeaves();
    this->leavesCount = entropy->getLeavesCount();
    this->sortNodes();
    this->buildTree();
    this->encodeMap = new map<char,string>();
    this->buildEncodeMap(this->theTree,"");

}


void HuffmanTreeBuilder::buildTree(){
    long leavesCount = this->leavesCount;
    huffmanTreeNode * currentNode,tmpNode;
    while(this->leavesCount != 1){
        currentNode = new huffmanTreeNode();
        currentNode->nodeWeight = this->leaves[0].nodeWeight+this->leaves[1].nodeWeight;
        currentNode->left = new huffmanTreeNode();
        currentNode->left->nodeValue = this->leaves[0].nodeValue;
        currentNode->left->nodeWeight = this->leaves[0].nodeWeight;
        currentNode->left->left = this->leaves[0].left;
        currentNode->left->right = this->leaves[0].right;
        currentNode->right = new huffmanTreeNode();
        currentNode->right->nodeValue = this->leaves[1].nodeValue;
        currentNode->right->nodeWeight = this->leaves[1].nodeWeight;
        currentNode->right->left = this->leaves[1].left;
        currentNode->right->right = this->leaves[1].right;

        this->leaves[0] = this->leaves[--this->leavesCount];
        this->leaves[1] = (*currentNode);

        this->sortNodes();

        this->theTree = currentNode;
    }

}

void HuffmanTreeBuilder::sortNodes(){
    qsort((void *) this->leaves,
       this->leavesCount,
       sizeof(struct huffmanTreeNode),
       (compfn)compareHuffmanTreeNode);
}

void HuffmanTreeBuilder::buildEncodeMap(huffmanTreeNode* tree, string value){
    if(tree->left != 0){
        this->buildEncodeMap(tree->left,value+"0");
        }

        if(tree->right != 0){
            this->buildEncodeMap(tree->right,value+"1");
        }

        if(tree->right == 0){
            if(tree->left == 0){
                (*this->encodeMap).insert(make_pair(tree->nodeValue,value));
            }
        }
}

map<char,string> HuffmanTreeBuilder::getEncodeMap(){
    map<char,string> emap;
    map<char, string>::const_iterator it = this->encodeMap->begin();
    while(it != this->encodeMap->end())
    {
        emap.insert(make_pair(it->first, it->second));
        ++it;
    }
    return emap;
}

HuffmanTreeBuilder::~HuffmanTreeBuilder(){
    delete[] this->leaves;
    delete this->encodeMap;
}
