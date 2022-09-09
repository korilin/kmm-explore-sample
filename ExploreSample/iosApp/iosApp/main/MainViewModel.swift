//
//  MainViewModel.swift
//  iosApp
//
//  Created by korililn on 2022/9/5.
//  Copyright © 2022 orgName. All rights reserved.
//

import Combine
import SwiftUI
import shared


class MainViewModel: ObservableObject {
    
    @Published var loading: Bool = true
    @State var message: String = ""
    @Published var records: [String] = Array()
    
    let platform = Platform().platform
    
    func initData() {
        
    }
}
