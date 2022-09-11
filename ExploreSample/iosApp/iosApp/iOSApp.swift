import SwiftUI

@main
class iOSApp: App {
    
    let viewModel: MainViewModel
    
    required init() {
        viewModel = MainViewModel()
    }
    
	var body: some Scene {
		WindowGroup {
            MainContentView().environmentObject(viewModel)
		}
	}
}
