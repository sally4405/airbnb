import UIKit

class SearchViewController: UIViewController {
    
    private var searchBar: UISearchBar = {
        var searchBar = UISearchBar()
        searchBar.placeholder = "어디로 여행가세요?"
        return searchBar
    }()
    
    private lazy var heroImageView: UIImageView = {
        var imageView = UIImageView()
        guard let image = UIImage(named: "heroImage") else {
            return imageView
        }
        
        imageView.contentMode = .top
        
        let widthScaleRatio = self.view.bounds.width / image.size.width
        
        let scaleFactor = widthScaleRatio
        
        let scaledImageSize = CGSize(
            width: image.size.width * scaleFactor,
            height: image.size.height * scaleFactor
        )
        
        let renderer = UIGraphicsImageRenderer(size: scaledImageSize)
        let scaledImage = renderer.image { _ in
            image.draw(in: CGRect(origin: .zero, size: scaledImageSize))
        }
        
        imageView.image = scaledImage
        
        return imageView
    }()
    
    private var titleLabel: UILabel = {
        var label = UILabel()
        label.text = "슬기로운 \n자연생활"
        label.font = UIFont.systemFont(ofSize: 34, weight: .bold)
        label.numberOfLines = 0
        return label
    }()
    
    private var contentLabel: UILabel = {
        var label = UILabel()
        label.text = "에어비앤비가 엄선한 \n위시리스트를 만나보세요."
        label.font = UIFont.systemFont(ofSize: 17)
        label.numberOfLines = 0
        return label
    }()
    
    private var ideaButton: UIButton = {
        var buttonConfiguration = UIButton.Configuration.filled()
        var container = AttributeContainer()
        container.font = UIFont.systemFont(ofSize: 17)
        buttonConfiguration.attributedTitle = AttributedString("여행 아이디어 얻기", attributes: container)
        
        buttonConfiguration.baseBackgroundColor = .black
        buttonConfiguration.contentInsets = .init(top: 8, leading: 16, bottom: 8, trailing: 16)
        
        var button = UIButton(configuration: buttonConfiguration)
        return button
    }()
    
    private let topSpace: CGFloat = 136
    private let labelSpace: CGFloat = 16
    
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

        setHeroImageView()
        setHeroLabels()
    }

    private func setHeroImageView() {
        self.view.addSubview(heroImageView)
        heroImageView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            heroImageView.topAnchor.constraint(equalTo: self.view.topAnchor),
            heroImageView.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor),
            heroImageView.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor)
        ])
    }
    
    private func setHeroLabels() {
        self.view.addSubview(titleLabel)
        self.view.addSubview(contentLabel)
        self.view.addSubview(ideaButton)

        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        contentLabel.translatesAutoresizingMaskIntoConstraints = false
        ideaButton.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: heroImageView.topAnchor, constant: topSpace),
            titleLabel.leadingAnchor.constraint(equalTo: heroImageView.leadingAnchor, constant: labelSpace),
            
            contentLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: labelSpace),
            contentLabel.leadingAnchor.constraint(equalTo: heroImageView.leadingAnchor, constant: labelSpace),
            
            ideaButton.topAnchor.constraint(equalTo: contentLabel.bottomAnchor, constant: labelSpace),
            ideaButton.leadingAnchor.constraint(equalTo: heroImageView.leadingAnchor, constant: labelSpace)
        ])
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.searchBar.endEditing(true)
    }
}

extension SearchViewController: UISearchBarDelegate, UISearchResultsUpdating {

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

    // 검색내용 기반으로 검색 결과 업데이트
    func updateSearchResults(for searchController: UISearchController) {

    }

}
