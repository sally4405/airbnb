import UIKit

class LocationTableViewController: UITableViewController {

    var locationList: [[String]] = [["서울", "차로 30분 거리"], ["광주", "차로 4시간 거리"]]
    var imageList: [String] = ["seoul", "gwangju"]
    
    private var searchText = ""

    override func viewDidLoad() {
        super.viewDidLoad()
        setUI()
        setConstraints()
        tableView.register(LocationTableViewCell.self, forCellReuseIdentifier: LocationTableViewCell.identifier)
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return locationList.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: LocationTableViewCell.identifier, for: indexPath) as? LocationTableViewCell else { return UITableViewCell() }
        cell.selectionStyle = .none
        let location = locationList[indexPath.row]
        cell.setTitle(location[0])
        cell.setDistance(location[1])
        cell.setImage(UIImage(named: imageList[indexPath.row]) ?? UIImage())
        return cell
    }

    override func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let view = UIView(frame: .init(x: 0, y: 0, width: tableView.frame.width, height: 32))
        let label = UILabel(frame: .init(x: 0, y: 0, width: view.frame.width, height: view.frame.height))
        label.text = "근처의 인기 여행지"
        label.font = UIFont.systemFont(ofSize: 17, weight: .bold)
        view.addSubview(label)

        return view
    }

    override func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 32
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        navigationController?.pushViewController(LocationCalenderViewController(), animated: true)
    }

    private func setUI() {
        self.navigationItem.title = "숙소 찾기"
        self.navigationItem.backButtonTitle = "뒤로"
        
//        let rightBarButtonItem = UIBarButtonItem(title: "지우기", style: .plain, target: self, action: #selector(eraseButtonClicked))
        let searchController = UISearchController(searchResultsController: nil)
        
        searchController.delegate = self
        searchController.searchBar.delegate = self
        self.navigationItem.searchController = searchController

        tableView.separatorStyle = .none
        
        self.navigationItem.hidesSearchBarWhenScrolling = false // navigationBar 내의 searchBar 항상 보이기
        self.navigationItem.searchController?.hidesNavigationBarDuringPresentation = false
        self.navigationItem.searchController?.searchBar.placeholder = "어디로 여행가세요?"
        self.navigationItem.searchController?.searchBar.showsCancelButton = false
    }
    
    @objc func eraseButtonClicked() {
        self.navigationItem.searchController?.searchBar.text = ""
    }

    private func setConstraints() {
        tableView.contentInset = UIEdgeInsets(top: 0, left: 16, bottom: -40, right: -16)
    }
}

extension LocationTableViewController: UISearchControllerDelegate, UISearchBarDelegate {
    

    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        guard searchText != "" else {
            return
        }
        navigationController?.pushViewController(LocationResultViewController(keyword: searchText), animated: false)
        self.navigationItem.searchController?.searchBar.text = ""
    }
}

