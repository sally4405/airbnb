import UIKit

class LocationResultViewController: UITableViewController {
    
    private var resultList: [String] = ["결과1", "결과2"]
    
    private var keyword: String = ""
    convenience init(keyword: String) {
        self.init()
        self.keyword = keyword
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        // self.navigationItem.searchController?.searchBar.becomeFirstResponder()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUI()
        setConstraints()
        tableView.register(LocationTableViewCell.self, forCellReuseIdentifier: LocationTableViewCell.identifier)
        
        self.navigationItem.title = "숙소 찾기"
    }
    
    private func setUI() {
        tableView.separatorStyle = .none
        
        let searchController = UISearchController(searchResultsController: nil)
        
        
        searchController.delegate = self
        searchController.searchBar.delegate = self
        self.navigationItem.searchController = searchController
        self.navigationItem.hidesSearchBarWhenScrolling = false
        self.navigationItem.searchController?.hidesNavigationBarDuringPresentation = false
        self.navigationItem.searchController?.searchBar.placeholder = "어디로 여행가세요?"
        self.navigationItem.searchController?.searchBar.showsCancelButton = false
        self.navigationItem.searchController?.searchBar.text = keyword
        
        let rightBarButtonItem = UIBarButtonItem(title: "지우기", style: .plain, target: self, action: #selector(eraseButtonClicked))
        self.navigationItem.rightBarButtonItem = rightBarButtonItem
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return resultList.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: LocationTableViewCell.identifier, for: indexPath) as? LocationTableViewCell else { return UITableViewCell() }
        cell.selectionStyle = .none
        let location = resultList[indexPath.row]
        cell.setTitle(location)
        return cell
    }

    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        navigationController?.pushViewController(LocationCalenderViewController(), animated: true)
    }
    
    // MARK: 검색결과에서 뒤로가기 누르면 검색Home으로 가도록 수정
    
    @objc func eraseButtonClicked() {
        self.navigationItem.searchController?.searchBar.text = ""
        navigationController?.popViewController(animated: false)
    }

    private func setConstraints() {
         tableView.contentInset = UIEdgeInsets(top: 0, left: 16, bottom: -40, right: -16)
    }
}

extension LocationResultViewController: UISearchBarDelegate, UISearchControllerDelegate {
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        if searchText == "" {
            navigationController?.popViewController(animated: false)
        }
    }
}
