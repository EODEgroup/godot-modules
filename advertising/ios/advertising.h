#include "core/object.h"

#ifdef __OBJC__
@class Interstitial;
typedef Interstitial * InterstitialPtr;
#else
typedef void * InterstitialPtr;
#endif

class Advertising : public Object {
    OBJ_TYPE(Advertising, Object);
public:
    static Advertising* get_singleton();
    void show_interstitial();
protected:
    Advertising();
    ~Advertising();

    String m_admob_id;
    bool m_initialized;
    InterstitialPtr m_interstitial;
    static Advertising* m_instance;

    void Initialize();
    static void _bind_methods();
};
