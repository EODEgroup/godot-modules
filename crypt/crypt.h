#pragma once

#include "reference.h"
#include "scene/main/node.h"
#include "core/globals.h"

class Crypt : public Reference {
    OBJ_TYPE(Crypt, Object);
public:
    String sha1(const String& string);
protected:
    static void _bind_methods();
};

