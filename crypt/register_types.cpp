#include "register_types.h"
#include "object_type_db.h"
#include "crypt.h"

void register_crypt_types() {
    ObjectTypeDB::register_type<Crypt>();
}

void unregister_crypt_types() {
       //nothing to do here
}
