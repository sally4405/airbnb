import UIKit

class LocationCalenderViewController: UIViewController {
    
    private let calendarVC = CalendarViewController()
    private let searchTableView = SearchTableView()

    override func viewDidLoad() {
        super.viewDidLoad()
        setUI()
        setConstraint()
    }
    
    private func setUI() {
        self.view.backgroundColor = .white
        self.view.addSubview(searchTableView)
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

    private func setConstraint() {
        searchTableView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            searchTableView.topAnchor.constraint(equalTo: self.view.topAnchor),
            searchTableView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 1),
            searchTableView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -1),
            searchTableView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor),
        ])
    }

}

