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
    @Published var message: String = Platform().devEnvLocalHost
    @Published var records: [ImageMessageRecord] = Array()
    
    private let repository = MessageRepository()
    
    @MainActor
    private func runCatch(block: @escaping () async throws -> Void) {
        Task {
            do {
                let _ = try await block()
            } catch let error {
                print(error)
            }
        }
    }
    
    @MainActor
    func initData() {
        runCatch {
            print(Thread.current)
            try await self.repository.doInitData()
            let records = try await self.repository.queryAllMessages()
            self.records = records
            self.loading = false
        }
    }
    
    @MainActor
    func postMessage() {
        runCatch {
            let record = try await self.repository.postMessage(message: self.message)
            self.message = ""
            self.records.append(record)
        }
    }
}
