import SwiftUI
import shared

@main
class iOSApp: App {
    
    let viewModel: MainViewModel
    
    required init() {
        AppDependenciesHelper().doInit()
        viewModel = MainViewModel()
        viewModel.initData()
    }
    
	var body: some Scene {
		WindowGroup {
            MainContentView().environmentObject(viewModel)
		}
	}
}
