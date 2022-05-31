//
//  LocationCalenderViewController.swift
//  airbnb
//
//  Created by Bibi on 2022/05/26.
//

import UIKit

class LocationCalenderViewController: UIViewController {
    
    private let calendarVC = CalendarViewController()
    private let searchTableView = SearchTableView()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        setUI()
    }
    
    private func setUI() {
        setCalendarView()
    }
    
    private func setCalendarView() {
        self.view.addSubview(calendarVC.view)
        self.addChild(calendarVC)
        calendarVC.didMove(toParent: self)
        
        calendarVC.view.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            calendarVC.view.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor),
            calendarVC.view.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor),
            calendarVC.view.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor),
            calendarVC.view.heightAnchor.constraint(equalToConstant: self.view.bounds.width)
        ])
    }
}

