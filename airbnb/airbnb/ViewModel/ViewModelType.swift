//
//  ViewModelType.swift
//  airbnb
//
//  Created by Bibi on 2022/06/07.
//

import Foundation

protocol ViewModelType {
    associatedtype Input
    associatedtype Output
    
    func transform(input: Input) -> Output
}
