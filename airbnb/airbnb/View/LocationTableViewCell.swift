import UIKit

class LocationTableViewCell: UITableViewCell {

    static let identifier: String = "LocationTableViewCell"

    private lazy var spotStackView: UIStackView = {
        var stackView = UIStackView(arrangedSubviews: [self.spotImageView, self.locationView])
        stackView.axis = .horizontal
        stackView.distribution = .fill
        stackView.spacing = 16
        return stackView
    }()

    private lazy var spotImageView: UIImageView = {
        var imageView = UIImageView()
        imageView.image = UIImage(systemName: "mappin")
        imageView.backgroundColor = .systemGray6
        imageView.tintColor = .systemGray3
        imageView.layer.borderColor = CGColor(gray: 0.7, alpha: 1)
        imageView.layer.borderWidth = 1
        imageView.clipsToBounds = true
        imageView.layer.cornerRadius = 10
        return imageView
    }()

    private lazy var locationView: UIView = {
        var view = UIView()
        view.addSubview(self.locationStackView)
        return view
    }()

    private lazy var locationStackView: UIStackView = {
        var stackView = UIStackView(arrangedSubviews: [self.titleLabel, self.distanceLabel])
        stackView.axis = .vertical
        stackView.spacing = 4
        return stackView
    }()

    private var titleLabel: UILabel = {
        var label = UILabel()
        label.font = UIFont.systemFont(ofSize: 17)
        return label
    }()

    private var distanceLabel: UILabel = {
        var label = UILabel()
        label.font = UIFont.systemFont(ofSize: 17)
        label.textColor = .systemGray3
        return label
    }()

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setUI()
        setConstraints()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setUI()
        setConstraints()
    }

    private func setUI() {
        self.addSubview(spotStackView)
    }

    private func setConstraints() {
        configureSpotStackViewContraint()
    }

    private func configureSpotStackViewContraint() {
        spotStackView.translatesAutoresizingMaskIntoConstraints = false
        spotImageView.translatesAutoresizingMaskIntoConstraints = false
        locationStackView.translatesAutoresizingMaskIntoConstraints = false
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        distanceLabel.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            spotStackView.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor, constant: 16),
            spotStackView.leadingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.leadingAnchor),
            spotStackView.trailingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.trailingAnchor),
            spotStackView.bottomAnchor.constraint(equalTo: self.safeAreaLayoutGuide.bottomAnchor),

            spotImageView.topAnchor.constraint(equalTo: spotStackView.topAnchor),
            spotImageView.leadingAnchor.constraint(equalTo: spotStackView.leadingAnchor),
            spotImageView.bottomAnchor.constraint(equalTo: spotStackView.bottomAnchor),
            spotImageView.heightAnchor.constraint(equalToConstant: 64),
            spotImageView.widthAnchor.constraint(equalTo: spotStackView.heightAnchor),

            locationView.topAnchor.constraint(equalTo: spotStackView.topAnchor),
            locationView.bottomAnchor.constraint(equalTo: spotStackView.bottomAnchor),

            locationStackView.centerYAnchor.constraint(equalTo: locationView.centerYAnchor)
        ])

    }

    func setImage(_ image: UIImage) {
        spotImageView.image = image
    }

    func setTitle(_ text: String) {
        titleLabel.text = text
    }

    func setDistance(_ text: String) {
        distanceLabel.text = text
    }
    
}
