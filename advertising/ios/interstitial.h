@import GoogleMobileAds

@interface Interstitial: UIViewController <GADInterstitialDelegate> {
    GADInterstitial *interstitial_;
    NSString* admobID;
}

@property(nonatomic, retain) GADInterstitial *interstitial;
- (void)initialize:(NSString*)admobId;
- (void)showInterstitial;
@end
