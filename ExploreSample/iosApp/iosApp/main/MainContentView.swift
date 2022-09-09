import SwiftUI

struct MainContentView: View {
    let viewModel = MainViewModel()
    
	var body: some View {
        VStack {
            EditText().environmentObject(viewModel)
            Text("SendButton")
            Text("CardList")
        }
	}
}

struct EditText: View {
    
    @EnvironmentObject var viewModel: MainViewModel
    
    private var hint = "Input Message"
    
    var body: some View {
        TextField(self.hint, text: self.viewModel.$message)
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        MainContentView()
	}
}

