//
//  TimeUtil.swift
//  iosApp
//
//  Created by korililn on 2022/9/12.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation


func formatTime(timestamp: Int64) -> String {
    let interval:TimeInterval=TimeInterval.init(timestamp / 1000)
    let date = Date(timeIntervalSince1970: interval)
    
    let dateformatter = DateFormatter()
    dateformatter.dateFormat = "yyyy-MM-dd HH:mm:ss"

    return dateformatter.string(from: date)
}
