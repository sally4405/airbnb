import UIKit
import RxSwift
import RxCocoa

class SearchViewController: UIViewController {

    private let searchView = SearchView()
    private let viewModel = SearchViewModel()
    private let disposeBag = DisposeBag()
    private let imageCacheManager = ImageCacheManager.publicCacheManager
    
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

        setView()
        setHeroImage()
    }
    
    private func setView() {
        self.view.addSubview(searchView)
        searchView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            searchView.topAnchor.constraint(equalTo: self.searchView.topAnchor),
            searchView.leadingAnchor.constraint(equalTo: self.searchView.safeAreaLayoutGuide.leadingAnchor),
            searchView.trailingAnchor.constraint(equalTo: self.searchView.safeAreaLayoutGuide.trailingAnchor),
            searchView.bottomAnchor.constraint(equalTo: self.searchView.bottomAnchor)
        ])
    }
    
    private func setHeroImage() {
        print("view-setHeroImage()")
        viewModel.heroImageData
            .observe(on: MainScheduler.instance)
            .map { UIImage(data: $0)?.scalePreservingAspectRatio(targetWidth: self.view.bounds.width) }
            .bind(to: self.searchView.heroImageView.rx.image)
            .disposed(by: disposeBag)
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.searchBar.endEditing(true)
    }
}

extension SearchViewController: UISearchBarDelegate {

    func searchBarShouldBeginEditing(_ searchBar: UISearchBar) -> Bool {
        removeAutoFocusFromSearchBar()
        let pushVC = LocationSearchViewController()
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
