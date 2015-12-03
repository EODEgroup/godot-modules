extends Node

const YOUR_STUDIO_ID = "ca-app-pub-YOUR_CA_PUB_ID"
const INTERSTITIAL_TEST_ID = "ca-app-pub-3940256099942544/1033173712"
var admob = null
var test = true

var percent = 1; #float [0, 1]

func _init() :
	admob = Globals.get_singleton("Advertising")
	if admob :
		if test :
			admob.init(TEST_ID)
		else :
			admob.init(EODE_ID)
	randomize()

func show_interstitial() :
	if admob :
		if randf() <= percent :
			admob.show_interstitial()
