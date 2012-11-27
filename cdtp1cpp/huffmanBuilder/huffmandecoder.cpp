#include "huffmandecoder.h"

HuffmanDecoder::HuffmanDecoder(map<char,string> * encodeMap)
{
    this->decodeMap = new map<string,char>();
    map<char, string>::const_iterator it = encodeMap->begin();
    this->longestWord = this->shortestWord = ((string)it->second).length();
    int slength = 0;
    while(it != encodeMap->end())
    {
        this->decodeMap->insert(make_pair(it->second, it->first));
        slength = ((string)it->second).length();
        if(this->shortestWord > slength){
            this->shortestWord = slength;
        } else if (this->longestWord < slength){
            this->longestWord = slength;
        }
        ++it;
    }
}

string HuffmanDecoder::decodeText(char * data){
    stringstream result;
    string current;
    for(long i=0;i<strlen(data);i++){
        current = "";
        for(int j=0;j<this->longestWord;j++){
             current+=data[i+j];
            if(j>=this->shortestWord-1){

            if(this->decodeMap->find(current) != this->decodeMap->end()){
                result.put((*this->decodeMap)[current]);
                i+=j;
                break;
            }
            }

        }
    }
    return result.str();

}


string HuffmanDecoder::decodeText(string data){
    char * sdata = const_cast<char*> (data.c_str());
    string result = this->decodeText(sdata);
    return result;
}

HuffmanDecoder::~HuffmanDecoder(){
    delete this->decodeMap;
}
