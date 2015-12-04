#include "register_types.h"
#include "object_type_db.h"
#include "ios/advertising.h"

void register_advertising_types() {
    Globals::get_singleton()->add_singleton(Globals::Singleton("Advertising", memnew(AdMob_iOS)));
}

void unregister_advertising_types() {
       //nothing to do here
}
