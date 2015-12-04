def can_build(platform) :
    return platform=="android" or platform=="iphone"

def configure(env) :
    if env['platform'] == "iphone" :
        env.Append(LINKFLAGS=['-ObjC', '-framework', 'MessageUI', '-framework', 'AdSupport'])
        env.Append(LIBPATH=['#modules/admob/ios/sdk'])
        env.Append(LIBS=['GoogleMobileAds'])
    if env['platform'] == 'android' :
        env.android_module_file("android/Advertising.java")
        env.android_module_manifest("android/AndroidManifestChunk.xml")
