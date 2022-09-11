import SwiftUI

struct MainContentView: View {
    
    @EnvironmentObject var viewModel: MainViewModel
    
	var body: some View {
        VStack {
            Title()
            Divider().padding(EdgeInsets(top: 10, leading: 0, bottom: 10, trailing: 0))
            MessageEditor(bindingMessage: self.$viewModel.message)
                .padding(EdgeInsets(top: 0, leading: 0, bottom: 10, trailing: 0))
            PostButton() {
                viewModel.postMessage()
            }
            Divider().padding(EdgeInsets(top: 10, leading: 0, bottom: 10, trailing: 0))
            Spacer()
        }.background(Color("0xfffafafa")).padding(25)
    }
}

struct Title: View {
    var body: some View {
        Text("iOS KMM Explore Sample")
            .fontWeight(Font.Weight.bold)
            .font(Font.custom("title", size: 20))
    }
}

struct PostButton: View {
    
    var action: () -> Void
    
    init(action: @escaping () -> Void) {
        self.action = action
    }
    
    var body: some View {
        Button(action: self.action, label: {
            Text("Post Message")
                .frame(maxWidth: .infinity)
                .padding(EdgeInsets(top: 5, leading: 10, bottom: 5, trailing: 10))
                .background(AppColor.primaryColor)
                .foregroundColor(AppColor.textOnPrimary)
                .cornerRadius(5)
        })
    }
}

struct MessageEditor: View {
    
    var bindingMessage: Binding<String>
    
    init(bindingMessage: Binding<String>) {
        self.bindingMessage = bindingMessage
    }
    
    var body: some View {
        TextEditor(text: self.bindingMessage)
            .frame(maxWidth:.infinity)
            .frame(height: 100)
            .autocapitalization(.words)
            .disableAutocorrection(true)
            .padding(5)
            .overlay(
                RoundedRectangle(cornerRadius: 5)
                    .stroke(AppColor.borderColor, lineWidth: 1)
            )
    }
}
