import UIKit

class SearchView: UIView {
    
    lazy var heroImageView: UIImageView = {
        var imageView = UIImageView()
        imageView.contentMode = .top
        return imageView
    }()
    
    private lazy var titleLabel: UILabel = {
        var label = UILabel()
        label.text = "슬기로운 \n자연생활"
        label.font = UIFont.systemFont(ofSize: 34, weight: .bold)
        label.numberOfLines = 0
        return label
    }()
    
    private lazy var contentLabel: UILabel = {
        var label = UILabel()
        label.text = "에어비앤비가 엄선한 \n위시리스트를 만나보세요."
        label.font = UIFont.systemFont(ofSize: 17)
        label.numberOfLines = 0
        return label
    }()
    
    private lazy var ideaButton: UIButton = {
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
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setUp()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setUp()
    }
    
    func setHeroImage(_ image: UIImage) {
        let targetWidth: CGFloat = self.bounds.width
        let scaledImage = image.scalePreservingAspectRatio(targetWidth: targetWidth)
        heroImageView.image = scaledImage
        DispatchQueue.main.async {
            self.reloadInputViews()
        }
    }
    
    private func setUp() {
        setHeroImageView()
        setHeroLabels()
    }
    
    private func setHeroImageView() {
        self.addSubview(heroImageView)
        heroImageView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            heroImageView.topAnchor.constraint(equalTo: self.topAnchor),
            heroImageView.leadingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.leadingAnchor),
            heroImageView.trailingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.trailingAnchor),
            heroImageView.heightAnchor.constraint(equalToConstant: 488)
        ])
    }
    
    private func setHeroLabels() {
        self.addSubview(titleLabel)
        self.addSubview(contentLabel)
        self.addSubview(ideaButton)

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
}
