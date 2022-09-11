import struct Kingfisher.KFImage
import SwiftUI

struct MainContentView: View {
    
    @EnvironmentObject var viewModel: MainViewModel
    
    init() {
        UITableView.appearance().backgroundColor = UIColor(AppColor.background)
    }
    
	var body: some View {
        VStack {
            Title()
            Divider().padding(EdgeInsets(top: 10, leading: 0, bottom: 10, trailing: 0))
            MessageEditor(bindingMessage: self.$viewModel.message)
                .padding(EdgeInsets(top: 0, leading: 0, bottom: 10, trailing: 0))
            PostButton() {
                viewModel.postMessage()
            }
            Divider().padding(EdgeInsets(top: 10, leading: 0, bottom: 0, trailing: 0))
            RecordList().environmentObject(viewModel)
            Spacer()
        }.padding(25).background(AppColor.background)
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
            .frame(height: 80)
            .autocapitalization(.words)
            .disableAutocorrection(true)
            .padding(5)
            .overlay(
                RoundedRectangle(cornerRadius: 5)
                    .stroke(AppColor.borderColor, lineWidth: 1)
            )
    }
}

struct RecordList: View {
    @EnvironmentObject var viewModel: MainViewModel
    
    var body: some View {
        List {
            ForEach(self.viewModel.records, id: \.self.time) { item in
                VStack(alignment: HorizontalAlignment.leading) {
                    let time = formatTime(timestamp: item.time)
                    Text(String(time))
                        .font(Font.custom("date", size: 10))
                        .foregroundColor(AppColor.secondaryTextColor)
                    
                    Text(item.msg)
                        .font(Font.custom("msg", size: 10))
                        .foregroundColor(AppColor.primaryTextColor)
                        .padding(EdgeInsets(top: 2, leading: 0, bottom: 10, trailing: 0))
                    
                    HStack {
                        Spacer()
                        KFImage(URL(string: item.img))
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(maxWidth: .infinity)
                        
                        Spacer()
                    }
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(15)
                .background(Color.white)
                .listRowInsets(EdgeInsets())
            
            }.onDelete { indexSet in
                indexSet.forEach { index in
                    viewModel.removeRecord(index: index)

                }
            }
        }
    }
}

struct MainContentPreview: PreviewProvider {
    
    static var previews: some View {
        MainContentView().environmentObject(MainViewModel())
    }
}
