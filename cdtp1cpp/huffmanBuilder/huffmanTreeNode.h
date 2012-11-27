#ifndef HUFFMANTREENODE_H
#define HUFFMANTREENODE_H

struct huffmanTreeNode {
    char nodeValue = 0;
    double nodeWeight = 0.d;
    struct huffmanTreeNode * left = 0;
    struct huffmanTreeNode * right = 0;
};

typedef int (*compfn)(const void*, const void*);

static int compareHuffmanTreeNode(struct huffmanTreeNode *elem1, struct huffmanTreeNode *elem2)
{
   if ( elem1->nodeWeight < elem2->nodeWeight)
      return -1;

   else if (elem1->nodeWeight > elem2->nodeWeight)
      return 1;

   else
      return 0;
}

#endif // HUFFMANTREENODE_H
