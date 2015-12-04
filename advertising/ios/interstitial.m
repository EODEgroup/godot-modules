#import "interstitial.h"
#import "app_delegate.h"

@implementation Interstitial

@synthesize interstitial = interstitial_;

- (void)dealloc {
    interstitial_.delegate = nil;
    [interstitial_ release];
    [super dealloc];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    NSLog(@"Interstitial iOS: viewDidLoad()");
}

- (void)initialize:(NSString*)admobId {
    admobID = admobId;
    NSLog(@"Interstitial iOS: initialize()");
}

- (void)showInterstitial {
    self.interstitial = [[[GADInterstitial alloc] init] autorelease];
    self.interstitial.delegate = self;  
    self.interstitial.adUnitID = admobID;  

    GADRequest *request = [GADRequest request];

    request.testDevices = [NSArray arrayWithObjects:
        GAD_SIMULATOR_ID, // Simulator
        nil];

    [self.interstitial loadRequest: request];  

    NSLog(@"Interstitial: showInterstitial()");
}

@end
