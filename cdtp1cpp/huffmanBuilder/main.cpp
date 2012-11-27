#include <iostream>
#include <fstream>
#include "entropy.h"
#include "huffmantreebuilder.h"
#include "huffmanencoder.h"
#include "huffmandecoder.h"
using namespace std;

char * loadFile(char * filename){
    FILE* f = fopen(filename, "r");
    fseek(f, 0, SEEK_END);
    size_t size = ftell(f);
    char* data = new char[size];
    rewind(f);
    fread(data, sizeof(char), size, f);
    fclose(f);
    return data;
}

void saveFile(char * filename, string data){
FILE *f = fopen (filename, "w");
if (f != 0) {
    fputs (data.c_str(),f);
    fclose (f);
}
}

int main()
{
    stringstream output;
    char * plain = loadFile("cdt.plain");

    output << "Text to encode:\n\n" << plain << endl;

    Entropy * et = new Entropy(plain); // calculate Entropy and build the tree leaves.

    output << "Entropy: " << et->getEntropy() << endl << endl << "Probabilities: \n\n";

    huffmanTreeNode * leaves = et->getTreeLeaves(); // get the leaves

    // print the probability of each leaf
    for(int i = 0;i<et->getLeavesCount();i++){

        char cur = leaves[i].nodeValue;
        if(cur == '\n'){
            output << "\"" << "\\n" << "\": " << leaves[i].nodeWeight <<  endl;
        } else {
        output << "\"" << cur << "\" : " << leaves[i].nodeWeight <<  endl;
        }

    }

    //setup the TreeBuilder with the leaves from entropy
    //builds the tree and encoding map
    HuffmanTreeBuilder *  htb = new HuffmanTreeBuilder(et);

    output << "\nTotalProbabilityCheck: " << et->getTotalProbability() << endl << endl;


    //get the map
    map<char,string> emap = htb->getEncodeMap();

    //print the map
    map<char, string>::const_iterator it = emap.begin();
    output << "Map to encode: " << endl << endl;
    while(it != emap.end())
    {
        char cur = it->first;
        if(cur == '\n'){
        output << "\"\\n\":" << it->second <<  endl;
        } else {
            output << "\"" << cur << "\" :" << it->second << endl;
        }
        ++it;
    }

    //setup new encoder with the map
    HuffmanEncoder *  he = new HuffmanEncoder(&emap);

    //encode the text
    string enc = he->encodeText(plain);
    saveFile("cdt.enc",enc);
    output << "\nEncoded text:\n" << endl <<  enc << endl;

    //average bits
    double avg = (double)enc.length() / (double)strlen(plain);
    output << "\nAverage bits: " << avg << endl;

    //setup the decoder with the map
    HuffmanDecoder * hd = new HuffmanDecoder(&emap);

    output << endl << "Decoded text:\n" << endl << hd->decodeText(enc) << endl;
    string result = output.str();
    saveFile("result.txt",result);
    cout << result;
    return 0;
}

