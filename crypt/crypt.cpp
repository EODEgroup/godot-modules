#include "crypt.h"
#include <openssl/sha.h>

String Crypt::sha1(const String& str) {
    unsigned char digest[SHA_DIGEST_LENGTH];
    SHA1((unsigned char *)(str.utf8().get_data()), str.utf8().length(), (unsigned char*)&digest);    
             
    char mdString[SHA_DIGEST_LENGTH*2+1];
                 
    for(int i = 0; i < SHA_DIGEST_LENGTH; i++)
        sprintf(&mdString[i*2], "%02x", (unsigned int)digest[i]);

    return String(mdString); 
}

void Crypt::_bind_methods() {
	ObjectTypeDB::bind_method(_MD("sha1", "text"), &Crypt::sha1);
}
