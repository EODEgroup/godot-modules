#import "interstitial.h"
#import "advertising.h"
#include "core/globals.h"

Advertising* Advertising::m_instance = NULL;

Advertising::Advertising() { 
    ERR_FAIL_COND(instance != NULL);
    m_instance = this;
    m_initialized = false;
    ERR_FAIL_COND(!Globals::get_singleton()->has("admob/api_key"));
    m_admob_id = GLOBAL_DEF("admob/api_key", "");
    Initialize();
};

Advertising::~Advertising()
{ };

Advertising* Advertising::get_singleton() {
    return m_instance;
};

void Advertising::_bind_methods() {
    ObjectTypeDB::bind_method(_MD("ShowInterstitial"),&Advertising::ShowInterstitial);
};

void Advertising::Initialize() {
    if (m_initialized) { 
        return;
    }

    NSLog(@"Advertising: Initialize()");
    m_initialized = true;

    NSString *idStr = [NSString stringWithCString:m_admob_id.utf8().get_data() encoding:NSUTF8StringEncoding];

    interstitial = [AdMobInterstitial alloc];
    [interstitial initialize:idStr];
};

void Advertising::show_interstitial() {
    if (m_initialized) {
        NSLog(@"Advertising: ShowInterstitial()");  
        [interstitial showInterstitial];
    }    
};
