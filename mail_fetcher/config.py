def can_build(platform) :
    return platform=="android"

def configure(env) :
    if env['platform'] == 'android' :
        env.android_module_file("android/UserEmailFetcher.java")
        env.android_module_manifest("android/AndroidManifestChunk.xml")
