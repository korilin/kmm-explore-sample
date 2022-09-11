//
//  UIColorExt.swift
//  iosApp
//
//  Created by korililn on 2022/9/11.
//  Copyright © 2022 orgName. All rights reserved.
//
import SwiftUI

extension UIColor {
    convenience init(red: Int, green: Int, blue: Int, a: Int = 0xFF) {
        self.init(
            red: CGFloat(red) / 255.0,
            green: CGFloat(green) / 255.0,
            blue: CGFloat(blue) / 255.0,
            alpha: CGFloat(a) / 255.0
        )
    }

    // let's suppose alpha is the first component (ARGB)
    convenience init(argb: Int) {
        self.init(
            red: (argb >> 16) & 0xFF,
            green: (argb >> 8) & 0xFF,
            blue: argb & 0xFF,
            a: (argb >> 24) & 0xFF
        )
    }
}

struct AppColor {
    
    static let background = Color(UIColor(argb: 0xfffafafa))
    static let editorBackground = Color(UIColor(argb: 0xFFFFFFFF))
    static let primaryColor = Color(UIColor(argb: 0xff64b5f6))
    static let primaryTextColor = Color(UIColor(argb: 0xff000000))
    static let secondaryTextColor = Color(UIColor(argb: 0xff424242))
    static let textOnPrimary = Color(UIColor(argb: 0xff000000))
    static let borderColor = Color(UIColor(argb: 0xffb0bec5))

}
