import UIKit

class LocationResultViewController: UIViewController {
    
    private var resultList: [String] = ["결과1", "결과2"]

    private var popularLocationView = PopularLocationView()

    private lazy var searchController: UISearchController = {
        var searchController = UISearchController()
        searchController.delegate = self
        searchController.searchBar.delegate = self

        searchController.hidesNavigationBarDuringPresentation = false
        searchController.searchBar.placeholder = "어디로 여행가세요?"
        searchController.searchBar.showsCancelButton = false

        return searchController
    }()

    private lazy var tableView: UITableView = {
        var table = UITableView()
        table.delegate = self
        table.dataSource = self
        table.register(LocationTableViewCell.self, forCellReuseIdentifier: LocationTableViewCell.identifier)
        table.separatorStyle = .none
        return table
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        setUI()
        setConstraints()
    }
    
    private func setUI() {
        self.view.backgroundColor = .systemBackground
        self.view.addSubview(tableView)
        self.view.addSubview(popularLocationView)
        self.navigationItem.title = "숙소 찾기"

        let rightBarButtonItem = UIBarButtonItem(title: "지우기", style: .plain, target: self, action: #selector(eraseButtonClicked))
        self.navigationItem.rightBarButtonItem = rightBarButtonItem
        self.navigationItem.searchController = self.searchController
        self.navigationItem.hidesSearchBarWhenScrolling = false
    }

    private func setConstraints() {
        popularLocationView.translatesAutoresizingMaskIntoConstraints = false
        tableView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            popularLocationView.topAnchor.constraint(equalTo: self.view.topAnchor),
            popularLocationView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            popularLocationView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            popularLocationView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor),

            tableView.topAnchor.constraint(equalTo: self.view.topAnchor),
            tableView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 16),
            tableView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -16),
            tableView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor, constant: -40)
        ])
    }
}

extension LocationResultViewController: UISearchControllerDelegate, UISearchBarDelegate {

    @objc func eraseButtonClicked() {
        self.navigationItem.searchController?.searchBar.text = ""
        popularLocationView.isHidden = false
    }

    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        guard searchText != "" else {
            popularLocationView.isHidden = false
            return
        }
        popularLocationView.isHidden = true
    }

}

extension LocationResultViewController: UITableViewDelegate, UITableViewDataSource {

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return resultList.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: LocationTableViewCell.identifier, for: indexPath) as? LocationTableViewCell else { return UITableViewCell() }
        cell.selectionStyle = .none
        let location = resultList[indexPath.row]
        cell.setTitle(location)
        return cell
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        navigationController?.pushViewController(LocationCalenderViewController(), animated: true)
    }

}
