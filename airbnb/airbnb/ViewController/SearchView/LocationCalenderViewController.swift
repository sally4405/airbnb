import UIKit

class LocationCalenderViewController: UIViewController {

    private var searchTableView = SearchTableView()

    override func viewDidLoad() {
        super.viewDidLoad()
        setUI()
        setConstraint()
    }

    private func setUI() {
        self.view.backgroundColor = .systemBlue
        self.view.addSubview(searchTableView)
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
