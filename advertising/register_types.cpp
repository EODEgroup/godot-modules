#include "register_types.h"
#include "object_type_db.h"
#include "ios/advertising.h"

void register_crypt_types() {
    Globals::get_singleton()->add_singleton(Globals::Singleton("Advertising", memnew(Advertising));
}

void unregister_crypt_types() {
       //nothing to do here
}
