import UIKit

class SearchViewController: UIViewController {

    private var searchBar: UISearchBar = {
        var searchBar = UISearchBar()
        searchBar.placeholder = "어디로 여행가세요?"
        return searchBar
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.searchBar.delegate = self
        self.searchBar.resignFirstResponder()
        
        setUp()
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.tabBarController?.tabBar.isHidden = false
    }
    
    private func setUp() {
        self.view.backgroundColor = .systemBackground

        self.navigationController?.navigationBar.backgroundColor = .systemGray6
        self.navigationItem.titleView = searchBar

        setSearchView()
    }
    
    private func setSearchView() {
        
        let searchView = SearchView()
        let searchViewModel = SearchViewModel()
        searchViewModel.configure(searchView)
        self.view.addSubview(searchView)
        
        searchView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            searchView.topAnchor.constraint(equalTo: self.view.topAnchor),
            searchView.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor),
            searchView.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor),
            searchView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor)
        ])
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.searchBar.endEditing(true)
    }
}

extension SearchViewController: UISearchBarDelegate {

    func searchBarShouldBeginEditing(_ searchBar: UISearchBar) -> Bool {
        removeAutoFocusFromSearchBar()
        let pushVC = LocationResultViewController()
        self.navigationController?.pushViewController(pushVC, animated: true)
        
        let backBarButtonItem = UIBarButtonItem(title: "뒤로", style: .plain, target: self, action: nil)
        self.navigationItem.backBarButtonItem = backBarButtonItem
        
        return true
    }
    
    @objc func removeAutoFocusFromSearchBar() {
        self.searchBar.endEditing(true)
        DispatchQueue.main.async {
            self.searchBar.resignFirstResponder()
        }
    }
}
